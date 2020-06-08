package chap06_Collection

/*
 * Map 컬렌션 틱징
 *  - python의 dict와 유사함
 *  - (key → value)
 *  - 수정 불가 / 수정 가능
 *  - key를 통해서 value 접근
 *  형식) Map("Key" → vlaue, "key" → value, ....)
 */

import scala.collection.mutable // 수정가능 Map 컬렉션 생성

object Step03_Map {
  
  def wc(texts : String) : Unit = {
    var word_count = mutable.Map.empty[String, Int] // [key, value]
    
    // "Kim, Hong! Kim, Hong, You" → split("[ ,!]+")
    var words = texts.split("[ ,!]+")
    println(words) // [Ljava.lang.String;@7cc355be
    println(words.mkString(",")) // Kim,Hong,Kim,Hong,You
    
    for(wd ← words){
      var word = wd.toLowerCase() // 소문자 변경
      // word 출현빈도수
      var word_value
      = if(word_count.contains(word)) // 기존 출현 word
        word_count(word) // key → value
        else  0 // 최초 출현 word
      
      // 수정 가능한 map에 (key → value) 저장
      word_count += (word → (word_value + 1))
    } // for end
    
    // word count 결과
    println(word_count)
  } // func end
  
  def main(args: Array[String]): Unit = {
    
    // 1. 수정 불가
    val map_list = Map("one" → 1, "two" → 2)
    println(map_list) // Map(one -> 1, two -> 2)
    println(map_list.size) // 2
    println(map_list("two")) // key → value
    
    // Map("key" → (값1, 값2, 값3))
    val emp = Map(1001 → ("홍길동", 250, 50), 1002 →("이순신", 350, 100), 1003 →("유관순", 200, 40))
    println(emp)
    println(emp(1002)) // key → value
    
    for(e ← emp.keys){
      println(e) // key
      println(emp(e)) // value
    }
    
    // 2. 수정 가능
    val texts = "Kim, Hong! kim, Hong, You"
    
    // 함수 호출
    wc(texts)
  }
}









