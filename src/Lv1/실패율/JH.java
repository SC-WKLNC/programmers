package Lv1.실패율;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
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
        int[] answer = {};

        StageCalculator stageCalculator = new StageCalculator(N,stages);
        for (int stage : stages) {
            stageCalculator.stageFailureRate(stage);
        }

        return answer;
    }

    class StageCalculator{
        private int max_stage;
        private int userCount;
        private UserStageMap userStageMap;

        public StageCalculator(int max_stage, int[] stages) {
            this.max_stage = max_stage + 1; //max stage +1 이 라스트 스테이지 이다.
            this.userCount = stages.length+1;
            this.userStageMap = new UserStageMap();
            Arrays.stream(stages)
                    .forEach(stage ->
                            userStageMap.addStageUser(stage)
                    );
        }
        
        //스테이지 실패율
        public void stageFailureRate(int stage){
            float stageChallengeUser = userStageMap.getStageChallengeUser(stage);
            float stageUser = userStageMap.getStageUser(stage);

            float sum = stageUser/stageChallengeUser;
            System.out.println("stage "+stage+" : "+stageUser+"/"+stageChallengeUser + " = "+sum);

        }


    }

    class UserStageMap {

        private Map<Integer, Integer> userStagePos = new HashMap<>();

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


