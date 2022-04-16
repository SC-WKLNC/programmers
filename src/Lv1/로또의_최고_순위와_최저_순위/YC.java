package Lv1.로또의_최고_순위와_최저_순위;

import java.util.Arrays;

public class YC {

    public int[] solution(final int[] lottos, final int[] win_nums) {
        final ExpectedLottoRank expectedLottoRank = new ExpectedLottoRank(lottos, win_nums);
        return expectedLottoRank.calcAndGetAnswer();
    }

    public static class ExpectedLottoRank {

        private static final int BASE_RANK_NUM = 7; // 랭크를 구하기 위한 기본 값
        private final int[] lottos;                 // 구매한 로또 번호
        private final int[] winNums;                // 당첨 로또 번호
        private final int currentRank;              // 모르는 번호를 제외한 당첨 로또 순위
        private final int unknownNumCount;          // 모르는 번호의 갯수
        private final int[] result = new int[2];    // 결과

        public ExpectedLottoRank(final int[] lottos, final int[] winNums) {
            this.lottos = lottos;
            this.winNums = winNums;
            this.currentRank = getCurrentRank();
            this.unknownNumCount = countUnknownNumbers();
        }

        private int getCurrentRank() { // 알고 있는 번호로 로또 순위 구하기
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

        private int countUnknownNumbers() { // 모르는 번호의 갯수 구하기
            return (int) Arrays.stream(lottos).filter(it -> it == 0).count();
        }

        private void calcRanking() { // 모르는 번호가 1개 이상 있을 경우 최고 순위를 해당 갯수 만큼 낮춘다
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
