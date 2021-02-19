package queue;

import java.util.Arrays;

public class 주식가격_이진호 {
    public int[] solution(int[] prices) {

        int pricesLength = prices.length;
        int targetTime;
        int comparison;//비교할 시간
        int[] answer = new int[pricesLength];

        for(int i = 0; i < pricesLength;i++) {
            targetTime = prices[ i];

            for(int j =i+1 , riseCount=0 ; j<pricesLength;j++) {
                riseCount++;
                comparison = prices[ j];
                //주식이 줄었거나 마지막까지 비교를 모두 했을때까지 줄어든적이 없다면 종료
                if(targetTime > comparison || j ==pricesLength-1) {
                    answer[ i] = riseCount ;
                    break;
                }
            }
        }

        return answer;
    }

    // 아래의 코드는 매우 간결하게 작성했으나 그만큼 손실이있었다.
    public int[] simplesolution(int[] prices) {

        int pricesLength = prices.length;
        int[] answer = new int[pricesLength];
        int i,j; // 이것도 별로다
        for(i = 0; i < pricesLength;i++) {
            for(j =i+1  ; j<pricesLength;j++) {
                answer[i]++; //이렇게 접근하는것도 별로 안좋은것같다.
                if( prices[i]> prices[j] ) { //이렇게 접근하는것도 별로 안좋은것같다.
                    break;
                }
            }
        }

        return answer;

    }

}
