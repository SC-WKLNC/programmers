package queue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class 기능개발_권영찬_Test {

    @ParameterizedTest(name = "{index}: {0}")
    @DisplayName("User 생성 유효성 검사")
    @MethodSource("provideArguments")
    public void test(String msg, int[] progresses, int[] speeds, int[] result) {
        assertArrayEquals(result, 기능개발_권영찬.solution(progresses, speeds));
    }

    public static Stream<Arguments> provideArguments() throws Throwable {
        return Stream.of(
                Arguments.of(
                        "테스트 케이스1",
                        new int[]{93, 30, 55},
                        new int[]{1, 30, 5},
                        new int[]{2, 1}),
                Arguments.of(
                        "테스트 케이스2",
                        new int[]{95, 90, 99, 99, 80, 99},
                        new int[]{1, 1, 1, 1, 1, 1},
                        new int[]{1, 3, 2}),
                Arguments.of(
                        "테스트 케이스3",
                        new int[]{1},
                        new int[]{99},
                        new int[]{1}),
                Arguments.of(
                        "테스트 케이스4",
                        new int[]{99},
                        new int[]{1},
                        new int[]{1}),
                Arguments.of(
                        "테스트 케이스5",
                        new int[]{1},
                        new int[]{1},
                        new int[]{1})
        );
    }

}
