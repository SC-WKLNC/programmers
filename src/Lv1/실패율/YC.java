package Lv1.실패율;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class YC {

    // 문제 접근 방식

    // 실패율이 높은순에서 낮은 순으로 결과 리턴

    // 스테이지의 길이는 1 이상 500 이하
    // 사용자는 1 이상 200000 이하

    // 사용자 수에 대해 효율적으로 알고리즘을 구현해야함

    // 객체 모델링
    // 1. 게임
    // 필드 : 스테이지들
    // 함수 : 스테이지 생성, 유저 정보 반영, 스테이지별 실패율 획득 및 정렬
    // 2. 스테이지
    // 필드 : 현재 도달해 있는 사용자 수, 도전한 사용자 수
    // 함수 : 사용자 정보 추가, 현재 스테이지를 도전한 유저인지 판별, 현재 스테이지를 클리어 하지 못판 유저인지 판별,
    // 3. 사용자

    public int[] solution(final int N, final int[] stages) {
        return new Game(N)
            .reflect(stages)
            .getFailureRates();
    }

    public static class Game { // 게임 객체

        private final Stages stages; // 스테이지 객체 일급 컬렉션
        public Game(final int n) {
            this.stages = new Stages(
                IntStream.range(0, n)
                    .mapToObj(Stage::new)
                    .collect(Collectors.toList())
            );
        }

        public Game reflect(final int[] users) { // 각 스테이지의 유저 플레이 정보 반영
            Arrays.stream(users)
                .forEach(stages::plusCountEachStages);
            return this;
        }

        public int[] getFailureRates() {
            return stages.getFailureRates();
        }

    }

    public static class Stages { // 스테이지 객체 일급 컬렉션

        private final List<Stage> stages;
        private final int lastStage;

        public Stages(final List<Stage> stages) {
            this.stages = stages;
            this.lastStage = stages.size();
        }

        public Stage getStage(final int stageLevel) {
            return stages.get(stageLevel);
        }

        public void plusCountEachStages(final int stageLevel) {
            IntStream.range(0, getMaxStageLevel(stageLevel)) // 스테이지 레벨 별 루프
                .forEach(level -> getStage(level).reflectCount(stageLevel));
        }

        private int getMaxStageLevel(final int stageLevel) { // 전부 클리어 했을 경우 마지막 스테이지 레벨 리턴
            return isNotAllClear(stageLevel) ? stageLevel : stageLevel - 1;
        }

        private boolean isNotAllClear(final int stageLevel) {
            return !isAllClear(stageLevel);
        }

        private boolean isAllClear(final int stageLevel) {
            return stageLevel > lastStage;
        }

        public int[] getFailureRates() { // 실패율 내림차순으로 스테이지 레벨 리턴
            return stages.stream()
                .sorted(Comparator.comparingDouble(Stage::getFailureRate).reversed())
                .mapToInt(Stage::getLevel)
                .toArray();
        }

    }

    public static class Stage { // 스테이지 객체

        private final int level; // 스테이지 레벨
        private int nowReachedCount = 0; // 현재 스테이지 유저 수
        private int challengeCount = 0; // 도전한 유저 수

        public Stage(final int level) {
            this.level = level+1;
        }

        public void reflectCount(final int stageLevel) {
            challengeCount++; // 도전한 사용자 수 증가
            if(stageLevel == level) // 현재 스테이지에 머물러 있는 경우
                nowReachedCount++; // 현재 스테이지에 도달한 사용자 수 증가
        }

        public double getFailureRate() { // 실패율 계산 ( 머물러 있는 사용자 수 / 도전한 사용자 수 )
            if(challengeCount == 0)
                return 0;
            return (double) nowReachedCount / challengeCount;
        }

        public int getLevel() {
            return level;
        }

    }

}