//import org.apache.log4j.Level
//import org.apache.log4j.Logger
import org.apache.spark._


object WordCount {

  def main(args: Array[String]) {

    val conf = new SparkConf()
      .setAppName("Simple Application")
      .setMaster("local")

    val sc = new SparkContext(conf)

    val lines = sc.textFile("src/main/resources/word_count.text").cache()

    val wordCounts = lines.flatMap(line => line.split(" "))
      .map(word => (word, 1))
      .reduceByKey((a, b) => a + b)

    for ((word, count) <- wordCounts)
      println(word + " : " + count)

    sc.stop()
  }
}
