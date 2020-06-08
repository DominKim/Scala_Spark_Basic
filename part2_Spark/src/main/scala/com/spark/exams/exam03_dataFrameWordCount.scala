package com.spark.exams

import org.apache.spark.sql.SparkSession

object exam03_dataFrameWordCount {
  def main(args: Array[String]): Unit = {
    
     // SparkSession 객체 생성 
     val spark = SparkSession.builder
                 .master("local") 
                 .appName("dataFrameAPI")
                 .getOrCreate()
                 
      // 단계1 : HDFS의 /test 디렉터리에서 README.txt 파일 읽기(만약 file 없으면 file 업로드)
      val readme = spark.read.text("hdfs://localhost:9000/test/README.txt")
      readme.show()
      val df = readme.toDF()
      // 단계2 : 줄단위 읽기 -> 공백 기준 단어 분리
      import spark.implicits._
      import org.apache.spark.sql.functions._
      val words_df = df.select(explode(split(col("value"), " ")).as("words"))
      words_df.show()
      
      // 단계3 : 워드 카운트 구하고, HDFS의 /output_wc 디렉터리에 저장하기
      val grp = words_df.groupBy("words")
      val wc = grp.count()
      wc.write.format("csv").option("header", "True").mode("overwrite").save("hdfs://localhost:9000/output_wc")
      
      // 단계4 : HDFS의 /output_wc 디렉터리에 저장된 결과 파일 보기           
      
            
     // 객체 닫기 
     spark.close()
     
  }
}