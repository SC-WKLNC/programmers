package Lv2.멀쩡한_사각형;

public class YC {

    // 문제 접근 방식

    // 선이 그어진 접점의 갯수를 구한다.

    public static void main(String[] args) {
        long solution1 = new YC().solution(9, 6);
        System.out.println(solution1);
    }

    public long solution(final int w, final int h) {
        final int count = getCountUnavailableArea(w, h);
        return ((long) w * h) - count;
    }

    private int getCountUnavailableArea(int w, int h) {
        if(Math.min(w, h) == 1) // 최소 길이 1
            return w * h;
        final double inclination = (double) h / w;
        if(inclination < 1)
            return calcByInclination(w, h); // 기울기 1 미만
         else if(inclination > 1)
            return calcByInclination(h, w); // 기울기 1 초과
         else
            return w; // 기울기 1 (정사각형)
    }

    private int calcByInclination(int longSide, int shortSide) {
        final int plus = longSide + shortSide; // 가로, 세로 격자 접점 총 합
        int gcd = gcd(longSide, shortSide);
        if(gcd > 1)
            return plus - ( shortSide /( shortSide/gcd ) ); // 격자 모서리를 지나는 경우 지나가는 횟수 만큼 감소
        return plus - 1; // 마지막 중복 횟수 -1
    }

    public int gcd(int a, int b) { // 최대 공약수
        while(b!=0) {
            int r = a%b;
            a=b;
            b=r;
        }
        return a;
    }

}
