package Lv1.키패드_누르기;

import java.util.HashMap;
import java.util.Map;

public class JH {
    public static void main(String[] args) {
        //int [] numbers = new int[]{1, 3, 4, 5, 8, 2, 1, 4, 5, 9, 5};
        int [] numbers = new int[]{7, 0, 8, 2, 8, 3, 1, 5, 7, 6, 2};
        //int [] numbers = new int[]{3, 3, 9, 1, 4, 3, 7};
        String hand = "left";
        Solution solution = new Solution();
        solution.solution(numbers,hand);
    }
    static class Solution {
        public String solution(int[] numbers, String hand) {

            KeyBoard keyBoard = new KeyBoard();
            HandKeyPad handKeyPad = new HandKeyPad(hand,keyBoard);
            StringBuilder stringBuilder = new StringBuilder();
            for (int number : numbers) {
                stringBuilder.append( handKeyPad.click(number));
            }


            return stringBuilder.toString();
        }
    }

    private static class HandKeyPad{

        private String hand;
        private Pair leftHandPos;
        private Pair rightHandPos;

        private KeyBoard keyBoard;
        public HandKeyPad(String hand, KeyBoard keyBoard){
            this.keyBoard = keyBoard;
            this.hand = hand;
            this.leftHandPos = keyBoard.getKeyBoardPos("*");
            this.rightHandPos = keyBoard.getKeyBoardPos("#");
        }
        private String result(String hand, Pair keyPos){


            if(hand.equals("L") || hand.equals("left")){
                hand = "L";
                leftHandPos = keyPos;
            }else{
                hand = "R";
                rightHandPos = keyPos;
            }

            return hand;
        }
        public String click(int number){
            Pair keyBoardPos = keyBoard.getKeyBoardPos(String.valueOf(number));

            //왼쪽 키패드인경우
            if(number == 1 || number == 4 || number == 7){
                return result("L",keyBoardPos);
            }
            //오른쪽 키패드 인경우
            else if(number == 3 || number == 6 || number == 9){
                return result("R",keyBoardPos);
            }

            //가운데 인경우 가까운 손이 누른다
            int leftRange =  Math.abs((leftHandPos.getX() - keyBoardPos.getX())) + Math.abs((leftHandPos.getY() - keyBoardPos.getY()));
            int rightRange =  Math.abs((rightHandPos.getX() - keyBoardPos.getX())) + Math.abs((rightHandPos.getY() - keyBoardPos.getY()));

            if(leftRange == rightRange){
                return result(hand, keyBoardPos);
            }

            return result(leftRange < rightRange ?"L":"R", keyBoardPos);

        }
    }


    private static class KeyBoard{
        Map<String,Pair> keyboardItem = new HashMap<>();
        public KeyBoard(){
            int x = 0;
            int y = 0;
            for(int i=1; i < 13; i ++){
                if(i < 10){
                    keyboardItem.put(String.valueOf(i), new Pair(x,y));
                }
                else if (i == 10){
                    keyboardItem.put("*", new Pair(x,y));
                }
                else if (i == 11){
                    keyboardItem.put("0", new Pair(x,y));
                }
                else if (i == 12){
                    keyboardItem.put("#", new Pair(x,y));
                }

                y ++;
                if(y >2){
                    y = 0;
                    x ++;
                }
            }

        }

        public Pair getKeyBoardPos(String target){
            return keyboardItem.get(target);
        }
    }

    static class Pair {
        private int x;
        private int y;

        Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX(){
            return x;
        }

        public int getY(){
            return y;
        }
    }


}
