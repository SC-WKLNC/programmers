package Lv2.카카오프렌즈_컬러링북;

import java.util.*;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class JY {

    public static void  main (String[] args)
    {
        int[] s = new int[] {1, 2, 3, 2, 1};
        int N = 4;
        int[][] test1 = new int[][] {
            {1, 1, 1, 0}
            , {1, 1, 1, 0}
            , {0, 0, 0, 1}
            , {0, 0, 0, 1}
            , {0, 0, 0, 1}
            , {0, 0, 0, 1}};
        int[][] test2 = new int[][] {
                {1,1,1,0}
                ,{1,2,2,0}
                ,{1,0,0,1}
                ,{0,0,0,1}
                ,{0,0,0,3}
                ,{0,0,0,3}};

        int[][] test3 = new int [][]
                {{0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0}, //0
                 {0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0}, //1
                 {0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0}, //2
                 {0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0}, //3
                 {0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0}, //4
                 {0, 1, 1, 1, 1, 2, 1, 1, 1, 1, 2, 1, 1, 1, 1, 0}, //5
                 {0, 1, 1, 1, 2, 1, 2, 1, 1, 2, 1, 2, 1, 1, 1, 0}, //6
                 {0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0}, //7
                 {0, 1, 3, 3, 3, 1, 1, 1, 1, 1, 1, 3, 3, 3, 1, 0}, //8
                 {0, 1, 1, 1, 1, 1, 2, 1, 1, 2, 1, 1, 1, 1, 1, 0}, //9
                 {0, 0, 1, 1, 1, 1, 1, 2, 2, 1, 1, 1, 1, 1, 0, 0}, //10
                 {0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0}, //11
                 {0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0}};//12

        Solution sol = new Solution();
        sol.solution(13,16,test3);


    }
    static class Solution {
        // M = X축 , N = Y 축 M * N 2차원 배열 picture
        // 0은 칠하지 않은 영억
        // 1 1 1 0
        // 1 2 2 0
        // 1 0 0 1
        // 0 0 0 1
        // 0 0 0 3
        // 0 0 0 3
        // 앞은 몇개의 영역 4
        // 가장큰영역 몇칸 5 = 4 ,5

        public int[] solution(int m, int n, int[][] picture) {

            ColorBoard colorBoard = new ColorBoard(picture).addBoard();
            Searcher searcher = new Searcher(colorBoard.getMaxX(),colorBoard.getMaxY(),colorBoard.getPixelBoard());
            searcher.search();

            int[] answer = new int[2];
            answer[0] = searcher.getMaxSector();
            answer[1] = searcher.getMaxSectorCount();
            return answer;
        }

        public class ColorBoard
        {
            private int MaxX ;
            private int MaxY ;
            private final int[][] arrayPicture;


            List<Pixel> pixelBoard = new ArrayList<>();
            public ColorBoard(final int[][] arrayPicture)
            {
                this.arrayPicture = arrayPicture;
                this.MaxX = arrayPicture.length;
                this.MaxY = arrayPicture[0].length;
            }
            public ColorBoard addBoard()
            {
                IntStream.range(0,MaxX)
                        .forEach(row-> IntStream.range(0,MaxY)
                                .forEach(column -> pixelBoard.add(new Pixel(row,column, arrayPicture[row][column]))
                                ));
                return this;
            }
            public List<Pixel> getPixelBoard() { return pixelBoard;}
            public int getMaxX() {return  MaxX;}
            public int getMaxY(){return MaxY;}
        }

        public class Pixel
        {
            private int x ;
            private int y ;
            private int value ;
            private int sector;
            public Pixel(final int x,final int y,final int value)
            {
                this.x = x;
                this.y = y;
                this.value = value;
                this.sector = 0;
            }
            public int getofDefaultSector(final int defaultSector){
                if(sector!=0) {return sector;}
                this.sector = defaultSector;
                return defaultSector;
            }
            public int getSector() {return sector;}
        }

        public class Searcher {
            private int MaxX ;
            private int MaxY ;
            private List<Pixel> Pixels;
            private int tempSector ;

            public Searcher(final int maxX ,final int maxY ,final List<Pixel> pixels)
            {
                this.MaxX = maxX;
                this.MaxY = maxY;
                this.Pixels = pixels;
                this.tempSector = 1;
            }
            public void search()
            {
                IntStream.range(0,Pixels.size()).forEach(fe-> {calcSector(fe);});
            }

            private void calcSector(int num)
            {
                Pixel currentPixel = Pixels.get(num);
                if(currentPixel.value ==0) return;
                if(upperSector(currentPixel,num)) return;
                if(rightSector(currentPixel,num)|leftSector(currentPixel,num)) return;
                if(bottomSector(currentPixel,num)) return;

                tempSector++;
                currentPixel.sector = tempSector;
                tempSector++;
            }
            private void chageValue(Pixel currentPixel, Pixel targetPixel)
            {
                if (currentPixel.sector != targetPixel.sector) {
                    if (currentPixel.sector != 0 && targetPixel.sector != 0)
                        Pixels.stream()
                                .filter(fl -> fl.sector == Math.max(currentPixel.sector, targetPixel.sector))
                                .forEach(fe -> fe.sector = Math.min(currentPixel.sector, targetPixel.sector));
                    else
                        Pixels.stream().filter(fl -> fl.sector == Math.max(currentPixel.sector, targetPixel.sector))
                                .forEach(fe -> fe.sector = Math.max(currentPixel.sector, targetPixel.sector));
                }
            }
            private boolean upperSector(Pixel _currentPixel , int num)
            {
                if(!(num - MaxY> -1)) return false;
                Pixel currentPixel = _currentPixel;
                Pixel targetPixel = Pixels.get(num - MaxY);
                if(!isUpAndDownSector(currentPixel , targetPixel))return false;

                chageValue(currentPixel,targetPixel);

                currentPixel.sector = targetPixel.getofDefaultSector(tempSector);
                tempSector++;
                return true;
            }
            private boolean rightSector(Pixel _currentPixel , int num)
            {   if(num +1> Pixels.size() -1) return false;
                Pixel currentPixel =_currentPixel;
                Pixel targetPixel = Pixels.get(num +1);
                if (!isLeftAndRightSector(currentPixel,targetPixel)) return false;
                chageValue(currentPixel,targetPixel);

                currentPixel.sector = targetPixel.getofDefaultSector(tempSector);

                return true;
            }
            private boolean leftSector(Pixel _currentPixel , int num)
            {   if(num -1< 0) return false;
                Pixel currentPixel =_currentPixel;
                Pixel targetPixel = Pixels.get(num -1);
                if (!isLeftAndRightSector(currentPixel,targetPixel)) return false;
                chageValue(currentPixel,targetPixel);
                currentPixel.sector = targetPixel.getofDefaultSector(tempSector);
                tempSector++;
                return true;
            }
            private boolean bottomSector(Pixel _currentPixel , int num)
            {
                if(num +MaxY> Pixels.size() -1) return false;
                Pixel currentPixel = _currentPixel;
                Pixel targetPixel = Pixels.get(num + MaxY);
                if(!isUpAndDownSector(currentPixel,targetPixel)) return false;

                chageValue(currentPixel,targetPixel);

                tempSector++;
                currentPixel.sector = targetPixel.getofDefaultSector(tempSector);
                tempSector++;
                return true;
            }
            private boolean isUpAndDownSector(Pixel currentPixel , Pixel targetPixel)
            {
                if(targetPixel.value == 0) return false;
                if(currentPixel.value == targetPixel.value) return true;
                return false;
            }
            private boolean isLeftAndRightSector(Pixel currentPixel , Pixel targetPixel)
            {
                if(targetPixel.value ==0) return false;
                if(currentPixel.x == targetPixel.x
                        && currentPixel.value == targetPixel.value) return true;
                return false;
            }

            public List<Pixel> getPixels()
            {
                return Pixels;
            }
            public int getMaxSector() {
                return (int)Pixels.stream()
                                .mapToInt(Pixel::getSector)
                                .filter(fl-> fl!=0)
                                .distinct()
                                .count();}
            public int getMaxSectorCount()
            {
                var sector = Pixels.stream()
                        .mapToInt(Pixel::getSector)
                        .filter(fl->fl != 0)
                        .boxed()
                        .collect(Collectors.toList());
               return  Pixels.stream()
                       .mapToInt(mp -> Collections
                               .frequency(sector,mp.getSector()))
                       .max()
                       .getAsInt();
            }
            public int getMaxSectorCount2()
            {
                var stream = Pixels.stream();
                var sector = stream.mapToInt(Pixel::getSector)
                        .filter(fl->fl != 0)
                        .boxed()
                        .collect(Collectors.toList());
                return  stream.mapToInt(mp -> Collections
                                .frequency(sector,mp.getSector()))
                        .max()
                        .getAsInt();
            }
        }
    }
}
