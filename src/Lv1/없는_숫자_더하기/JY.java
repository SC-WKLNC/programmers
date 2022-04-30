package Lv1.없는_숫자_더하기;
import java.util.Arrays;
public class JY {
    class Solution {
        private final int MAX_ADDNUMBER = 45;
        public int solution(int[] numbers) {
            return MAX_ADDNUMBER- Arrays.stream(numbers).sum();
        }
    }
}
