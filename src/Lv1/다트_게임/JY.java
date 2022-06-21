package Lv1.다트_게임;

import java.util.ArrayList;
import java.util.List;

public class JY {

    public static void main(String[] ags) {
        Solution solution = new Solution();
        Solution solution2 = new Solution();
        Solution solution3 = new Solution();
        Solution solution4 = new Solution();

        String case1 = "1S2D*3T"; //37
        String case2 = "1D2S#10S"; //9
        String case3 = "1D2S0T";  // 3
        String case4 = "1S*2T*3S"; //23

        System.out.println("Result=  " +solution.solution(case1));
        System.out.println("Result=  " +solution2.solution(case2));
        System.out.println("Result=  " +solution3.solution(case3));
        System.out.println("Result=  " +solution4.solution(case4));
    }

    public static class Solution {
        // 필드 : values = 리스트로된 첫,둘,셋 던진 포인트를저장
        //       listNum = 포인트를 계산할 list위치를 저장
        // 함수 :: initvalue 던진점수만 저장
        //        initPoint 싱글 더블 트리플 스타상 아차상 계산하여 포인트 입력
        //        swichChar 위의 char 가들어오면 리스트를 가져와서 계산한뒤 리스트에 다시넣음
        //        sum      sum
        public int solution(String dartResult) {
            initValue(dartResult);
            initPoint(dartResult);
            return sum();
        }
        //
        //1S2D*3T
        //1D2S#10S
        //1D2S0T
        //1S*2T*3S

        List<Integer> values = new ArrayList<>();
        int listNum = 0;
        public void initValue(final String value){
            StringBuilder sb = new StringBuilder();
            for (int i=0; i<value.length();i++){
                char chr = value.charAt(i);
                if (48 <= chr && chr <= 57) {
                    sb.append(chr);
                }
                else if(sb.length()!=0){
                    values.add(Integer.parseInt(sb.toString()));
                    sb.setLength(0);
                }
            }
        }
        public void initPoint(final String value) {
            for (int i = 0; i < value.length(); i++) {
                var charset = value.charAt(i);
                switchChar(charset);
            }
        }
        public int sum(){
           return values.stream().mapToInt(mti->mti).sum();
        }

        public void switchChar(final char value) {
            switch (value) {
                case 'S':
                    values.set(listNum,(int)Math.pow(values.get(listNum),1));
                    listNum++;
                    break;
                case 'D':
                    values.set(listNum,(int)Math.pow(values.get(listNum),2));
                    listNum++;
                    break;
                case 'T':
                    values.set(listNum,(int)Math.pow(values.get(listNum),3));
                    listNum++;
                    break;
                case '*':
                    values.set(listNum-1,values.get(listNum-1)*2);
                    if(listNum!=1){
                        values.set(listNum-2,values.get(listNum-2)*2);
                    }
                    break;
                case '#':
                    values.set(listNum-1,values.get(listNum-1)*-1);
                    break;
                default:
                    break;
            }
        }

    }
}
