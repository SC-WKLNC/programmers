package Lv1.크레인_인형뽑기_게임;

import java.util.Stack;
/**
 * [0,0,0,0,0]
 * [0,0,1,0,3]
 * [0,2,5,0,1]
 * [4,2,4,4,2]
 * [3,5,1,3,1]
 */


public class JH {
    public static void main(String[] args) {

        int[][] board = new int[][]{{0,0,0,0,0},{0,0,1,0,3},{0,2,5,0,1},{4,2,4,4,2},{3,5,1,3,1}};
        int[] moves = new int[]{1,5,3,5,1,2,1,4};

        Solution solution = new Solution();
        solution.solution(board,moves);
    }
    static class Solution {
        public int solution(int[][] board, int[] moves) {

            Game game = new Game(board);
            for (int move : moves) {
                game.action(move);
            }

            return game.finish();
        }
    }

    static class Game{
        private  int[][] board;
        private Stack<Integer> basket;
        private int deleteCount = 0;

        public Game(int[][] board){
            this.board = board;
            this.basket = new Stack<>();
        }
        //크레인을 작동 시킨다.
        public void action(int move){
            int item = 0;
            for (int[] boardRow : board) {
                item = boardRow[move-1];
                if(item != 0) {
                    boardRow[move-1] = 0; //인형을 뽑았기에 0으로 변경
                    break;
                }
            }
            if(item != 0)  itemMoveBasket(item);

        }
        //뽑은 인형을 바구니에 넣는다.
        private void itemMoveBasket(int item){

            //이전 아이템과 같은 아이템이면 넣지 않는다.
            int topItem = basket.empty() ? -1 : basket.peek();
            if(topItem != item) basket.push(item);
            else {
                basket.pop(); //같다면 전의 데이터 파기
                deleteCount +=2;
            }
        }

        public int finish(){
            return deleteCount;
        }


    }
}
