package Lv1.폰켓몬;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class YC {

    // 문제 접근 방식

    // 주어진 포켓몬 수의 절반을 뽑는데 가장 많은 종류의 포켓몬을 뽑을 수 있는 경우의 수

    public int solution(int[] nums) {
        return Arrays.stream(nums)
            .boxed()
            .collect(
                Collectors.collectingAndThen(
                    Collectors.toSet(),
                    set -> Integer.min(nums.length / 2, set.size())
                )
            );
    }

    public int solution2(int[] nums) {
        final Set<Integer> set = new HashSet<>();
        Arrays.stream(nums).forEach(set::add);
        return Math.min(nums.length / 2, set.size());
    }

}
