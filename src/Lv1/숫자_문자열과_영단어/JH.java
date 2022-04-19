package Lv1.숫자_문자열과_영단어;

import java.util.ArrayList;
import java.util.List;

public class JH {
    public static void main(String[] args) {
        String text = "one4seveneight";
        String text1 = "23four5six7";
        Solution solution = new Solution();
        solution.solution(text1);

    }


    static class Solution {

        public int solution(String s) {
            int answer = 0;
            String result = "";
            //new NumberCollection(s);
            List<Integer> numberPositions = new ArrayList<>();
            char[] chars = s.toCharArray();
            for(int i = 0 ; i <chars.length ; i++){
                if(chars[i] >= '0' && chars[i] <= '9'){
                    System.out.println("i = " + i);
                    numberPositions.add(i);
                }
            }

            for(int i = 0 ; i < numberPositions.size()-1 ; i++){
                int startIndex = numberPositions.get(i)+1; //해당 위치는 숫자를 가리키고있기때문에 +1 을 하여 다음 문자부터 시작한다.
                int endIndex = numberPositions.get(i + 1);

                result += startIndex;
                if(endIndex - startIndex < 1) result += endIndex; //중간에 문자가 없다면.
                else{
                    String substring = s.substring(startIndex, endIndex);
                    result+= conNumberChar(substring);
                    result+=endIndex;
                }


            }
            System.out.println("substring = " + result);

            return answer;
        }


        private int conNumberChar(String number){
            number = number.toLowerCase();
            switch (number){
                case "zero" :
                    return 0;
                case "one" :
                    return 1;
                case "two" :
                    return 2;
                case "three" :
                    return 3;
                case "four" :
                    return 4;
                case "five" :
                    return 5;
                case "six" :
                    return 6;
                case "seven" :
                    return 7;
                case "eight" :
                    return 8;
                case "nine" :
                    return 9;
            }
            return 0;
        }


    }
    static class NumberCollection{
        final List<Integer> numberPositions;
        //final Pair charPositions;

        public NumberCollection(String text) {
            numberPositions = new ArrayList<>();

            char[] chars = text.toCharArray();
            for(int i = 0 ; i <chars.length ; i++){
                if(chars[i] >= '0' && chars[i] <= '9'){
                    System.out.println("i = " + i);
                    numberPositions.add(i);
                }
            }

            for(int i = 0 ; i < numberPositions.size()-1 ; i++){
                int startIndex = numberPositions.get(i)+1; //해당 위치는 숫자를 가리키고있기때문에 +1 을 하여 다음 문자부터 시작한다.
                int endIndex = numberPositions.get(i + 1);

                String substring = text.substring(startIndex, endIndex);
                System.out.println("substring = " + substring);
            }


        }

    }

    static class Pair<L,R>{
        final L first;
        final R second;

        public Pair(L first, R second) {
            this.first = first;
            this.second = second;
        }
    }
}
