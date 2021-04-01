package queue;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class 프린터_이진호 {
    public int solution(int[] priorities, int location) {


       
        TargetOrder targetOrder= new TargetOrder(priorities);
        Queue<Integer> printList = new LinkedList<>();
        Arrays.stream(priorities).forEach(p->printList.offer(p));
        int count =0;

        while(true)
        {
            int printLength = printList.size();
            int target = printList.peek();
            boolean isOrder = targetOrder.isTargetOrder(target);
            if(isOrder)
            {
                location--;
                count++;
                printList.poll();
                targetOrder.upDataTargetOrder(printList);
                if(location <= 0) break;
            }
            else
            {
                printList.offer(printList.poll());
                location--;
                if(location <= -1) location= printLength;
            }


        }

        return count;
    }


}
class TargetOrder
{
    int[] printList;
    public TargetOrder(int[] _printList)
    {
        this.printList= _printList;
    }
    //큐에서 데이터가 나갔으면 이곳의 데이터도 갱신시킨다.
    public void upDataTargetOrder(Queue<Integer> queue)
    {
        int queueLength = queue.size();
        int[] _printList= new int[queueLength];
        for(int i =0 ; i<queueLength ; i++) {
            int tmp = queue.poll();
            _printList[i] = tmp;
            queue.offer(tmp);

        }
        this.printList= _printList;
    }
    //지금 대상보다 큰 값이 존재하는지 검사
    public boolean isTargetOrder(int target)
    {
        for(int index = 0 ; index<printList.length ; index++)
        {
            if(printList[index]>target)
            {
                return false;
            }
        }

        return true;
    }

}
