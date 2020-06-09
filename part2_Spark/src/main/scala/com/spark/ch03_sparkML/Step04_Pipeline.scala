package com.spark.ch03_sparkML

/*
 * Logistic Regression
 */

import org.apache.spark.sql.SparkSession // DataFrame 생성
import org.apache.spark.ml.feature.VectorAssembler // features 생성
// model 생성, model save ㅐㄱ load
import org.apache.spark.ml.classification.{LogisticRegression, LogisticRegressionModel}
// 이항 or 다항 분류 평가
import org.apache.spark.ml.evaluation.MulticlassClassificationEvaluator
// pipeline model 생성, save / load
import org.apache.spark.ml.{Pipeline, PipelineModel}

object Step04_Pipeline {

    def main(args: Array[String]): Unit = {
    
    // SparkSession 객체 생성
    val spark = SparkSession.builder
                .master("local")
                .appName("dataFrameAPI")
                .getOrCreate()
    
    // 1. dataset 생성
    
    import spark.implicits._ // scala → DF
    
    // 1) train set : 키, 몸무게, 나이, 성별
    val train = List((171, 68.65, 29, 1),
                     (175, 74.5, 29, 1),
                     (159, 58.6, 29, 0)).toDF("height", "weight", "age", "gender")
    train.show()
    
    // 1) test set : 키, 몸무게, 나이, 성별
    val test = List((169, 65.0, 35, 1),
                    (161, 58.6, 29, 0),
                    (175, 70.5, 25, 1)).toDF("height", "weight", "age", "gender")
    test.show()

    // 2. assembler 생성 : features
    val assembler = new VectorAssembler().setInputCols(Array("height", "weight", "age")) // x변수 선택
    .setOutputCol("features") // x변수 → features 지정
    
    // 3. model 생성 : train
    val lr_obj = new LogisticRegression().setMaxIter(10) // 반복학습 횟수
    .setRegParam(0.01) // 학습률
    .setLabelCol("gender") // y변수
    .setFeaturesCol("features") // x변수
    
    // 4. pipeline model : step01 : features → step2 : lr_model
    val pipeline = new Pipeline().setStages(Array(assembler, lr_obj))
    
    // model 생성
    val pipelineModel = pipeline.fit(train) // model 
    
    // model 평가
    val pred = pipelineModel.transform(test)
    pred.show()
    
    // 5. pipeline model save & load
    val path = "C:/hadoop-2.6.0/pipeModel"
    pipelineModel.write.overwrite().save(path) // model save
    println("model saved")
    
    val new_pipeModel = PipelineModel.load(path)
    val new_pred = new_pipeModel.transform(train)
    new_pred.show()
    
    
    // 객체 닫기
    spark.close()
  }
}