package queue;

public class 다리를지나는트럭_권영찬 {
}

class BridgeQueue {

    int[] truckWeights;
    int[] queue;
    int[] sequence;
    int totalOfTrucks;
    int maxLocation;
    int location;
    int maxWeight;
    int totalWeight;
    int next;
    int idxOfVeryFrontTruck;
    int time;

    public BridgeQueue(int[] truckWeights, int bridgeLength, int maxWeight) {
        this.truckWeights = truckWeights;
        this.queue = new int[truckWeights.length];
        this.sequence = new int[truckWeights.length];
        this.maxLocation = bridgeLength;
        this.maxWeight = maxWeight;
        this.totalOfTrucks = truckWeights.length;
    }
    public int execute() {
        while(idxOfVeryFrontTruck < totalOfTrucks) {
            if(next < totalOfTrucks) add(truckWeights[next]);
            else moveOfRemainTrucks();
        }
        return time;
    }
    public void moveOfRemainTrucks() {
        minusPassingTruck();
        moveForward();
    }
    public void add(int truck) {
        minusPassingTruck();
        moveForward();
        if((totalWeight+truck) > maxWeight) return;
        queue[next] = truck;
        sequence[next++] = time;
        totalWeight += truck;
    }
    public void remove() {
        if((totalWeight -= queue[idxOfVeryFrontTruck++]) == 0) location = 0;
        else location += sequence[idxOfVeryFrontTruck-1] - sequence[idxOfVeryFrontTruck];
    }
    private void minusPassingTruck() {
        if (location == maxLocation) remove();
    }
    private void moveForward() {
        if (location < maxLocation) location++;
        time++;
    }
}