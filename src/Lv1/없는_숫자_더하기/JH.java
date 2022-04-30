package Lv1.없는_숫자_더하기;

import java.util.HashMap;
import java.util.Map;

public class JH {
    public static void main(String[] args) {

        int[] numbers = new int[]{1,2,3,4,6,7,8,0};

        JH jh = new JH();
        jh.solution(numbers);


    }
    public int solution(int[] numbers) {
        int answer = 0;

        Map<Integer,Integer> map = new HashMap();
        for (int number : numbers) {
            map.put(number,0);
        }

        for(int i =0; i <10; i ++){
            answer+=map.getOrDefault(i,i);
        }
        return answer;
    }
}
