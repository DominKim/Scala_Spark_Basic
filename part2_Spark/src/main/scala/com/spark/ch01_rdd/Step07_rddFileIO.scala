package com.spark.ch01_rdd

import org.apache.spark.{SparkConf, SparkContext}

object Step07_rddFileIO {
  
  def main(args: Array[String]): Unit = {
    
    // 1. SparkContext object 생성
    val conf = new SparkConf()
      .setAppName("SparkTest")
      .setMaster("local") // spark 환경 객체
            
    // 2. SparkContext object 생성 : rdd data 생성
    val sc = new SparkContext(conf) // 분산 파일 읽기
    
    val filePath = "src/main/resources/"
    val rdd = sc.textFile(filePath + "input.txt")
    
    println(rdd.collect.mkString("\n"))
    
    val rdd2 = rdd.flatMap(_.split(" ")) // words
    
    val rdd3 = rdd2.filter(_.size > 3)
    
    val wc = rdd3.map((_, 1)).reduceByKey(_+_)
    
    wc.saveAsTextFile(filePath + "output")
    
    println("sucess")
    sc.stop()
  } // main end  
}
  
