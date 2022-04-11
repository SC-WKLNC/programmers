package Lv1.신고_결과_받기;

import java.util.*;

/**
 * 한 유저는 여러 유저를 신고할수있지만 한번 신고할때는 한명씩만 신고가능
 * 한 유저가 같은 유저를 두번 신고해도 해당 신고는 한번만 적용됨
 * 정해진 횟수 k 이상 신고된 유저는 이용 정지가 되며 해당 유저를 신고한 유저에게 신고한 유저가 정지 되었다는 메일을 보내준다.
 *
 */
public class JH {
    public static void main(String[] args) {
        Solution solution = new Solution();

        String[] id_list = new String[]{"muzi", "frodo", "apeach", "neo"};
        String[] report = new String[]{"muzi frodo","apeach frodo","frodo neo","muzi neo","apeach muzi"};
        int k = 2;
        solution.solution(id_list,report,k);
    }
    static class Solution {
        public int[] solution(String[] id_list, String[] report, int k) {

            int badReportLimit = k;
            //해당 유저의 신고한 유저의 리스트
            Map<String, Set<String>> userBadReport = new HashMap<>();
            //해당 유저가 받을 메일 카운트
            Map<String, Integer> userMail = new HashMap<>();

            //init
            for (String id : id_list) {
                userBadReport.put(id,new HashSet<>());
                userMail.put(id,0);
            }

            //유저 key 와 이 유저를 신고한 유저들을 저장한다.
            //set 으로 저장하여 중복 신고를 무시한다.
            for (String reportItem : report){
                String[] temp = reportItem.split(" ");
                String reportUser = temp[0];
                String reportTarget = temp[1];

                //해당 유저를 신고한 사람을 추가한다.
                Set<String> userReportList = userBadReport.get(reportTarget);
                userReportList.add(reportUser);
            }

            //k 이상 신고 받은 유저를 찾은후 해당 유저를 신고한 유저에게 메일은 전송한다.
            for (String key : userBadReport.keySet()) {
                Set<String> userReportList = userBadReport.get(key);
                if(userReportList.size() < badReportLimit) continue;

                for (String userReport : userReportList) {
                    userMail.put(userReport,  userMail.get(userReport) + 1);
                }
            }



            int idLength = id_list.length;

            int[] answer = new int[idLength];
            for(int i =0 ; i <idLength; i ++){
                answer[i] = userMail.get(id_list[i]);
            }

            return answer;
        }
    }
}

