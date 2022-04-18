package Lv1.신규_아이디_추천

class YC_Kotlin {

    fun solution(new_id: String): String =
        if (!IdChecker(new_id).validate())
            IdRecommender(new_id).recommendedId()
        else
            new_id

    class IdChecker(private val id: String) { // 아이디 유효성 검사기

        companion object {
            const val MIN_SIZE = 3
            const val MAX_SIZE = 15
        }

        private val length: Int = id.length                                 // 아이디 길이
        private val chArr: CharArray = id.toCharArray()                     // 아이디 char 배열
        private val allowSpecialChar: List<String> = listOf("-", "_", ".")  // 허용된 특수 문자 리스트

        // 아이디 유효성 검증 (길이 확인, 허용된 문자 확인, 마침표 확인)
        fun validate(): Boolean = checkSize() and checkStr() and checkPeriod()

        private fun checkSize(): Boolean = length in MIN_SIZE..MAX_SIZE
        private fun checkStr(): Boolean = isSmallLetter() or isDigit() or isSpecialChar()
        private fun checkPeriod(): Boolean {
            if (id.isEmpty()) return false
            if (chArr[0] == '.' || chArr[length - 1] == '.') return false
            for (i in 0 until length-2) {
                if (chArr[i] == '.' && chArr[i + 1] == '.') return false
            }
            return true
        }

        private fun isSmallLetter(): Boolean    = chArr.all { it in 'a'..'z' }
        private fun isDigit(): Boolean          = chArr.all (Character::isDigit)
        private fun isSpecialChar(): Boolean    = chArr.all { allowSpecialChar.contains(it.toString()) }

    }

    // 추천 아이디 생성기
    class IdRecommender(private val id: String) {

        private var result: String = id

        fun recommendedId(): String {
            stepOne()       // 1단계 소문자 치환
            stepTwo()       // 2단계 비허용 문자 제거
            stepThree()     // 3단계 연속 마침표 1개로 치환
            stepFour()      // 4단계 처음, 끝 마침표 제거
            stepFive()      // 5단계 문자열이 비었을 경우 'a' 로 치환
            stepSix()       // 6단계 15개의 문자를 제외한 나머지 문자 제거 // 제거 후 처음, 끝 마침표 제거
            stepSeven()     // 7단계 문자열 길이가 2자 이하인 경우 길이가 3이 될때까지 문자 추가
            return result
        }

        private fun stepOne() { result = result.lowercase() }
        private fun stepTwo() { result = result.replace("[^0-9a-z._-]".toRegex(), "") }
        private fun stepThree() { result = result.replace("\\.{2,}".toRegex(), ".") }
        private fun stepFour() { result = result.replace("^\\.|\\.$".toRegex(), "") }
        private fun stepFive() { if(result.isEmpty()) result = "a" }
        private fun stepSix() {
            if (result.length > IdChecker.MAX_SIZE)
                result = result.substring(0, IdChecker.MAX_SIZE)
            if (result[result.length - 1] == '.')
                result = result.substring(0, result.length - 1)
        }
        private fun stepSeven() {
            if (result.length <= 2) {
                do {
                    result += result[result.length-1]
                } while (result.length < 3)
            }
        }

    }

}