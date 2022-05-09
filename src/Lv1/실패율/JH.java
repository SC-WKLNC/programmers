package Lv1.실패율;

import java.util.*;
import java.util.stream.IntStream;

/**
 * 각 스테이지를 key 로 잡아 몇명의 유저 해당 스테이지에 존재하는지 저장한다.
 *
 */
public class JH {
    public static void main(String[] args) {

        int n = 5;
        int[] stages = new int[]{2, 1, 2, 6, 2, 4, 3, 3};
        JH jh = new JH();
        jh.solution(n,stages);

    }
    public int[] solution(int N, int[] stages) {
        UserStageMap userStageMap = new UserStageMap(N + 1);
        Arrays.stream(stages)
                .forEach(stage ->
                        userStageMap.addStageUser(stage)
                );



        int[] answer = new StageDifficulty(userStageMap).getStageDifficulty();

        return answer;
    }


    class StageDifficulty{
        //스테이지 난이도가 키, 해당 난이도를 가지고있는 스테이지가 벨류
        private Map<Float, List<Integer>> difficultyStage = new HashMap<>();

        //난이도의 키들
        private Set<Float> difficulty = new HashSet<>();

        public StageDifficulty(UserStageMap userStageMap){
            int maxStage = userStageMap.getMaxStage();
            for(int i=1; i <maxStage; i ++){
                float stageChallengeUser = userStageMap.getStageChallengeUser(i);
                float stageUser = userStageMap.getStageUser(i);

                float sum = stageChallengeUser != 0 ? stageUser/stageChallengeUser : 0;

                this.difficulty.add(sum);
                List<Integer> integers = this.difficultyStage.getOrDefault(sum, new ArrayList<>());
                integers.add(i);
                this.difficultyStage.put(sum,integers);

            }

        }

        public int[] getStageDifficulty(){

            List<Float> keyList = new ArrayList(difficulty);
            Collections.sort(keyList,Collections.reverseOrder()); //내림차순 정렬

            List<Integer> result = new ArrayList<>();

            for (Float difficultyKey : keyList) {
                List<Integer> orDefault = difficultyStage.getOrDefault(difficultyKey, new ArrayList<>());
                orDefault.stream().sorted();
                for (Integer integer : orDefault) {
                    result.add(integer);
                }
            }
            return arrayFromList(result);
        }

        private int[] arrayFromList(List<Integer> list){
            int size = list.size();
            int[] array = new int[size];
            for (int i = 0; i < size; i++) {
                array[i] = list.get(i);
            }
            return array;
        }

    }

    class UserStageMap {
        //몇번 스테이지에 몇명 있는지
        private Map<Integer, Integer> userStagePos = new HashMap<>();
        private int maxStage;

        public UserStageMap(int maxStage){
            this.maxStage = maxStage;
        }

        public int getMaxStage() {
            return maxStage;
        }


        public void addStageUser(int stage){
            userStagePos.put(stage, getStageUser(stage) + 1 );
        }
        public int getStageUser(int stage){
            return userStagePos.getOrDefault(stage,0);
        }

        //해당 스테이지에 도달한적이 있는 유저 반환
        public int getStageChallengeUser(int stage){
            int total = 0;
            for (Integer integer : userStagePos.keySet()) {
                if(integer < stage) continue;

                total += userStagePos.get(integer);
            }
            return total;
        }
    }

}


