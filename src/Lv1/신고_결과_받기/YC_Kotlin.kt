package Lv1.신고_결과_받기

class YC_Kotlin {

    fun solution(id_list: Array<String>, report: Array<String>, k: Int): IntArray {
        val userReportList = UserReport(report)
        userReportList.report()
        return userReportList.getSendMailCountList(id_list, k)
    }

    class UserReport(private val report: Array<String>) { // 유저 신고 객체

        private val users: UserList = UserList()

        fun report() = users.report(report)

        fun getSendMailCountList(idList: Array<String>, k: Int): IntArray =
            users.getSendMailCountList(idList, k).toIntArray()

    }

    class UserList { // 유저 신고 일급 컬렉션

        private val users: MutableMap<String, User> = HashMap()

        fun report(report: Array<String>) { // 신고
            if (users.isEmpty())
                report.forEach(this::addReport)
        }

        private fun addReport(reportInfo: String) {
            val reportUserInfoList = reportInfo.split(" ")
            val user = addUserAndGet(reportUserInfoList[0])         // 신고한 유저
            val targetUser = addUserAndGet(reportUserInfoList[1])   // 신고된 유저
            user.reportUser(targetUser) // 유저를 신고
        }

        private fun addUserAndGet(userName: String): User =
            users.computeIfAbsent(userName, ::User)

        // 각 유저의 신고한 유저 일급 컬렉션에서 K 이상 신고된 유저의 갯수 카운팅
        fun getSendMailCountList(idList: Array<String>, k: Int): List<Int> =
            idList.map { (users[it] ?: User.default).getSendMailCount(k) }

    }

    class User(val name: String) { // 유저 객체

        companion object {
            val default = User("") // default 객체
        }

        private val targetUsers: ReportTargetUsers = ReportTargetUsers() // 신고한 유저 일급 컬렉션 (One To Many : ReportTargetUser)
        private var reportCount: Int = 0                                 // 신고 당한 횟수

        // 해당 유저의 신고 당한 횟수 카운트
        fun reported() =
            reportCount++

        // 유저 신고
        fun reportUser(targetUser: User): Unit =
            targetUsers.reportUser(targetUser)

        // 해당 유저의 신고된 횟수가 정지 기준 이상인지 확인
        fun isSuspensionTarget(criteriaNum: Int): Boolean =
            reportCount >= criteriaNum

        // 메일 전송 갯수
        fun getSendMailCount(k: Int): Int =
            targetUsers.getSendMailCount(k)

    }

    class ReportTargetUsers { // 신고한 유저 일급 컬렉션 (Many To One : User)

        private val targetUsers: MutableMap<String, User> = HashMap()

        // 이미 신고한 유저인지 확인하고 map 에 추가후 신고처리
        fun reportUser(targetUser: User) {
            if (!targetUsers.containsKey(targetUser.name)) {
                targetUsers[targetUser.name] = targetUser
                targetUser.reported()
            }
        }

        // 신고한 유저 리스트 중 정지 기준 횟수 이상 신고받은 유저의 갯수 카운팅 (메일 전송 갯수)
        fun getSendMailCount(k: Int): Int = targetUsers.values.count { it.isSuspensionTarget(k) }

    }

}