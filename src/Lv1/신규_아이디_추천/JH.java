package Lv1.신규_아이디_추천;

import java.util.Locale;

/**
 * 1단계 new_id의 모든 대문자를 대응되는 소문자로 치환합니다.
 * 2단계 new_id에서 알파벳 소문자, 숫자, 빼기(-), 밑줄(_), 마침표(.)를 제외한 모든 문자를 제거합니다.
 * 3단계 new_id에서 마침표(.)가 2번 이상 연속된 부분을 하나의 마침표(.)로 치환합니다.
 * 4단계 new_id에서 마침표(.)가 처음이나 끝에 위치한다면 제거합니다.
 * 5단계 new_id가 빈 문자열이라면, new_id에 "a"를 대입합니다.
 * 6단계 new_id의 길이가 16자 이상이면, new_id의 첫 15개의 문자를 제외한 나머지 문자들을 모두 제거합니다.
 *      만약 제거 후 마침표(.)가 new_id의 끝에 위치한다면 끝에 위치한 마침표(.) 문자를 제거합니다.
 * 7단계 new_id의 길이가 2자 이하라면, new_id의 마지막 문자를 new_id의 길이가 3이 될 때까지 반복해서 끝에 붙입니다.
 */
public class JH {
    public static void main(String[] args) {
        //String new_id = "...Ab..!c.D^_^&.A....Ab..!c.D^_^&.A....Ab..!c.D^_^&.A.";
        //String new_id = "z";
        String new_id = "...!@BaT#*..y.abcdefghijklm";
        Solution solution = new Solution();
        solution.solution(new_id);
    }
    static class Solution {
        public String solution(String new_id) {
            String answer = "";
            answer = ConversionValidId(new_id);
            System.out.println("id 결과 = " + answer);
            return answer;
        }

        private String  ConversionValidId(String id){


            //1단계 new_id의 모든 대문자를 대응되는 소문자로 치환합니다.
            id = id.toLowerCase();
            System.out.println("대문자를 소문자로 id = " + id);

            // 2단계 new_id에서 알파벳 소문자, 숫자, 빼기(-), 밑줄(_), 마침표(.)를 제외한 모든 문자를 제거합니다.
            char[] idArrays = id.toCharArray();
            String temp = "";
            for (char idArrayItem : idArrays) {
                if(idArrayItem >= 'a' && idArrayItem <= 'z'){
                    temp += String.valueOf(idArrayItem);
                }else if (idArrayItem >= 'A' && idArrayItem <= 'Z'){
                    temp += String.valueOf(idArrayItem);
                }else if (idArrayItem >= '0' && idArrayItem <= '9'){
                    temp += String.valueOf(idArrayItem);
                }
                else if (idArrayItem =='-' || idArrayItem == '_' || idArrayItem == '.'){
                    temp += String.valueOf(idArrayItem);
                }

            }
            id = temp;
            System.out.println("사용가능한 문자로만 id = " + id);
            //3단계 new_id에서 마침표(.)가 2번 이상 연속된 부분을 하나의 마침표(.)로 치환합니다.
            id = EndPointCharacter(id);
            System.out.println("중복 마침표 제거 id = " + id);

            //4단계 new_id에서 마침표(.)가 처음이나 끝에 위치한다면 제거합니다.
            if(id.startsWith(".")){
                id = id.substring(1);
                System.out.println("시작 마침표 제거 id = " + id);
            }
            if(id.endsWith(".")){
                id = id.substring(0, id.length()-1);
                System.out.println("끝 마침표 제거 id = " + id);
            }

            //5단계 new_id가 빈 문자열이라면, new_id에 "a"를 대입합니다.
            if(id.isEmpty()){
                id = "a";
            }
            int idLength = id.length();

            //6단계 new_id의 길이가 16자 이상이면, new_id의 첫 15개의 문자를 제외한 나머지 문자들을 모두 제거합니다.
             if(idLength >= 16){
                id = id.substring(0,15);
                System.out.println("id 길이 제거 id = " + id);
            }
             //코드 중복
            if(id.endsWith(".")){
                id = id.substring(0, id.length()-1);
                System.out.println("끝 마침표 제거 id = " + id);
            }

            //7단계 new_id의 길이가 2자 이하라면, new_id의 마지막 문자를 new_id의 길이가 3이 될 때까지 반복해서 끝에 붙입니다.
            else if (idLength <= 2){
                String endCharacter = id.substring(idLength-1);
                for(int i = idLength; i < 3; i++){
                    id += endCharacter;
                }
            }
            return id;
        }


        // 중복된 마침표는 하나로.
        private String EndPointCharacter(String id){
            if(id.contains("..")){
                id = id.replace("..",".");
                id = EndPointCharacter(id);
            }

            return id;
        }
    }
}
