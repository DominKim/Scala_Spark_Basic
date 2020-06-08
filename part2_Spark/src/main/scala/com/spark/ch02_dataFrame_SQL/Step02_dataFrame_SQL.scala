package com.spark.ch02_dataFrame_SQL

import org.apache.spark.sql.SparkSession          // DataFrame 생성

/*
 * DataFame에서 SQL문 사용
 */

object Step02_dataFrame_SQL {
  
  def main(args: Array[String]): Unit = {
    
    // SparkSession 객체 생성
    val spark = SparkSession.builder
                .master("local")
                .appName("dataFrameAPI")
                .getOrCreate()
                
    val df = spark.read
             .format("csv")
             .option("header", "true")
             .option("delimiter", ",")
             .option("inferSchema", "true")
             .load("src/main/resources/iris.csv")
             
    // 컬럼 구조 보기
    df.printSchema()
    
    // 컬럼명 변경하기 : col1 ~ col4, Sepal.Length
    val colNames = Seq("Sepal_Length", "Sepal_Width", "Petal_Length", "Petal_Width", "Species")
    // old DF → new DF
    val iris_df = df.toDF(colNames : _*)
    iris_df.show()
    
    // 가상 테이블(view) 만들기
    iris_df.createOrReplaceTempView("iris") // 가상 테이블 : iris
    
    // SQL문 사용하기
    spark.sql("select Sepal_Length, Petal_Length from iris").show()
    spark.sql("select * from iris where Sepal_Length > 6.0").show()
    spark.sql("select * from iris where Sepal_Length > 6.0 order by Sepal_Length desc").show()
    spark.sql("select Species, mean(Sepal_Length), mean(Petal_Length) from iris group by Species").show()
    
    // 특정 컬럼 제거하기
    val iris_x = iris_df.drop("Species")
    iris_x.show()
    
    // 전체 컬럼 대상 기술 통계량 구하기
    iris_x.describe().show()
    
    
    // 객체 닫기
    spark.close()
  } 
}














