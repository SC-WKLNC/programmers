package queue;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class 주식가격_이진호Test {

    @Test
    public void solution() {
        주식가격_이진호 Solution = new 주식가격_이진호();
        int[] result = Solution.solution(new int[]{1,2,3,2,3});
        int[] expect = new int[]{4,3,1,1,0};
        Assertions.assertArrayEquals(expect,result);
    }
}