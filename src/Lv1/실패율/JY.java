package Lv1.실패율;

import java.util.*;
import java.util.stream.IntStream;

public class JY {

    public static void  main (String[] args)
    {
        int[] s = new int[] {1, 2, 3, 2, 1};
        int N = 4;
        Solution sol = new Solution();
        sol.solution(N,s);

    }
    // 실패율 = 스테이지에 도달했으나 아직 클리어하지 못한 플레이어 수 / 스테이지에 도달한 플레이어 수
    //스테이지 개수 N 사용자가 멈춰있는 스테이지 번호 = stages  실패율이 높은 스테이지부터 내림차순
    // n 1~500   /  stages 1~ 200,000 / stages 1< X < n+1
    // 실피율이 같다면 작은번호의 스테이지가 먼저 오도록함 즉 먼저 실패율 정렬 뒤 순서정렬
    // 스테이지에 도달한 유저가 없는경우 해당스테이지는 실피율이 0 이됨.
    public static class  Solution {

        public int[] solution(int N, int[] stages) {
            // 게임존을 만들고 스테이지를 넣고 사람을 입장시킴
            GameZone zone = new GameZone(N).initStage().joinPerson(stages);

            // 실패율 계산
            FailRateCalcurater fail = new FailRateCalcurater(zone).initFailRateMap();

            //내림차순으로 계산한뒤 key를 array로만들고 리턴
            return fail.getFailrate().entrySet()
                    .stream()
                    .sorted((x,y) -> y.getValue().compareTo(x.getValue()))
                    .mapToInt(Map.Entry::getKey)
                    .toArray();
        }
        public class FailRateCalcurater
        {
            private final GameZone zone;
            private HashMap<Integer,Double> failrate = new HashMap<>();
            public FailRateCalcurater(final GameZone zone)
            {
                this.zone = zone;
            }
            public FailRateCalcurater initFailRateMap()
            {
                IntStream.range(1,zone.MaxStageRound+1)
                        .forEach(fe-> failrate.put(fe, calcRate(fe)));
                return this;
            }
            private double calcRate(final int targetRound)
            {
                int maxhuman= 0;
                // 게임존에서 스테이지를 가져와서 타겟라운드보다 큰값을 가져와 스테이지의 사람수를 maptoint하여 sum함
                maxhuman=zone.getStage()
                        .stream()
                        .filter(fl-> fl.getRound()>=targetRound)
                        .mapToInt(mti->mti
                                .getPerson()
                                .size())
                        .sum();
                // 현재 스테이지에 아무도없으면 실패율을 0으로 봄
                if(zone.getStage().get(targetRound-1).getPerson().size()==0) return 0.0;
                // 현재 스테이지에 사람 사이즈 / Maxhuman
                return (double) zone.getStage().get(targetRound-1).getPerson().size()/maxhuman;
            }
            public HashMap<Integer,Double> getFailrate()
            {
                return failrate;
            }
        }


        public class GameZone {
            private final int MaxStageRound;
            List<Stage> stageList = new ArrayList<>();

            public GameZone(final int stageRound)
            {
                this.MaxStageRound = stageRound;
            }
            // 배열을 가져와 Person으로 생성후 stagelist에 넣음.
            private GameZone joinPerson(final int[] stages)
            {
                Arrays.stream(stages)
                        .mapToObj(Person::new)
                        .forEach(x-> {stageList.get(x.getStage()-1).addPerson(x);});
                return this;
            }
            // 스테이지 생성
            private GameZone initStage()
            {
                IntStream.range(1,MaxStageRound+2)
                        .forEach(fe->stageList.add(new Stage(fe)));
                return this;
            }
            public List<Stage> getStage()
            {
                return this.stageList;
            }
        }

        // 스테이지는 자신의 라운드와 라운드에속한 사람들을 가지고 있음.
        public class Stage{
            private final int round;
            private List<Person> Persons = new ArrayList<>();

            public Stage(int round)
            {
                this.round = round;
            }

            private void addPerson(Person person)
            {
                Persons.add(person);
            }
            public int getRound()
            {
                return round;
            }
            public List<Person> getPerson()
            {
                return this.Persons;
            }
        }
        // 사람은 자신의 스테이지를 가지고있음
        public class Person {
            private final int stage;

            public Person(final int stage) {
                this.stage = stage;
            }
            public int getStage()
            {
                return this.stage;
            }
        }
    }
}
