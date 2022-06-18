package Lv1.다트_게임;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

public class YC {

    // start    2022/06/18 13:50
    // end      2022/06/18 14:59

    // 순차적으로 계산되는 스코어는 이전 스코어에 영향을 줄 수 있다.

    // 문자열을 자르는 알고리즘이 필요하다.

    public static void main(String[] args) {
        int solution = new YC().solution("1S*2T*3S");
        System.out.println(solution);
    }

    public int solution(final String dartResult) {
        return new Scores(dartResult)
            .getTotalScore();
    }

    public static class Scores {

        private final List<Score> scores = new ArrayList<>();
        private final String info;

        public Scores(final String info) {
            this.info = info;
            splitAndAdd();
            calcOption();
        }

        private void splitAndAdd() {
            // 이전 문자가 숫자가 아니고 이번 문자가 숫자일 때 자른다.
            char beforeChar = info.charAt(0);
            int beforeAt = 0;
            for (int i = 1; i <info.length(); i++) {
                if(!Character.isDigit(beforeChar) && Character.isDigit(info.charAt(i))) {
                    scores.add(
                        new Score(
                            info.substring(beforeAt, i)
                        )
                    );
                    beforeAt = i;
                }
                beforeChar = info.charAt(i);
            }
            scores.add(
                new Score(
                    info.substring(beforeAt)
                )
            );
        }

        private void calcOption() {
            // 옵션 적용
            IntStream
                .range(0, scores.size())
                .forEach(idx -> {
                    final Score score = scores.get(idx);
                    if(score.isHasOption()) {
                        if(score.getOption().equals("*")) {
                            if(idx > 0) {
                                Optional.ofNullable(scores.get(idx-1))
                                    .ifPresent(it -> it.applyMultiple(score.getOptionVal()));
                            }
                            score.applyMultiple(score.getOptionVal());
                        } else {
                            score.applyMultiple(score.getOptionVal());
                        }
                    }
                });
        }

        public int getTotalScore() {
            return scores.stream()
                .mapToInt(Score::getScore)
                .sum();
        }

    }

    public static class Score {

        private final String info;
        private int point;
        private int score;
        private String area;
        private String option = "NONE";
        private boolean isHasOption = false;

        public Score(final String info) {
            this.info = info;
            this.score = calcScore();
        }

        private int calcScore() {
            // 숫자일 때 까지 자르고
            // 영역 잘랐을 때 남아있다면 옵션 저장
            final StringBuilder sb = new StringBuilder();
            for (int i = 0; i <info.length(); i++) {
                char ch = info.charAt(i);
                if(Character.isDigit(ch))
                    sb.append(ch);
                else {
                    area = String.valueOf(ch);
                    if(i+1 != info.length()) {
                        option = String.valueOf(info.charAt(i+1));
                        isHasOption = true;
                    }
                    break;
                }
            }
            point = Integer.parseInt(sb.toString());
            return (int) Math.pow(point, getIncVal());
        }

        private int getIncVal() {
            switch (area) {
                case "S": return 1;
                case "D": return 2;
                case "T": return 3;
                default: throw new IllegalArgumentException(String.format("잘못된 입력값 : %s", area));
            }
        }

        public int getOptionVal() {
            switch (option) {
                case "*": return 2;
                case "#": return -1;
                default: throw new IllegalArgumentException(String.format("잘못된 입력값 : %s", option));
            }
        }

        public void applyMultiple(final int val) {
            score *= val;
        }

        public boolean isHasOption() {
            return isHasOption;
        }

        public String getOption() {
            return option;
        }

        public int getScore() {
            return score;
        }

    }

}
