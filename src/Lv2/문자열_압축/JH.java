package Lv2.문자열_압축;

import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * 접근방식
 * 문자열을 압축하는것이기때문에 최대 길이 /2 만큼 만 확인하면된다
 * 1의 압축 부터 최대길이/2 까지 모두 압축을 진행하여 가장 높은 압축률을 반환한다.
 */
public class JH {
    public static void main(String[] args) {
        //String s = "aabbaccc";
        //String s = "ababcdcdababcdcd";
        //String s = "abcabcabcabc3dede";
        String s = "abcabcdede";
        JH jh = new JH();
        jh.solution(s);
    }
    public int solution(String s) {
        int originTextSize = s.length();
        int answer = originTextSize;

        Compressor compressor = new Compressor(s);


        for (int i = 1; i <= s.length(); i++) {
            compressor.setCompressorValue(i);
            String optimumValue = compressor.execute();


            if(optimumValue.length() < answer) answer = optimumValue.length();
            if(i >= originTextSize/2) break;
        }

        return answer;
    }


    class Compressor{

        private char[] originalTextChars;
        private int compressorValue = 1;
        public Compressor(String originalText){
            this.originalTextChars = originalText.toCharArray();
        }

        public void setCompressorValue(int compressorValue) {
            this.compressorValue = compressorValue;
        }

        public String execute(){

            StringBuilder builder = new StringBuilder();

            Queue<String> originalQueue = getOriginalQueue(compressorValue);

            while( ! originalQueue.isEmpty()){
                builder.append(getOptimumQueue(originalQueue));
            }

            return builder.toString();
        }
        //앞의 중복을 제거하고 몇번을 제거했는지 반환한다.
        private String getOptimumQueue(Queue<String> queue){
            int result = 0;

            String target = queue.poll();

            while(target.equals(queue.peek())){
                if(result == 0) result = 1;
                queue.poll();
                result++;
            }

            return result == 0 ? target : result+target;
        }

        //전제 문자를 길이만큼 짤라서 큐에 넣는다.
        private Queue<String> getOriginalQueue(int compressorSize){
            Queue<String> queue = new LinkedList<>();
            int forSize = originalTextChars.length;

            for (int i = 0; i < forSize; i+=compressorSize) {

                String str = "";
                for(int j = i; j < (i+compressorSize); j++){
                    if(j >= originalTextChars.length) break;

                    str += originalTextChars[j];
                }
                queue.add(str);
            }
            return queue;
        }

    }
}
