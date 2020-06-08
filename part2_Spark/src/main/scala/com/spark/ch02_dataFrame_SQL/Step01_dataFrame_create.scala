package com.spark.ch02_dataFrame_SQL

import org.apache.spark.sql.SparkSession          // DataFrame 생성

object Step01_dataFrame_create {
  
  
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
             
    df.show()  // df 내용 보기
    println("관측치 수 =" + df.count()) // rows 관측치 수 = 150
    df.show(150) // 전체 행 보기
    
    // 컬럼 type
    df.printSchema()
    
    // 컬럼 단위 통계 구하기
    // df.describe("Sepal.Length") // error
    
    // 컬럼명 변경하기 : col1 ~ col4, Sepal.Length
    val colNames = Seq("col1", "col2", "col3", "col4", "Species")
    // old DF → new DF
    val iris_df = df.toDF(colNames : _*)
    iris_df.show() // |col1|col2|col3|col4|Species|
    
    // 컬럼 단위 통계 구하기
    iris_df.describe("col1").show()
    
    // 컬럼 집단 변수로 그룹화
    val df_grp = iris_df.groupBy("Species")
    df_grp.count().show()
    /*
		 * | virginica|   50|
 		 * |versicolor|   50|
 		 * |    setosa|   50|  
 		 */
    

    // 그룹 단위 통계 구하기
    df_grp.max("col1").show()
    df_grp.min("col1").show()
    df_grp.mean("col1").show()
    
    // DataFrame save " project 디렉토리 저장
    iris_df.write.format("csv")
           .option("header", "True")
           .mode("overwrite")
           .save("src/main/resources/output_df")
    
    // DataFrame save : HDFS 디렉토리 저장
    iris_df.write.format("csv")
           .option("header", "True")
           .mode("overwrite")
           .save("hdfs://localhost:9000/output_df")
           
    println("file save successed")
    
    
    
    // 객체 닫기
    spark.close()
  }
}














