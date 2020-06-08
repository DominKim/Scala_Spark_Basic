package com.spark.ch01_rdd

import org.apache.spark.{SparkConf, SparkContext}

object Step00_rddAPI {
  
  def main(args: Array[String]): Unit = {
    
    // 1. SparkContext object 생성
    val conf = new SparkConf()
      .setAppName("SparkTest")
      .setMaster("local") // spark 환경 객체
            
    // 2. SparkContext object 생성 : rdd data 생성
    val sc = new SparkContext(conf) // 분산 파일 읽기
  } // main end
  
} // class end