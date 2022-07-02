package Lv1.비밀지도;

public class YC {

    // 두 배열을 or 연산하여 1을 "#"로 치환 0을 " "로 치환
    public String[] solution(final int n, final int[] arr1, final int[] arr2) {
        final String[] answer = new String[n];
        for (int i = 0; i < n; i++) {
            answer[i] = String.format("%0"+n+"d", Long.parseLong(Long.toBinaryString(arr1[i] | arr2[i])))
                    .replace('1', '#')
                    .replace('0', ' ');
        }
        return answer;
    }

}
