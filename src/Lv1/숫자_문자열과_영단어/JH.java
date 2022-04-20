package Lv1.숫자_문자열과_영단어;

import java.util.*;

/*
"one4seveneight"	1478
"23four5six7"	234567
"2three45sixseven"	234567
"123"	123

 */
public class JH {
    public static void main(String[] args) {
        String text = "one4seveneight";
        String text1 = "23four5six7";
        Solution solution = new Solution();
        solution.solution(text);

        //solution.test(text1);

    }


    static class Solution {

        public int solution(String s) {
            int answer = 0;
            String result = "";
            char[] chars = s.toCharArray();
            int charStrIndex = -1;

            for(int i = 0 ; i <chars.length ; i++){
                //해당 문자가 0~9 가 아니면서 마지막 문자가 아닐때
                if(!(chars[i] >= '0' && chars[i] <= '9')) {
                    if(charStrIndex == -1 ) charStrIndex = i;
                    if(i < chars.length -1)  continue;
                    

                }


                if(charStrIndex != -1){
                    int startIndex = charStrIndex;
                    int endIndex = i;
                    String substring = s.substring(startIndex, endIndex);
                    String s1 = conNumberChar(substring);

                    System.out.println("문자 추가 = " + s1);
                    result += s1;
                    charStrIndex = -1;
                }

                System.out.println("숫자추가 = " + chars[i]);
                result += chars[i];


            }

            System.out.println("결과 = " + result);
            return answer;
        }


        private String conNumberChar(String number){

            String inputNumber  = number.toLowerCase();

            List<String> numberKeys = new ArrayList<>();
            numberKeys.add("zero");
            numberKeys.add("one");
            numberKeys.add("two");
            numberKeys.add("three");
            numberKeys.add("four");
            numberKeys.add("five");
            numberKeys.add("six");
            numberKeys.add("seven");
            numberKeys.add("eight");
            numberKeys.add("nine");

            String result = "";
            System.out.println("number = " + inputNumber);
            while(!inputNumber.isEmpty()){

                for(int i = 0; i<numberKeys.size() ; i++){
                    if(inputNumber.startsWith(numberKeys.get(i))){
                        System.out.println("제거전 = " + inputNumber);
                        inputNumber = inputNumber.replace(numberKeys.get(i), "");
                        System.out.println("제거후 = " + inputNumber);

                        result += i;
                        break;
                    }
                }
            }

          return result;




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
//switch (number){
//        case "zero" :
//            return 0;
//        case "one" :
//            return 1;
//        case "two" :
//            return 2;
//        case "three" :
//            return 3;
//        case "four" :
//            return 4;
//        case "five" :
//            return 5;
//        case "six" :
//            return 6;
//        case "seven" :
//            return 7;
//        case "eight" :
//            return 8;
//        case "nine" :
//            return 9;
//    }
//            return 0;
    static class Pair<L,R>{
        final L first;
        final R second;

        public Pair(L first, R second) {
            this.first = first;
            this.second = second;
        }
    }
}
