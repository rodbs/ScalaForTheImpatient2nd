import scala.collection.SortedMap

val stream  = getClass.getResourceAsStream("/myfile.txt")
val lines = scala.io.Source.fromInputStream( stream ).getLines.mkString

val wordCount = new scala.collection.mutable.HashMap[String, Int]

for (w <- lines.split("\\W+")) {
  wordCount(w) = wordCount.getOrElse(w,0) +1
}
wordCount.foreach(println)

val sd = lines.split("\\W+").foreach(w => wordCount(w) = wordCount.getOrElse(w, 0) + 1)
wordCount.foreach(println)


val url =  getClass.getResource("/myfile.txt")

val words = scala.io.Source.fromURL(url).mkString.split("\\W+")

val wordscounts = for (w <- words.distinct) yield (w, words.count(_==w))
wordscounts.sorted.toMap


val x = SortedMap[String, Int]()  ++ Array(("e", 2), ("a", 4))



import scala.collection.JavaConversions.propertiesAsScalaMap
val props: scala.collection.Map[String, String] = System.getProperties
val maxLength = props.keys.maxBy(_.length)
val maxLength2 = props.keys.maxBy(_.length).length


val arr1 = Array(1,3,-6)
arr1.min
arr1.length
arr1.count(_<5)

//for (i <- arr1 if i<4) cou




