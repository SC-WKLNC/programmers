package Lv1.신규_아이디_추천;

import java.util.List;

public class YC {

    public String solution(String new_id) {
        if(!new IdChecker(new_id).validate())
            return new IdRecommender(new_id).getRecommendedId();
        return new_id;
    }

    public static class IdChecker { // 아이디 유효성 검사기

        private final String id;                     // 아이디
        private final int length;                    // 아이디 길이
        private final char[] chArr;                  // 아이디 char 배열
        private final List<String> allowSpecialChar; // 허용된 특수 문자 리스트
        private static final int MIN_SIZE = 3;
        private static final int MAX_SIZE = 15;

        public IdChecker(final String newId) {
            this.id = newId;
            this.length = id.length();
            this.chArr = id.toCharArray();
            this.allowSpecialChar = List.of("-", "_", ".");
        }

        private Boolean isEmpty() { // 아이디가 비었는지 확인
            return id == null
                || id.isEmpty();
        }

        // 아이디 유효성 검증 (길이 확인, 허용된 문자 확인, 마침표 확인)
        public Boolean validate() {
            return checkSize() && checkStr() && checkPeriod();
        }

        private Boolean checkSize() {
            return (MIN_SIZE <= length && length <= MAX_SIZE);
        }

        private Boolean checkStr() {
            return isSmallLetter() || isDigit() || isSpecialChar();
        }

        private Boolean isSmallLetter() {
            for(char ch : chArr) {
                if(!('a' <= ch && ch <= 'z'))
                    return false;
            }
            return true;
        }

        private Boolean isDigit() {
            for(char ch : chArr) {
                if(!Character.isDigit(ch))
                    return false;
            }
            return true;
        }

        private Boolean isSpecialChar() {
            for(char ch : chArr) {
                if(!allowSpecialChar.contains(String.valueOf(ch)))
                    return false;
            }
            return true;
        }

        private Boolean checkPeriod() {
            if(isEmpty())
                return false;
            if(chArr[0] == '.' || chArr[length-1] == '.')
                return false;
            for(int i=0; i<length-2; i++) {
                if(chArr[i] == '.' && chArr[i+1] == '.')
                    return false;
            }
            return true;
        }

    }

    public static class IdRecommender { // 추천 아이디 생성기

        private final String id;
        private String result;

        public IdRecommender(final String id) {
            this.id = id;
            result = id;
        }

        public String getRecommendedId() {
            stepOne();      // 1단계 소문자 치환
            stepTwo();      // 2단계 비허용 문자 제거
            stepThree();    // 3단계 연속 마침표 1개로 치환
            stepFour();     // 4단계 처음, 끝 마침표 제거
            stepFive();     // 5단계 문자열이 비었을 경우 'a' 로 치환
            stepSix();      // 6단계 15개의 문자를 제외한 나머지 문자 제거 // 제거 후 처음, 끝 마침표 제거
            stepSeven();    // 7단계 문자열 길이가 2자 이하인 경우 길이가 3이 될때까지 문자 추가
            return result;
        }

        private void stepOne() { result = id.toLowerCase(); }
        private void stepTwo() { result = result.replaceAll("[^0-9a-z._-]", ""); }
        private void stepThree() { result = result.replaceAll("\\.{2,}", "."); }
        private void stepFour() { result = result.replaceAll("^\\.|\\.$", ""); }
        private void stepFive() { if(result.isEmpty()) result = "a"; }
        private void stepSix() {
            if(result.length() > IdChecker.MAX_SIZE)
                result = result.substring(0, IdChecker.MAX_SIZE);
            if(result.charAt(result.length()-1) == '.')
                result = result.substring(0, result.length()-1);
        }
        private void stepSeven() {
            if(result.length() <= 2) {
                do {
                    result += String.valueOf(result.charAt(result.length() - 1));
                } while (result.length() != 3);
            }
        }

    }

}
