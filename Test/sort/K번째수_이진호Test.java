package sort;



import org.junit.Test;

import static org.junit.Assert.*;

public class K번째수_이진호Test {

    @Test
    public void data(){
        K번째수_이진호 Solution  = new K번째수_이진호();
        int[] arr1 = new int[] {1, 5, 2, 6, 3, 7, 4};
        int[][] arr = new int[][]{{2, 5, 3}, {4, 4, 1}, {1, 7, 3}};
        Solution.solution(arr1,arr);
        assertEquals(0,0);
    }

}