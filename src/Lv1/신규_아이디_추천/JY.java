package 신규아이디추천;

public class JY {
    public String solution(String new_id) {
        String answer = "";
        //1단계 lowercase
        String downScale_id = new_id.toLowerCase();
        //2단계 정규
        String regexScale_id = downScale_id.replaceAll("[^a-z\\d\\-_.]","");
        //3단계 .의 2중복이상
        String dubleColonScale_id = regexScale_id.replaceAll("\\.{2,}",".");
        //4단계
        String firstColonScale_id = dubleColonScale_id.substring(0,1).equals(".")?
                dubleColonScale_id.substring(1,dubleColonScale_id.length()) : dubleColonScale_id;
        String endColonScale_id = firstColonScale_id.substring(firstColonScale_id.length()==0?0:firstColonScale_id.length(),firstColonScale_id.length()).equals(".")?
                firstColonScale_id.substring(0,dubleColonScale_id.length()-1):firstColonScale_id;
        //5단계
        String nullCheckScale_id = endColonScale_id.equals("")? "a": endColonScale_id;
        //6단계
        String lengthCheckScale_id = nullCheckScale_id.length()>=16?nullCheckScale_id.substring(0,15) :nullCheckScale_id;
        String afterLengthCheckEndColonScale_id = lengthCheckScale_id.substring(lengthCheckScale_id.length()-1,lengthCheckScale_id.length()).equals(".")?
                lengthCheckScale_id.substring(0,lengthCheckScale_id.length()-1) : lengthCheckScale_id;
        //7단계
        String lengthToThreeScale = afterLengthCheckEndColonScale_id.length()<3 ? afterLengthCheckEndColonScale_id.substring(afterLengthCheckEndColonScale_id.length()-1,afterLengthCheckEndColonScale_id.length())
                : null;

        String completeScale_id;
        if(lengthToThreeScale !=null) completeScale_id=afterLengthCheckEndColonScale_id.length()>1? afterLengthCheckEndColonScale_id+lengthToThreeScale:afterLengthCheckEndColonScale_id+lengthToThreeScale+lengthToThreeScale;
        else completeScale_id = afterLengthCheckEndColonScale_id;

        answer = completeScale_id;

        return answer;
    }
}
