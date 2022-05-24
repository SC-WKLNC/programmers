package Lv2.순위_검색;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * 시작 시간 5월17일 오후 9시 2 분
 * 종료 시간 5월17일 오후 10시 4분
 * 시간 초과 해결
 *
 *  데이터의 종류는 4가지가 존재하며 질의를 할때 순서대로 질의 하나 - 를 통해 제한을 없앨수 았다.
 *
 *  모델
 *  유저 User 자신의 언어, 포지션, 경력, 소울푸드, 코테 점수를 가진다.
 *  필터 카운터 유저들을 생성자로 주입하고 빌더를 통해 검색한다.
 */
public class JH {
    public static void main(String[] args) {
        String[] info = new String[]{"java backend junior pizza 150","python frontend senior chicken 210","python frontend senior chicken 150","cpp backend senior pizza 260","java backend junior chicken 80","python backend senior chicken 50"};
        String[] query = new String[] {"java and backend and junior and pizza 100","python and frontend and senior and chicken 200","cpp and - and senior and pizza 250","- and backend and senior and - 150","- and - and - and chicken 100","- and - and - and - 150"};
        Lv2.순위_검색.JH jh = new Lv2.순위_검색.JH();
        jh.solution(info,query);
    }

    public int[] solution(String[] info, String[] query) {
        int resultSize = query.length;
        int[] answer = new int[resultSize];
        List<User> users = new ArrayList<>();

        Arrays.stream(info)
                .map(User::new)
                .forEach(users::add);


        for (int i = 0; i < resultSize ; i++) {
            String userQuery = query[i];
            String[] userQueryArr = userQuery.split(" ");

            String languageStr =  userQueryArr[0];
            String skillPositionStr =  userQueryArr[2];
            String skillRankStr =  userQueryArr[4];
            String soulFoodStr =  userQueryArr[6];
            int score = Integer.parseInt(userQueryArr[7]);

            UserFilter userFilter = new UserFilter(users).filterCodingTestScore(score);
            if(isNotEquals(languageStr,"-")){
                userFilter.filterLanguage(Language.valueOf(languageStr));
            }
            if(isNotEquals(skillPositionStr,"-")){
                userFilter.filterSkillPosition(SkillPosition.valueOf(skillPositionStr));
            }
            if(isNotEquals(skillRankStr,"-")){
                userFilter.filterSkillRank(SkillRank.valueOf(skillRankStr));
            }
            if(isNotEquals(soulFoodStr,"-")){
                userFilter.filterSoulFood(SoulFood.valueOf(soulFoodStr));
            }

            int userCount = userFilter.getUserCount();

            answer[i] = userCount;

        }

        return answer;
    }
    public boolean isNotEquals(String a, String b){
        if(a.equals(b)){
            return false;
        }
        return true;
    }

    class UserFilter{
        private List<User> users;
        public UserFilter(List<User> users) {
            this.users = new ArrayList<>(users);
        }
        public UserFilter filterLanguage(Language language){

            List<User> deleteUsers = new ArrayList<>();

            for (User user : users) {
                if(user.language != language){
                    deleteUsers.add(user);
                }
            }

            deleteUsers.forEach(deleteUser -> users.remove(deleteUser));
            return this;
        }
        public UserFilter filterSkillPosition(SkillPosition position){
            List<User> deleteUsers = new ArrayList<>();

            for (User user : users) {
                if(user.position != position){
                    deleteUsers.add(user);
                }
            }
            deleteUsers.forEach(deleteUser -> users.remove(deleteUser));
            return this;
        }
        public UserFilter filterSkillRank(SkillRank rank){
            List<User> deleteUsers = new ArrayList<>();

            for (User user : users) {
                if(user.rank != rank){
                    deleteUsers.add(user);
                }
            }
            deleteUsers.forEach(deleteUser -> users.remove(deleteUser));
            return this;
        }
        public UserFilter filterSoulFood(SoulFood soulFood){
            List<User> deleteUsers = new ArrayList<>();

            for (User user : users) {
                if(user.soulFood != soulFood){
                    deleteUsers.add(user);
                }
            }
            deleteUsers.forEach(deleteUser -> users.remove(deleteUser));
            return this;
        }
        public UserFilter filterCodingTestScore(int codingTestScore){

            List<User> deleteUsers = new ArrayList<>();

            for (User user : users) {
                if(user.codingTestScore < codingTestScore){
                    deleteUsers.add(user);
                }
            }
            deleteUsers.forEach(deleteUser -> users.remove(deleteUser));
            return this;
        }
        public int getUserCount(){
            return users.size();
        }
    }

    class UserFilter2{
        private List<User> originUsers;
        private List<User> resultUsers;
        
        UserFilter2(List<User> users){
            this.originUsers = users;
        }


    }
    class User{


        private final Language language ;
        private final SkillPosition position ;
        private final SkillRank rank;
        private final SoulFood soulFood;
        private final int codingTestScore;

        public User(String userInfo) {
            String[] userInfoArr = userInfo.split(" ");

            this.language = Language.valueOf(userInfoArr[0]);
            this.position = SkillPosition.valueOf(userInfoArr[1]);
            this.rank  = SkillRank.valueOf(userInfoArr[2]);
            this.soulFood  = SoulFood.valueOf(userInfoArr[3]);
            codingTestScore = Integer.parseInt(userInfoArr[4] );

        }

    }


    enum Language{
        cpp,java,python
    }
    enum SkillPosition{
        frontend, backend
    }
    enum SkillRank{
        junior, senior
    }
    enum SoulFood{
        chicken, pizza
    }

}