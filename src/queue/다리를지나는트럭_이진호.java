package queue;

import javax.management.Query;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class 다리를지나는트럭_이진호 {


    public int solution(int bridge_length, int weight, int[] truck_weights)
    {
        int totalTruckCount = truck_weights.length;
        Queue<Integer> readyTruck = new LinkedList<>(); //대기 중인 트럭
        Queue<Integer> truckRoad = new LinkedList<>();  // 도로

        //트럭 대기 시킨다.
        Arrays.stream(truck_weights).forEach(p ->readyTruck.offer(p));
        for (int i = 0; i <bridge_length ; i++) {
            truckRoad.offer(0);
        }

        int totalWeight =0; // 현제 다리위에있는 트럭의 총 무게
        int totalTime =1 ; //전체 트럭 경과시간
        int time =0; //하나의 트럭의 경과시간
        boolean isEnd = false;
        int frontTruck = 0; //출발전 자신 이전 트럭과의 거리


        while(true) //대기중인 트럭이 없을때까지
        {
            if(readyTruck.isEmpty()) break;
            System.out.println("다음 대기 차 "+readyTruck.peek() +" 총 무게 "+(totalWeight+readyTruck.peek()));
            if((totalWeight+readyTruck.peek()) <= weight) //다리를 건널수있는 상황이라면
            {
                System.out.println("출발");

                if(frontTruck>=1) { //앞에 차가 있다면 그 거리만큼만 가면된다.
                    if(frontTruck>2)frontTruck--;
                    totalTime+=frontTruck ;

                }
                else  //트럭이 출발 하면 결국 도로길이만큼 시간이 걸린다.
                {

                    totalTime+=bridge_length;

                }

                //대기중인 트럭 도로로 출발
                int targetTruck = readyTruck.poll();
                System.out.println("도착 시간  "+totalTime+"\n");
                truckRoad.offer(targetTruck);//트럭 출발


                totalWeight+=targetTruck; //도로 무게 증가
                if(readyTruck.isEmpty()) {
                    time = (bridge_length); //마지막 트럭이 였다면
                    isEnd = true; // 종료
                }

                frontTruck=1; //거리 초기화
            }
            else
            {
                truckRoad.offer(0);
                System.out.println("거리증가");
                frontTruck++;//앞의 차와의 거리 증가
            }


            if(isEnd) break;
            //도로의 도착지점 부분을 빼낸다.
            int targetTruck = truckRoad.poll();
            totalWeight-=targetTruck; //도로 무게 감소

            if(!truckRoad.isEmpty() && truckRoad.peek()>0)
            {
                time =bridge_length-1;
            }




        }
        return totalTime;
    }

    public int solution2(int bridge_length, int weight, int[] truck_weights) {

        int time =0;
        int totalTruckWeight=0;
        int truckLength = truck_weights.length;
        for(int i =0 ; i < truckLength ; i++){
            totalTruckWeight=0;

            int truckCount=1;
            while(true)
            {

                int truck = truck_weights[i];
                totalTruckWeight+=truck;
                //총 무게가 다리가 버틸수있는 중량이 넘어가는경우
                if(totalTruckWeight>=weight)
                {
                    //무게를 넘었을경우 트럭을 돌려보낸다
                    if(totalTruckWeight>weight)
                    {
                        i--;
                        truckCount--;
                    }

                    time+=bridge_length;
                    if(truckCount>1){
                        time+=(truckCount-1);
                    }
                    break;
                }
                else if(truckLength<=(i+1)){
                    truckCount--;
                    time+=bridge_length;
                    if(truckCount>1){
                        time+=(truckCount-1);
                    }
                    break;
                }

                truckCount++;
                i++;
                if(truckLength<=i)break;
            }
        }

        return time+1;
    }


}




