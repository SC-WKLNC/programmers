package Lv1.부족한_금액_계산하기;

public class YC {

    // 시작 2022/05/17 23:20
    // 종료 2022/05/17 23:47

    // 패널티.. 질문하기에서 long 에 대한 언급을 봐버림...

    // 객체 모델링

    // 1. 놀이기구
    // 필드 : 가격
    // 함수 : count 번 탔을때 갖고있는 금액에서 모자란 금액 계산

    public static void main(String[] args) {
        long solution = new YC().solution(1, 1, 10);
        System.out.println(solution);
    }

    public long solution(int price, int money, int count) {
        final Ride ride = new Ride(price, money, count);
        return ride.getResult();
    }

    public static class Ride {

        private final int price;
        private final int money;
        private final int count;

        public Ride(final int price, final int money, final int count) {
            this.price = price;
            this.money = money;
            this.count = count;
        }

        public long getResult() {
            long totalPrice = calcTotalPrice();
            if(money >= totalPrice)
                return 0;
            else
                return totalPrice - money;
        }

        public long calcTotalPrice() {
            final int half = count / 2;
            final int tmpPrice = price * (count+1);
            if(count % 2 == 0) {
                return (long) tmpPrice * half;
            } else {
                return ((long) tmpPrice * half) + ((long) price * (half+1));
            }
        }

    }

}
