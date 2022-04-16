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

    public static class UserReport {

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

    public static class UserList {

        private final Map<String, User> users;

        public UserList() {
            this.users = new HashMap<>();
        }

        public void report(final String[] report) {
            if(users.isEmpty()) {
                Arrays.stream(report).forEach(this::addReport);
            }
        }

        public void addReport(final String reportInfo) {
            final String[] reportUserInfoList = reportInfo.split(" ");
            final User user = addUserAndGet(reportUserInfoList[0]);
            final User targetUser = addUserAndGet(reportUserInfoList[1]);
            user.reportUser(targetUser);
        }

        private User addUserAndGet(final String userName) {
            return users.computeIfAbsent(userName, User::new);
        }

        public int[] getSendMailCountList(final String[] idList, final int k) {
            return Arrays.stream(idList)
                .mapToInt(id -> users.getOrDefault(id, User.getDefault()).getSendMailCount(k))
                .toArray();
        }

    }

    public static class User {

        private final String name;
        private final ReportTargetUsers targetUsers;
        private int reportCount;

        public User(final String name) {
            this.name = name;
            this.targetUsers = new ReportTargetUsers();
            this.reportCount = 0;
        }

        public static User getDefault() {
            return new User("");
        }

        public String getName() {
            return name;
        }

        public void reported() {
            reportCount++;
        }

        public void reportUser(final User targetUser) {
            targetUsers.reportUser(targetUser);
        }

        public Boolean isSuspensionTarget(final int criteriaNum) {
            return reportCount >= criteriaNum;
        }

        public int getSendMailCount(int k) {
            return targetUsers.getSendMailCount(k);
        }

    }

    public static class ReportTargetUsers {

        private final Map<String, User> targetUsers;

        public ReportTargetUsers() {
            this.targetUsers = new HashMap<>();
        }

        public void reportUser(final User targetUser) {
            if(!targetUsers.containsKey(targetUser.getName())) {
                targetUsers.put(targetUser.getName(), targetUser);
                targetUser.reported();
            }
        }

        public int getSendMailCount(int k) {
            return (int) targetUsers.values().stream()
                .filter(targetUser -> targetUser.isSuspensionTarget(k))
                .count();
        }

    }

}