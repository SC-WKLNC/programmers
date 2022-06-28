package Lv2.타겟_넘버;

public class YC {

    public int solution(int[] numbers, int target) {
        return dfs(numbers, 0, 0, target);
    }

    public int dfs(int[] numbers, int idx, int sum, int target) {

        if(idx == numbers.length) {
            if(sum == target) {
                return 1;
            } else {
                return 0;
            }
        } else {
            return dfs(numbers, idx + 1, sum + numbers[idx], target)
                    + dfs(numbers, idx + 1, sum - numbers[idx], target);
        }
    }

}
