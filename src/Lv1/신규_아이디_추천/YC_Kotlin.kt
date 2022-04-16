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
            stepOne()
            stepTwo()
            stepThree()
            stepFour()
            stepFive()
            stepSix()
            stepSeven()
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