package queue;

import java.util.Arrays;

public class 기능개발_권영찬 {

    public static int[] solution(int[] progresses, int[] speeds) {
        int dpCnt = 0; // 배포 횟수 (0부터 시작)
        int dDay = 100; // 남은 일수
        double tmp; // 임시 저장 영역
        int[] fCnt = new int[progresses.length]; // 배포 기능 갯수 배열
        for(int i=0; i<progresses.length; i++) { // for 루프
            tmp = ((100 - progresses[i]) / (double)speeds[i]); // 남은 일수 계산
            if((tmp - (int)tmp) > 0) tmp++; // 0보다 작은 소수일 경우 +1
            if(dDay >= tmp) fCnt[dpCnt]++; // 우선순위가 높은 기능이 남은 일수가 더 많거나 같을 경우 배포 기능 갯수 +1
            else fCnt[++dpCnt]++; // 아닐경우 배포 횟수 +1,  배포 기능 갯수 +1
            dDay = (int)tmp; // 낮은 우선순위 남은 일수로 D-Day 초기화
        }
        return Arrays.copyOf(fCnt, dpCnt+1); // 배포 횟수만큼 배열 복사 결과 반환
    }

}
