package Lv2.타겟_넘버;

import java.util.Arrays;
import java.util.stream.IntStream;

public class JY {
    public static void main(String[] ags) {
        Solution solution = new Solution();

        int[] case1 = new int[] {1, 1, 1, 1, 1};
        int t = 3;

        int[] case2 = new int[] {4, 1, 2, 1};
        int t2 = 4;


        solution.solution(case2,t2);
    }
   static class Solution {
       // 1. DfsCalc
       // 필드 : count , number ,target
       // 함수 : dfs = 들어온숫자의 길이만큼 돌고 타겟과 같으면 카운트 +1
       //      더한것과 뺀것의 분기를 탄다.
        public int solution(int[] numbers, int target) {
            DfsCalc calc = new DfsCalc(numbers,target);
            calc.dfs(0,0);
            return calc.getCount();
        }

        public class DfsCalc
        {
            private int count = 0;
            private final int[] numbers;
            private final int target;
            public DfsCalc(final int[] numbers,final int target){
                this.numbers = numbers;
                this.target = target;
            }
            public void dfs(final int numCoodi,final int startNumber){
                if(numCoodi == numbers.length) {
                    if(startNumber == target) count++;
                }
                else{
                    //만약더한다면
                    dfs(numCoodi+1,startNumber+numbers[numCoodi]);
                    //만약뺀다면
                    dfs(numCoodi+1,startNumber-numbers[numCoodi]);
                }
            }
            public int getCount(){return count;}
        }
    }
}
