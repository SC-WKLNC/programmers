package 로또_최고순위_최저순위;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JY {
    public int[] solution(int[] lottos, int[] win_nums) {
        int[] answer = new int[2];
        int zeroNumberCount =0;
        List CorrectNumberList = new ArrayList();
        // 배열안 0갯수 파악
        zeroNumberCount= (int) Arrays.stream(lottos).filter(fi->fi==0).count();
        // lotto배열과 win 배열중 일치하는것을 aim리스트에 넣음
        Arrays.stream(win_nums).forEach(x -> {if(Arrays.stream(lottos).filter(fi -> fi==x).count()>0) {CorrectNumberList.add(x);}});
        int maxCurrent;
        int minCurrent;
        // current => 0~6 맞는갯수만큼
        // aim -> 0~6맞는갯수만큼
        // 6개중 1개맞았을때 0개맞았을때를 꼴지로 계산하기 때문에 이렇게처리함
        maxCurrent = 7-(zeroNumberCount+CorrectNumberList.size());
        minCurrent = 7-CorrectNumberList.size();
        answer[0] = maxCurrent<7 ? maxCurrent : 6;
        answer[1] = minCurrent<7 ? minCurrent : 6;

        return answer;
    }
}
