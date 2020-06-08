package com.spark.ch02_dataFrame_SQL

import org.apache.spark.sql.SparkSession          // DataFrame 생성

object Step04_dataFrame_json {
  
  def main(args: Array[String]): Unit = {
    
    // SparkSession 객체 생성
    val spark = SparkSession.builder
                .master("local")
                .appName("dataFrameAPI")
                .getOrCreate()
    
    // path 변수
    val path = "src/main/resources/usagov_bitly.txt"
    
    // json → DataFrame 
    val df = spark.read.json(path)
    df.show()
    
    df.printSchema()
    
    // 가상 테이블 만들기
    df.createOrReplaceTempView("json_df")
    
    // sql문 작성하기    
    spark.sql("select * from json_df").show()
    
    spark.sql("select count(*) from json_df").show()
    
    // subset 생성하기
    val nk_true = spark.sql("select * from json_df where nk = 1")
    nk_true.show()
    println("전체 관측치 : " + nk_true.count()) // 전체 관측치 : 1587
    
    // 객체 닫기
    spark.close()
  } 
}