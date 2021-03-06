package chap03_Method.exams

/*
 * 정수의 원소(1~10)를 갖는 Array 컬렉션을 인수로 갖는 함수 정의하기
 *  <함수1> 함수의 반환값 있음, 반환값으로 전체 원소의 합계 출력
 *     출력 결과 : sum = 55
 *  <함수2> 함수의 반환값 없음, 전체 원소 중에서 홀수 출력  
 *     출력 결과 : 1  3  5  7  9  
 */

object Exam02 {
 
  // <함수1> : func1 
  def func1(arrNum : Array[Int]): Int = {
    var tot = 0
    for (i <- arrNum ) tot += i
    return tot
  }
  
  // <함수2> : func2
 def func2(arrNum : Array[Int]): Unit = {
   for(i <- arrNum if (i % 2 == 1)) print(i)
 }

  
  
  def main(args: Array[String]): Unit = {
    // Array 컬렉션 : 실인수 데이터  
    var arrNum = Array(1,2,3,4,5,6,7,8,9,10) 
    println("fun1 = " + func1(arrNum))
    func2(arrNum)
    
  }
    
}



