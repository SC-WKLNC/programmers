package Lv1.숫자_문자열과_영단어;

import java.util.HashMap;
import java.util.concurrent.atomic.AtomicReference;

public class JY {
    class Solution {

        //hashmap 생성 및 초기화
        HashMap<String,String> numberMap = new HashMap<>();

        public int solution(String s) {
            int answer=0;
            // hashmap에 값넣음
            initMap();
            String result = s;
            // 값을 넣고
            result =convertToNumber(s);
            //정수아닌값있으면 0으로 리턴
            if (!vaildStringtoint(result)) return 0;
            //int로변환
            answer =Integer.parseInt(result);
            return answer;
        }

        public boolean vaildStringtoint(String s)
        {
            return s.matches("[0-9]+");
        }

        public void initMap()
        {
            numberMap.put("zero","0");
            numberMap.put("one","1");
            numberMap.put("two","2");
            numberMap.put("three","3");
            numberMap.put("four","4");
            numberMap.put("five","5");
            numberMap.put("six","6");
            numberMap.put("seven","7");
            numberMap.put("eight","8");
            numberMap.put("nine","9");
        }

        public String convertToNumber(String _inputValue)
        {
            //Atomic 선언
            AtomicReference<String> tempValue = new AtomicReference<>();
            //Atomic 값 Set
            tempValue.set(_inputValue);
            // map에 있는 key와 같은값이 있는지 확인 그값을 replace
            numberMap.entrySet().stream().filter(fi->tempValue.get().contains(fi.getKey())).
                    forEach(fe->tempValue.set(tempValue.get().replace(fe.getKey(),fe.getValue())));
            return tempValue.get();
        }
    }
}
