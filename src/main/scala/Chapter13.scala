import scala.collection.mutable.ListBuffer

/**
  * Created by user on 4/26/2017.
  */
object Chapter13 extends App {

  /**
    * 1. Write a function that, given a string, produces a map of the indexes of all
    characters. For example, indexes("Mississippi") should return a map associating
  'M' with the set {0}, 'i' with the set {1, 4, 7, 10}, and so on. Use a mutable map
    of characters to mutable sets. How can you ensure that the set is sorted?
    */

  println("Ex1")
  println("DONT UnDERSTAND")

  def fun10(s: String) = {
    import scala.collection.mutable.Map
    import scala.collection.mutable.SortedSet
    s.indices.foldLeft( Map[Char,SortedSet[Int]]() ) {
      (m, i) =>
        m += ( s(i) -> (m.getOrElse(s(i), SortedSet[Int]()) += i)	)
    }
  }

  val st = "Mississipi"
  println(fun10(st))



  /**
    * 2. Repeat the preceding exercise, using an immutable map of characters to lists.
    */

  println("Ex2")
  println("TO DO")


  /**
    * 3. Write a function that removes every second element from a ListBuffer. Try it
    two ways. Call remove(i) for all even i starting at the end of the list. Copy every
    second element to a new list. Compare the performance.
    */
  println("Ex2")
  println("IMPROVED")
  val lb30 = ListBuffer(1,2,3,4,5)
  val lb31 = ListBuffer(1,2,3,4)

  def fun30(lb:ListBuffer[Int]) = {
    lb.zipWithIndex.filter( p => p._2 % 2 == 0).map(p => p._1)
  }
  println(fun30(lb30))
  println(fun30(lb31))



  /**
    * 4. Write a function that receives a collection of strings and a map from strings
  to integers. Return a collection of integers that are values of the map corresponding
    to one of the strings in the collection. For example, given Array("Tom",
    "Fred", "Harry") and Map("Tom" -> 3, "Dick" -> 4, "Harry" -> 5), return Array(3, 5).
    Hint: Use flatMap to combine the Option values returned by get.
    */

  println("Ex4")

  def fun40 ( ar:Array[String], mp:Map[String,Int]) = {
    ar.flatMap(mp.get(_))
  }
  val arr40 = Array("Tom", "Fred", "Harry")
  val mp40 = Map("Tom" -> 3, "Dick" -> 4, "Harry" -> 5)

  val arr41 = fun40(arr40,mp40)
  println(arr41.mkString(", "))

  /**
    * 5. Implement a function that works just like mkString, using reduceLeft.
    */

  println("Ex5")
  println("TO IMPROVE")
  def mkString50(arr:Array[String], st:String) = {
    arr.map(_ + st).reduceLeft(_ + _)
  }

  val arr50 = Array("Bob", "Peter", "Michael")

  val arr51 = mkString50(arr50, ", ")
  println(arr51)


  /**
    * 6. Given a list of integers lst, what is (lst :\ List[Int]())(_ :: _)? (List[Int]() /:
    lst)(_ :+ _)? How can you modify one of them to reverse the list?
    */
  println("Ex6")

  val lst = List(1,2,3,4,5)
  //foldRight
  val lst60 =  (lst :\ List[Int]())(_ :: _)

  //foldLeft with append
  val lst61 = (List[Int]() /: lst)(_ :+ _)

  println(lst60)
  println(lst61)

  //foldLeft with prepend
  val lst65 =  (List[Int]() /: lst)((a,b) => b +: a)
  println(lst65)

  /**
    * 7. In Section 13.10, “Zipping,” on page 187, the expression (prices zip quantities)
  map { p => p._1 * p._2 } is a bit inelegant. We can’t do (prices zip quantities) map
    { _ * _ } because _ * _ is a function with two arguments, and we need a
    function with one argument that is a tuple. The tupled method of the Function
  object changes a function with two arguments to one that takes a tuple. Apply
  tupled to the multiplication function so you can map it over the list of pairs.
    */

  println("Ex7")

  val prices = List(5.0, 20.0, 9.95)
  val quantities = List(10, 2, 1)

  val total = prices.zip(quantities).map(tp => tp._1*tp._2)
  println(total)

  val total2 = prices.zip(quantities).map( ( (_:Double) * (_:Int) ).tupled)
  println(total2)


  /**
    * 8. Write a function that turns an array of Double values into a two-dimensional
  array. Pass the number of columns as a parameter. For example, with Array(1,
    2, 3, 4, 5, 6) and three columns, return Array(Array(1, 2, 3), Array(4, 5, 6)).
    Use the grouped method.
    */

  println("Ex8")

  def fun80(arr:Array[Int],col:Int) = {
    arr.grouped(col).toArray
  }

  val arr80 = Array(1, 2, 3, 4, 5, 6)
  val arr81 = fun80(arr80,3)
  println(arr80.deep)
  println(arr81.deep)



  /**
    * 9. The Scala compiler transforms a for/yield expression
  for (i <- 1 to 10; j <- 1 to i) yield i * j
  to invocations of flatMap and map, like this:
  (1 to 10).flatMap(i => (1 to i).map(j => i * j))
  Explain the use of flatMap. Hint: What is (1 to i).map(j => i * j) when i is 1, 2, 3?
    What happens when there are three generators in the for/yield expression?
    */

  println("Ex9")
  println("TODO")



  /**
    * 10. The method java.util.TimeZone.getAvailableIDs yields time zones such as Africa/
    Cairo and Asia/Chungking. Which continent has the most time zones? Hint: groupBy.
    */

  println("Ex10")
  println("TO IMPROVED")

  val zones = java.util.TimeZone.getAvailableIDs
  val continent = zones.groupBy(_.substring(0,2))
  val continent2 = zones.groupBy(_.substring(0,2)).groupBy(_._1).mapValues(_.size)
  println(zones.deep)
  println(continent)
  println(continent2)




  /**
    * 11. Harry Hacker reads a file into a string and wants to use a parallel collection
    to update the letter frequencies concurrently on portions of the string. He
  uses the following code:
  val frequencies = new scala.collection.mutable.HashMap[Char, Int]
  for (c <- str.par) frequencies(c) = frequencies.getOrElse(c, 0) + 1
  Why is this a terrible idea? How can he really parallelize the computation?
  (Hint: Use aggregate.)
    */

  println("Ex11")
  println("TODO")


}
