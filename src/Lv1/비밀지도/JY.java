package Lv1.비밀지도;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane;
import java.text.MessageFormat;
import java.util.Arrays;
import java.util.stream.IntStream;

public class JY {
    public static void  main (String[] args)
    {
        int[] s = new int[] {1, 2, 3, 2, 1};
        int N = 4;
        Solution sol = new Solution();


        int case1_q = 5;
        int[] case1_w = {9, 20, 28, 18, 11};
        int[] case1_e = {30, 1, 21, 17, 28};

        int case2_q = 6;
        int[] case2_w = {46, 33, 33, 22, 31, 50};
        int[] case2_e = {27, 56, 19, 14, 14, 10};

        sol.solution(case2_q,case2_w,case2_e);


        //인트스트림으로 효율적으로 어캐짜야할지모르겟음.
        //IntStream.range(0,n).forEach(x-> IntStream.range(0,n).forEach(y-> {
        //   String resultString="";
        //   if(map1[x].charAt(y)=='0'&& map1[x].charAt(y) == map2[x].charAt(y))
        //      resultString+= " ";
        //  else resultString+="#";
        //    resultString.replaceAll("  "," ").trim();
        //    resultArray[x] = resultString;
        //        }));
    }

    public static class Solution {
        // 1. Map
        // 필드 : 프론트맵 , 비하인맵
        // 함수 : 컨버트맵 = 맵을 2진수로 바꿔 배열저장
        //       마지맵 = 2진수로 바뀐 맵을 겹침
        public String[] solution(int n, int[] arr1, int[] arr2) {
            Map map = new Map(arr1,arr2,n);
            return map.mergeMap();
        }

        public class Map{
            private final String[] frontMap;
            private final String[] behindMap;
            private final int length;
            public Map(final int[] arrayA,final int[] arrayB,int n){
                length = n;
                frontMap = convertMap(arrayA);
                behindMap = convertMap(arrayB);
            }
            public String[] convertMap(final int[] array){
                String patten = MessageFormat.format("%0{0}d",length);
                return Arrays.stream(array)
                        .mapToObj(Integer::toBinaryString)
                        .map(mp->String.format(patten,Long.parseLong(mp)))
                        .toArray(String[]::new);
            }

            public String[] mergeMap() {
                String[] resultArray = new String[length];
                for (int x = 0; x < length; x++) {
                    String resultString = "";
                    for (int y = 0; y < length; y++) {
                        if (frontMap[x].charAt(y) == '0' && frontMap[x].charAt(y) == behindMap[x].charAt(y))
                            resultString += " ";
                        else resultString += "#";
                        //resultString = resultString.replaceAll("\\s{2,}", " ");
                        resultArray[x] = resultString;
                    }
                }
                return resultArray;
            }
        }
    }
}
