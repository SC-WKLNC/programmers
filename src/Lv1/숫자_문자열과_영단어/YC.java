package Lv1.숫자_문자열과_영단어;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class YC {

    // 문제 접근 방식

    // 1. "2three45sixseven" 문자열이 234567 숫자로 변환된다.
    // 2. 변환 과정에서 일어날 수 있는 액션을 나열해보자
    // 3. 문자열을 주면 해당하는 숫자로 변환해주는 변환기가 필요하다.
    // 4. 변환기 내부 동작 방식
    //      1) 문자열을 받아 처음부터 하나씩 순환하면서 맞는게 있으면 해당 숫자를 반환.
    //      2) 모든 문자를 연결해 해당 숫자를 반환.
    //      3) 주어진 숫자 문자열외의 다른 숫자 문자열들이 추가 될 수 있다고 가정하고 진행.

    private static final List<NumberPair> list = // 주어진 숫자 문자열 리스트
        List.of(
            new NumberPair(0, "zero"), new NumberPair(1, "one"),
            new NumberPair(2, "two"), new NumberPair(3, "three"),
            new NumberPair(4, "four"), new NumberPair(5, "five"),
            new NumberPair(6, "six"), new NumberPair(7, "seven"),
            new NumberPair(8, "eight"), new NumberPair(9, "nine")
        );

    public int solution(String s) {
        final StringToNumberConverter converter = new StringToNumberConverter(s);
        final Preprocessor preprocessor = new Preprocessor(list);
        return converter.convertToNumber(preprocessor);
    }

    public static class StringToNumberConverter { // 숫자 변환기

        private final StringBuilder result = new StringBuilder();   // 결과 StringBuilder
        private int idx = 0;                                        // 현재 인덱스
        private final char[] chArr;                                 // char 배열

        public StringToNumberConverter(final String str) {
            chArr = str.toCharArray(); // char 배열 초기화
        }

        public Integer convertToNumber(final Preprocessor preprocessor) { // 숫자 변환
            recursiveSearch(preprocessor.getFirstWays());
            return Integer.parseInt(result.toString());
        }

        private void recursiveSearch(final Map<String, NumberObject> ways) {
            if(isAllConverted()) // 변환 완료 체크
                return;
            final char ch = chArr[idx++]; // 변환 대상 문자
            final NumberObject numberObject = ways.getOrDefault(String.valueOf(ch), NumberObject.getDefault()); // 문자에 해당하는 NumberObject 반환
            if(Character.isDigit(ch)) { // 숫자일 경우
                result.append(ch);
                recursiveSearch(ways);

            } else if(numberObject.type.isLook()) { // target 이 아닌 경우 현재 map 에서 계속 진행
                recursiveSearch(numberObject.getNowWays());

            } else if(numberObject.type.isDestination()) { // target 인 경우
                result.append(numberObject.getNumberToString()); // 해당하는 숫자를 결과에 반영
                recursiveSearch(numberObject.getFirstWays()); // 처음 부터 재귀 시작

            } else if(numberObject.type.isNone()){ // 문자에 해당하는 object 가 없을 경우
                throw new IllegalStateException("NumberObject is None");
            }
        }

        private boolean isAllConverted() {
            return idx == chArr.length;
        }

    }

    public static class Preprocessor { // 전처리기

        private final Map<String, NumberObject> firstWays = new HashMap<>();

        // 먼저 맨 앞 자리부터 같은것 끼리 그룹핑해서 Number Object 에 넣는다.
        // 끝이 아니면 type 을 look 으로 넣고
        // 끝이라면 DESTINATION 으로 넣는다.

        public Preprocessor(final List<NumberPair> list) {
            processing(list);
        }

        private void processing(final List<NumberPair> list) {
            // 1개씩 해도 충분할듯 찾아서 있으면 그대로 쓰고 없으면 만들면 된다.
            // 재귀로 만들면 충분.
            list.forEach(numberPair -> recursive(numberPair, firstWays));
        }

        public void recursive(final NumberPair numberPair, final Map<String, NumberObject> ways) { // 재귀로 NumberObject 생성 및 할당
            final String str = numberPair.next();
            if(numberPair.isEnd()) { // 마지막 문자일 경우 DESTINATION 타입으로 put
                ways.put(str, new NumberObject(str, numberPair.getFirst(), NumberObject.Type.DESTINATION, firstWays));
            } else { // 마지막 문자가 아닐 경우 LOOK 타입으로 put 하고 재귀
                final NumberObject nextNumberObject = ways.computeIfAbsent(
                    str, key -> new NumberObject(key, numberPair.getFirst(), NumberObject.Type.LOOK, firstWays)
                );
                recursive(numberPair, nextNumberObject.getNowWays());
            }
        }

        public Map<String, NumberObject> getFirstWays() {
            return firstWays;
        }

    }

    public static class NumberObject {

        private final String key;       // 문자 key 값
        private final Integer number;   // 해당 숫자
        private final Type type;        // object 타입
        private final Map<String, NumberObject> firstWays;                  // 첫번째 way map
        private final Map<String, NumberObject> nowWays = new HashMap<>();  // 오브젝트의 way map

        enum Type {
            NONE, LOOK, DESTINATION;
            public boolean isLook() {
                return this == LOOK;
            }
            public boolean isDestination() {
                return this == DESTINATION;
            }
            public boolean isNone() {
                return this == NONE;
            }
        }

        public NumberObject(final String name, final Integer number, final Type type, Map<String, NumberObject> firstWays) {
            this.key = name;
            this.number = number;
            this.type = type;
            this.firstWays = firstWays;
        }

        public static NumberObject getDefault() { // get default
            return new NumberObject("", -1, Type.NONE, null);
        }

        public Map<String, NumberObject> getNowWays() {
            return nowWays;
        }

        public String getNumberToString() {
            return String.valueOf(number);
        }

        public Map<String, NumberObject> getFirstWays() {
            return firstWays;
        }

    }

    public static class Pair<A, B> {

        protected final A first;
        protected final B second;

        public Pair(final A first, final B second) {
            this.first = first;
            this.second = second;
        }

        public A getFirst() {
            return first;
        }

        public B getSecond() {
            return second;
        }

    }

    public static class NumberPair extends Pair<Integer, String> {

        private final List<String> strArr;
        private int currentIdx;

        public NumberPair(final Integer first, final String second) {
            super(first, second);
            this.strArr = getStringList();
        }

        private List<String> getStringList() {
            final ArrayList<String> arr = new ArrayList<>();
            for(char s : second.toCharArray()) {
                arr.add(String.valueOf(s));
            }
            return arr;
        }

        public String next() {
            return strArr.get(currentIdx++);
        }

        public int size() {
            return strArr.size();
        }

        public boolean isEnd() {
            return currentIdx == size();
        }

    }

}
