package Lv1.부족한_금액_계산하기;

import Lv1.신규_아이디_추천.JH;

import java.util.HashMap;
import java.util.stream.LongStream;

public class JY {
    public static void main(String[] args) {
        Solution solution = new Solution();
        // test case 3 / 20 / 4 ;
        Long result = solution.solution(3,20,7);
        System.out.println(result);
        //bat.y.abcdefghi

    }
   static class Solution {

        // 이용료 price n번째 이용한다면 원래이용료의 n배를 청구
        // 처음이 100원이면 2번째엔 200원받음 아주 악덕업체가 따로없음 상놈의쉑기덜
        // 놀이기구를 몇번 타게되면 자신의 금액에서 얼마가 모자라는지 return
        // 금액이 부족하지 않으면 0 return
        // 이용료는 1원 부터 2500원
        // 돈은10억까지
        // 이용횟수는 1부터 2500
        //3,20,4 = 3 6 9 12 =>

        // 객체 모델링
        // 1. 사람
        // 필드 : 돈
        // 2. 롯데월드
        // 필드 : 놀이기구Hashmap
        // 함수 : 놀이기구추가, 놀이기구탬플릿가져오기
        // 3. 놀이기구
        // 필드 : 가격 , 이름
        // 4. 판매점
        // 필드 : 롯데월드
        // 함수 : 타고싶은 놀이기구 입장권계산, 입장권의총가격
        public long solution(int price, int money, int count) {
            Apparatus turnNeckHorse = new Apparatus("TurnNeckHorse",price);
            LotteWorld lotteWorld = new LotteWorld().addRide(turnNeckHorse);
            SaleStore saleStore = new SaleStore(lotteWorld);
            Human jinyoung = new Human(money);

            long calcMoney = saleStore.calculation(jinyoung,"TurnNeckHorse",count);
            if(calcMoney>=0) return 0;
            return Math.abs(calcMoney);
        }
        public class Human{
            private final int money;
            public Human(int money){
                this.money = money;
            }
        }
        public class LotteWorld{
            private HashMap<String,Apparatus> rideTemplete = new HashMap<>();
            public LotteWorld(){
            }
            public LotteWorld addRide(Apparatus apparatus){
                rideTemplete.put(apparatus.name,apparatus);
                return this;
            }
            public HashMap<String,Apparatus> getRideTemplete(){return rideTemplete;}

        }
        public class Apparatus {
            private final int price;
            private final String name;
            public Apparatus(String name,int price) {
                this.price = price;
                this.name = name;
            }
            public int getPrice(){return price;}
        }

        public class SaleStore{
            private final LotteWorld lotteWorld;
            public SaleStore(LotteWorld lotteWorld) {
                this.lotteWorld = lotteWorld;
            }
            public Long calculation(Human human,String name,int count) {
                int humanMoney = human.money;
                int targetPrice = lotteWorld.getRideTemplete().get(name).getPrice();
                return humanMoney - paymentAmount(targetPrice,count);
            }
            // 1 * 3 , 2 * 3 , 3 * 3
            private Long paymentAmount(int price,int count) {
                 return LongStream.range(0,count).map(mp-> price*(mp+1)).sum();
            }
        }
    }
}
