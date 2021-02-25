package queue;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class 기능개발_이진호 {

    //1.각 기능은 각자의 속도가있다.
    //2 하나의 기능이 100이되어야 배포를 할수있다.
    //3 앞의 기능이 배포가안되면 뒤의기능은 100이여도 배포할수없다.
    //4 3의경우 앞의기능이 100이되면 같이 배포된다
    // 이때 몇번의 배포를 통해 모두 완료가되는지를 알아내야한다.
    public int[] solution(int[] progresses, int[] speeds) {


        Queue<Integer> developProcess = new LinkedList<>(); //기능들
        Queue<Integer> speedsProcess = new LinkedList<>(); //기능 시간

        Arrays.stream(progresses).forEach(p ->developProcess.offer(p));
        Arrays.stream(speeds).forEach(p ->speedsProcess.offer(p));

        Queue<Integer> successProcess = new LinkedList<>(); //완료 기능



        while( ! developProcess.isEmpty())
        {
            if(developProcess.peek() >=100)
            {
                int count =0;
                do
                {
                    count++;
                    //작업이 100이라면 큐에서 뽑아낸다.
                    developProcess.poll();
                    speedsProcess.poll();

                }while ((! developProcess.isEmpty()) && developProcess.peek() >=100);
                successProcess.offer(count); //작업 완료된 개수
            }
            else
            {
                int size = developProcess.size();
                for(int index = 0 ; index<size;index++)
                {
                    int develop = developProcess.poll();
                    int speed = speedsProcess.poll();
                    develop +=speed;
                    developProcess.offer(develop);
                    speedsProcess.offer(speed);
                }
            }
        }
        int size = successProcess.size();
        int[] answer = new int[size];
        for(int index = 0 ; index<size;index++)
        {
            answer[index] = successProcess.poll();
        }

        return answer;
    }

}


