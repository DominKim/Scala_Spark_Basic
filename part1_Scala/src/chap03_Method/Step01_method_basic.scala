package chap03_Method

/*
 * method vs function
 * method : class or object에서 선언한 함수(객체지향언어)
 * function : 단독으로 선언한 함수(함수 지향언어)
 * 
 * 형식)
 * def 함수명(인수 : type): 리턴타입 = {
 *     실행문1
 *     실행문2
 *     [return 반환값]
 * }
 * 
 * 반환값이 없는 경우 : 리턴타입 = Unit
 */


object Step01_method_basic {
  
  
  // max method
  def max(x: Int, y: Int): Int = {
    // x = 25 -> 인수(매개변수) = val -> 수정불가
    var max_re = if(x > y) x else y
    // return 생략
    return max_re
  }
  
  // adder method : 반환값 있는 경우
  def adder(x : Float, y : Float) : Float = {
    val add : Float = (x+y) * 0.5f
    return add // return 값
  }
  
  // 반환값 없는 method
  def adder2(x : Float, y : Float) : Unit = {
    val add : Float = (x+y) * 0.5f
    println("adder = " + add)
  }
  
  // PI = 3.14159
  def getPI(): Double = {
    return 3.14159 // 상수 선언 -> default : double
  }
  
  // return, {} 생략
  def getPI2() : Double = 3.14159
  
  // 인수 없는 함수 : (), return, {} 생략
  def getPI3 : Double = 3.14159
  
  // 시작점 : main method 
  def main(args: Array[String]): Unit = {
    println("max method")
    val x = 20; val y = 15
    var max_re = max(x, y)
    // max method 호출
    println("max = " + max_re) // max = 20
    
    // adder method 호출
    var adder_re = adder(15f, 20f)
    println("adder = " + adder_re) // adder = 17.5
    
    // adder2 method 호출
    adder2(1.5f, 25.5f)
    
    // getPI method 호출
    println("PI = " + getPI()) // PI = 3.14159
    
    println("PI = " + getPI2()) // PI = 3.14159
    
    println("PI = " + getPI3) // PI = 3.14159
  }
}










