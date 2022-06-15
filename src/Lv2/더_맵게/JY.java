package Lv2.더_맵게;

import java.util.Arrays;
import java.util.PriorityQueue;

public class JY {


    // 1. Heap
    // 필드 : 우선순위 큐, K , 섞은카운트
    // 함수 : 우선순위큐에넣기 , while 문으로 도달할때까지 반복

    class Solution {
        public int solution(int[] scoville, int K) {
            if(scoville.length==1) return 0;
            Heap heap = new Heap(scoville,K);
            heap.calcScoville();
            return heap.getCount();
        }


        public class Heap{
            PriorityQueue<Long> pQueue = new PriorityQueue<>();
            private final int k ;
            private int count = 0;
            public Heap(final int[] scoville,final int k){
                initQueue(scoville);
                this.k =k;
            }
            private void initQueue(int[] scoville){
                Arrays.stream(scoville).forEach(fe-> pQueue.add((long)fe));
            }

            public void calcScoville(){
                while (true) {
                    long first = pQueue.poll();
                    if (first >= k) return;
                    if (pQueue.size() == 0) {
                        count = -1;
                        return;
                    }
                    long secound = pQueue.poll();
                    pQueue.add(mixScoville(first, secound));
                    count++;
                }
            }
            private long mixScoville(final long first ,final long second){
                return first + (second*2);
            }
            public int getCount(){
                return count;
            }

        }
    }
}
