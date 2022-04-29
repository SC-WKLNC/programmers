package Lv2.문자열_압축;

import java.util.*;
import java.util.stream.IntStream;

public class JY {

    public static void main(String[] ags)
    {
        Solution s = new Solution();
        String test1 = "aaaaaaaaaa";
        System.out.println(s.solution(test1));
    }

    static class Solution {
        public int solution(String s) {
            // init map
            SplitMap map = new SplitMap(s).addSplitMap();
            // map Count 가 0 이면 원래 string의 length를 반환
            if(map.getCount()==0) return s.length();
            // 생성된 맵으로 LengthList를 생성하여 가장 낮은값을 반환
            MergeList margeList = new MergeList(map).setList();
            return margeList
                    .getList()
                    .stream()
                    .mapToInt(Integer::intValue)
                    .min()
                    .getAsInt();
        }
        public class SplitMap {
            private final String splitTarget;
            private final int targetLength;
            private HashMap<Integer,List<String>> subStringMap = new HashMap<>();
            public SplitMap(final String splitTarget)
            {
                this.splitTarget= splitTarget;
                this.targetLength = splitTarget.length();
            }
            public int getCount()
            {
                return subStringMap.size();
            }
            // 맵생성시 랭스가 0,1이면 반환
            private SplitMap addSplitMap()
            {
                if(targetLength==1|| targetLength==0) return this;
                IntStream.range(1,targetLength)
                        .forEach(fe->subStringMap.put(fe,addList(splitTarget,fe)));
                return this;
            }
            //자르려는 랭스만큼 intstream을 만들어서 그대로돌림
            private List<String> addList(final String targetString,final int drainNum)
            {
                List<String> subList= new ArrayList<>();
                IntStream.iterate(0,i->i+drainNum)
                        .limit((targetLength/drainNum)+1)
                        .forEach(fe->subList.add(subString(targetString,fe,drainNum)));
                return subList;
            }
            // string을 start , end로 자름
            private String subString(String targetString,int start,int end)
            {
                // 최대 랭스보다 길경우 시작점과 쵀대랭스를 자름
                if(start+end > targetLength) return targetString.substring(start,this.targetLength);
                return targetString.substring(start,start+end);
            }
            public HashMap<Integer,List<String>> getSplitMap()
            {
                return subStringMap;
            }
        }
        public class LengthCalculrater
        {
            private final List<String> targetStringList;
            private final int listLength;
            private int targetStringLength;
            private boolean isDuflxLengthAdd = true;
            private int duflxCount = 1;
            private int lastaddDuflxCount =0;

            public LengthCalculrater(final List<String> list) {
                this.targetStringList = list;
                this.listLength = list.size();
                this.targetStringLength = list.get(0).length();
            }
            //같은 문자가 있는지 listlength만큼 돌림
            private LengthCalculrater checkSameString ()
            {
                IntStream.range(1,listLength).forEach(fe->{
                    if(targetStringList.get(fe-1).equals(targetStringList.get(fe)))mixLength(true);
                    else mixLength(targetStringList.get(fe).length());
                });
                return this;
            }
            // 같은문자가 있으면 랭스를 늘림 하지만 10번이상될시에 1을 더추가해야하기때문에 log10을써서 자릿수로 더함.
            protected void mixLength(final boolean isduplicate)
            {
                duflxCount++;
                if(isduplicate && (isDuflxLengthAdd||(int)(Math.log10(duflxCount)+1)>lastaddDuflxCount))
                {
                    targetStringLength+=(int)(Math.log10(duflxCount))+1-lastaddDuflxCount;
                    lastaddDuflxCount =(int)(Math.log10(duflxCount))+1;
                    isDuflxLengthAdd=false;
                }
            }
            // 다른문자면 문자의 길이만큼 넣고 같은문자일때 썻던 변수를 초기화 시킴.
            protected void mixLength(final int addValue)
            {
                isDuflxLengthAdd=true;
                duflxCount =1;
                lastaddDuflxCount=0;
                targetStringLength += addValue;
            }

            public int getLength() {return targetStringLength;}
        }
        public class MergeList
        {
            private final SplitMap splitMap;
            private List<Integer> lenghths = new ArrayList<>();
            public MergeList(SplitMap splitMap)
            {
                this.splitMap = splitMap;
            }
            // 맵을 가져와서 length계산을 돌린뒤 리스트에 추기함.
            private MergeList setList()
            {
                splitMap.getSplitMap()
                        .entrySet()
                        .stream()
                        .forEach(fe-> lenghths.add(new LengthCalculrater(fe.getValue())
                                            .checkSameString()
                                            .getLength()));
                return this;
            }
            public List<Integer> getList()
            {
                return this.lenghths;
            }
        }
    }
}
