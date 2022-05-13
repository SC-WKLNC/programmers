package Lv1.폰켓몬;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * 모델링
 * 폰켓몬 박스 -> 포켓몬 들을 넣으면 원하는 마리수 만큼 중복되지 않게 출력해준다.
 */
public class JH {
    public static void main(String[] args) {
        int[] nums = new int[]{3,3,3,2,2,2};
        JH jh = new JH();
        jh.solution(nums);
    }
    public int solution(int[] nums) {

        PhoneKeMonBox phoneKeMonBox = new PhoneKeMonBox(nums);
        int phoneKeMonSize = phoneKeMonBox.getPhoneKeMonSize();
        return phoneKeMonSize;
    }
    class PhoneKeMonBox{

        int choiceCount = 0;
        Set<Integer> phoneKeMons = new HashSet<>();
        PhoneKeMonBox(int[] nums){
            choiceCount = nums.length/2;

            Arrays.stream(nums).forEach(item -> {
                phoneKeMons.add(item);
            });

        }
        public int getPhoneKeMonSize(){
            return phoneKeMons.size() >= choiceCount ? choiceCount : phoneKeMons.size();
        }
    }
}

