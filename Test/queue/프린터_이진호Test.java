package queue;

import org.junit.Test;

import static org.junit.Assert.*;

public class 프린터_이진호Test {

    프린터_이진호 Solution = new 프린터_이진호();
    @Test
    public void solution() {
        int expected = 1;
        int result = Solution.solution(new int[]{2,1,3,2},2);
        assertEquals(expected,result);
    }
    @Test
    public void solution1() {
        int expected = 5;
        int result = Solution.solution(new int[]{1,1,9,1,1,1},0);
        assertEquals(expected,result);
    }
    @Test
    public void solution2() {
        int expected = 6;
        int result = Solution.solution(new int[]{2,2,2,1,3,4},3);
        assertEquals(expected,result);
    }
}