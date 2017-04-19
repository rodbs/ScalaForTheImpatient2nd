/**
  * Created by user on 4/18/2017.
  */
object Chapter4 extends App {

/**
  * 1. Set up a map of prices for a number of gizmos that you covet. Then produce
a second map with the same keys and the prices at a 10 percent discount.
  */

  val gizmos = Map("Iphone" -> 700, "Samsung Galaxy" -> 600, "Xiaomi" -> 300)

  val gizmosDisc = for ((k,v) <- gizmos) yield (k,v*.9)

  println(gizmosDisc)




/**
  * 2. Write a program that reads words from a file. Use a mutable map to count
how often each word appears. To read the words, simply use a java.util.Scanner:
val in = new java.util.Scanner(new java.io.File("myfile.txt"))
while (in.hasNext()) process in.next()
Or look at Chapter 9 for a Scalaesque way.
At the end, print out all words and their counts.
  */
  println("Ex2")
  import scala.io.Source

  //val classLoader = getClass.getClassLoader
  // val in = new java.util.Scanner(new java.io.File(classLoader.getResource("myFile.txt").getFile()))
  // while (in.hasNext()) process in.next()

  //Scala 2.12
  //val readmeText : Iterator[String] = Source.fromResource("/myfile.txt").getLines

  val stream20  = getClass.getResourceAsStream("/myfile.txt")
  val words20 = scala.io.Source.fromInputStream( stream20 ).getLines.mkString

  val mp20 = new scala.collection.mutable.HashMap[String, Int]

  for (w <- words20.split("\\W+")) {
    mp20(w) = mp20.getOrElse(w,0) +1
  }
  println(mp20)
//  wordCount.foreach(println)

  //foreach: like map but its function doesn't return a value
  //lines.split("\\W+").foreach(w => wordCount(w) = wordCount.getOrElse(w, 0) + 1)


/**
  * 3. Repeat the preceding exercise with an immutable map.  *
  */
  println("Ex3")
  val url30 =  getClass.getResource("/myfile.txt")
  val words30 = scala.io.Source.fromURL(url30).mkString.split("\\W+")

  val arr30 = for (w <- words30.distinct) yield (w, words30.count(_==w))
  val mp30 = arr30.toMap
  println(mp30)


/**
  * 4. Repeat the preceding exercise with a sorted map, so that the words are
printed in sorted order.
  */
  println("Ex4")
  val url40 =  getClass.getResource("/myfile.txt")
  val words40 = scala.io.Source.fromURL(url40).mkString.split("\\W+")

  val arr40 = for (w <- words40.distinct) yield (w, words40.count(_==w))
  val mp40 = scala.collection.immutable.SortedMap[String, Int]() ++ arr40
  println(mp40)




  /**
  * 5. Repeat the preceding exercise with a java.util.TreeMap that you adapt to the
  Scala API.
  */



/**
  * 6. Define a linked hash map that maps "Monday" to java.util.Calendar.MONDAY, and
similarly for the other weekdays. Demonstrate that the elements are visited
  in insertion order.
  */
  println("Ex6")
  //LinkedHashMap keeps insertion order
  val days = scala.collection.mutable.LinkedHashMap(
    "MONDAY" -> java.util.Calendar.MONDAY,
    "TUESDAY" -> java.util.Calendar.TUESDAY,
    "WEDNESDAY" -> java.util.Calendar.WEDNESDAY,
    "THURSDAY" -> java.util.Calendar.THURSDAY,
    "FRIDAY" -> java.util.Calendar.FRIDAY,
    "SATURDAY" -> java.util.Calendar.SATURDAY,
    "SUNDAY" -> java.util.Calendar.SUNDAY
  )
  println(days)

  assert(days == (for(d <- days) yield d))



/**
  * 7. Print a table of all Java properties reported by the getProperties method of the java.lang.System class, like this:
  java.runtime.name | Java(TM) SE Runtime Environment
  sun.boot.library.path | /home/apps/jdk1.6.0_21/jre/lib/i386
  java.vm.version | 17.0-b16
  java.vm.vendor | Sun Microsystems Inc.
  java.vendor.url | http://java.sun.com/
  path.separator | :
  java.vm.name | Java HotSpot(TM) Server VM
You need to find the length of the longest key before you can print the table.
  */
  println("Ex7")

  import scala.collection.JavaConversions.propertiesAsScalaMap
  val props: scala.collection.Map[String, String] = System.getProperties

  val maxLength = props.keys.maxBy(_.length).length

 // for((k,v) <- props) println(k + " " * (maxLength - k.length) + "|" + v)



/**
  * 8. Write a function minmax(values: Array[Int]) that returns a pair containing the
smallest and the largest values in the array.
  */
  println("Ex8")

  def minmax(values:Array[Int]) = {
    (values.min, values.max)
  }

  val arr80 = Array(1,6,3,-3)
  println(minmax(arr80))

/**
  * 9. Write a function lteqgt(values: Array[Int], v: Int) that returns a triple containing
  the counts of values less than v, equal to v, and greater than v.
  */
  println("Ex9")

  def lteqgt(values: Array[Int], v: Int) = {
    (values.count(_<v), values.count(_==v),values.count(_>v))
  }

  val arr90 = Array(1,2,3,4,5,6,7,8)
  println(lteqgt(arr90,5))


/**10. What happens when you zip together two strings, such as "Hello".zip("World")?
  Come up with a plausible use case.
*/
  println("Ex10")

  //creates a sequence of Tuples
  val st100 = "Hello".zip("World")

  println(st100)

  //use case: Create maps from different arrays
  val countries = Array("USA", "Spain", "France")
  val capitals = Array("Washington DC", "Madrid", "Paris")

  val mp100 = countries.zip(capitals).toMap
  print(mp100)


}
