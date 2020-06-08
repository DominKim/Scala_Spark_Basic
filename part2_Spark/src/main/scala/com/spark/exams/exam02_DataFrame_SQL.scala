package com.spark.exams

import org.apache.spark.sql.SparkSession

object exam02_DataFrame_SQL {
  
  /*
   * 문) socre_iq.csv 파일 dataFrame 객체로 읽어서 다음과 같이 처리하시오.
   *    단계1 : spark DataFrame 객체 생성(file 위치 : "src/main/resources")
   *    
   *    단계2 : academy 변수를 대상으로 그룹화 하고, 집단별 score 평균 구하기
   *    |academy|       avg(score)|
+-------+-----------------+
|      1|73.75555555555556|
|      3|81.35714285714286|
|      4|             87.0|
|      2|             81.1|
|      0|             65.0|
+-------+-----------------+
   *    
   *    단계3 : DataFrame을 대상으로 가상 테이블 만들기(테이블명 : score_df) 
   *    
   *    단계4 : tv 컬럼이 2이상인 관측치를 대상응로 subset 만들기
   *    
   *    단계5 : subset을 csv 형식으로 저장하기(file 위치 : "src/main/resources/output_df")
   */
  
  def main(args: Array[String]): Unit = {
    
    // SparkSession 객체 생성
    val spark = SparkSession.builder
                .master("local")
                .appName("dataFrameAPI")
                .getOrCreate()
    
    val df = spark.read.format("csv").option("inferSchema", "true").option("header", "true").option("delimiter", ",").load("src/main/resources/score_iq.csv")
    
    df.createOrReplaceTempView("score_df")

    spark.sql("select academy, mean(score) from score_df group by academy").show()
    
    val subset = spark.sql("select * from score_df where tv >= 2 order by score desc")
    
    subset.write.format("csv").option("header", "True").mode("overwrite").save("src/main/resources/output_df")
    
    // 객체 닫기
    spark.close()
  } 
}