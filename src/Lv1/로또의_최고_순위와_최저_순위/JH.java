package Lv1.로또의_최고_순위와_최저_순위;

import java.util.Arrays;

/**
 * lottos 안의 0 값은 어떤 값이든 될수있다.
 * 이때의 최저 당첨과 최고 당첨을 구한다.
 *
 */
public class JH {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] lottos = new int[]{44, 1, 0, 0, 31, 25};
        int[] win_nums = new int[]{31, 10, 45, 1, 6, 19};
        int[] solution1 = solution.solution(lottos, win_nums);
        for (int i : solution1) {
            System.out.println("i = " + i);
        }

    }
    static class Solution {
        public int[] solution(int[] lottos, int[] win_nums) {

            int winCount = 0;
            //당첨 횟수를 찾는다.
            for(int lotto : lottos){
                for(int win_num : win_nums){
                    if(lotto == win_num) {
                        winCount ++;
                        break;
                    }
                }
            }
            //0의 개수를 찾는다.
            int luckyCount = 0;
            for(int lotto : lottos){
                if(lotto == 0) luckyCount ++;
            }

            int minRanking = ranking(winCount);
            int maxRanking = ranking(winCount + luckyCount);

            return new int[]{maxRanking,minRanking};
        }

        private int  ranking(int number){
            if(number == 0) number = 1;
            return (7 - number);
        }
    }
}
