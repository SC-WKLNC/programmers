package sort;

import java.lang.reflect.Array;
import java.util.Arrays;

public class K번째수_이진호 {
    public int[] solution(int[] array, int[][] commands) {

        int length = commands.length;
        int[] resultArr = new int[length];

        for (int i = 0; i < length; i++) {
            int startIndex = commands[i][0];
            int endIndex = commands[i][1];
            int searchIndex = commands[i][2] -1 ; // 몇번째 위치 인지를 배열 에서 찾기 위해 -1

            int[] cutArr = getCutArray(array,startIndex,endIndex);
            Arrays.sort(cutArr);

            resultArr[i] = cutArr[searchIndex];
        }
        return resultArr;
    }

    private int[] getCutArray(int[] array ,int startIndex, int endIndex){
        int[] resultArray = new int[endIndex-(startIndex-1)];

        for (int resultIndex = 0, i = startIndex-1; i <endIndex ;resultIndex++, i++) {
            resultArray[resultIndex]= array[i];
        }

        return resultArray;
    }
}
