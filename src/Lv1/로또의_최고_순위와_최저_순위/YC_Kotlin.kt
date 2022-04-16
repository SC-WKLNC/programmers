package Lv1.로또의_최고_순위와_최저_순위

class YC_Kotlin {

    fun solution(lottos: IntArray, win_nums: IntArray): IntArray {
        val expectedLottoRank = ExpectedLottoRank(lottos, win_nums)
        return expectedLottoRank.calcAndGetAnswer()
    }

    class ExpectedLottoRank(private val lottos: IntArray, private val winNums: IntArray) {

        companion object {
            private const val BASE_RANK_NUM = 7 // 랭크를 구하기 위한 기본 값
        }

        private val currentRank: Int = getCurrentRank()          // 모르는 번호를 제외한 당첨 로또 순위
        private val unknownNumCount: Int = countUnknownNumbers() // 모르는 번호의 갯수
        private val result = IntArray(2)                    // 결과

        private fun getCurrentRank(): Int { // 알고 있는 번호로 로또 순위 구하기
            val tempRank = BASE_RANK_NUM - lottos.count(this::isMatch)
            val realRank = tempRank.coerceAtMost(BASE_RANK_NUM - 1) // 가장 작은 갯수 구하기
            result[0] = realRank
            result[1] = realRank
            return realRank
        }

        private fun isMatch(num: Int): Boolean = winNums.any { it == num }

        private fun countUnknownNumbers(): Int = lottos.count { it == 0 } // 모르는 번호의 갯수 구하기

        private fun calcRanking() { // 모르는 번호가 1개 이상 있을 경우 최고 순위를 해당 갯수 만큼 낮춘다
            if (unknownNumCount > 0) {
                val rank = currentRank - unknownNumCount
                result[0] = rank.coerceAtMost(1)
            }
        }

        fun calcAndGetAnswer(): IntArray {
            calcRanking()
            return result
        }

    }

}