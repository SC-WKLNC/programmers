package Lv2.카카오프렌즈_컬러링북;

/**
 *
 * [
 * [1, 1, 1, 0],
 * [1, 2, 2, 0],
 * [1, 0, 0, 1],
 * [0, 0, 0, 1],
 * [0, 0, 0, 3],
 * [0, 0, 0, 3]]
 */

import java.util.*;

/**
 *
 * 해당 배열을 가공하여 색깔 별로 배열을 생성한다.
 * 1색 배열, 2색배열, 3색배열
 * 만들어진 배열을 순회하여 이어지지 않은 요소를 찾아 새로운 배열로 저장한다.
 * 그렇게 이어진 색별 배열들을 가지고 계산을 한다.
 */
public class JH {
    public static void main(String[] args) {
        int m = 6;
        int n = 4;
        int[][] picture = new int[][]{{1, 1, 1, 0}, {1, 2, 2, 0}, {1, 0, 0, 1}, {0, 0, 0, 1}, {0, 0, 0, 3}, {0, 0, 0, 3}};
        JH solution = new JH();
        solution.solution(m,n,picture);
    }

    public int[] solution(int m, int n, int[][] picture) {
        int numberOfArea = 0;
        int maxSizeOfOneArea = 0;


        DrawingProcessor drawingProcessor = new DrawingProcessor(picture,m,n);
        Integer[] drawingColors = drawingProcessor.getDrawingColors();


        for (int drawingColor : drawingColors) {
            int[][] colorMap = drawingProcessor.getColorMap(drawingColor);
            ColorMapInfo colorMapInfo = new ColorMapInfo(colorMap,m,n);
            numberOfArea += colorMapInfo.getAreaCount();
            int maxAreaSize = colorMapInfo.getMaxAreaSize();
            if(maxSizeOfOneArea< maxAreaSize)  maxSizeOfOneArea = maxAreaSize;

        }

        int[] answer = new int[2];
        answer[0] = numberOfArea;
        answer[1] = maxSizeOfOneArea;

        return answer;
    }


    class ColorMapInfo{
        private int[][] drawingMap;
        boolean[][] checkMap;    //방문 위치 체크

        private int drawingM;
        private int drawingN;
        private int areaCount = 0; //영역 수
        private int areaSize = 0; //한 영역의 크기
        private int maxAreaSize = 0;//가장큰 영역의 크기

        private int[] xPos = {-1,1,0,0};
        private int[] yPos = {0,0,-1,1};
        public ColorMapInfo(int[][] drawingMap, int m, int n) {
            this.drawingMap = drawingMap;
            this.checkMap = new boolean[m][n];
            this.drawingM = m;
            this.drawingN = n;
            init();

        }

        public int getAreaCount() {
            return areaCount;
        }

        public int getMaxAreaSize() {
            return maxAreaSize;
        }
//        private void print(int[][] picture){
//            for(int[] p : picture){
//                for(int item : p){
//                    System.out.print(item);
//                }
//                System.out.println();
//            }
//            System.out.println();
//            System.out.println();
//        }
        public void init(){


            for(int i =0;i<drawingM;i++) {
                for (int j = 0; j < drawingN; j++) {
                    if(drawingMap[i][j] != 0 && ! checkMap[i][j]){
                        //새로운 영역 이다.
                        areaCount++;
                        dfs(i,j);
                    }

                    if(areaSize > maxAreaSize) maxAreaSize = areaSize;
                    areaSize = 0;
                }
            }

        }

        private void dfs(int x, int y){
            if(checkMap[x][y]) return;
            checkMap[x][y] = true;

            areaSize++;
            for(int i =0 ; i< 4; i++){
                int tempX = x+xPos[i];
                int tempY = y+yPos[i];
                //영역을 벗어났다면 다음 반복
                if(tempX<0 || tempX>=drawingM || tempY<0 || tempY>=drawingN){
                    continue;
                }

                if(drawingMap[tempX][tempY] != 0 && (!checkMap[tempX][tempY])) dfs(tempX, tempY);

            }

        }
    }


    class DrawingProcessor{

        private Integer[] drawingColors;
        private Map<Integer,int[][]> ColorMap = new HashMap<>();
        public DrawingProcessor(int[][] picture , int x, int y){
            initDrawingCount(picture);
            initColorMapDivision(picture,x,y);

        }
        //2차원 배열에서 색의 가짓수를 얻는다.
        private void initDrawingCount(int[][] picture){
            Set<Integer> set = new HashSet<>();
            for(int[] p : picture){
                for(int item : p){
                    set.add(item);
                }
            }
            set.remove(0);
            this.drawingColors = set.toArray(new Integer[set.size()]);

        }
        //색상 별로 2차원 배열을 생성한다.
        private void initColorMapDivision(int[][] picture, int x, int y){
            for(int color : drawingColors){
                int[][] tmpPicture = new int[x][y];

                for(int i = 0; i < x; i++){
                    for(int j = 0 ; j< y ; j++){
                        if(picture[i][j] == color) {
                            tmpPicture[i][j] = color;
                        }
                    }
                }

                ColorMap.put(color,tmpPicture);
            }


        }

        public Integer[] getDrawingColors() {
            return drawingColors;
        }

        public int[][] getColorMap(int color) {
            return ColorMap.get(color);
        }


    }
}
