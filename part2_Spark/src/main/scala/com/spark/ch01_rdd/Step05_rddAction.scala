package com.spark.ch01_rdd

import org.apache.spark.{ SparkConf, SparkContext }

/*
 * RDD Action : load & save
 * - load 관련 : rdd.collect(), first(), take(n)
 *  1. rdd.collect()  : rdd 원소 추출 → vector 형식 생성
 *  2. rdd = textFile(local file or HDFS) : 텍스트 파일 읽어서 RDD 생성 
 *  3. rdd.saveaStEXTfILE(local file or HDFS) : rdd 객체를 텍스트 파일에 저장
 */

object Step05_rddAction {

  def main(args: Array[String]): Unit = {
    
    // 1. SparkContext object 생성
    val conf = new SparkConf()
      .setAppName("SparkTest")
      .setMaster("local") // spark 환경 객체
            
    // 2. SparkContext object 생성 : rdd data 생성
    val sc = new SparkContext(conf) // 분산 파일 읽기
    
    // 1. load 관련 : rdd.collect(), first(), take()
    val rdd = sc.parallelize(1 to 100, 5) // data : 정수 컬렌션, 파티션 수 :5
    
    println(rdd.collect.mkString(" "))
    println(rdd.first)    // 첫번째 원소 추출
    println(rdd.take(10).mkString(" ")) // 첫번째 원소 ~ n번째 : 1 2 3 4 5 6 7 8 9 10
    
    // 2. rdd = textFile(local file or HDFS) 
    val rdd2 = sc.textFile("file:/C:/hadoop-2.6.0/README.txt")
    println(rdd2.count()) // 줄 수 = RDD 원소 수 : 31
    println(rdd2.take(20).mkString("\n"))
    
    // 3. save 관련 : rdd 객체 → file 저장
    // rdd.saveAsTextFile("file:/C:/hadoop-2.6.0/output1")  // 파티션 5개
    // rdd2.saveAsTextFile("file:/C:/hadoop-2.6.0/output2") // 파티션 1개
    
    // 4. HDFS file load & HDFS file svae
    val hdfs_rdd = sc.textFile("hdfs://localhost:9000/test/README.txt", 5)
    
    // old rdd → new rdd
    // val hdfs_rdd2 = hdfs_rdd.collect()
    hdfs_rdd.saveAsTextFile("hdfs://localhost:9000/output") // output 디렉토리 자동 생성
    /*
     * 기존 디렉토리가 있는 경우 error 발생
     * 디렉토리 삭제 명령어
     * > hdfs dfs -rm -R /디렉토리명
     */
    
    
    println("success")
    sc.stop
  } // main end

}