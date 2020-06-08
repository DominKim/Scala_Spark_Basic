package chap02_Control

/*
 * 형식1)
 * for(변수 <- 컬렉션){
 * 		반복문1
 * 		반복문2
 * }
 * 
 * - 컬렉션(collection) : 열거형 data를 저장하는 자료구조(Array, List)
 * - 제너레이터 식 : (변수 <- 컬렉션)
 * 
 * 형식2)
 * for(변수 <- 컬렉션if 조건식) 반복문
 *   - 가드(guard) : 조건에 만족하는 경우만 반복문 실행
 */
object Step02_Control {
  
  def main(args: Array[String]): Unit = {
    
    // for 형식1)
    var tot = 0 // var tot : Int = 0
    // start until stop-1 : 정수 컬렉션 생성
    for(i <- 1 until 11){ // 1 ~ 10 : range(n) 유사함
      print(i + " ") // 같은 line 중복 출력
      tot += i // 누적변수
    } // for end
   
    println("\ntot = " + tot)
   
    // start to stop : 정수 컬렉션 생성
    tot = 0
    for(i <- 1 to 10){
      // format() 함수
      print("i = %d, tot = %d\n".format(i, tot))
      tot += i
    } // for end
    println("tot = "+tot)
    
    // List 컬렉션
    var dogList = List("진돗개-한국", "셰퍼트-독일", "불독-독일","풍산개-한국")
    // 불록 없는 for문
    for(dog <- dogList) println(dog)
    
    // for 형식2) 가드 포함 
    for(dog <- dogList if(dog.contains("한국"))) println(dog)
    // python : [for 변수 in 열거형 if 조건식]
    
    // 조건식 : and 연산자 적용
    println("조건식 : and 연산자 적용")
    for(dog <- dogList if(dog.contains("한국") && dog.startsWith("진돗개"))) println(dog)
    
    
    // 조건식 : or 연산자 적용
    println("조건식 : or 연산자 적용")
    for(dog <- dogList if(dog.contains("한국") || dog.startsWith("진돗개"))) println(dog)
    
    
    /*
     * 문) 가드 문법을 적용하여 다음결과를 추력하시오.
     * 진돗개-한국
     * 세퍼드-독일
     * 불독-독일
     */
    println("문제")
    for(dog <- dogList if(dog.contains("독일") || dog.startsWith("진돗개"))) println(dog)
    
    // yield(양보)
    //var 변수 = for(변수 <- 컬렉션 if 조건식) yield
    var dogVar = for(dog <- dogList if(dog.contains("독일") || dog.startsWith("진돗개"))) yield dog
    println("dogVar = " + dogVar) // dogVar = List(진돗개-한국, 셰퍼트-독일, 불독-독일)
    // 변수 = [실행문 for 변수 in 열거형 if 조건식]
  } 
}











