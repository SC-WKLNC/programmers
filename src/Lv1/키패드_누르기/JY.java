package Lv1.키패드_누르기;

import java.util.HashMap;

public class JY {


    class Solution {

        HashMap<Integer, int[]> keypadMap = new HashMap<Integer, int[]>();

        public String solution(int[] numbers, String hand) {
            // int keypad
            initKeypad();
            // set ConvertModule
            final ConvertModule module = new ConvertModule(keypadMap, hand);
            // call result Value
            return module.resultValue(numbers);
        }
        public void initKeypad()
        {
            // 하기 위치에 맞춰 위치 셋업
            // 0,0(1)  0,1(2)  0,2(3)
            // 1,0(4)  1,1(5)  1,2(6)
            // 2,0(7)  2,1(8)  2,2(9)
            //         3,1(0)
            keypadMap.put(1, new int[] {0,0});
            keypadMap.put(2, new int[] {0,1});
            keypadMap.put(3, new int[] {0,2});
            keypadMap.put(4, new int[] {1,0});
            keypadMap.put(5, new int[] {1,1});
            keypadMap.put(6, new int[] {1,2});
            keypadMap.put(7, new int[] {2,0});
            keypadMap.put(8, new int[] {2,1});
            keypadMap.put(9, new int[] {2,2});
            keypadMap.put(0, new int[] {3,1});
        }
    }

    class ConvertModule{

        private final HashMap<Integer,int[]> keypad;
        private final String hand;
        private StringBuilder stringBuilder =new StringBuilder();
        private MoveHandController moveHandController;
        public ConvertModule(final HashMap<Integer,int[]> _keypad,final String _hand)
        {
            this.keypad = _keypad;
            // left , right 의 첫값을 따서 L , R 로 변환
            this.hand = _hand.substring(0,1).toUpperCase();
            // init movehandler
            this.moveHandController = new MoveHandController(hand);
        }

        public String resultValue(final int[] numbers)
        {
            //result는 오직 string 만
            return addpendString(numbers);
        }
        private String addpendString(final int[] _numbers)
        {
            //addpend는 addpend 값을 받아서 addpend만
            for (int number:_numbers) {
                stringBuilder.append(moveHandController.moveHand(keypad.get(number)));
            }
            return stringBuilder.toString();
        }
    }

    class MoveHandController
    {

        protected int[] lastLeftHandSecter;
        private int[] lastRightHandSecter;
        final String hand;

        public MoveHandController(String _hand)
        {
            this.hand = _hand;
            // left hand 초기값
            this.lastLeftHandSecter = new int[] {3,0};
            // right hand 초기값
            this.lastRightHandSecter = new int[] {3,2};
        }

        public String moveHand(final int[] _number)
        {
            // left , right 위치값만 찾기
            if (_number[1]==0 || _number[1] ==2) return vaildHandSector(_number);
            // center 위치값일때
            return vaildCenterHandSector(_number);
        }

        private String vaildHandSector(final int[] leftNrightNum)
        {
            // left 일때만.
            if(leftNrightNum[1]==0) return initHandSector("L",leftNrightNum);
            // right 일때만.
            return initHandSector("R",leftNrightNum);
        }
        private String initHandSector(final String _sector, final int[] _num)
        {
            // secter L , R 일때 현재의 위치를 기록함
            switch (_sector)
            {
                case "L":
                    lastLeftHandSecter =_num;
                    break;
                case "R" :
                    lastRightHandSecter = _num;
                    break;
                default:
                    break;
            }
            return _sector;
        }
        private String vaildCenterHandSector(final int[] centerNum)
        {
            // 값초기화
            int leftLenth = 0;
            int rightLenth = 0;
            // left, right 를 현재 중앙값을 계산후에 abs로 저장함.
            leftLenth = Math.abs(lastLeftHandSecter[0] - centerNum[0])+Math.abs(lastLeftHandSecter[1] - centerNum[1]);
            rightLenth = Math.abs(lastRightHandSecter[0] - centerNum[0])+Math.abs(lastRightHandSecter[1] - centerNum[1]);
            // 두값이 같으면 자신의 손잡이 (hand)로 값을 입력
            if(leftLenth == rightLenth)return initHandSector(hand,centerNum);
            //거리가 가까운 값을 입력
            if(leftLenth>rightLenth) return initHandSector("R",centerNum);
            return initHandSector("L",centerNum);
        }
    }
}

