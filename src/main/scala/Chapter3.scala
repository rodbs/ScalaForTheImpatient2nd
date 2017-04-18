import scala.collection.mutable.ArrayBuffer

/**
  * Created by user on 4/17/2017.
  */
object Chapter3 extends App{

/**
1. Write a code snippet that sets a to an array of n random integers between 0
(inclusive) and n (exclusive).*/

  println("Ex1")

  def randArray(n: Int) = Array.fill(n)(util.Random.nextInt(100))

  val ra1 = randArray(5)
  println(ra1.toList)


  def randArray2(n:Int) = for (i <- 1 to n) yield util.Random.nextInt(100)

  val ra2 = randArray2(6)
  println(ra2.toList)



/**
2. Write a loop that swaps adjacent elements of an array of integers. For example,
Array(1, 2, 3, 4, 5) becomes Array(2, 1, 4, 3, 5). */

  println("Ex2")

  def swapArray1(arr: Array[Int]) = {
    for (i <- 0 until(if (arr.length % 2 == 0) arr.length else arr.length - 1, 2)) {
      val t = arr(i)
      arr(i) = arr(i + 1)
      arr(i + 1) = t
    }
    arr
  }

  val arr1 = swapArray1(Array(1,2,3,4,5))
  println(arr1.toList)
  val arr2 = swapArray1(Array(1,2,3,4))
  println(arr2.toList)

  assert( swapArray1(Array(1,2,3,4,5)).deep == Array(2,1,4,3,5).deep )   //deep
  assert( swapArray1(Array(1,2,3,4)).sameElements(Array(2,1,4,3)))  //sameElements


/**
3. Repeat the preceding assignment, but produce a new array with the swapped
  values. Use for/yield.*/

  println("Ex3")

  def swapArray2(arr:Array[Int]) = {
    (for(i <- 0 until arr.length) yield
      if (i%2==0 && (i+1)==arr.length) arr(i) //last element for odd arrays
      else if (i%2==0) arr(i+1)
      else arr(i-1)
      ).toArray
  }

  val arr10 = swapArray2(Array(1,2,3,4,5))
  println(arr10.toList)
  val arr11 = swapArray2(Array(1,2,3,4))
  println(arr11.toList)
  assert( swapArray2(Array(1,2,3,4,5)).deep == Array(2,1,4,3,5).deep )   //deep
  assert( swapArray2(Array(1,2,3,4)).sameElements(Array(2,1,4,3)))  //sameElements


    /**
4. Given an array of integers, produce a new array that contains all positive
values of the original array, in their original order, followed by all values that
are zero or negative, in their original order.*/

  println("Ex4")

  def orderArray40(arr:Array[Int]) = {
     (for(i <- arr if i >0) yield i) ++(for(i <- arr if i<=0) yield i)

  }

  val arr40 =orderArray40(Array(1,-3,2,3,-2,-1,4,0))
  println(arr40.toList)

  def orderArray41(arr:Array[Int]) = {
    arr.filter(_>0) ++ arr.filter(_<=0)
  }

  val arr41 =orderArray41(Array(1,-3,2,3,-2,-1,4,0))
  println(arr41.toList)




/**
5. How do you compute the average of an Array[Double]?*/

  println("Ex5")

  def avgArray(arr:Array[Double]) = {
    arr.sum/arr.length

  }

  val arr50 = avgArray(Array(0, 5, 10.0))
  println(arr50)


/**
6. How do you rearrange the elements of an Array[Int] so that they appear in
reverse sorted order? How do you do the same with an ArrayBuffer[Int]? */

  println("Ex6")

  val arr61 = Array(1,4,7,6,8)
  val arr62 = arr61.sortWith(_>_)
  println(arr62.toList)


  val arr65 = ArrayBuffer(1,4,6,7,8)
  val arr66 = arr65.sortWith(_>_)
  println(arr66.toList)


  assert( arr62.deep == Array(8,7,6,4,1).deep )

  assert( arr66 == ArrayBuffer(8,7,6,4,1) )


/**
7. Write a code snippet that produces all values from an array with duplicates
removed. (Hint: Look at Scaladoc.) */

  println("Ex7")

  val arr71 = Array(1,2,2,5,6,5)
  val arr72 = arr71.distinct
  println(arr72.toList)



  /**8. Suppose you are given an array buffer of integers and want to remove all but
the first negative number. Here is a sequential solution that sets a flag
when the first negative number is called, then removes all elements beyond.
    var first = true
    var n = a.length
    var i = 0
    while (i < n) {
      if (a(i) >= 0) i += 1
      else {
        if (first) { first = false; i += 1 }
        else { a.remove(i); n -= 1 }
      }
    }
This is a complex and inefficient solution. Rewrite it in Scala by collecting
positions of the negative elements, dropping the first element, reversing the
sequence, and calling a.remove(i) for each index.
    */

    println("Ex8")

    val a = ArrayBuffer(1,3,-4,4,7,-3,6,-4,7)
    var first = true
    var n = a.length
    var i = 0
    while (i < n) {
      if (a(i) >= 0) i += 1
      else {
        if (first) { first = false; i += 1 }
        else { a.remove(i); n -= 1 }
      }
    }
    println(a.toList)

    val arr81 = ArrayBuffer(1,3,-4,4,7,-3,6,-4,7)
    val indToDel = arr81.indices.filter(arr81(_)<0).drop(1)
    val arr82 = for (i <- 0 until arr81.length if !indToDel.contains(i)) yield arr81(i)
    println(arr82.toList)



  /**
    * 9. Improve the solution of the preceding exercise by collecting the positions
that should be moved and their target positions. Make those moves and
truncate the buffer. Don’t copy any elements before the first unwanted
element.
    */



  /**
    * 10. Make a collection of all time zones returned by java.util.TimeZone.getAvailableIDs
that are in America. Strip off the "America/" prefix and sort the result.
    */
    println("Ex8")

    val id = "America/"
    val arr100 = java.util.TimeZone.getAvailableIDs.filter(_.startsWith(id)).map(_.stripPrefix(id)).sorted

    //arr100.foreach(println)



  /**
    * 11. Import java.awt.datatransfer._ and make an object of type SystemFlavorMap with
the call
val flavors = SystemFlavorMap.getDefaultFlavorMap().asInstanceOf[SystemFlavorMap]
Then call the getNativesForFlavor method with parameter DataFlavor.imageFlavor
and get the return value as a Scala buffer. (Why this obscure class? It’s hard
to find uses of java.util.List in the standard Java library.)
    */

    import java.awt.datatransfer._
    val flavors = SystemFlavorMap.getDefaultFlavorMap().asInstanceOf[SystemFlavorMap]
    val buff = flavors.getNativesForFlavor(DataFlavor.imageFlavor)
    println(buff)




}
