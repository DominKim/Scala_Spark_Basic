package com.spark.exams

import org.apache.spark.sql.SparkSession
import org.apache.spark.ml.regression.LinearRegression
import org.apache.spark.ml.feature.VectorAssembler // x, y변수 선택
import org.apache.spark.ml.evaluation.RegressionEvaluator // model 평가 


object exam04_sparkML_linearRegression {
  
  /*
   * score_iq.csv 파일을 대상으로 선형회귀분석을 수행하시오.
   * - y 변수 : score
   * - x 변수 : iq, academy, tv, game
   * - sid 변수 제거
   * - train / test split(8:2)
   * - model 생성 : train set
   * - model 평가 : test set
   */

  def main(args: Array[String]): Unit = {
    
    // SparkSession 객체 생성
    val spark = SparkSession.builder
                .master("local")
                .appName("dataFrameAPI")
                .getOrCreate()
                
    // 1. score_iq dataset 불러오기
    val df = spark.read.format("csv").option("header", "true").option("delimiter", ",").option("inferSchema", "true")
    .load("src/main/resources/score_iq.csv")
    
    // x, y 변수 생성
    val score_xy = df.drop("sid")
    score_xy.show()
    
    // 2. label, features 생성 : VectorAssembler
    val assemble = new VectorAssembler().setInputCols(Array("iq", "academy", "game", "tv")) // x변수 선택
    .setOutputCol("features") // x변수 → features 지정
    
    val data = assemble.transform(score_xy)
    
    // 3. train / test split(80% vs 20%)
    val Array(train, test) = data.randomSplit(Array(0.8, 0.2), seed = 123)

    // 4. model 생성
    val lr = new LinearRegression()
    .setMaxIter(10) // hyper parameter
    .setFeaturesCol("features")  // x변수
    .setLabelCol("score") // y변수
    
    val model = lr.fit(train) // 훈련셋 이용
    val model_summary = model.summary

    val pred = model.transform(test)
    
    val evaluater = new RegressionEvaluator().setLabelCol("score") // 정답 컬럼
    .setPredictionCol("prediction") // 예측치 컬럼
    
    val rmse = evaluater.setMetricName("rmse").evaluate(pred) // root 평균 제곱 오차
    val mse = evaluater.setMetricName("mse").evaluate(pred) // 평균 제곱 오차
    val mae = evaluater.setMetricName("mae").evaluate(pred) // 평균 절대값 오차
    val r2_score = evaluater.setMetricName("r2").evaluate(pred)
    
    println("Test set 적용 model 적용 평가")
    println(s"rmse : ${rmse}, MSE : ${mse}, mae : ${mae}, r2 : ${r2_score}")
    // rmse : 1.416329334515348, MSE : 2.0059887838086885, mae : 1.2436961043958465, r2 : 0.9641912995429347
    
    
    // 객체 닫기
    spark.close()
  } 

}