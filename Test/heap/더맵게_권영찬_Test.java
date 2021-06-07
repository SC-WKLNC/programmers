package heap;

import java.util.Arrays;
import java.util.PriorityQueue;

public class 더맵게_권영찬_Test {

    public int solution(int[] scoville, int K) {

        int answer = -1;

        // 1. 우선순위 큐 사용
        // 2. 큐의 사이즈가 2 이상이고 맨 앞 요소가 K보다 작을 경우 스코빌 지수 계산

        PriorityQueue<Integer> queue = new PriorityQueue<>(scoville.length);
        Arrays.stream(scoville).forEach(queue::add);

        int times = 0;

        while(queue.size() > 1) {

            if(queue.peek() >= K) {
                answer = times;
                break;
            }

            final int s1 = queue.poll();
            final int s2 = queue.poll();

            final int sum = s1 + (s2 * 2);
            queue.add(sum);
            times++;
        }

        if(queue.poll() >= K) {
            answer = times;
        }

        return answer;
    }
}
