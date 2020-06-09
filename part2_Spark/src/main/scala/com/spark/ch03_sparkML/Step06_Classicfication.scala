package com.spark.ch03_sparkML

/*
 * Tree model + confusion matrix
 */

import org.apache.spark.sql.SparkSession // DataFrame 생성
import org.apache.spark.ml.classification.{DecisionTreeClassifier} // tree model 생성
import org.apache.spark.ml.evaluation.MulticlassClassificationEvaluator // model 평가
import org.apache.spark.mllib.evaluation.MulticlassMetrics // confusion matrix


/*
 * ml vs mllib
 * ml    : DataFrame model
 * mllib : RDD model 
 */

object Step06_Classicfication {
   
  def main(args: Array[String]): Unit = {
    
    // SparkSession 객체 생성
    val spark = SparkSession.builder
                .master("local")
                .appName("dataFrameAPI")
                .getOrCreate()

    // 1. dataset load
    val df = spark.read.format("libsvm").load("src/main/resources/iris_libsvm.txt")
    
    // 전체 컬럼 모두 보기
    df.show(false)
    /*
     * +-----+-------------------------------+
     * |label|features                       |
     * +-----+-------------------------------+
     * |0.0  |(4,[0,1,2,3],[5.1,3.5,1.4,0.2])|
     * |0.0  |(4,[0,1,2,3],[4.9,3.0,1.4,0.2])|
     */
    
    // 2. train / test split
    val Array(train, test) = df.randomSplit(Array(0.7, 0.3))
    // dataset memory loading
    train.cache()
    test.cache()
    
    //3. Treee model
    val dt = new DecisionTreeClassifier()
    .setLabelCol("label")
    .setFeaturesCol("features")
    // .setPredictionCol("predictions")
    
    val model = dt.fit(train)
    
    println(s"변수의 중요 : ${model.featureImportances}")
    // 변수의 중요 : (4,[0,2,3],[0.031868222829235994,0.5512062406314077,0.4169255365393563])
    
    // old DF(df:2) → new DF(pred:2 + 1)
    val pred = model.transform(test)
    pred.show() // predictions
    
    // 4. model 평가
    val evaluator = new MulticlassClassificationEvaluator()
    .setLabelCol("label")
    .setPredictionCol("prediction")
    
    val acc = evaluator.setMetricName("accuracy").evaluate(pred)
    val f1 = evaluator.setMetricName("f1").evaluate(pred)
    
    println(s"accuracy : ${acc}, f1 : ${f1}")
    // accuracy : 0.9565217391304348, f1 : 0.9568352842809364
    
    // 교차분할표(confusion matrix)
    import spark.implicits._ // DataFrame → RDD
    
    // DataFrame → rdd
    val predRdd = pred.select("label", "prediction").as[(Double, Double)].rdd
    
    val con_mat = new MulticlassMetrics(predRdd) // RDD data
    println("교차분할표(confusion matrix)")
    println(con_mat.confusionMatrix)
    
    // 객체 닫기
    spark.close()
  }
}























