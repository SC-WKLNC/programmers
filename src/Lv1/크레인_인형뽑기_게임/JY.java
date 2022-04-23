package Lv1.크레인_인형뽑기_게임;


import java.util.*;

public class JY {
    class Solution {
        HashMap<Integer, List<Integer>> gameMap = new HashMap<>();
        public void initGameMap(final int _length)
        {
            for(int i =0;i<_length;i++)
            {gameMap.put(i,new ArrayList<>());}
        }
        public void initDoll(final int [][] _board,final int _length)
        {
            for (int stack = 0; stack<_length;stack++)
            {
                for (int line = 0 ; line<_length; line++)
                {
                    if(_board[stack][line]!=0) gameMap.get(line).add(_board[stack][line]);
                }
            }
        }
        public int solution(int[][] board, int[] moves) {
            int answer = 0;
            final int length = board.length;

            //init
            initGameMap(length);
            //init Doll
            initDoll(board,length);
            DollContainer dollContainer = new DollContainer(gameMap);
            return dollContainer.move(moves);
            // 0 0 0 0 0
            // 0 0 1 0 3
            // 0 2 5 0 1
            // 4 2 4 4 2
            // 3 5 1 3 1
            // Q W E R T

            // 4(1) 3(5) 1(3) 1(5) 3(1) 2(2) X(1) 4(4)
            // 0 0 0 0 0
            // 0 0 0 0 0
            // 0 0 5 0 0
            // 0 2 4 0 2
            // 0 5 1 3 1
            // Q W E R T\

        }

    }
    class DollContainer
    {
        private HashMap<Integer,List<Integer>> gameMap;
        private List<Integer> targetContainer =new ArrayList<>();
        private List<Integer> reStoreContainer = new ArrayList<>();
        public int deleteValue =0;
        public DollContainer(final HashMap<Integer,List<Integer>> _gameMap)
        {
            this.gameMap = _gameMap;
        }

        private int popStorege(final int _storegeSector)
        {
            //값가쟈옴
            int popint = targetContainer.get(0);
            //가져온값은 지금현제 storege에서 지움
            gameMap.get(_storegeSector).remove(0);
            return popint;
        }
        private boolean vaildnullStorege(final List<Integer> _target)
        {
            // size 0인지 체크
            if(_target.size()==0) return true;
            return false;
        }
        private void pushStorege(int _data)
        {
            //가져온값 넣기
            reStoreContainer.add(_data);
        }
        private boolean vaildstorege(int _data)
        {
            //비어있으면 투르
            if(vaildnullStorege(reStoreContainer))return true;
            //앞인덱스 값을 가져왔을때 다르면 투르
            if(reStoreContainer.get(reStoreContainer.size()-1) != _data)return true;
            //아니면 값을 지우고 터트렸다 가정해서 value 2++
            deleteValue +=2;
            reStoreContainer.remove(reStoreContainer.size()-1);
            return false;
        }

        public int move(final int[] moves)
        {
            Arrays.stream(moves).forEach(fe->storegeController(fe-1));
            return deleteValue;
        }
        private void storegeController(final int sector)
        {
            // targetcontainer에 값 입력
            targetContainer = gameMap.get(sector);
            // null Check
            if(vaildnullStorege(targetContainer)) return;
            // 가져온값 적재
            final int popInt= popStorege(sector);
            // 넣을수있는지 Check
            if(!vaildstorege(popInt))return;
            // push
            pushStorege(popInt);
        }
    }
}
