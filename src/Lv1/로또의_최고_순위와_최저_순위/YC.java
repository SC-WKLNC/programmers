package Lv1.로또의_최고_순위와_최저_순위;

import java.util.Arrays;

public class YC {

    public int[] solution(final int[] lottos, final int[] win_nums) {
        final ExpectedLottoRank expectedLottoRank = new ExpectedLottoRank(lottos, win_nums);
        return expectedLottoRank.calcAndGetAnswer();
    }

    public static class ExpectedLottoRank {

        private static final int BASE_RANK_NUM = 7;
        private final int[] lottos;
        private final int[] winNums;
        private final int currentRank;
        private final int unknownNumCount;
        private final int[] result = new int[2];

        public ExpectedLottoRank(final int[] lottos, final int[] winNums) {
            this.lottos = lottos;
            this.winNums = winNums;
            this.currentRank = getCurrentRank();
            this.unknownNumCount = countUnknownNumbers();
        }

        private int getCurrentRank() {
            final int tempRank = BASE_RANK_NUM - (int) (Arrays.stream(lottos).filter(this::isMatch).count());
            final int realRank = Math.min(tempRank, BASE_RANK_NUM - 1);
            result[0] = realRank;
            result[1] = realRank;
            return realRank;
        }

        private Boolean isMatch(final int num) {
            return Arrays.stream(winNums)
                .anyMatch(it -> it == num);
        }

        private int countUnknownNumbers() {
            return (int) Arrays.stream(lottos).filter(it -> it == 0).count();
        }

        private void calcRanking() {
            if(unknownNumCount > 0) {
                int rank = currentRank - unknownNumCount;
                result[0] = Math.max(rank, 1);
            }
        }

        public int[] calcAndGetAnswer() {
            calcRanking();
            return result;
        }

    }

}
