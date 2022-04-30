package Lv2.문자열_압축;

import java.util.stream.IntStream;

public class YC {

    public int solution(String s) {
        final StringCompressor stringCompressor = new StringCompressor(s);
        return stringCompressor.getCompressLength();
    }

    public static class StringCompressor {

        private final String s; // 문자열
        private int sLength; // 문자열 길이
        private final int unitMaxLength; // 최대 압축 단위 길이
        private final StringBuilder sb = new StringBuilder();
        private int repeatCount = 0; // 반복 횟수
        private String beforeStr = ""; // 압축 단위 이전 문자열
        private boolean isStarted = false; // 압축 시작 여부
        private int result = Integer.MAX_VALUE; // 가장 작게 압축된 문자열 길이

        public StringCompressor(final String s) {
            this.s = s;
            this.sLength = s.length();
            this.unitMaxLength = s.length() / 2 + 1;
        }

        public int getCompressLength() {
            IntStream.range(0, unitMaxLength)
                .forEach(x -> {
                    final int compressLength = compress(x + 1); // 1부터 최대 압축 단위까지 압축
                    if(compressLength < result)
                        result = compressLength;
                });
            return result;
        }

        private int compress(final int unit) {
            clear(); // 변수 재사용을 위한 초기화
            IntStream
                .iterate(0, i -> (i + unit) <= sLength, i -> i + unit)
                .forEach(i -> accumulateSameStr(i, unit));
            addRemainStr(unit); // 압축 단위 이외의 나머지 문자열 추가
            return sb.length();
        }

        private void addRemainStr(int unit) { // 나머지 길이 구해서 압축 문자열에 추가
            final int remainStrCount = sLength % unit;
            if(remainStrCount > 0) {
                String substring = s.substring(sLength - remainStrCount);
                sb.append(substring);
            }
        }

        private void accumulateSameStr(final int idx, final int unit) {
            final String substring = s.substring(idx, idx + unit);
            if(!isStarted) {
                plusRepeat();
                beforeStr = substring;
            } else if(beforeStr.equals(substring)) {
                plusRepeat();
            } else {
                appendStr(substring);
            }
            if((idx + (unit*2)) > sLength) // 압축 단위 마지막 위치 일 경우
                appendStr(substring);
            isStarted = true;
        }

        private void appendStr(String substring) {
            if(repeatCount > 1) {
                sb.append(repeatCount);
            }
            sb.append(beforeStr);
            beforeStr = substring;
            repeatCount = 1;
        }

        private void plusRepeat() {
            repeatCount++;
        }

        private void clear() {
            this.repeatCount = 0;
            this.beforeStr = "";
            this.sb.setLength(0);
            this.isStarted = false;
        }

    }

}
