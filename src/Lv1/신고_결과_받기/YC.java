package Lv1.신고_결과_받기;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class YC {

    public int[] solution(final String[] id_list, final String[] report, final int k) {
        final UserReport userReportList = new UserReport(report);
        userReportList.report();
        return userReportList.getSendMailCountList(id_list, k);
    }

    public static class UserReport { // 유저 신고 객체

        private final String[] report;
        private final UserList users;

        public UserReport(final String[] report) {
            this.report = report;
            this.users = new UserList();
        }

        public void report() {
            users.report(report);
        }

        public int[] getSendMailCountList(final String[] idList, final int k) {
            return users.getSendMailCountList(idList, k);
        }

    }

    public static class UserList { // 유저 신고 일급 컬렉션

        private final Map<String, User> users;

        public UserList() {
            this.users = new HashMap<>();
        }

        public void report(final String[] report) { // 신고
            if(users.isEmpty()) {
                Arrays.stream(report).forEach(this::addReport);
            }
        }

        public void addReport(final String reportInfo) {
            final String[] reportUserInfoList = reportInfo.split(" ");
            final User user = addUserAndGet(reportUserInfoList[0]);         // 신고한 유저
            final User targetUser = addUserAndGet(reportUserInfoList[1]);   // 신고된 유저
            user.reportUser(targetUser); // 유저를 신고
        }

        private User addUserAndGet(final String userName) {
            return users.computeIfAbsent(userName, User::new);
        }

        public int[] getSendMailCountList(final String[] idList, final int k) { // 각 유저의 신고한 유저 일급 컬렉션에서 K 이상 신고된 유저의 갯수 카운팅
            return Arrays.stream(idList)
                .mapToInt(id -> users.getOrDefault(id, User.getDefault()).getSendMailCount(k))
                .toArray();
        }

    }

    public static class User { // 유저 객체

        private final String name;                   // 유저 이름
        private final ReportTargetUsers targetUsers; // 신고한 유저 일급 컬렉션 (One To Many : ReportTargetUser)
        private int reportCount;                     // 신고 당한 횟수

        public User(final String name) {
            this.name = name;
            this.targetUsers = new ReportTargetUsers();
            this.reportCount = 0;
        }

        public static User getDefault() { // default 객체 생성
            return new User("");
        }

        public String getName() {
            return name;
        }

        public void reported() { // 해당 유저의 신고 당한 횟수 카운트
            reportCount++;
        }

        public void reportUser(final User targetUser) {
            targetUsers.reportUser(targetUser);
        }

        public Boolean isSuspensionTarget(final int criteriaNum) { // 해당 유저의 신고된 횟수가 정지 기준 이상인지 확인
            return reportCount >= criteriaNum;
        }

        public int getSendMailCount(int k) {
            return targetUsers.getSendMailCount(k);
        }

    }

    public static class ReportTargetUsers { // 신고한 유저 일급 컬렉션 (Many To One : User)

        private final Map<String, User> targetUsers;

        public ReportTargetUsers() {
            this.targetUsers = new HashMap<>();
        }

        public void reportUser(final User targetUser) {
            if(!targetUsers.containsKey(targetUser.getName())) { // 이미 신고한 유저인지 확인하고 map 에 추가후 신고처리
                targetUsers.put(targetUser.getName(), targetUser);
                targetUser.reported();
            }
        }

        public int getSendMailCount(int k) { // 신고한 유저 리스트 중 정지 기준 횟수 이상 신고받은 유저의 갯수 카운팅 (메일 전송 갯수)
            return (int) targetUsers.values().stream()
                .filter(targetUser -> targetUser.isSuspensionTarget(k))
                .count();
        }

    }

}