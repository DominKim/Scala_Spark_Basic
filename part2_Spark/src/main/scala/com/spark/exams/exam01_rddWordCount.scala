package com.spark.exams

import org.apache.spark.{SparkConf, SparkContext}
import scala.collection.mutable // 수정가능 Map 컬렉션 생성

object exam01_rddWordCount_result {

  def main(args: Array[String]) = {
      val conf = new SparkConf()
        .setAppName("WordCount2")
        .setMaster("local")
      val sc = new SparkContext(conf)
      
      // 단계1 : HDFS의 /test 디렉터리에서 NOTICE.txt 파일 읽기(만약 file 없으면 file 업로드)
      val rdd = sc.textFile("hdfs://localhost:9000/test/README.txt")
            
      // 단계2 : 줄단위 읽기 -> 공백 기준 단어 분리 
      val rdd_re = rdd.flatMap(_.split(" "))
      
      // 단계3 : 단어 길이 3자 이상 단어 필터링
      val rdd_re2 = rdd_re.filter(_.size >= 3)
      
      // 단계4 : 워드 카운트 구하고, HDFS의 /output 디렉터리에 저장하기
      val word_count = rdd_re2.map((_,1)).reduceByKey(_+_)
      word_count.saveAsTextFile("hdfs://localhost:9000/output")

      // 단계5 : HDFS의 /output 디렉터리에 저장된 워드 카운트 결과 파일 보기
      val rdd2 = sc.textFile("hdfs://localhost:9000/output/part-00000")
      println(rdd2.collect().mkString(" "))
      
      /*
       * HDFS에 파일 업로드 명령어 
       * > hdfs dfs -put  업로드파일명  /업로드디렉터리명  
       * 
       * HDFS의 파일 보기 명령어 
       * > hdfs dfs -cat /디렉터리명/파일명
       * 
       * HDFS 디렉터리 삭제 명령어 
       * > hdfs dfs -rm -R /디렉터리명 
       */
             
      print("execute success!!")  
      //Stop the Spark context      
      sc.stop
      
   }  
}