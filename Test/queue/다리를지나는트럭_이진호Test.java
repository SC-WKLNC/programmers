package queue;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class 다리를지나는트럭_이진호Test {

    @Test
    public void solution() {

        다리를지나는트럭_이진호 truck = new 다리를지나는트럭_이진호();
                  /*int bridge_length = 2;
        int weight = 10;
        int[] truck_weights = new int[]{7,4,5,6};*/
        int bridge_length = 100;
        int weight = 100;
        int[] truck_weights = new int[]{10,10,10,10,10,10,10,10,10,10};
        //solution(bridge_length,weight,truck_weights);

       /* truck.solution(4, 2, new int[] {1,1,1,1}); //10
        truck.solution(3, 1, new int[] {1,1,1}); //10
        truck.solution(3, 1, new int[] {1,1,1,1,1}); //16
        truck.solution(5, 5, new int[] {1,1,1,1,1,2,2}); //14
        truck.solution(7, 7, new int[] {1,1,1,1,1,3,3}); //18
        truck.solution(5, 5, new int[] {1,1,1,1,1,2,2,2,2}); //19
        truck.solution(5, 5, new int[] {2,2,2,2,1,1,1,1,1}); //19*/

        int result = truck.solution(2, 10, new int[] {7,4,5,6}); //4
        Assertions.assertEquals(8,result);







    }
    @Test
    public void truck13(){
        다리를지나는트럭_이진호 truck = new 다리를지나는트럭_이진호();
        int result = truck.solution(5, 5, new int[] {1,1,1,1,1,2,2,2,2}); //19
        Assertions.assertEquals(19,result);
    }
    @Test
    public void truck12(){
        다리를지나는트럭_이진호 truck = new 다리를지나는트럭_이진호();
        int result =truck.solution(7, 7, new int[] {1,1,1,1,1,3,3}); //18
        Assertions.assertEquals(18,result);
    }
    @Test
    public void truck11(){
        다리를지나는트럭_이진호 truck = new 다리를지나는트럭_이진호();
        int result = truck.solution(5, 5, new int[] {1,1,1,1,1,2,2}); //14
        Assertions.assertEquals(14,result);
    }
    @Test
    public void truck10(){
        다리를지나는트럭_이진호 truck = new 다리를지나는트럭_이진호();
        int result =     truck.solution(3, 1, new int[] {1,1,1,1,1}); //16
        Assertions.assertEquals(16,result);
    }
    @Test
    public void truck9(){
        다리를지나는트럭_이진호 truck = new 다리를지나는트럭_이진호();
        int result =  truck.solution(3, 1, new int[] {1,1,1}); //
        Assertions.assertEquals(10,result);
    }
    @Test
    public void truck8(){
        다리를지나는트럭_이진호 truck = new 다리를지나는트럭_이진호();
        int result = truck.solution(4, 2, new int[] {1,1,1,1}); //4
        Assertions.assertEquals(10,result);
    }
    @Test
    public void truck1(){
        다리를지나는트럭_이진호 truck = new 다리를지나는트럭_이진호();
        int result = truck.solution(100, 100, new int[]{10,10,10,10,10,10,10,10,10,10}); //4
        Assertions.assertEquals(110,result);
    }
    @Test
    public void truck2(){
        다리를지나는트럭_이진호 truck = new 다리를지나는트럭_이진호();
        int result = truck.solution(1, 2, new int[] {1,1,1}); //4
        Assertions.assertEquals(4,result);
    }
    @Test
    public void truck3(){
        다리를지나는트럭_이진호 truck = new 다리를지나는트럭_이진호();
        int result = truck.solution(5, 5, new int[] {2,2,2,2,1,1,1,1,1});
        Assertions.assertEquals(19,result);
    }
    @Test
    public void truck4(){
        다리를지나는트럭_이진호 truck = new 다리를지나는트럭_이진호();
        int result =   truck.solution(5, 5, new int[] {1,1,1,1,1,2,2});
        Assertions.assertEquals(14,result);
    }
    @Test
    public void truck5(){
        다리를지나는트럭_이진호 truck = new 다리를지나는트럭_이진호();
        int result =   truck.solution(2, 10, new int[] {7,4,5,6});
        Assertions.assertEquals(8,result);
    }
    @Test
    public void truck6(){
        다리를지나는트럭_이진호 truck = new 다리를지나는트럭_이진호();
        int result =   truck.solution(100, 100, new int[] {10});
        Assertions.assertEquals(101,result);
    }

}