package Lv2.타겟_넘버;

import java.util.ArrayList;
import java.util.List;

/*
연산자 +,- 에 대해 numbers 만큼 dfs 로 모두 얻는다.
연산자를 모두 얻었다면 해당 연산자를 순회하면서 numbers 를 계산하여 target 와 같은지 확인한다.

얻은점
dfs 에서 파리미터를 변경하여 다시 재귀돌리면 큰일난다.
operators += this.operatorList.get(i); //버그
String newOperators = operators + this.operatorList.get(i); //정상
 */
public class JH {
    List<String> operatorList = List.of("+","-");
    int result = 0;
    public static void main(String[] args) {
        int[] numbers = new int[] {4, 1, 2, 1};
        int target = 4;
        JH jh = new JH();
        jh.solution(numbers,target);


    }
    public int solution(int[] numbers, int target) {
        dfs("",numbers,target);
        return result;
    }
    private void dfs(String operators, int[] numbers, int target){
        if(operators.length() == numbers.length){
            checkOperators(operators, numbers, target);
            return;
        }
        for(int i = 0; i < this.operatorList.size(); i ++){
            String newOperators = operators + this.operatorList.get(i);
            dfs(newOperators,numbers,target);
        }
    }
    private void checkOperators(String operators, int[] numbers, int target){
        int sum = 0;
        for(int i =0; i<operators.length(); i++){
            int number = numbers[i];
            if(operators.charAt(i) == '+') sum += number;
            else sum-=number;
        }
        if(sum == target) this.result++;
    }

}
