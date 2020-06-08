package chap03_Method.exams

/*
 * 문) 두 실수를 인수로 받아서 나눗셈 연산 후 실수값으로 반환하는  div 메서드 정의하기
 *    함수명 : div()
 */

object Exam01 {
  
  //  메서드 정의
  def div(x : Float, y : Float): Float = { // 리턴 타입(Double > Float)
    var di = x / y
    return di // 리턴값
  }
  
  
  def main(args: Array[String]): Unit = {
    //  메서드 호출
    var x : Float = 10.5f
    var y : Float = 2.5f
    var di = div(x, y)
    printf("x = %.3f, y = %.3f, x / y = %.3f", x, y, di)
    // x = 10.000, y = 20.000, x / y = 0.500
  
  }
  
}


