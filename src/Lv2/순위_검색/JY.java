package Lv2.순위_검색;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class JY {
    // 코테 cpp java python - 중 택 1
    // 직군 backend frontend - 중 택 1
    // 경력 junior senior - 중 택1
    // 푸드 chicken pizza - 중 택1
    // 점수
    // 모든건 스페이스바로 분열됨.
    public static void main(String[] args) {

        Solution s = new Solution();

        var ss = new String[] {
                "java backend junior pizza 150"
                , "python frontend senior chicken 210"
                , "python frontend senior chicken 150"
                , "cpp backend senior pizza 260"
                , "java backend junior chicken 80"
                , "python backend senior chicken 50"};
        var sss = new String[] {
                "java and backend and junior and pizza 100"
                , "python and frontend and senior and chicken 200"
                , "cpp and - and senior and pizza 250"
                , "- and backend and senior and - 150"
                , "- and - and - and chicken 100"
                , "- and - and - and - 150"};
        int[] se = s.solution(ss,sss);
        Arrays.stream(se).forEach(fe -> System.out.println(fe));
    }

    public static class Solution {
        public int[] solution(String[] info, String[] query) {
            DataBase dataBase = new DataBase().initHumanData(info)
                    .initQueryData(query);


            Matcher matcher = new Matcher(dataBase);
            int[] answer = new int[query.length];
            //IntStream.range(0, query.length).map(mp -> matcher.matche(mp)).toArray();
            IntStream.range(0, query.length).forEach(fe-> answer[fe] = matcher.matche(fe));
            return  answer;

        }

        public class DataBase {
            private List<Data> humans = new ArrayList<>();
            private List<Data> querys = new ArrayList<>();

            public DataBase initHumanData(String[] data) {
                Arrays.stream(data)
                        .map(mp -> mp.split("\\s"))
                        .forEach(fe -> humans.add(new Data(
                                        EnumContainer.Lang.getLang(fe[0])
                                        , EnumContainer.DevType.getLang(fe[1])
                                        , EnumContainer.Career.getLang(fe[2])
                                        , EnumContainer.Soulfood.getLang(fe[3])
                                        , Integer.parseInt(fe[4])
                                )
                        ));

                return this;
            }
            public DataBase initQueryData(String[] data) {
                Arrays.stream(data)
                        .map(mp -> mp.split("\\sand\\s"))
                        .forEach(fe -> querys.add(new Data(
                                        EnumContainer.Lang.getLang(fe[0])
                                        , EnumContainer.DevType.getLang(fe[1])
                                        , EnumContainer.Career.getLang(fe[2])
                                        , EnumContainer.Soulfood.getLang(fe[3].split(" ")[0])
                                        , Integer.parseInt(fe[3].split(" ")[1])
                                )
                        ));

                return this;
            }

            public List<Data> getQuerys() {
                return querys;
            }
            public List<Data> getHumans() {
                return humans;
            }
        }

        public class Matcher {
            private final DataBase base;

            public Matcher(DataBase base) {
                this.base = base;
            }

            public int matche(int num) {
                Data query = base.getQuerys().get(num);
                return (int)base.getHumans().stream()
                        .filter(fl -> query.score <= fl.score)
                        .filter(fl -> langMatcher(fl.lang, query.lang))
                        .filter(fl -> devTypeatcher(fl.devType, query.devType))
                        .filter(fl -> careerMatcher(fl.career, query.career))
                        .filter(fl -> soulMatcher(fl.soulfood, query.soulfood))
                        .count();

            }

            private boolean langMatcher(EnumContainer.Lang target, EnumContainer.Lang vaildValue) {
                if (vaildValue == EnumContainer.Lang.All) return true;
                if (target == vaildValue) return true;
                return false;
            }

            private boolean devTypeatcher(EnumContainer.DevType target, EnumContainer.DevType vaildValue) {
                if (vaildValue == EnumContainer.DevType.All) return true;
                if (target == vaildValue) return true;
                return false;
            }

            private boolean careerMatcher(EnumContainer.Career target, EnumContainer.Career vaildValue) {
                if (vaildValue == EnumContainer.Career.All) return true;
                if (target == vaildValue) return true;
                return false;
            }

            private boolean soulMatcher(EnumContainer.Soulfood target, EnumContainer.Soulfood vaildValue) {
                if (vaildValue == EnumContainer.Soulfood.All) return true;
                if (target == vaildValue) return true;
                return false;
            }
        }

        public class Data {
            private final EnumContainer.Lang lang;
            private final EnumContainer.DevType devType;
            private final EnumContainer.Career career;
            private final EnumContainer.Soulfood soulfood;
            private final int score;

            public Data(EnumContainer.Lang lang
                    , EnumContainer.DevType devType
                    , EnumContainer.Career career
                    , EnumContainer.Soulfood soulfood
                    , int score) {

                this.lang = lang;
                this.devType = devType;
                this.career = career;
                this.soulfood = soulfood;
                this.score = score;
            }
        }

        public static class EnumContainer {
            enum Lang {
                Cpp("cpp"), Java("java"), Python("python"), All("-"), None("");
                private final String value;

                Lang(String value) {
                    this.value = value;
                }

                public static Lang getLang(String value) {
                    return Arrays.stream(values())
                            .filter(fl -> fl.value.equals(value))
                            .findFirst()
                            .orElse(None);
                }
            }

            enum DevType {
                BackEnd("backend"), FrontEnd("frontend"), All("-"), None("");
                private final String value;

                DevType(String value) {
                    this.value = value;
                }

                public static DevType getLang(String value) {
                    return Arrays.stream(values())
                            .filter(fl -> fl.value.equals(value))
                            .findFirst()
                            .orElse(None);
                }
            }

            enum Career {
                Junior("junior"), Senior("senior"), All("-"), None("");
                private final String value;

                Career(String value) {
                    this.value = value;
                }

                public static Career getLang(String value) {
                    return Arrays.stream(values())
                            .filter(fl -> fl.value.equals(value))
                            .findFirst()
                            .orElse(None);
                }
            }

            enum Soulfood {
                Chicken("chicken"), Pizza("pizza"), All("-"), None("");
                private final String value;

                Soulfood(String value) {
                    this.value = value;
                }

                public static Soulfood getLang(String value) {
                    return Arrays.stream(values())
                            .filter(fl -> fl.value.equals(value))
                            .findFirst()
                            .orElse(None);
                }
            }

            enum DataType {
                Human, Query
            }
        }
    }
}
