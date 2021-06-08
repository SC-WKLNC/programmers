package search.dfs_bfs;

public class 타겟넘버_권영찬 {

    static class Test {
        public static void main(String[] args) {
            int[] arr = {1, 1, 1, 1, 1};
            int target = 3;
            int expect = 5;
            int result = new 타겟넘버_권영찬().solution(arr, target);
            System.out.println(expect == result);
        }
    }

    public int solution(int[] numbers, int target) {

        int answer = 0;
        answer = dfs(numbers, 0, 0, target);
        return answer;
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
