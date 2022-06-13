package Lv1.예산;

import java.util.Arrays;
import java.util.stream.IntStream;

public class JY {

    // greed 문제
    // 금액이 최대 얼마고
    // 얼마까지 구매해야한다.
    // 돈을 무조건 다써야한다.

    // 1. Calculrate
    // 필드 : 돈필요한사람 , 최대금액 , 현제금액, 몇번반복했는지
    // 함수 : 최대금액보다 작은 값을 정렬 , 금액을 더해가며 최대금액보다 많거나 같으면 멈추고 돌때마다 카운트
    class Solution {
        public int solution(int[] d, int budget) {

            var calc = new Calculrate(d,budget);
            calc.greed();
            return calc.getCount();
        }


        public class Calculrate{
            private final int[] intArray;
            private final int maxMoney;
            private int money = 0;
            private int count = 0;
            public Calculrate(final int[] values ,final int budget){
                this.maxMoney = budget;
                this.intArray = arrayArrangement(values);
            }

            private int[] arrayArrangement(final int[] values){
                return Arrays.stream(values).filter(fl-> fl<=this.maxMoney).sorted().toArray();
            }

            public void greed(){
                IntStream.range(0,intArray.length).forEach(this::sum);
            }
            private void sum(final int arrayPos){
                int sumvalue = money+intArray[arrayPos];
                if(sumvalue>maxMoney) return;
                money = sumvalue;
                count+=1;
            }
            public int getCount(){
                return count;
            }
        }
    }
}
