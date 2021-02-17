package queue;

public class 다리를지나는트럭_권영찬 {
    public static void main(String[] args) {

//        2	10	[7,4,5,6]	8
//        100	100	[10]	101
//        100	100	[10,10,10,10,10,10,10,10,10,10]	110

        int[] bridge_lengths = new int[]{2, 100, 100};
        int[] weights = new int[]{10, 100, 100};
        int[] truck_weights1 = new int[]{7,4,5,6};
        int[] truck_weights2 = new int[]{10};
        int[] truck_weights3 = new int[]{10,10,10,10,10,10,10,10,10,10};


        int bridge_length = bridge_lengths[2];
        int weight = weights[2];
        int[] truck_weights = truck_weights3;

        int totalOfTrucks = truck_weights.length;
        int numberOfTrucks = totalOfTrucks;
        BridgeQueue queue = new BridgeQueue(bridge_length, weight, truck_weights);

//        int second = 0;
//        while(numberOfTrucks > 0) {
//            if(queue.add(truck_weights[totalOfTrucks-numberOfTrucks])) {
//                numberOfTrucks--;
//            }
//            second++;
//        }

        System.out.println(queue.execute());
    }

}

class BridgeQueue {

    // 가장중요한 포인트 : 트럭들은 항상 붙어서 지나감 (트럭 사이의 공간이 없음)

    int[] queue; // 배열
    int maxLocation; // 다리 길이
    int location; // 가장 앞선 트럭의 위치
    int maxWeight; // 최대 무게
    int totalWeight; // 현재 무게
    int next; // 다음 삽입 위치

    int[] truckWeights;
    int totalOfTrucks;
    int numberOfTrucks;

    int totalCountTrucks;

    public BridgeQueue(int bridgeLength, int maxWeight, int[] truckWeights) {
        this.queue = new int[bridgeLength];
        this.maxLocation = bridgeLength;
        this.maxWeight = maxWeight;
        this.location = 0;
        this.totalWeight = 0;
        this.next = 0;

        this.truckWeights = truckWeights;
        this.totalOfTrucks = this.truckWeights.length;
        this.numberOfTrucks = totalOfTrucks;
        this.totalCountTrucks = totalOfTrucks;
    }

    public int execute() {
        int second = 0;
        while(totalCountTrucks > 0) {
            if(numberOfTrucks > 0) {
                if(add(truckWeights[totalOfTrucks-numberOfTrucks])) {
                    numberOfTrucks--;
                }
            } else {
                run();
            }
            second++;
        }
        return second;
    }

    public void run() {
        if(location == maxLocation) {
            remove();
            totalCountTrucks--;
        }
        if(location < maxLocation) {
            location++;
        }
    }

    // 삽입(배열의 맨뒤)
    public boolean add(int truck) {

        if(location == maxLocation) { // 가장 앞선 트럭의 위치가 마지막 위치일 경우 해당 트럭 remove
            remove();
            totalCountTrucks--;
        }

        if((totalWeight+truck) > maxWeight) { // 현재 무게 + 들어올 트럭의 무게가 최대 무게 이하일때 add
            if(location < maxLocation) {
                location++;
            }
            return false;
        }

        this.queue[next] = truck;
        totalWeight += truck; // 무게 추가
        next++;
        location++;
        return true;
    }

    // 삭제(배열의 맨앞)
    public void remove() {
        totalWeight -= this.queue[0];
        for(int i=1; i<next; i++) {
            this.queue[i-1] = this.queue[i];
        }
        next--;
        if(next == 0) { //remove 후 next 가 0일 경우 location 0으로 초기화
            location = 0;
        }
    }

}