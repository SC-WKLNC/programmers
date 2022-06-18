package Lv2.오픈채팅방;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

public class YC {

    // start    2022/06/18 15:07
    // end      2022/06/18 17:28

    // 객체 모델링

    // 채팅방
    // 필드 : 입장한 유저, 채팅로그
    // 액션 : 유저 입장, 닉네임 변경, 유저 퇴장, 로그 기록

    // 채팅 로그
    // 필드 : 로그아이디, 유저아이디, 변경된 로그 내역, 마지막 로그
    // 액션 : 로그 추가 및 마지막 로그 변경

    // 유저
    // 필드 : 아이디, 닉네임

    public String[] solution(String[] record) {

        final ChatRoom chatRoom = new ChatRoom();
        final Map<String, User> userMap = new HashMap<>();

        Arrays.stream(record)
            .map(it -> it.split(" "))
            .forEach(rcd -> {
                final ChatRoom.Action action = ChatRoom.Action.getAction(rcd[0]);
                final String userId = rcd[1];
                final User user = userMap.computeIfAbsent(userId, User::new);
                if (action != ChatRoom.Action.LEAVE)
                    user.applyNickname(rcd[2]);
                chatRoom.action(user, action);
            });

        return chatRoom.getLogs().toArray(new String[0]);
    }

    public static class ChatRoom {

        private final Map<String, List<ChatLog>> logMap = new HashMap<>();
        private final List<ChatLog> logList = new ArrayList<>();
        private long logSequence = 0;

        public enum Action {
            NONE("", "NONE"),
            ENTER("Enter", "들어왔습니다."),
            CHANGE("Change", "닉네임을 변경했습니다."),
            LEAVE("Leave", "나갔습니다.");

            private final String value;
            private final String desc;

            Action(final String value, final String desc) {
                this.value = value;
                this.desc = desc;
            }

            public String getDesc() {
                return desc;
            }

            public static Action getAction(final String str) {
                return Arrays.stream(values())
                    .filter(it -> it.isMatch(str))
                    .findFirst()
                    .orElseThrow();
            }
            
            private boolean isMatch(final String str) {
                return this.value.equals(str);
            }

        }

        public void action(final User user, final Action action) {
            logging(user, action);
        }

        private void logging(final User user, final Action action) {
            // 먼저 기존 유저 아이디의 로그들을 찾는다.
            // 로그에 각각 변경 로그를 추가한다.
            // 단, 입장했을 때 판단할 것은 닉네임이 변경되었는지 판단하고 변경 로그 추가.

            // 닉네임 변경 액션이 아닐 경우 로그를 추가한다.

            Optional.ofNullable(logMap.get(user.getId()))
                .ifPresentOrElse(
                    mapLogs -> {
                        if(!mapLogs.isEmpty())
                            // if(mapLogs.get(0).isNotEqualsNickname(user.getNickname()))
                            // 이 조건을 넣었을때 왜 답이 틀리는지 모르겠다,,
                            mapLogs.forEach(log -> log.logging(user.getNickname()));
                        addLog(user, action, mapLogs);
                    },
                    () -> addLog(user, action, null)
                );
        }

        private void addLog(final User user, final Action action, final List<ChatLog> mapLogs) {
            if(action != Action.CHANGE) {
                logSequence++;
                final ChatLog chatLog = new ChatLog(logSequence, user.getId(), user.getNickname(), action);
                if(mapLogs != null) {
                    mapLogs.add(chatLog);
                } else {
                    final List<ChatLog> newMapLogs = new ArrayList<>();
                    newMapLogs.add(chatLog);
                    logMap.put(user.getId(), newMapLogs);
                }
                logList.add(chatLog);
            }
        }

        public List<String> getLogs() {
            return logList.stream()
                .map(ChatLog::getLastLog)
                .collect(Collectors.toList());
        }

    }

    public static class ChatLog {

        private final long id;
        private final String userId;
        private final String nickname;
        private final ChatRoom.Action action;
        private final List<String> logs = new ArrayList<>();
        private String lastLog = "";

        public ChatLog(final long logId, final String userId, final String nickname, final ChatRoom.Action action) {
            this.id = logId;
            this.userId = userId;
            this.action = action;
            this.nickname = nickname;
            logging(nickname);
        }

        public boolean isEqualsNickname(final String nickname) {
            return this.nickname.equals(nickname);
        }

        public boolean isNotEqualsNickname(final String nickname) {
            return !isEqualsNickname(nickname);
        }

        public void logging(final String nickname) {
            final String log = String.format("%s님이 %s", nickname, action.getDesc());
            logs.add(log);
            lastLog = log;
        }

        public String getLastLog() {
            return lastLog;
        }
    }

    public static class User {
        private final String id;
        private String nickname;

        public User(final String id) {
            this.id = id;
        }

        public User(final String id, final String nickname) {
            this(id);
            this.nickname = nickname;
        }

        public void applyNickname(final String newNickname) {
            nickname = newNickname;
        }

        public String getId() {
            return id;
        }

        public String getNickname() {
            return nickname;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            User user = (User) o;
            return Objects.equals(id, user.id);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id);
        }
    }

}
