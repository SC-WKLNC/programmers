package sample


fun main()
{
    val items = listOf<String>("user1","user2","user3","user4")
    val forSample = For_Sample(items)
    println("1>>>>>")
    forSample.sample1()
    println("2>>>>>")
    forSample.sample2()
    println("3>>>>>")
    forSample.sample3()
    println("4>>>>>")
    forSample.sample4()
    println("5>>>>>")
    forSample.sample5()
}
class For_Sample(val items:List<String>) {

    //리스트 의 요소를 하나씩 꺼내서 item 에 담는다.
    fun sample1()
    {
        for(item in items){
            println(item)
        }
    }
    //0 부터 리스트 사이즈 -1 까지 의 인덱스 를 돌면서 요소를 가져 온다.
    fun sample2()
    {
        for(index in 0..(items.size-1)){
            println(items[index])
        }
    }
    //타입의 표현이 애매하기때문에 until 를 붙힌다.
    fun sample3()
    {
        for(index in 0 until items.size){
            println(items[index])
        }
    }
    //최소 인덱스 부터 최대 인덱스 까지 순회 한다.
    fun sample4()
    {
        for(index in items.indices){
            println(items[index])
        }
    }

    //최소 인덱스 부터 최대 인덱스 를 리스트로 반환
    fun sample5()
    {
        val tmpList = items.indices.toList()
        tmpList.forEach { println(it) }
    }

}