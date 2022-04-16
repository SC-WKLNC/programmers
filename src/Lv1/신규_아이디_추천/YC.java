package Lv1.신규_아이디_추천;

import java.util.List;

public class YC {

    public String solution(String new_id) {
        if(!new IdChecker(new_id).validate())
            return new IdRecommender(new_id).getRecommendedId();
        return new_id;
    }

    public static class IdChecker {

        private final String id;
        private final int length;
        private final char[] chArr;
        private final List<String> allowSpecialChar;

        public IdChecker(final String newId) {
            this.id = newId;
            this.length = id.length();
            this.chArr = id.toCharArray();
            this.allowSpecialChar = List.of("-", "_", ".");
        }

        private Boolean isEmpty() {
            return id == null
                || id.isEmpty();
        }

        public Boolean validate() {
            return checkSize()
                && checkStr()
                && checkPeriod();
        }

        private Boolean checkSize() {
            return (3 <= length && length <= 15);
        }

        private Boolean checkStr() {
            return isSmallLetter()
                || isDigit()
                || isSpecialChar();
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

    public static class IdRecommender {

        private final String id;

        public IdRecommender(final String id) {
            this.id = id;
        }

        public String getRecommendedId() {
            String replaceId = id.toLowerCase();
            replaceId = replaceId.replaceAll("[^0-9a-z._-]", "");
            replaceId = replaceId.replaceAll("\\.{2,}", ".");
            replaceId = replaceId.replaceAll("^\\.|\\.$", "");
            if(replaceId.isEmpty())
                replaceId = "a";
            if(replaceId.length() > 15)
                replaceId = replaceId.substring(0, 15);
            if(replaceId.charAt(replaceId.length()-1) == '.')
                replaceId = replaceId.substring(0, replaceId.length()-1);
            if(replaceId.length() <= 2) {
                do {
                    replaceId += String.valueOf(replaceId.charAt(replaceId.length() - 1));
                } while (replaceId.length() != 3);
            }
            return replaceId;
        }

    }

}
