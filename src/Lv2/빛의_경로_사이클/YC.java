package Lv2.빛의_경로_사이클;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Queue;

public class YC {

    // start 2022/05/28 15:40

    // 핵심 포인트
    // 동일한 홉에서 동일한 방향으로 라우팅 될 경우 한개의 사이클을 찾은 것이다. (곧 동일한 사이클. 더 찾을 필요 없음.)
    // 각 홉에서 라우팅 되지 않은 방향으로 새로운 사이클을 찾아 나서면 된다.

    // 한개의 사이클이 완료 될 때 마다 길이 추가 (모든 사이클 길이는 같음)

    // 객체 모델링

    // : 그래프
    // -> 필드 : 연결 노드, 노드 연결을 위한 이차원 배열
    // -> 함수 : 모든 노드 연결, 사이클 탐색(큐에다 넣고 모든 방향 엣지가 되면 없애기)

    // : 노드
    // -> 필드 : 유일 식별 값, 각 방향 엣지, 좌표
    // -> 같은 방향으로 엣지 되었는지 여부 확인, 남은 엣지 반환, 다음 방향 타겟 노드 반환

    // : 엣지
    // -> 필드 : 대상 노드, 엣지 여부
    // -> 엣지 체크 및 노드 반환

    public static void main(String[] args) {
        String[] test = new String[]{"SL","LR"};
        int[] solution = new YC().solution(test);
        Arrays.stream(solution)
                .forEach(System.out::println);
        System.out.println("----");
        String[] test2 = new String[]{"S"};
        int[] solution2 = new YC().solution(test2);
        Arrays.stream(solution2)
            .forEach(System.out::println);
        System.out.println("----");
        String[] test3 = new String[]{"R", "R"};
        int[] solution3 = new YC().solution(test3);
        Arrays.stream(solution3)
            .forEach(System.out::println);
        System.out.println("----");
        String[] test4 = new String[]{"S", "S"};
        int[] solution4 = new YC().solution(test4);
        Arrays.stream(solution4)
            .forEach(System.out::println);
        System.out.println("----");
        String[] test5 = new String[]{"SSS"};
        int[] solution5 = new YC().solution(test5);
        Arrays.stream(solution5)
            .forEach(System.out::println);
    }

    public int[] solution(String[] grid) {
        final Graph graph = new Graph(grid);
        graph.navigatingCycle();
        return graph.getResult();
    }

    public static class Graph {

        private final List<Node> nodes = new ArrayList<>();
        private final Node[][] board;
        private final int maxX;
        private final int maxY;
        private final List<Integer> cycle = new ArrayList<>();
        private int cycleLength = 0;

        private final Queue<Node> queue = new LinkedList<>();

        public Graph(final String[] grid) {
            this.maxX = grid[0].length()-1;
            this.maxY = grid.length-1;
            this.board = bindGrid(grid);
            edgeAll();
        }

        private Node[][] bindGrid(final String[] grid) { // 이차원 배열에 담기
            final Node[][] board = new Node[maxY+1][maxX+1];
            int id = 1;
            for(int y = maxY; y >= 0; y--) {
                for(int x = 0; x <= maxX; x++) {
                    final char course = grid[maxY-y].charAt(x);
                    final Node node = new Node(id++, String.valueOf(course), new Coordinate(x, y));
                    nodes.add(node);
                    board[y][x] = node;
                }
            }
            return board;
        }

        private void edgeAll() {
            nodes.forEach(this::edgeNode);
        }

        // 이차원 배열 순회 하면서 각 방향(상하좌우)에 있는 노드들을 찾아서 엣지 시키기
        // -> 엣지 다 시키면 큐에 넣기
        private void edgeNode(final Node node) {
            Arrays.stream(Direction.values())
                .forEach(direction -> {
                    final Coordinate targetCoordinate = node.getCoordinate().getTargetCoordinate(direction, maxX, maxY);
                    final Node targetNode = getNode(targetCoordinate);
                    node.edge(direction, targetNode);
                });
            queue.add(node);
        }

        private Node getNode(final Coordinate coordinate) { // 좌표에 해당하는 노드 get
            return board[coordinate.getY()][coordinate.getX()];
        }

        // 큐에 있는거 하나 빼서 남은 방향으로 사이클 탐색.
        // -> 남은 방향이 없으면 큐에서 제거
        // -> 한 사이클 돌고나서 남은 방향이 있으면 다시 사이클 탐색
        // -> 큐의 모든 원소가 제거 될때 까지 반복

        public void navigatingCycle() {
            while (!queue.isEmpty()) {
                final Node node = queue.peek();
                if(node.isAllEdged())
                    queue.poll();
                else {
                    node.getRemainEdge().ifPresent(this::navigate);
                }

            }
        }

        private void navigate(final Edge edge) { // 진입해오는 방향을 구하기
            Edge nextEdge = edge;
            while(nextEdge.isNotEdge()) {
                nextEdge.edge();
                cycleLength++;
                nextEdge = nextEdge.getNextEdge();
            }
            cycle.add(cycleLength);
            cycleLength = 0;
        }

        public int[] getResult() {
            return cycle.stream()
                .mapToInt(Integer::intValue)
                .sorted()
                .toArray();
        }

    }

