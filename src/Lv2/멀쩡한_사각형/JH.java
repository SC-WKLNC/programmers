package Lv2.멀쩡한_사각형;


/**
 *
 * 패턴 1 선을 자르게 되면 세로 라인마다 2칸씩 사용할수 없게된다.
 * 패턴 2 선을 자르게 되면 가로 라인에 사용수가 1칸 2칸, 1칸,2칸 씩 사용할수 없게된다
 *   - 가로 홀, 세로짝 일때 시작개수 2번 반복 시작
 *   - 가로 짝, 세로 짝 일때 시작 개수 1번 반복 시작
 *   - 가로 짝  세로 홀
 * 패턴2가 정확한 방법 같은 느낌이 들지만 패턴 1이 더 편해보이니 패턴 1로 진행 -> 안됨
 *
 * 직사각형의 경우 세로가 더 큰 방향으로 고정한다.
 *
 */

public class JH {
    public static void main(String[] args) {
        int w = 8;
        int h = 12; //80

        JH jh = new JH();
        jh.solution(w,h);
    }
    public long solution(int w, int h) {

        long answer = 0;

        for(int i=0; i<w; i++) {
            int i1 = h * i / w;
            System.out.println("i1 = " + i1);
            answer += i1;

        }
        System.out.println("answer = " + answer);
        return answer;
    }


    public long notSquare(int w, int h){
        //직사각형의 방향을 세로가 길게 변경한다.
        if(h < w){
            int temp = w;
            w = h;
            h = temp;
        }
        if(w == 2){
            if(h %2 ==0) return h;
            return h-1;
        }
        return enableBoxSize(w);
    }

    public long enableBoxSize(int w){
        int boxW = w;
        int result = 0;
        boolean flag = false;
        while (boxW > 0){
            boxW = boxW-1;
            result += boxW;
            if(boxW == 1) break;

            if(flag) {
                result += boxW;
            }
            flag = !flag;

        }

        return result*2;
    }






    public void pattern1(int w, int h){
        int boxCount = w*h;
        int disableCount = w*2;
        System.out.println("boxCount = " + (boxCount-disableCount));
    }
}
