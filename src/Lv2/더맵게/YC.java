package Lv2.더맵게;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

public class YC {

    // start 2022/06/07 14:56
    // end   2022/06/07 15:30

    // 섞은 음식의 스코빌 지수 = 가장 맵지 않은 음식의 스코빌 지수 + (두 번째로 맵지 않은 음식의 스코빌 지수 * 2)

    public int solution(int[] scoville, int K) {
        final Queue<Integer> queue = new PriorityQueue<>(scoville.length);
        Arrays.stream(scoville).forEach(queue::add);
        int result = 0;
        while(queue.size() > 1) {
            final Integer s1 = queue.poll();
            if(s1 >= K) break;
            final Integer s2 = queue.poll();
            queue.add(s1 + (s2 * 2));
            result++;
        }
        return queue.poll() < K ? -1 : result;
    }
    
}