    public static class Node {
        private final int id;
        private final Course course;
        private final Coordinate coordinate;
        private final EnumMap<Direction, Edge> edges = new EnumMap<>(Direction.class);

        public Node(final int id, final String courseVal, final Coordinate coordinate) {
            this.id = id;
            this.course = Course.byValue(courseVal);
            this.coordinate = coordinate;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node node = (Node) o;
            return id == node.id;
        }

        public Coordinate getCoordinate() {
            return coordinate;
        }

        public boolean isAlreadyEdged(final Direction entry) { // 이 함수가 true 라면 한 사이클 돈 것임.
            final Optional<Edge> edge = Optional.ofNullable(getEdgeByEntry(entry));
            return edge
                .map(Edge::isEdged)
                .orElse(false);
        }

        public boolean isAllEdged() {
            return Arrays.stream(Direction.values())
                .allMatch(this::isAlreadyEdged);
        }

        public Optional<Edge> getRemainEdge() {
            return edges.values().stream()
                .filter(Edge::isNotEdge)
                .findFirst();
        }

        public Edge getEdgeByEntry(final Direction entry) {
            return edges.get(course.getDirectionByEntry(entry));
        }

        public void edge(final Direction direction, final Node targetNode) {
            Optional.ofNullable(edges.get(direction))
                .ifPresentOrElse(ac -> {}, () -> edges.put(direction, new Edge(targetNode, direction)));
            targetNode.edged(direction.getOpposite(), this);
        }

        public void edged(final Direction direction, final Node targetNode) {
            Optional.ofNullable(edges.get(direction))
                .ifPresentOrElse(ac -> {}, () -> edges.put(direction, new Edge(targetNode, direction)));
        }

    }

    public static class Edge {

        private final Node targetNode;
        private final Direction direction;

        private boolean isEdged = false;
        public Edge(final Node targetNode, final Direction entry) {
            this.targetNode = targetNode;
            this.direction = entry;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Edge edge = (Edge) o;
            return Objects.equals(targetNode, edge.targetNode) && direction == edge.direction;
        }

        public Edge getNextEdge() {
            return this.targetNode.getEdgeByEntry(this.direction.getOpposite());
        }

        public boolean isEdged() {
            return isEdged;
        }

        public boolean isNotEdge() {
            return !isEdged();
        }

        public void edge() {
            isEdged = true;
        }

    }

    enum Course {

        STRAIGHT("S"), LEFT("L"), RIGHT("R");

        private final String value;

        Course(final String value) {
            this.value = value;
        }

        public static Course byValue(final String value) {
            return Arrays.stream(values())
                .filter(course -> course.isEqualsByValue(value))
                .findFirst()
                .orElseThrow();
        }

        public boolean isEqualsByValue(final String value) {
            return this.value.equals(value);
        }

        public Direction getDirectionByEntry(final Direction entry) {
            switch (this) {
                case STRAIGHT: {
                    if(entry == Direction.BOTTOM)
                        return Direction.TOP;
                    else if(entry == Direction.TOP)
                        return Direction.BOTTOM;
                    else if(entry == Direction.LEFT)
                        return Direction.RIGHT;
                    else
                        return Direction.LEFT;
                }
                case LEFT: {
                    if(entry == Direction.BOTTOM)
                        return Direction.LEFT;
                    else if(entry == Direction.TOP)
                        return Direction.RIGHT;
                    else if(entry == Direction.LEFT)
                        return Direction.TOP;
                    else
                        return Direction.BOTTOM;
                }
                case RIGHT: {
                    if(entry == Direction.BOTTOM)
                        return Direction.RIGHT;
                    else if(entry == Direction.TOP)
                        return Direction.LEFT;
                    else if(entry == Direction.LEFT)
                        return Direction.BOTTOM;
                    else
                        return Direction.TOP;
                }
                default: throw new IllegalArgumentException();
            }
        }

    }

    enum Direction {
        TOP, BOTTOM, LEFT, RIGHT;

        public Direction getOpposite() {
            switch (this) {
                case TOP:       return BOTTOM;
                case BOTTOM:    return TOP;
                case LEFT:      return RIGHT;
                case RIGHT:     return LEFT;
                default:        throw new IllegalArgumentException();
            }
        }

    }

    public static class Coordinate { // 좌표 객체

        private final int x;
        private final int y;

        public Coordinate(final int x, final int y) {
            this.x = x;
            this.y = y;
        }

        public Coordinate getTargetCoordinate(final Direction direction, final int maxX, final int maxY) {
            switch (direction) {
                case TOP: {
                    if(y == maxY)
                        return new Coordinate(x, 0);
                    else
                        return new Coordinate(x, y+1);
                }
                case BOTTOM: {
                    if(y == 0)
                        return new Coordinate(x, maxY);
                    else
                        return new Coordinate(x, y-1);
                }
                case LEFT: {
                    if(x == 0)
                        return new Coordinate(maxX, y);
                    else
                        return new Coordinate(x-1, y);
                }
                case RIGHT: {
                    if(x == maxX)
                        return new Coordinate(0, y);
                    else
                        return new Coordinate(x+1, y);
                }
                default: throw new IllegalArgumentException();
            }
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }
    }

}