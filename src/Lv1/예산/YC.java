package Lv1.예산;

import java.util.Arrays;

public class YC {

    // start 2022/06/07 14:47
    // end   2022/06/07 14:54

    // 최대한 많은 부서

    public int solution(int[] d, int budget) {
        int[] sorted = Arrays.stream(d)
            .sorted()
            .toArray();
        int result = 0;
        for (int anInt : sorted) {
            if (budget >= anInt) {
                result++;
                budget -= anInt;
            } else
                break;
        }
        return result;
    }

}
