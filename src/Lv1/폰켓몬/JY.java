package Lv1.폰켓몬;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class JY {
    public static void main(String[] ags) {
        Solution sol = new Solution();
        String test1 = "aaaaaaaaaa";
    }

    //  N마리의 폰켓몬중 N/2마리를 가져가도 좋다.
    //  종류에 따라 번호를 부여하여 구분
    //  같은 종류는 같은 번호를 가지고 있음
    //  ex) 4마리 -> 3 , 1 ,2 ,3 일때 1번 1마리 2번 한마리 3번 2마리
    //  최대한 다양한 종류의 폰켓몬을 가지기 위해 최대한 많은 종류로 N/2
    //  가장많은 종류를 선택하여 종류번호 return
    //  return 여러가지인 경우에도 선택할수있는 종류의 최댓값만 return

    // 객체
    // 1. 폰켓몬저장소
    //      필드 : 폰켓몬들 , 최대고를수있는 값
    // 2. 폰켓몬선택
    //      필드 : 폰캣몬
    //      함수 : 폰켓몬의 종류를 Count, Max값과 비교하여 Return

    public static class Solution {
        public int solution(int[] nums) {
            PonKetMonCan ponKetMonCan = new PonKetMonCan(nums);
            SelectPonketMon selectPonketMon = new SelectPonketMon(ponKetMonCan);
            return selectPonketMon.getSelectedPonKetMon();
        }

        public class PonKetMonCan {
            private final int limitMonsCount;
            private final int[] mons;

            public PonKetMonCan(int[] mons) {
                this.limitMonsCount = mons.length / 2;
                this.mons = mons;
            }

            public int[] getMons() {return mons;}
            public int getLimitMonsCount() {return limitMonsCount;}
        }

        public class SelectPonketMon {
            private final PonKetMonCan ponKetMonCan;

            public SelectPonketMon(PonKetMonCan ponKetMonCan) {
                this.ponKetMonCan = ponKetMonCan;
            }
            private int getPonKetMonKindCount() {
               return (int)Arrays.stream(ponKetMonCan.getMons())
                               .distinct()
                               .count();
            }
            public int getSelectedPonKetMon(){
                int allKindCount = getPonKetMonKindCount();
                int limitMonsCount = ponKetMonCan.getLimitMonsCount();
                if(allKindCount>limitMonsCount) return limitMonsCount;
                return allKindCount;
            }
        }
    }
}