package queue;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class 기능개발_이진호Test {

    기능개발_이진호 solution = new 기능개발_이진호();
    @Test
    public void solution() {

        int [] progresses = new int[]{93,30,55};
        int [] speeds = new int[] {1,30,5};
        int [] expected = new int[]{2,1};
        int[] result = solution.solution(progresses,speeds);
        assertArrayEquals(expected,result);
    }

    @Test
    public void solution1() {
        int [] progresses = new int[]{95, 90, 99, 99, 80, 99};
        int [] speeds = new int[] {1,1,1,1,1,1};
        int [] expected = new int[]{1,3,2};
        int[] result = solution.solution(progresses,speeds);
        assertArrayEquals(expected,result);
    }
}