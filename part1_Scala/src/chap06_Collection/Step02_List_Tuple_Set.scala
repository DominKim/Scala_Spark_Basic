package chap06_Collection

/*
 * 1. List 컬렉션 특징
 *  - 수정 불가(값 초기화)
 *  - 순서 존재(index)
 *  - 중복 허용
 *  - 동일 자료형
 *  형식) val 변수 = List(값1, 값2, ....)
 *  
 * 2. Tuple 컬렉션 특징
 *  - Tuple 클래스 없음(기호 이용)
 *  - 수정 불가(값 초기화)
 *  - 순서 존재(index)
 *  - 서로 다른 자료형 저장 
 *  형식) val 변수 = (값1, 값2, ....)
 * 
 * 3. Set 컬렉션 특징
 *  - 수정 불가 or 수정 가능(import)
 *  - 순서 없음, 중복 불가
 *  형식) val 변수 = Set(값1, 값2, ...)
 *  
 */

import scala.collection.mutable // 수정 가능한 컬렉션 객체 

object Step02_List_Tuple_Set {
  
  def main(args: Array[String]): Unit = {
    // 1. List 컬렉션
    val num = List(1,2,3,4,5,1.5,2,3,7)
    println("num size = " + num.size) // num size = 9
    
    println(num) // List(1, 2, 3, 4, 5, 1, 2, 3, 7)
    println(num.mkString(" ")) // 1 2 3 4 5 1 2 3 7
    println(num(0)) // index
    
    // 원소 수정
    // num(num.size - 1) = 70 // error
    
    val num2 = List.range(1, 11)
    
    for(n <- num2) print(n + " ") // 1 2 3 4 5 6 7 8 9 10
    println()
    
    // 2. Tuple 컬렉션
    val names = ("홍길동", 35, "이순신", 45, "유관순", 25)
    println(names) // (홍길동,35,이순신,45,유관순,25)
    println(names._1) // 홍길동 : object._index
    println(names._2) // 35
    
    // 제너레이터 식 : error
    // for(name <- names) print(name + "")
    
    // 3. Set 컬렉션
    val num3 = Set(1,2,3,4,5,1,2,3)
    println("num3 size = " + num3.size) // num3 size = 5 : 중복 불가
    println(num3) // Set(5, 1, 2, 3, 4) : 순서 없음
    
    // 문장 → 단어 추출
    val texts = "kim hong! kim, park, hong"
    val wordArr = texts.split("[ !,.]+") // 공백, 특수문자 → word
    // println("word : " + wordArr)
    
    for(word ← wordArr) print(word + " ") // kim hong kim park hong 
    
    println()
    // 수정 가능한 Set 컬렉션 생성
    val words = mutable.Set.empty[String] // String 원소를 갖는 Set 객체 생성
    for(word ← wordArr){
      words += word // Set 저장
    }
    
    // set 컬렌션보기
    println(words) // Set(hong, park, kim)
    println("단어수 : " + words.size) // 단어수 : 3
  }
}











