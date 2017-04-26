/**
  * Created by user on 4/25/2017.
  */
object Chapter12 extends App{

  /**
    *  1. Write a function values(fun: (Int) => Int, low: Int, high: Int) that yields a collection
  of function inputs and outputs in a given range. For example, values(x =>
    x * x, -5, 5) should produce a collection of pairs (-5, 25), (-4, 16), (-3, 9), . . . ,
  (5, 25).
    */

  println("Ex1")
  def fun1(f:Int=>Int, low:Int, high:Int) = {
    for (i<- (low to high)) yield (i,f(i))
  }

  println(fun1(x=> x * x, -5, 5))




  /**
    * 2. How do you get the largest element of an array with reduceLeft?

    */
  println("Ex2")

  val arr20 = Array(1,3,6,8,3,5)
  val res20 = arr20.reduceLeft((a,b) => if (a>b) a else b)
  println(arr20.mkString(", "))
  println(res20)




  /**
    * 3. Implement the factorial function using to and reduceLeft, without a loop or
recursion.
    */
  println("Ex3")

  def fact30(num: Int) ={
    (num to (1,-1)).reduceLeft(_*_)
  }

  println(fact30(4))

  /**
    * 4. The previous implementation needed a special case when n < 1. Show how
    you can avoid this with foldLeft. (Look at the Scaladoc for foldLeft. It’s like
    reduceLeft, except that the first value in the chain of combined values is supplied
  in the call.)
    * */
  println("Ex4")

  def fact40(num: Int) ={
    (num to (1,-1)).foldLeft(1)(_*_)
  }

  println(fact40(4))



  /**
    * 5. Write a function largest(fun: (Int) => Int, inputs: Seq[Int]) that yields the largest
  value of a function within a given sequence of inputs. For example, largest(x
  => 10 * x - x * x, 1 to 10) should return 25. Don’t use a loop or recursion.
    */

  println("Ex5")

  def largest5(fun: (Int) => Int, inputs: Seq[Int])= {
    inputs.map(fun(_)).reduceLeft((a,b) => if (a>b) a else b)
  }

  println(largest5(x  => 10 * x - x * x, 1 to 10))

  /**
    *  6. Modify the previous function to return the input at which the output is largest.
  For example, largestAt(x => 10 * x - x * x, 1 to 10) should return 5. Don’t use
    a loop or recursion.
    */

  println("Ex6")

  def largest6(fun: (Int) => Int, inputs: Seq[Int])= {
    inputs.map(x => (x,fun(x))).reduceLeft((a,b) => if (a._2 > b._2) a else b)._2
  }

  println(largest6(x  => 10 * x - x * x, 1 to 10))


  /**
    * 7. It’s easy to get a sequence of pairs, for example:
  val pairs = (1 to 10) zip (11 to 20)
  Now, suppose you want to do something with such a sequence—say, add
  up the values. But you can’t do
  pairs.map(_ + _)
  The function _ + _ takes two Int parameters, not an (Int, Int) pair. Write a
    function adjustToPair that receives a function of type (Int, Int) => Int and returns
  the equivalent function that operates on a pair. For example, adjustToPair(_ *
    _)((6, 7)) is 42.
  Then use this function in conjunction with map to compute the sums of the
  elements in pairs.
    */
  println("Ex7")

  def adjustToPair(f:(Int,Int)=>Int) = (t: (Int, Int)) => f(t._1, t._2)

  println(adjustToPair((a,b)=>a*b)(3,4))
  println(adjustToPair(_*_)(3,4))

  val pairs = (1 to 10) zip (11 to 20)
  println(pairs)
  println(pairs.map(adjustToPair(_+_)))



  /**
    *  8. In Section 12.8, “Currying,” on page 164, you saw the corresponds method used
  with two arrays of strings. Make a call to corresponds that checks whether the
  elements in an array of strings have the lengths given in an array of integers.
    */

  println("Ex8")

  val str80 = Array("Bob", "Peter", "Robert")
  val len80 = Array(3,5,6)

  println(str80.corresponds(len80)(_.length==_))



  /**
    * 9. Implement corresponds without currying. Then try the call from the preceding
  exercise. What problem do you encounter?
    */

  println("Ex9")

  val str90 = Array("Bob", "Peter", "Robert")
  val len90 = Array(3,5,6)

  def corresponds9(st:Array[String], num:Array[Int], f:(String,Int)=>Boolean) = {
    st.zip(num).map(tp => f(tp._1,tp._2)).reduce(_&_)
  }

  println(corresponds9(str90,len90,(x,y) => x.length==y))



  /**
    * 10. Implement an unless control abstraction that works just like if, but with an
  inverted condition. Does the first parameter need to be a call-by-name
  parameter? Do you need currying?
    */

  println("Ex10")

  def unless(condition: => Boolean)(block: => Unit) {
    if (!condition) block
  }

  val cond = 10

  unless(cond == 10) {
    println(cond)
  }




}
