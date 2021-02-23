package queue;

public class 다리를지나는트럭_권영찬 {
}

class BridgeQueue {

    int[] truckWeights, queue, sequence;
    int totalOfTrucks, maxLocation, location, maxWeight
        ,totalWeight, next, idxOfVeryFrontTruck, time;

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
            else moveForward();
        }
        return time;
    }
    public void add(int truck) {
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
    private void moveForward() {
        if (location == maxLocation) remove();
        if (location < maxLocation) location++;
        time++;
    }
}