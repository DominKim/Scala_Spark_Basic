package chap05_Class_import

// io 패키지의 1개 멤버 사용
import scala.io.Source
// io 패키지의 전체 멤버 사용
import scala.io._
// io 패키지의 일부분 멤버 사용
import scala.io.{BufferedSource, Source}

// 사용자 클래스 import
import class_test.{TestClass1, TestClass2}


/*
 * 1. class vs object
 * - class  : 해당 클래스를 이용해서 다수 객체(object) 생성
 *   형식) var 변수 = new Class
 * - object : 해당 클래스를 이용해서 1개 객체(object) 생성(싱글톤)
 * 
 * 2. class 유형
 *  - class      : new 키워드로 다수 객체 생성
 *  - case class : 생성자(객체+초기화) 이용 다수 객체 생성
 */


// case class 선언
case class Car(var name : String, var cc : Double)


object class_import {
  
  // 프로그램 싯작점
  def main(args: Array[String]): Unit = {
   
    // 1. class 형식
    var tc1_1 = new TestClass1 // object1
    println(tc1_1.display())   // object.method()
    
    var tc1_2 = new TestClass1 // object2
    println(tc1_2.display())
    
    var tc2 = new TestClass2 // object3
    println(tc2.display())
    
    // 2. case class 형식
    var car1 = Car("쏘나타", 2.5) // 생성자 -> object1
    var car2 = Car("그랜저", 3.0) // 생성자 -> object2
    println("car1_name = " + car1.name)
    println("car1_name = %s, cc = %s".format(car1.name, car1.cc))
    println("car2_name = " + car2.name + car2.cc)
 }
}














