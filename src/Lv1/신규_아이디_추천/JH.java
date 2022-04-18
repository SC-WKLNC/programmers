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
 *
 * https://programmers.co.kr/learn/courses/30/lessons/72410
 */
public class JH {
    public static void main(String[] args) {
        //String new_id = "...Ab..!c.D^_^&.A....Ab..!c.D^_^&.A....Ab..!c.D^_^&.A.";
        //String new_id = "z";
        String new_id = "...!@BaT#*..y.abcdefghijklm";
        Solution solution = new Solution();
        String solution1 = solution.solution(new_id);
        System.out.println("solution1 = " + solution1);
        //bat.y.abcdefghi

    }
    static class Solution {
        public String solution(String new_id) {

            String newID = new ConversionID(new_id)
                    .idLowerCase()
                    .deleteBlackListCharacter()
                    .deleteDuplicationEndPointCharacter()
                    .deleteSideEndPointCharacter()
                    .isEmpty()
                    .validMAXLength()
                    .validMINLength().inputID;
            return newID;
        }


        private static class ConversionID{

            private String inputID;
            ConversionID(String inputID){
                this.inputID = inputID;
            }

            public ConversionID idLowerCase(){
                inputID = inputID.toLowerCase();
                return this;
            }
            // 2단계 new_id에서 알파벳 소문자, 숫자, 빼기(-), 밑줄(_), 마침표(.)를 제외한 모든 문자를 제거합니다.
            public ConversionID deleteBlackListCharacter(){
                char[] idArrays = inputID.toCharArray();
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
                inputID = temp;
                return this;
            }
            //3단계 new_id에서 마침표(.)가 2번 이상 연속된 부분을 하나의 마침표(.)로 치환합니다.
            public ConversionID deleteDuplicationEndPointCharacter(){
                inputID = deleteDuplicationEndPointCharacter(inputID);
                return this;
            }

            //4단계 new_id에서 마침표(.)가 처음이나 끝에 위치한다면 제거합니다.
            public ConversionID deleteSideEndPointCharacter(){
                if(inputID.startsWith(".")){
                    inputID = inputID.substring(1);
                }
                if(inputID.endsWith(".")){
                    inputID = inputID.substring(0, inputID.length()-1);
                }
                return this;
            }

            //5단계 new_id가 빈 문자열이라면, new_id에 "a"를 대입합니다.
            public ConversionID isEmpty(){
                if(inputID.isEmpty()){
                    inputID = "a";
                }
                return this;
            }
            //6단계 new_id의 길이가 16자 이상이면, new_id의 첫 15개의 문자를 제외한 나머지 문자들을 모두 제거합니다.
            public ConversionID validMAXLength(){
                if(inputID.length() >= 16){
                    inputID = inputID.substring(0,15);
                    inputID = deleteSideEndPointCharacter().inputID;
                }
                return this;
            }


            //7단계 new_id의 길이가 2자 이하라면, new_id의 마지막 문자를 new_id의 길이가 3이 될 때까지 반복해서 끝에 붙입니다.
            public ConversionID validMINLength(){
                int idLength = inputID.length();
                if (idLength <= 2){
                    String endCharacter = inputID.substring(idLength-1);
                    for(int i = idLength; i < 3; i++){
                        inputID += endCharacter;
                    }
                }
                return this;
            }

            private String deleteDuplicationEndPointCharacter(String id){
                if(id.contains("..")){
                    id = id.replace("..",".");
                    id = deleteDuplicationEndPointCharacter(id);
                }

                return id;
            }



        }

    }
}
