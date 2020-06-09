package com.spark.ch03_sparkML

import org.apache.spark.sql.SparkSession // DataFrame 생성
import org.apache.spark.ml.feature.StringIndexer // y변수 : dummy 변수 : ham(0), spam(1)
import org.apache.spark.ml.feature.VectorAssembler // x변수 : features 생성
// text mining : 토큰 생성기(word) → 불용어 제거 → word : 고유번호 → 희소행렬(가중치)
import org.apache.spark.ml.feature.{RegexTokenizer, CountVectorizer, StopWordsRemover, IDF}
import org.apache.spark.ml.classification.{LogisticRegression} // model 생성
import org.apache.spark.ml.evaluation.MulticlassClassificationEvaluator // model 평가

object Step05_TextMining_Logistic {
  
  def main(args: Array[String]): Unit = {
    
    // SparkSession 객체 생성
    val spark = SparkSession.builder
                .master("local")
                .appName("dataFrameAPI")
                .getOrCreate()
    
    // 1. dataset load
    val df = spark.read.format("csv").option("header", "false").option("delimiter", ",")
    .option("inferSchema", "true").load("src/main/resources/sms_spam_data.csv")
    df.show()
    /*
     * +----+--------------------+
     * | _c0|                 _c1|
     * +----+--------------------+
     * | ham|Go until jurong p...|
     * 
     * 전처리 과정 : _c0 → dummy, _c1 → sparse matrix(features)
     */
    
    // 2. StringIndexer : String → dummy변수
    val idx = new StringIndexer().setInputCol("_c0").setOutputCol("label")
    
    // fit() : model -> transform() : old DF → new DF 
    val sms_data_label = idx.fit(df).transform(df)
    sms_data_label.show()
    /*
     * +----+--------------------+-----+
     * | _c0|                 _c1|label|
     * +----+--------------------+-----+
     * | ham|Go until jurong p...|  0.0|
     * | ham|Ok lar... Joking ...|  0.0|
     * |spam|Free entry in 2 a...|  1.0|
     */
    
    // 2. RegexTokenizer : 정규표현식 이용 토큰 생성
    val tokenizer = new RegexTokenizer().setInputCol("_c1") // 문장
    .setOutputCol("words") // 단어
    .setPattern("\\W+") // 토큰 구분자 : 정규표현식 이용
    
    val tokenizered = tokenizer.transform(sms_data_label)
    tokenizered.show()
    
    // 3. StopWordsRemover : 단어에 포함된 불용어 제거
    val stopWords = new StopWordsRemover()
    .setInputCol("words")  // 불용어 포한된 단어
    .setOutputCol("terms") // 정제된 단어
    
    // old DF → new DF
    val newData = stopWords.transform(tokenizered)
    newData.select("words", "terms").show(false)
    
    // 5. CountVectorizer : word → 고유번호, 단어 길이 제한
    val countVec = new CountVectorizer()
    .setInputCol("terms")     // 단어
    .setOutputCol("countVec") // 고유번호
    .setVocabSize(4000)       // 단어 길이
    
    // fit() : model -> transform() : old -> new DF
    val newDataCount = countVec.fit(newData).transform(newData)
    newDataCount.show()
    // | _c0|                 _c1|label|               words|               terms|            countVec|
    
    // 6. IDF : 단어 출현빈도수에 대한 가중치(TFIDF)
    val tfidf = new IDF()
    .setInputCol("countVec")
    .setOutputCol("tfidfVec") // features
    
    val tfidf_data = tfidf.fit(newDataCount).transform(newDataCount)
    tfidf_data.show(false)
    
    // label → y변수, tfidfVec -> x변수
    tfidf_data.select("label", "tfidfVec").show(false)
    
    // 7. features 생성
    val assembler = new VectorAssembler().setInputCols(Array("tfidfVec")) // x변수 선택
    .setOutputCol("features") // x변수 → features 지정
    
    // old DF → new DF
    val data = assembler.transform(tfidf_data)
    data.show()
    data.select("label", "features").show(false) // libsvm file
    
    // 8. train / test split
    val Array(train, test) = data.randomSplit(Array(0.8, 0.2))
    
    // 9. model 생성
    val lr = new LogisticRegression()
    .setMaxIter(10)
    .setRegParam(0.01)
    .setLabelCol("label") // y변수
    .setFeaturesCol("features") // x변수
    
    val model = lr.fit(train)
    val pred = model.transform(test)
    pred.show()
    pred.select("_c0","label", "prediction").show(1000)
    
    // 10. model 평가
    val evaluator = new MulticlassClassificationEvaluator().setLabelCol("label").setPredictionCol("prediction")
    val acc = evaluator.setMetricName("accuracy").evaluate(pred)
    val f1 = evaluator.setMetricName("f1").evaluate(pred)
    
    println(s"accuracy : ${acc}, f1 score : ${f1}")
    //accuracy : 0.9828054298642533, f1 score : 0.9824875415109419
    
    // 객체 닫기
    spark.close()
  }  
}
















