package Lv2.오픈채팅방;

public class JH {
}
//class Solution {
//    fun solution(record: Array<String>): Array<String> {
//        // id와 닉네임의 매칭을 위한 해시맵
//        var idMap = HashMap<String,String>()
//        // id 의 히스토리 저장
//        var history = mutableListOf<HistoryItem>()
//        for(data in record){
//            val list = data.split(" ")
//            when( list[0] ){
//                "Enter" -> {
//                    idMap[list[1]] = list[2]
//                    history.add(HistoryItem(id =list[1], action = "님이 들어왔습니다." ))
//                }
//                "Leave" -> {
//                    history.add(HistoryItem(id =list[1], action = "님이 나갔습니다." ))
//                }
//                "Change" ->{
//                    idMap[list[1]] = list[2]
//                }
//            }
//
//        }
//
//
//        return resultMapper(idMap, history)
//    }
//    private fun resultMapper(idMap:HashMap<String,String>, history:MutableList<HistoryItem>):Array<String>{
//        var result = history.map { "${idMap[it.id]}${it.action}" }
//        return result.toTypedArray()
//    }
//}
//data class HistoryItem(
//        var id :String,
//        var action :String
//)