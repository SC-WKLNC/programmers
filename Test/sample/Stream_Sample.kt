package sample

fun main(){
/*   val mapSample = Map_Sample()
    mapSample.sample3()*/

/*    val filterSample = fiter_Sample()
    filterSample.sample5()*/


    val dropSample = Drop_Sample()
    dropSample.sample3()
}
//map 는 컬렉션 내 인자를 다른 값 혹은 타입을 변경할때 사용
class Map_Sample {
    var items = listOf<String>("User","boo","User1","test1","a","User2","User3","test2")
    fun sample1()
    {
        val sampleItems = items
         sampleItems.map { item -> item.toUpperCase() }.forEach { println(it) }
    }
    fun sample2()
    {
        val sampleItems = items
        sampleItems.map { item -> item.length }.forEach { println("length = ${it}") }
    }
    fun sample3()
    {
        //컬렉션의 인자를 반환하지만 null인 경우 무시한다.
        val sampleItems = items
        //인자를 반환하지만 test라는 문자열이있다면 반환하고 아니라면 null을 반환해라
        sampleItems.mapNotNull { item-> if(item.contains("test")) item else null }.forEach { println(it) }
    }
}

//컬렉션 인자중 조건과 일치하는것만 통과시켜준다
class fiter_Sample {
    var items = listOf<String>("User", "boo", "User1", "test1", "a", "User2", "User3", "test2")
    fun sample1() {
        items.filter { item -> item.length >= 5 }.forEach { println(it) }
    }

    //take 는 마치 인덱스 슬라이싱과 같다. 어디서부터 매개변수까지 뭘하겠다
    fun sample2() {
        items.take(1).forEach { println(it) }
        //0번부터 3번까지의 값을 리턴한다.
        items.take(3).forEach { println(it) }
    }

    fun sample3() {
        //마지막 인자부터 3개
        items.takeLast(3).forEach { println(it) }
    }

    fun sample4() {
        //조건에 해당하는 값을 반환을 하다가 해당하지않는 값이 나오면 중단한다.
        items.takeWhile { item -> item.length <= 4 }.forEach { println(it) }
    }

    fun sample5() {
        //조건에 해당하는 값을 뒤에서 부터 보면서 반환을 하다가 해당하지않는 값이 나오면 중단한다.
        items.takeLastWhile { item -> item.length == 5 }.forEach { println(it) }
    }
}
    //take 함수의 반대역활로 조건에 만족하는 항목을 제외한 나머지를 반환한다.
    class Drop_Sample
    {
        var items = listOf<String>("User","boo","a","User1","test1","User2","User3","test2")
        fun sample1()
        {
            //한개의 항목을 제외한 나머지 출력
            items.drop(1).forEach { println(it) }
        }
        fun sample2()
        {
            //items.dropWhile { item-> item.length<5 }.forEach { println(it) }
           // println(items.dropWhile { item-> item.length<5 }.first())
            items.dropWhile { item-> item.length<5 }.distinct().forEach { println(it) }
        }
        fun sample3()
        {
            //items.dropWhile { item-> item.length<5 }.forEach { println(it) }
            // println(items.dropWhile { item-> item.length<5 }.first())
            items.indices.forEach { println(it) }
        }

    }

