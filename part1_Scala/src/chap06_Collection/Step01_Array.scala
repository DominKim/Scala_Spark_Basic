package chap06_Collection

/*
 * 컬렉션(collection)
 *  - 데이터 집합 의미
 *  - 수정 여부, 순서 보장(index), 중복 허용 등으로 분류
 *  
 * Array 컬렌션 특징
 *  - 수정 가능
 *  - 순서 존재 : index 사용
 *  - 중복 허용
 *  - 주의 : 동일 type 저장 가능
 *  형식) var 변수 : Array[type] = new Array[type](size)
 */

object Step01_Array {
   
  def main(args: Array[String]): Unit = {
    
    // 1. new 명령어 객체 생성
    var arr : Array[Int] = new Array[Int](5) // 1) object
    arr(0) = 10// 2) arr(index) = 값
    arr(1) = 20
    arr(2) = 30
    arr(3) = 40
    arr(4) = 50
    
    // 원소 수정
    arr(4) = 500
    for(i <- arr) print(i + " ") // 10 20 30 40 50

    println() // line skip

    // 2. 객체 생성 + 초기화
    var arr2 = Array(10, 20, 33, 40, 50)
    
    for(i <- arr2 if(i % 2 == 0)) print(i + " ") // 10 20 40 50 
    
    
    // 3. Array 생성 축약형
    var arr3 = new Array[Double](50) // 1) object
    
    // index : 0 ~ 49 -> start until stop-1
    var idx = 0 until 50
    for(i <- idx){
      var r = Math.random() // 0 ~ 1
      arr3(i) = r // 1) data 삽입
    }
    
    // 컬렉션 원소 출력
    var cnt = 0
    for(a <- arr3 if(a >= 0.5 && a <= 0.8)){
      print(a.round + " ")
      cnt += 1
    } 
    print()
    
  }
}











