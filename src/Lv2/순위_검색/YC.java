package Lv2.순위_검색;

import java.util.*;
import java.util.stream.Collectors;

public class YC {

    // 2022/05/19 00:20 시작
    // 2022/05/19 01:06 효율성 실패

    // 코딩 테스트

    // 개발 언어 cpp, java, python
    // 직군 항목 backend, frontend
    // 경력 구분 항목 junior, senior
    // 소울 푸드 chicken, pizza
    // 점수 1 <= 100000
    // 구분자 " "
    // 모두 and 조건

    // 객체
    // 코딩 테스트
    // 필드 : 쿼리
    // 함수 : 특정 지원자가 조건을 충족하는지
    // 지원자
    // 필드 : 각 항목
    // 함수 : 각 항목에 대한 조건에 만족하는지 판별하는 함수
    // 쿼리
    // 필드 : 각 항목
    // 효율성 캐시
    // 필드 : 쿼리, count

    // 점수를

    public int[] solution(String[] info, String[] query) {

        final List<Candidate> candidates = Arrays.stream(info)
                .map(Candidate::new)
                .collect(Collectors.toList());

        final Map<String, Query> queryMap = new HashMap<>();

        final List<Query> queries = Arrays.stream(query)
                .map(q ->  queryMap.computeIfAbsent(q, Query::new))
                .collect(Collectors.toList());

        final CodingTest codingTest = new CodingTest(candidates, queries);

        return codingTest.getHitCounts();

    }

    public static class CodingTest {

        private final List<Candidate> candidates;
        private final List<Query> queries;
        private final QueryCache queryCache;

        public CodingTest(final List<Candidate> candidates, final List<Query> queries) {
            this.candidates = candidates;
            this.queries = queries;
            this.queryCache = new QueryCache();
        }

        // 각 쿼리에 해당하는 갯수 구하기
        public int[] getHitCounts() {
            return queries.stream()
                    .mapToInt(this::hitCacheAndGetCount)
                    .toArray();

        }

        private int hitCacheAndGetCount(Query query) {
            final Optional<Integer> cache = queryCache.hitCache(query);
            if(cache.isPresent())
                return cache.get();
            else {
                int count = (int) this.candidates.stream()
                        .filter(candidate -> this.isMatch(query, candidate))
                        .count();
                queryCache.saveCache(query, count);
                return count;
            }
        }

        private boolean isMatch(final Query query, final Candidate candidate) {
            return
                    query.moreScore(candidate.getScore())
                    && query.eqLanguage(candidate.getLanguage())
                    && query.eqPosition(candidate.getPosition())
                    && query.eqCareerType(candidate.getCareerType())
                    && query.eqSoulFood(candidate.getSoulFood());
        }

    }

    public static class QueryCache {

        private final HashMap<String, Integer> cache = new HashMap<>();

        public void saveCache(final Query query, final int hitCount) {
            cache.put(query.getQuery(), hitCount);
        }

        public Optional<Integer> hitCache(final Query query) {
            return Optional.ofNullable(cache.get(query.getQuery()));
        }

    }

    public static class Query {

        private final String query;

        private final String language;
        private final String position;
        private final String careerType;
        private final String soulFood;
        private final int score;
        private final boolean isExcludeScore;

        public Query(final String query) {
            this.query = query;
            final String[] values = query.split(" and ");
            this.language = values[0];
            this.position = values[1];
            this.careerType = values[2];
            final String[] lastValues = values[3].split(" ");
            this.soulFood = lastValues[0];
            if("-".equals(lastValues[1])) {
                this.isExcludeScore = true;
                this.score = 0;
            } else {
                this.isExcludeScore = false;
                this.score = Integer.parseInt(lastValues[1]);
            }
        }

        public String getQuery() {
            return query;
        }

        public boolean eqLanguage(final String val) {
            if(ifAny(this.language))
                return true;
            return this.language.equals(val);
        }

        public boolean eqPosition(final String val) {
            if(ifAny(this.position))
                return true;
            return this.position.equals(val);
        }

        public boolean eqCareerType(final String val) {
            if(ifAny(this.careerType))
                return true;
            return this.careerType.equals(val);
        }

        public boolean eqSoulFood(final String val) {
            if(ifAny(this.soulFood))
                return true;
            return this.soulFood.equals(val);
        }

        public boolean moreScore(final int val) {
            if(this.isExcludeScore)
                return true;
            return val >= this.score;
        }

        private boolean ifAny(final String value) {
            return value.equals("-");
        }

    }

    public static class Candidate {

        private final String language;
        private final String position;
        private final String careerType;
        private final String soulFood;
        private final int score;

        public Candidate(final String info) {
            final String[] values = info.split(" ");
            this.language = values[0];
            this.position = values[1];
            this.careerType = values[2];
            this.soulFood = values[3];
            this.score = Integer.parseInt(values[4]);
        }

        public int getScore() {
            return score;
        }

        public String getCareerType() {
            return careerType;
        }

        public String getLanguage() {
            return language;
        }

        public String getPosition() {
            return position;
        }

        public String getSoulFood() {
            return soulFood;
        }
    }


}
