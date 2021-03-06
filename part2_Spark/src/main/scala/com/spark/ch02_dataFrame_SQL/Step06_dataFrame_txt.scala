package com.spark.ch02_dataFrame_SQL

import org.apache.spark.sql.SparkSession

/*
 * text file → DataFrame
 * 
 * - column 사용을 위한 표준 내장 함수 : 문자열 처리 함수
 */

object Step06_dataFrame_txt {

  def main(args: Array[String]): Unit = {
    
    // SparkSession 객체 생성
    val spark = SparkSession.builder
                .master("local")
                .appName("dataFrameAPI")
                .getOrCreate()
    
    // 1. text file → DF
    val path = "src/main/resources/"
    val df = spark.read.text(path + "input.txt")
    df.show(false)
    /*
     +-------------------------------+
		 |value                          |
		 +-------------------------------+
		 |programming is fun             | 
     */
    
    // 2. column 사용을 위한 표준내장 함수
    import org.apache.spark.sql.functions._
    // 형식) df.select(column 표준내장 함수)
    
    df.select(col("value")).show()
    // col() → split() → explode()
    df.select(explode(split(col("value"), " ")).as("words")).show(false)
    
    // sentence → words
    val words = df.select(explode(split(col("value"), " ")).as("words"))
    println("단어 생성 결과")
    words.show(false)
    
    // word count
    val grp = words.groupBy("words") // df.groupBy()
    val wc = grp.count() // 단어 출현 빈도수
    
    // println(wc)
    wc.show()
    
    // 객체 닫기
    spark.close()
  } 
}





