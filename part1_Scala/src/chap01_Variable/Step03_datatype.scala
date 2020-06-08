package chap01_Variable

/*
 * Scala 기본 자료형
 *  Int     : 정수형(4) : default
 *  Long    : 정수형(8)
 *  Float   : 실수형(4)
 *  Double  : 실수형(8) : default
 *  String  : 문자형(문자 조합)
 *  Char    : 단일문자형(1개 문자)
 *  Boolean : 논리형(True or False)
 */
object Step03_datatype {
   
  def main(args: Array[String]): Unit = {
    val calc : Int = 100*4 / 3 // 실수 -> 정수형
    // val calc2 : Int = 100*0.25 / 3 // error 발생 (Int 정수형으로만 구성가능)
    println("calc = " + calc)
    
    val calc_long : Long = 1000000*2
    println("calc_long = " + calc_long, calc_long.getClass()) // (calc_long = 2000000,long)
    
    val calc_re : Float = 100*4 / 3 // 실수 -> 실수형
    println("calc_re = " + calc_re)
    
    val calc_re2 = 100*2.5 / 3 // 실수 -> 실수형
    println("calc_re2 = " + calc_re2) // calc_re2 = 83.33333333333333
    // format 이용, 줄바꿈 없음
    printf("calc_re2 = %.5f", calc_re2)
    println() // line skip
    
    println("type = " + calc_re2.getClass()) // type = double
    
    // 실수형 변수 선언
    val x : Double = 2.4
    val a : Float = 0.5f
    val b : Float = 1.0f
    
    val y_pred = x * a + b
    println("y_pred = " + y_pred) // y_pred = 2.2
    
    // 논리형 변수 선언
    var bool_re : Boolean = 2.5f == y_pred // 논리식
    println("bool_re = " + bool_re) 
    
    // 문자형과 단일문자형
    var strVar : String = "우리나라 대한민국"
    var charVar : Char = '우' // 단일문자형
    println(strVar)
    println(charVar, charVar.getClass()) // (우,char)
    
  }
}







 
