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
        String text1 = "one4seveneight";
        String text2 = "23four5six7";
        String text3 = "2three45sixseven";
        Solution solution = new Solution();
        solution.solution(text3);


    }


    static class Solution {


        public int solution(String s) {
            int answer = 0;
            String text = s;
            Queue<Integer> queue = new LinkedList<>();

            String result = "";
            char[] chars = text.toCharArray();

            //숫자의 위치 찾기
            for(int i = 0 ; i <chars.length ; i++){
                if(chars[i] >= '0' && chars[i] <= '9'){
                    queue.add(i);
                }
            }

            //시작이 숫자가 아닐 경우
            if(queue.peek() != 0){
                int first = queue.peek();
                String substring = text.substring(0, first);
                result += conNumberChar(substring);
            }


            while (!queue.isEmpty()){
                int first = queue.poll();
                int second = queue.peek() == null ? text.length() : queue.peek(); //null 이라면 마지막 까지 자르기

                System.out.println("first = " + first);
                System.out.println("second = " + second);
                result += chars[first]; //숫자는 바로 넣는다.
                System.out.println("result = " + result);

                String substring = text.substring(first+1, second);
                System.out.println("substring = " + substring);
                if(substring.isEmpty()) continue; //잘랐는데 아무것도 없다면 패스

                result += conNumberChar(substring);


            }

            System.out.println("result = " + result);

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

            while(!inputNumber.isEmpty()){

                for(int i = 0; i<numberKeys.size() ; i++){
                    if(inputNumber.startsWith(numberKeys.get(i))){
                        inputNumber = inputNumber.replace(numberKeys.get(i), "");

                        result += i;
                        break;
                    }
                }
            }

          return result;




        }


    }

}
