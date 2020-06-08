package chap04_FileRead_except

// io 패키지 import
import scala.io.Source // file source read
import java.io.FileNotFoundException // 예외처리


object Step02_file_read {
  
    // iris.csv -> read 함수 정의
    def csv_load(filename : String) : Unit = {
      try{
        val fileRead = Source.fromFile("C:/ITWILL/5_Hadoop_Spark/workspace/part1_Scala/src/fileDir/"+filename)
        println("iris.csv load 성공")
        var lines = fileRead.getLines().drop(1) // 제목줄 제거(.drop(1)), 전체 줄 단위 읽기
        for(line <- lines){
          // 한 줄 읽기 -> 콤마 기준 토큰 생성 -> 토큰 앞 / 뒤 공백제거 -> 변수 저장
          var cols = line.split(",").map(_.trim) // line -> column
          
          // 컬럼단위 출력 : s"${컬럼}"
          println(s"${cols(0)}, ${cols(1)}, ${cols(2)}, ${cols(3)}, ${cols(4)}") 
        }
        fileRead.close // file 객체 닫기
      }catch{
        case ex : FileNotFoundException => println("예외정보 : " + ex)
      }
    }
  
    def main(args: Array[String]): Unit = {
    
      try{
        val fileRead = Source.fromFile("C:/ITWILL/5_Hadoop_Spark/workspace/part1_Scala/src/fileDir/scala_object.txt")
        println("file 읽기 성공")
        
        // 줄단위 전체 읽기
        var lines = fileRead.getLines()
        for(line <- lines) println(line)
        fileRead.close
      }catch{
        case ex : FileNotFoundException => println("예외정보 : " + ex)
      }
      
      // 함수 호출
      csv_load("iris.csv")
  }
}