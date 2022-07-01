package Lv1.비밀지도;

import java.util.Arrays;
import java.util.stream.IntStream;

/**
 *
 * 같은 라인의 정수값을 or 비트 연산을 수행한다.
 * 정수값을 2진수로 변환한다.
 * 2 진수의 맨앞자리가 0 이라면 채워준다.
 * 0 과 1의 모양을 변경한다.
 * 반환한다.
 */
public class JH {
    public static void main(String[] args) {
//        int n = 5;
//        int[] arr1 = new int[]{9, 20, 28, 18, 11};
//        int[] arr2 = new int[]{30, 1, 21, 17, 28};

        int n = 6;
        int[] arr1 = new int[]{46, 33, 33, 22, 31, 50};
        int[] arr2 = new int[]{27, 56, 19, 14, 14, 10};
        Solution solution = new Solution();
        solution.solution(n, arr1, arr2);
    }

    static class Solution {
        private int n = 0;

        public String[] solution(int n, int[] arr1, int[] arr2) {
            this.n = n;
            return  IntStream.range(0, n)
                    .mapToObj(index -> arr1[index] | arr2[index])
                    .map(Integer::toBinaryString)
                    .map(this::completionBinary)
                    .map(this::bitToMap)
                    .toArray(String[]::new);
        }

        private String completionBinary(String binary) {
            String result = "";
            if (binary.length() < n) {
                for (int i = 0; i < n - binary.length(); i++) result += "0";
            }
            return result + binary;
        }

        private String bitToMap(String binary) {
            String result = "";
            for (int i = 0; i < binary.length(); i++) {
                result += binary.charAt(i) == '0' ? " " : "#";
            }
            return result;
        }
    }
}