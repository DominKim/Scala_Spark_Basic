package chap06_Collection

/*
 * 바인딩 메서드(Binding method)
 *  - 컬렉션 객체에서 호출 가능한 함수
 *  형식) object.method1().method2()
 *  - 원소를 순차적으로 method1 넘김 → ㅡmethod1(처리) → method2(처리)
 *  - 자체 제너레이터(반복) 기능 포함
 */


object Step04_Binding {
  
  
  def main(args: Array[String]): Unit = {

    // 1. 컬렌션 객체 생성 
    // var nums = 1 to 20 // 숫자 컬렌션 생성
    
    val nums = List(1,2,3,4,5,11,2,13,7) // List 컬렉션
    println(nums.size) // 20
    
    // 3. 바인딩 메서드
    
    // 1) object.foreach(func) : object 원소를 순차적으로 받고, func 자료 처리
    println("foreach")
    nums.foreach((x: Int) ⇒  print(x + " ")) // 무명함수 : (x) ⇒ x + 1
    
    // 2) object.map(_매핑연산자) : object 원소를 순차적으로 받고(_), 연산 수행
    println("map")
    var map_re = nums.map(_*2) // 1*2 = 2, 2*2=4
    println(map_re) // Vector(2, 4, 6, 8, 10, 12, 14, 16, 18, 20, 22, 24, 26, 28, 30, 32, 34, 36, 38, 40)
    
    // 3) object.filter(_조건식) : object 원소를 순차적으로 받고(_), 조건에 따라서 필터링
    var filter_re = nums.filter(_%2 == 0)
    println(filter_re) // Vector(2, 4, 6, 8, 10, 12, 14, 16, 18, 20)
    
    // 4) object.filter().map()
    var filter_map_re = nums.filter(_>10).map(_*0.5)
    println(filter_map_re) // Vector(5.5, 6.0, 6.5, 7.0, 7.5, 8.0, 8.5, 9.0, 9.5, 10.0)
  }
}
















