package queue;

import java.util.ArrayList;
import java.util.List;

public class 다리를지나는트럭_권영찬_Test {

    public static void main(String[] args) {
        testCase();
    }

    public static int execute(int[] truck_weights, int bridge_length, int weight) {

        return new BridgeQueue(truck_weights, bridge_length, weight).execute();
    }

    public static void testCase() {
        int[] bridge_lengths = new int[]{2,100,100,1,4,4,3,3,5,7,5,5};
        int[] weights = new int[]{10,100,100,1,4,2,1,1,5,7,5,5};
        int[] answer = new int[]{8,101,110,2,9,10,10,16,14,18,19,19};
        List<int[]> truck_weights_list = new ArrayList<>();
        truck_weights_list.add(new int[]{7,4,5,6});
        truck_weights_list.add(new int[]{10});
        truck_weights_list.add(new int[]{10,10,10,10,10,10,10,10,10,10});
        truck_weights_list.add(new int[]{1});
        truck_weights_list.add(new int[]{1,2,1,1});
        truck_weights_list.add(new int[]{1,1,1,1});
        truck_weights_list.add(new int[]{1,1,1});
        truck_weights_list.add(new int[]{1,1,1,1,1});
        truck_weights_list.add(new int[]{1,1,1,1,1,2,2});
        truck_weights_list.add(new int[]{1,1,1,1,1,3,3});
        truck_weights_list.add(new int[]{1,1,1,1,1,2,2,2,2});
        truck_weights_list.add(new int[]{2,2,2,2,1,1,1,1,1});

        for (int i = 0; i < 12; i++) {
            int bridge_length = bridge_lengths[i];
            int weight = weights[i];
            int[] truck_weights = truck_weights_list.get(i);

            int second = execute(truck_weights, bridge_length, weight);
            System.out.println((i+1) + "번 \t테스트 실행 결과 : " + ((answer[i] == second) ? "성공" : "실패"));
        }
    }

}
