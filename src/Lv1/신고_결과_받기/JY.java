package 신고결과받기;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class JY {
    public int[] solution(String[] id_list, String[] report, int k) {


        HashMap<String, List<String>> users = new HashMap<String, List<String>>();
        HashMap<String,Integer> reportCount= new HashMap<String,Integer>();
        List banList = new ArrayList();
        int[] answer = new int[id_list.length];
        int answer_Length = answer.length;
        for (String id:id_list)
        {   // id에 리폿한사람을 해시맵에 넣음
            users.put(id, Arrays.stream(report).filter(fl->fl.contains(id)).filter(fl -> fl.split(" ")[0]
                            .equals(id)).map(mp-> mp.replace(MessageFormat.format("{0} ", id), "")).distinct()
                    .collect(Collectors.toList()));
            //넣으면서 신고한애들 몇번 당했는지 기록
            for (String user : users.get(id)) {
                Integer getCountValue = reportCount.get(user);
                reportCount.put(user,getCountValue==null ? 1 : getCountValue+1);
            }
        }


        //일정이상 신고당했다면 벤리스트에 넣음
        Arrays.stream(id_list).collect(Collectors.toList()).forEach(x-> {
            Integer getCountValue = reportCount.get(x);
            if(getCountValue!=null && getCountValue>=k)
                banList.add(x);
        });

        //횟수입력
        for (int i = 0 ; i<answer_Length; i++)
        {
            answer[i]= (int) users.get(id_list[i]).stream().filter(fl-> banList.indexOf(fl)!= -1).count();
        }
        return answer;

    }
}
