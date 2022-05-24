package Lv1.부족한_금액_계산하기;


/**
 *
 * 시작 시간 5월17일 오후7시 30 분
 *  종료 시간 5월17일 오후7시 46분
 */
public class JH {
    public static void main(String[] args) {
        int price = 3;
        int money = 20;
        int count = 4;
        Lv1.부족한_금액_계산하기.JH jh = new Lv1.부족한_금액_계산하기.JH();
        jh.solution(price,money,count);
    }

    public long solution(int price, int money, int count) {

        long sumPrice = 0;
        for(int i = 1 ; i <= count; i ++){
            sumPrice += price*i;
        }

        long result = money - sumPrice;

        return  result >= 0 ? 0 : (result*-1);
    }
}