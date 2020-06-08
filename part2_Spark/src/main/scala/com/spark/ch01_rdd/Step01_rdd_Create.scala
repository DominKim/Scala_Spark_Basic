package com.spark.ch01_rdd

import org.apache.spark.{SparkConf, SparkContext}

object Step01_rdd_Create {
 def main(args: Array[String]): Unit = {
    
    // 1. SparkContext object 생성
    val conf = new SparkConf()
      .setAppName("SparkTest")
      .setMaster("local") // spark 환경 객체
            
    // 2. SparkContext object 생성 : rdd data 생성
    val sc = new SparkContext(conf) // 분산 파일 읽기
    
    // 1. 파티션 지정 RDD 생성 : parallelize(data, 파티션수) 이용
    val rdd1 = sc.parallelize(1 to 100, 5) // data = 정수컬렉션
    println(rdd1) // ParallelCollectionRDD[0] at parallelize at Step01_rdd_Create.scala:17
    
    // object.bind()
    rdd1.foreach((x : Int) ⇒ print(x + " ")) // 무명함수
    
    val rdd2 = sc.parallelize(List("a", "b", "c", "d", "e")) // data = LIst Collection
    println(rdd2) // ParallelCollectionRDD[1] at parallelize at Step01_rdd_Create.scala:23
    
    rdd2.foreach((x : String) ⇒ print(x + " ")) // a b c d e
    
    // 2. 외부 저장 매체(file, HDFS) 데이터 이용 RDD 생성 : spark_home/README.md
    val rdd3 = sc.textFile("file:/C:/hadoop-2.6.0/NOTICE.txt")
    println(rdd3)
    
    // 텍스트 파일에서 1줄 → RDD 1개 원소
    rdd3.foreach((x : String) ⇒ println(x)) // 줄 단위 출력
    /*
     * This product includes software developed by The Apache Software
     * Foundation (http://www.apache.org/).
     */
    
    // 객체 닫기
    sc.stop
  } // main end
}








