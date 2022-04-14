package 신고결과받기;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class JY {
    public int[] solution(String[] id_list, String[] report, int k) {


        HashMap<String, List<String>> Users = new HashMap<String, List<String>>();
        HashMap<String,Integer> ReportCount= new HashMap<String,Integer>();
        List banList = new ArrayList();
        int[] answer = new int[id_list.length];
        int answer_Length = answer.length;
        for (String id:id_list)
        {   // id에 리폿한사람을 해시맵에 넣음
            Users.put(id, Arrays.stream(report).filter(x->x.contains(id)).filter(x -> x.split(" ")[0]
                            .equals(id)).map(x -> x.replace(MessageFormat.format("{0} ", id), "")).distinct()
                    .collect(Collectors.toList()));
            //넣으면서 신고한애들 몇번 당했는지 기록
            for (String User : Users.get(id)) {
                Integer getCountValue = ReportCount.get(User);
                ReportCount.put(User,getCountValue==null ? 1 : getCountValue+1);
            }
        }


        //일정이상 신고당했다면 벤리스트에 넣음
        Arrays.stream(id_list).collect(Collectors.toList()).forEach(x-> {
            Integer getCountValue = ReportCount.get(x);
            if(getCountValue!=null && getCountValue>=k)
                banList.add(x);
        });

        //횟수입력
        for (int i = 0 ; i<answer_Length; i++)
        {
            answer[i]= (int) Users.get(id_list[i]).stream().filter(x-> banList.indexOf(x)!= -1).count();
        }
        return answer;

    }
}
