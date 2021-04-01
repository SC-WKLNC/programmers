package queue

import java.util.*
fun main()
{
    val priorities = intArrayOf(1,2,2,4,5,6)
    val location =3
    val Solution =  프린터_이진호kt()
    Solution.solution(priorities,location)
}

class 프린터_이진호kt {
    fun solution(priorities: IntArray, location: Int): Int {
        var printQueue = LinkedList<Int>().apply {
            priorities.forEach { add(it)}
        }
        //출력물 카운트
        var printCount =0;

        //큐가 윰직일때마다 그 위치를 잡아주는 포지션이있어야한다
        var targetLocation = location

        while(printQueue.peek() != null)
        {
            val highPriority:Int = GetHighPriority(printQueue)
            val queueSize :Int = printQueue.size

            for(i in 0..queueSize)
            {
                val target:Int = printQueue.poll()
                targetLocation = moveTargetLocation(targetLocation ,queueSize-1 )

                if(target != highPriority)
                {
                    printQueue.add(target)

                    continue
                }

                printCount++
               // targetLocation = moveTargetLocation(targetLocation ,queueSize-1 )

                break

            }
            if(targetLocation==queueSize-1)
            {
                return printCount
            }
        }

        return 0
    }

    fun moveTargetLocation(targetLocation:Int , resetValue:Int):Int
    {
        var location = targetLocation-1
        if(location == -1){
            location = resetValue
        }
        return location
    }

    fun GetHighPriority(printQueue:LinkedList<Int> ) :Int{
        var maxData:Int = 0
        for(i in printQueue){
            if(maxData<i) maxData=i
        }


        return maxData
    }
}


class Solution {
    fun solution(priorities: IntArray, location: Int): Int {

        var waitList = priorities.indices.toList()
        var processed: List<Int> = listOf()
        while (waitList.isNotEmpty()) {
            val first = waitList.get(0)!!
            val l = processed.takeLastWhile {i -> priorities[i] < priorities[first] }
            processed = processed.dropLast(l.size) + first
            waitList = waitList.drop(1) + l
        }
        return processed.indexOf(location) + 1
    }
}