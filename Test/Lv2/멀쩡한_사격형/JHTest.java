package Lv2.멀쩡한_사격형;


import Lv2.멀쩡한_사각형.JH;
import org.junit.Assert;
import org.junit.Test;

public class JHTest {

    //8 12 = 80
    //2 3 = 2
    //3 4 = 6
    //2 6 = 6
    //2 7 = 6
    //2 5 = 4
    //3 6 = 12
    JH jh = new JH();

    @Test
    public void 직사각형_1() {
        int w = 8;
        int h = 12;
        long solution = jh.solution(w, h);
        long result = 80;
        Assert.assertEquals(result,solution);
    }
    @Test
    public void 직사각형_2() {
        int w = 2;
        int h = 3;
        long solution = jh.solution(w, h);
        long result = 2;
        Assert.assertEquals(result,solution);
    }
    @Test
    public void 직사각형_3() {
        int w = 3;
        int h = 2;
        long solution = jh.solution(w, h);
        long result = 2;
        Assert.assertEquals(result,solution);
    }
    @Test
    public void 직사각형_4() {
        int w = 12;
        int h = 8;
        long solution = jh.solution(w, h);
        long result = 80;
        Assert.assertEquals(result,solution);
    }
    @Test
    public void 직사각형_5() {
        int w = 4;
        int h = 3;
        long solution = jh.solution(w, h);
        long result = 6;
        Assert.assertEquals(result,solution);
    }
    @Test
    public void 직사각형_6() {
        int w = 2;
        int h = 6;
        long solution = jh.solution(w, h);
        long result = 6;
        Assert.assertEquals(result,solution);
    }
    @Test
    public void 직사각형_7() {
        int w = 2;
        int h = 7;
        long solution = jh.solution(w, h);
        long result = 6;
        Assert.assertEquals(result,solution);
    }
    @Test
    public void 직사각형_8() {
        int w = 2;
        int h = 8;
        long solution = jh.solution(w, h);
        long result = 8;
        Assert.assertEquals(result,solution);
    }
    @Test
    public void 직사각형_9() {
        int w = 2;
        int h = 5;
        long solution = jh.solution(w, h);
        long result = 4;
        Assert.assertEquals(result,solution);
    }
    @Test
    public void 직사각형_11() {
        int w = 3;
        int h = 6;
        long solution = jh.solution(w, h);
        long result = 12;
        Assert.assertEquals(result,solution);
    }

}