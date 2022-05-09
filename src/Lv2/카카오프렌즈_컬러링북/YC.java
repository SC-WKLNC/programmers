package Lv2.카카오프렌즈_컬러링북;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class YC {

    // 객체

    // 1. 사진
    //      필드 : 사진 크기, 픽셀 정보
    //      함수 : 색과 상하좌우 연결 정보를 바탕으로, 영역 추출
    // 2. 영역
    //      필드 : 색상, 픽셀 노드 리스트
    // 3. 픽셀
    //      필드 : 색상, 좌표
    // 4. 색상
    //      필드 : 색 번호
    // 5. 좌표
    //      필드 : x, y
    //      함수 : 서로 맞다아 있는지 확인하는 함수
    // 6. 영역 추출기
    //      필드 : 사진, 픽셀 그래프[링크드리스트], 영역 Map
    //      함수 : 픽셀 연결 그래프 생성, 그래프 탐색 및 제거 (영역 갯수 및 넓이 구하기)
    // 7. 픽셀 그래프[링크드 리스트]
    // 8. 픽셀 노드

    public int[] solution(final int m, final int n, final int[][] picture) {
        final Picture pictureObj = new Picture(n, m, picture);
        final AreaExtractor extractor = new AreaExtractor(pictureObj);
        return extractor
            .execute()
            .getResult();
    }

    public static class Picture { // 사진
        private final int weight; // 가로 길이
        private final int height; // 세로 길이
        private final Pixel[][] pixels; // 픽셀 객체 이차원 배열
        public Picture(final int weight, final int height, final int[][] pixels) {
            this.weight = weight;
            this.height = height;
            this.pixels = new Pixel[height][weight];
            convertToPixel(pixels); // 픽셀 이차원 배열로 변환
        }

        private void convertToPixel(int[][] pixels) {
            for(int y = 0; y < height; y++) {
                for(int x = 0; x < weight; x++) {
                    final int value = pixels[y][x];
                    if(value > 0) { // 색상 값이 0 이상일 경우
                        final Pixel pixel = new Pixel( // 픽셀 생성
                            new Color(value), // 색상 생성
                            new Coordinate(x, y) // 좌표 생성
                        );
                        this.pixels[y][x] = pixel; // 픽셀 보드에 할당
                    }
                }
            }
        }

        public int getWeight() {
            return weight;
        }

        public int getHeight() {
            return height;
        }

        public Optional<Pixel> getPixel(final Coordinate coordinate) { // 좌표에 해당하는 픽셀 조회
            return Optional.ofNullable(
                pixels[coordinate.getY()][coordinate.getX()]
            );
        }

        public Optional<Pixel> getPixel(final int x, final int y) { // 좌표에 해당하는 픽셀 조회
            return Optional.ofNullable(pixels[y][x]);
        }

    }

    public static class Pixel { // 픽셀 객체
        private final Color color; // 색상
        private final Coordinate coordinate; // 좌표
        private boolean isVisited = false; // 그래프 방문 여부
        private Pixel top;
        private Pixel bottom;
        private Pixel left;
        private Pixel right;
        public Pixel(final Color color, final Coordinate coordinate) {
            this.color = color;
            this.coordinate = coordinate;
        }
        public Color getColor() {
            return color;
        }
        public Coordinate getCoordinate() {
            return coordinate;
        }
        public Optional<Pixel> getTop() {
            return Optional.ofNullable(top);
        }
        public Optional<Pixel> getBottom() {
            return Optional.ofNullable(bottom);
        }
        public Optional<Pixel> getLeft() {
            return Optional.ofNullable(left);
        }
        public Optional<Pixel> getRight() {
            return Optional.ofNullable(right);
        }
        public void setTop(final Pixel pixel) {
            top = pixel;
        }
        public void setBottom(final Pixel pixel) {
            bottom = pixel;
        }
        public void setLeft(final Pixel pixel) {
            left = pixel;
        }
        public void setRight(final Pixel pixel) {
            right = pixel;
        }
        public boolean isVisited() {
            return isVisited;
        }
        public void visit() {
            isVisited = true;
        }
    }

    public static class Color { // 색상 객체
        private final int value;
        public Color(final int value) {
            this.value = value;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Color color = (Color) o;
            return value == color.value;
        }

    }

    public static class Coordinate { // 좌표 객체
        private final int x;
        private final int y;
        public Coordinate(final int x, final int y) {
            this.x = x;
            this.y = y;
        }
        public int getX() {
            return x;
        }
        public int getY() {
            return y;
        }
        public boolean hasTop() {
            return y > 0;
        }
        public boolean hasBottom(final int height) {
            return y < height-1;
        }
        public boolean hasLeft() {
            return x > 0;
        }
        public boolean hasRight(final int weight) {
            return x < weight-1;
        }
        public Coordinate getTop() {
            return new Coordinate(x, y-1);
        }
        public Coordinate getBottom() {
            return new Coordinate(x, y+1);
        }
        public Coordinate getLeft() {
            return new Coordinate(x-1, y);
        }
        public Coordinate getRight() {
            return new Coordinate(x+1, y);
        }
    }

    public static class AreaExtractor { // 영역 추출 객체
        private final Picture picture;
        private final List<Area> areas = new ArrayList<>(); // 추출된 영역 리스트

        public AreaExtractor(final Picture picture) {
            this.picture = picture;
        }

        public AreaExtractor execute() {
            addEdges(); // 색상이 같은 인접 픽셀 탐색
            extractArea(); // 영역 생성 및 탐색 된 인접 픽셀 방문 처리
            return this;
        }

        public int[] getResult() {
            final Optional<Area> area = areas.stream().max(Comparator.comparingInt(Area::getSize));
            if(area.isEmpty()) {
                throw new IllegalStateException("Error empty list");
            }
            return new int[] {areas.size(), area.get().getSize()};
        }

        private void addEdges() {
            for(int y = 0; y < picture.getHeight(); y++) {
                for(int x = 0; x < picture.getWeight(); x++) {
                    picture
                        .getPixel(x, y)
                        .ifPresent(this::addEdge);
                }
            }
        }

        private void addEdge(final Pixel pixel) {
            if(pixel.getCoordinate().hasTop()) {
                picture
                    .getPixel(pixel.getCoordinate().getTop())
                    .ifPresent(other -> {
                        if(pixel.getColor().equals(other.getColor()))
                            pixel.setTop(other);
                    });
            }
            if(pixel.getCoordinate().hasBottom(picture.getHeight())) {
                picture
                    .getPixel(pixel.getCoordinate().getBottom())
                    .ifPresent(other -> {
                        if(pixel.getColor().equals(other.getColor()))
                            pixel.setBottom(other);
                    });
            }
            if(pixel.getCoordinate().hasLeft()) {
                picture
                    .getPixel(pixel.getCoordinate().getLeft())
                    .ifPresent(other -> {
                        if(pixel.getColor().equals(other.getColor()))
                            pixel.setLeft(other);
                    });
            }
            if(pixel.getCoordinate().hasRight(picture.getWeight())) {
                picture
                    .getPixel(pixel.getCoordinate().getRight())
                    .ifPresent(other -> {
                        if(pixel.getColor().equals(other.getColor()))
                            pixel.setRight(other);
                    });
            }
        }

        private void extractArea() {
            for(int y = 0; y < picture.getHeight(); y++) {
                for(int x = 0; x < picture.getWeight(); x++) {
                    picture
                        .getPixel(x, y)
                        .ifPresent(pixel -> {
                            if(!pixel.isVisited()) { // 방문하지 않은 픽셀인 경우
                                final Area area = new Area(pixel.getColor());
                                areas.add(area);
                                dfs(area, pixel); // DFS 탐색
                            }
                        });
                }
            }
        }

        private void dfs(final Area area, final Pixel pixel) {
            if(!pixel.isVisited()) {
                pixel.visit();
                area.addNode(pixel);
                pixel.getTop().ifPresent(top -> dfs(area, top));
                pixel.getBottom().ifPresent(bottom -> dfs(area, bottom));
                pixel.getLeft().ifPresent(left -> dfs(area, left));
                pixel.getRight().ifPresent(right -> dfs(area, right));
            }
        }

    }

    public static class Area { // 영역 객체
        private final List<Pixel> nodes = new ArrayList<>();
        private final Color color;
        public Area(final Color color) {
            this.color = color;
        }
        public void addNode(final Pixel node) {
            nodes.add(node);
        }
        public int getSize() {
            return nodes.size();
        }
    }

}
