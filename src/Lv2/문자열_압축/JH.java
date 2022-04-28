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
        String s = "abcabcabcabcdededededede";
        JH jh = new JH();
        jh.solution(s);
    }
    public int solution(String s) {
        int answer = 0;

        Compressor compressor = new Compressor(s);
        int optimumValue = compressor.getOptimumSize();

        return answer;
    }


    class Compressor{

        private String originalText;
        private char[] originalTextChars;
        private int maxCompressorSize;

        public Compressor(String originalText){
            this.originalText = originalText;
            this.originalTextChars = originalText.toCharArray();
            this.maxCompressorSize = originalText.length()/2;
        }

        //최적의 압축 길이를 리턴한다.
        public int getOptimumSize(){

            //1 부터 압축 비율을 반복한다.
            for (int i = 1; i <= maxCompressorSize; i++) {
                //나누어 떨어지지 않는 압축 비율은 무시한다.
                if(maxCompressorSize%i != 0) continue;
                //압축률 만큼 문자를 뺸다.
                Queue<String> originalQueue = getOriginalQueue(i);

            }



            //뒷문자가 꺼낸 문자와 같다면 또 뺀다.

            return 0;
        }

        //전제 문자를 길이만큼 짤라서 큐에 넣는다.
        private Queue<String> getOriginalQueue(int compressorSize){
            Queue<String> queue = new LinkedList<>();
            int forSize = originalTextChars.length;
            System.out.print(">>>>> ");

            for (int i = 0; i < forSize; i+=compressorSize) {

                String str = "";
                for(int j = i; j < (i+compressorSize); j++){
                    str += originalTextChars[j];
                }
                System.out.print("/"+str);
                queue.add(str);
            }
            System.out.println("");
            return queue;
        }

    }
}
