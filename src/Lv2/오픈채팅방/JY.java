package Lv2.오픈채팅방;

import kotlin.reflect.jvm.internal.impl.serialization.deserialization.builtins.BuiltInSerializerProtocol;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class JY {
    public static void main(String[] ags) {
        Solution solution = new Solution();

        String[] case1 = new String[] {"Enter uid1234 Muzi", "Enter uid4567 Prodo", "Leave uid1234", "Enter uid1234 Prodo", "Change uid4567 Ryan"};

        Arrays.stream(solution.solution(case1)).forEach(System.out::println);
    }
    //["Enter uid1234 Muzi", "Enter uid4567 Prodo", "Leave uid1234", "Enter uid1234 Prodo", "Change uid4567 Ryan"]
    public static class Solution {

        // 필드 : log 들어왔,나갔을 기록하는 로그
        //       idMap 리얼아이디를 키로 오픈아이디를 값으로 가지고 있는 맵
        // 함수 initLog 로그를 기록하는것 들어올때와 아이디를 바꿀때만 idmap에 기록하고 들어올때와 나갈때만 log에 기록한다.
        //     메세지포맷 채인져 로그를 기반으로 아이디맵을탐색하여 스트링으로 변환
        // User
        // 필드 : 실제아이디와 상태
        // State
        // Enum //

        public String[] solution(String[] record) {
            Arrays.stream(record).forEach(fe->initLog(fe));
            return log.stream()
                    .map(mp->messageFormatChanger(mp))
                    .toArray(String[]::new);
        }
        List<User> log = new ArrayList<>();
        HashMap<String,String> idMap = new HashMap<>();


        public void initLog (final String recode){
            var spliteRecode = recode.split(" ");
            State state = State.setValue(spliteRecode[0]);
            if (state== State.NONE) return;

            var user =new User(spliteRecode[1],state);
            if(user.state!= State.Leave)
            idMap.put(spliteRecode[1],spliteRecode[2]);

            if(user.state== State.Change) {
                return;
            }
            log.add(user);
        }
        public String messageFormatChanger(final User user){
            String id = idMap.get(user.userID);
            String state;
            if(user.state == State.Enter)
                state = "들어왔습니다.";
            else state = "나갔습니다.";
            return MessageFormat.format("{0}님이 {1}",id,state);
        }

        public class User{
            private final String userID;
            private State state;
            public User(final String id,final State state) {
                this.userID =id;
                this.state = state;
            }
        }

        enum State{
            Enter("Enter"),Leave("Leave"),Change("Change"),NONE("NONE");
            private final String value;
            State(String value){this.value =value;}
            public static State setValue(final String value){
                return Arrays.stream(values())
                        .filter(fl -> fl.value.equals(value))
                        .findFirst()
                        .orElse(NONE);
            }
        }
    }


}
