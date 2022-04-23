package Lv1.숫자_문자열과_영단어;

import java.util.*;

public class JH {

    static class Solution {

        List<String> numberKeys;
        public Solution(){
            numberKeys= new ArrayList<>();
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
        }


        public int solution(String s) {

            String text = s;

            return Integer.parseInt(conversionNumber(text));
        }


        private String conversionNumber(String number){

            String inputNumber  = number.toLowerCase();
            String result = "";

            while(!inputNumber.isEmpty()){
                char startChar = inputNumber.toCharArray()[0];

                //첫번째 문자가 숫자 라면 그냥 넣는다.
                if(startChar >= '0' && startChar <= '9'){
                    result += startChar;
                    inputNumber = inputNumber.substring(1);
                    continue;
                }

                for(int i = 0; i<numberKeys.size() ; i++){
                    //문자가 하나가 아닌 여러개가 같이 올수도있기때문에 변환한 문자는 제거하고 반복한다.
                    if(inputNumber.startsWith(numberKeys.get(i))){
                        inputNumber = inputNumber.substring(numberKeys.get(i).length());
                        result += i;
                        break;
                    }
                }
            }

            return result;


        }


    }

}
