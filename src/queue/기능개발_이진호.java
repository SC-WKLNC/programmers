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
        int[] answer = {};

        int progressesLength = progresses.length;
        Queue<Integer> developProcess = new LinkedList<>(); //기능들
        Queue<Integer> speedsProcess = new LinkedList<>(); //기능 시간

        Arrays.stream(progresses).forEach(p ->developProcess.offer(p));
        Arrays.stream(speeds).forEach(p ->speedsProcess.offer(p));

        Queue<Integer> successProcess = new LinkedList<>(); //완료 기능
        int header = developProcess.peek();

        int[] datas = new int[progressesLength];

        int head_count =1000;
        int index =-1;
        do {
            int count =1;
            int develop = developProcess.poll();
            int speed = speedsProcess.poll();
            while(develop <100)
            {
                develop+=speed;
                count++;
            }

            if(head_count > count)//앞의 작업보다 카운팅이 작다면 같이 완성된다.
            {
                index--;
            }
            else
            {
                head_count = count; //해드 변경
            }
            datas[index]=count;
            index++;

        }while ( ! developProcess.isEmpty());

        return answer;
    }
}

//7 3 9
