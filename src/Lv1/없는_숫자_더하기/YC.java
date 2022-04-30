package Lv1.없는_숫자_더하기;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class YC {

    public int solution(final int[] numbers) {
        final List<Integer> nums = List.of(0, 1, 2, 3, 4, 5, 6, 7, 8, 9);
        final Predicate<Integer> isNotContain = x -> Arrays.stream(numbers).noneMatch(n -> n == x);
        return nums.stream()
            .filter(isNotContain)
            .mapToInt(Integer::intValue)
            .sum();
    }

}
