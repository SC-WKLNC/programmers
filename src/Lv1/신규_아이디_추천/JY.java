package Lv1.신규_아이디_추천;

public class JY {
    public String solution(String new_id) {
        String answer = "";
        //1단계 lowercase
        String downCase = new_id.toLowerCase();
        //2단계 정규
        String regexScale = downCase.replaceAll("[^a-z\\d\\-_.]","");
        //3단계 .의 2중복이상
        String doubleColonDel = regexScale.replaceAll("\\.{2,}",".");
        //4단계
        String firstColonDel = doubleColonDel.substring(0,1).equals(".")?
                doubleColonDel.substring(1,doubleColonDel.length()) : doubleColonDel;
        String endColonDel = firstColonDel.substring(firstColonDel.length()==0?0:firstColonDel.length(),firstColonDel.length()).equals(".")?
                firstColonDel.substring(0,doubleColonDel.length()-1):firstColonDel;
        //5단계
        String checkStringEmpty = endColonDel.isEmpty()? "a": endColonDel;
        //6단계
        String checkStringLength = checkStringEmpty.length()>=16?checkStringEmpty.substring(0,15) :checkStringEmpty;
        //상기 endcolonDel과 일단 로직은 같음.
        String lastEndColonDel = checkStringLength.substring(checkStringLength.length()-1,checkStringLength.length()).equals(".")?
                checkStringLength.substring(0,checkStringLength.length()-1) : checkStringLength;
        //7단계
        String lengthToThreeScale = lastEndColonDel.length()<3 ? lastEndColonDel.substring(lastEndColonDel.length()-1,lastEndColonDel.length())
                : null;

        String completeScale_id;
        if(lengthToThreeScale !=null) completeScale_id=lastEndColonDel.length()>1? lastEndColonDel+lengthToThreeScale:lastEndColonDel+lengthToThreeScale+lengthToThreeScale;
        else completeScale_id = lastEndColonDel;

        answer = completeScale_id;

        return answer;
    }
}
