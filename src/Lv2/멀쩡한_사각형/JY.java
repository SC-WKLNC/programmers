package Lv2.멀쩡한_사각형;

import java.math.BigInteger;

public class JY {
    // 직사각형 종이가 있음
    // 정사각형으로 잘라사용예정
    // 누군가가 대각선으로 자름
    // 그래서 같은 크기의 직각삼각형 2개로 나뉨
    // 1cm 1cm으로 잘라 사용할수있는 만큼만 사용
    // 사용할수 있는 정사각형의 갯수


    // 객체
    // 1. paper
    //      필드 : 종이의 크기 , 총블럭의 값
    //      함수 : 총블럭의 값 리턴
    // 2. WhiteBlockSearcher
    //      필드 : 종이 , gcd
    //      함수 : Gcd값 계산 , 총 흰색 블럭값 리턴

    public static void main(String[] ags) {
        Solution s = new Solution();
        s.solution(50,25);
    }


    public static class Solution {
        public long solution(int w, int h) {
            //종이 생성
            Paper paper = new Paper(w,h);
            //검색기 생성
            WhiteBlockSearcher whiteBlockSearcher = new WhiteBlockSearcher(paper);

            long answer = paper.getTotalBlock() - whiteBlockSearcher.getWhiteBlock();
            return answer;
        }


        //종이
        public class Paper{
            private final int width;
            private final int height;

            public Paper(int width,int height) {
                this.width = width;
                this.height = height;
            }
            public long getTotalBlock() {return (long) width * height;}
        }

        public class WhiteBlockSearcher {
            private final Paper paper;
            private final int gcd;

            public WhiteBlockSearcher(Paper paper) {
                this.paper = paper;
                this.gcd = calcGCD(paper.width,paper.height);
            }
            // gcd 계산 알고리즘
            private int calcGCD(int posX,int posY){
                if(posY ==0) return posX;
                return calcGCD(posY, posX%posY );
            }

            // 흰블럭은 가로에 걸친만큼 생성되고 세로에 걸친만큼 생성된다.
            // 따라 중첩이되는 꼭지점엔 하나만 생성되니 중첩이되는 꼭지점만큼 빼줘야한다 (중복이기 때문)
            // 예로 2 , 3 일때 가로만판단하면 첫번째블럭 , 3번째블럭 , 마지막꼭지점
            //               세로만판단하면 두번째블럭 , 마지막꼭지점
            // 이렇게 통과할때마다 블럭이 흰블럭으로바뀌기 때문에 마지막꼭지점 (gcd)는 중복이되기때문에 하나를 빼주는것이다.
            // 그렇기 때문에 식은 width + height - gcd
            public int getWhiteBlock() {return paper.width+paper.height-gcd;}
        }
    }
}
