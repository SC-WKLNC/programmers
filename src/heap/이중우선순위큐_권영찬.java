package heap;

import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 이중우선순위큐_권영찬 {

    private final String OP_INSERT = "I";
    private final String OP_DELETE = "D";
    private final String DELIMITER = " ";
    private final String MIN = "-1";
    private final String MAX = "1";

    public int[] solution(String[] operations) throws Exception {

        PriorityQueue<Integer> minQueue = new PriorityQueue<>(); // 최소힙 : 최소값을 제거
        PriorityQueue<Integer> maxQueue = new PriorityQueue<>(Collections.reverseOrder()); // 최대힙 : 최대값을 제거

        for(String str : operations) {
            StringTokenizer tokenizer = new StringTokenizer(str, DELIMITER);
            final String op = (String)tokenizer.nextElement();
            if(OP_INSERT.equals(op)) {
                final int num = Integer.parseInt((String) tokenizer.nextElement());
                minQueue.add(num);
                maxQueue.add(num);
            } else if(OP_DELETE.equals(op)) {
                String deleteType = (String)tokenizer.nextElement();
                switch (deleteType) {
                    case MIN: if(minQueue.size() > 0) minQueue.poll(); break;
                    case MAX: if(maxQueue.size() > 0) maxQueue.poll(); break;
                    default: throw new Exception("알 수 없는 오류");
                }
                System.out.println("===================================");
                System.out.println("maxQueue.size() : " + maxQueue.size());
                System.out.println("minQueue.size() : " + minQueue.size());
            }
        }

        int[] result = new int[2];

        if(maxQueue.size() > 0 && minQueue.size() > 0) {
            result[0] = maxQueue.poll();
            result[1] = minQueue.poll();
        }

        return result;
    }


    public static void main(String[] args) throws Exception {
//        String[] operations = {"I 7","I 5","I -5","D -1"};
//        int[] result = {7,5};
//        final int[] solution = new 이중우선순위큐_권영찬().solution(operations);
//        System.out.println(Arrays.equals(result, solution));
//
//        String[] operations2 = {"I 16", "D 1"};
//        int[] result2 = {0,0};
//        final int[] solution2 = new 이중우선순위큐_권영찬().solution(operations2);
//        System.out.println(Arrays.equals(result2, solution2));

        String[] operations3 = {"I 16", "I -5643", "D -1", "D 1", "D 1", "I 123", "D -1"};
        int[] result3 = {0,0};
        final int[] solution3 = new 이중우선순위큐_권영찬().solution(operations3);
        System.out.println(Arrays.equals(result3, solution3));
    }
}
