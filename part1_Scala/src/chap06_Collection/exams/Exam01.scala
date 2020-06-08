package chap06_Collection.exams

/*
 * 문) Array 컬렉션 객체 생성 
 * 단계1 : 실수 100를 저장할 수 있는 Array 객체 생성
 * 단계2 : 난수 실수 100개를 생성하여 Array 객체에 저장
 * 단계3 : 최댓값/최솟값 출력하기   
 */
object Exam01 {
  
  def main(args: Array[String]): Unit = {
      
      // 단계1 : 실수 100를 저장할 수 있는 Array 객체 생성
      val arr : Array[Double] = new Array[Double](100)
      
      
      // 단계2 : 난수 실수 100개를 생성하여 Array 객체에 저장
      var idx = 0 until 100
      for(i <- idx) arr(i) = Math.random()
      
      
      // 단계3 : 최댓값/최솟값 출력하기
      var max_num = arr(0)
      var min_num = arr(0)
      for(i <- arr){
        if(i > max_num) max_num = i
        if(i < max_num) min_num = i
      }
      
      println("max_num = %f, min_num = %f".format(max_num, min_num))
    }
  
  
}