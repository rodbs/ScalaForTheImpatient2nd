
import scala.collection.mutable.ArrayBuffer

/**
  * Created by user on 4/21/2017.
  */
object Chapter11 extends App {
  /**
    * 1. According to the precedence rules, how are 3 + 4 -> 5 and 3 -> 4 + 5 evaluated?
    */

  println("Ex1")
  //From left to right


  /**
    * 2. The BigInt class has a pow method, not an operator. Why didn’t the Scala library
    designers choose ** (as in Fortran) or ^ (as in Pascal) for a power operator?
    */
  println("Ex2")
  //Because of precedence problem, pow operator must have top precedence

  /**
    * 3. Implement the Fraction class with operations + - * /. Normalize fractions, for
    example, turning 15/–6 into –5/2. Divide by the greatest common divisor,
  like this:
  class Fraction(n: Int, d: Int) {
    private val num: Int = if (d == 0) 1 else n * sign(d) / gcd(n, d);
    private val den: Int = if (d == 0) 0 else d * sign(d) / gcd(n, d);
    override def toString = s"$num/$den"
    def sign(a: Int) = if (a > 0) 1 else if (a < 0) -1 else 0
    def gcd(a: Int, b: Int): Int = if (b == 0) abs(a) else gcd(b, a % b)
    ...
  }
    */

  import scala.math._
  class Fraction(n: Int, d: Int) {
    private val num: Int = if (d == 0) 1 else n * sign(d) / gcd(n, d);
    private val den: Int = if (d == 0) 0 else d * sign(d) / gcd(n, d);

    override def toString = s"$num/$den"

    def sign(a: Int) = if (a > 0) 1 else if (a < 0) -1 else 0

    def gcd(a: Int, b: Int): Int = if (b == 0) abs(a) else gcd(b, a % b)

    def +(other: Fraction) = new Fraction(num * other.den + other.num * den, den * other.den)
    def -(other: Fraction) = new Fraction(num * other.den - other.num * den, den * other.den)
    def *(other: Fraction) = new Fraction(num * other.num, den * other.den)
    def /(other: Fraction) = new Fraction(num * other.den, den * other.num)

  }

  val f1 = new Fraction(4,-3)
  val f2 = new Fraction(-2,3)


  println((f1+f2).toString)
  println((f1-f2).toString)
  println((f1*f2).toString)
  println((f1/f2).toString)

  /**
    * 4. Implement a class Money with fields for dollars and cents. Supply +, - operators
  as well as comparison operators == and <. For example, Money(1, 75) + Money(0,
    50) == Money(2, 25) should be true. Should you also supply * and / operators?
  Why or why not?
    */

  println("Ex4")

  class Money (d: Int, c: Int){
    def +(other: Money) =  Money(this.toFloat + other.toFloat)
    def -(other: Money) =  Money(this.toFloat - other.toFloat)
    def ==(other: Money) = this.toFloat == other.toFloat
    def <(other: Money) = this.toFloat < other.toFloat

    def toFloat =(d+"."+c).toFloat
  }


  object Money {
    def apply (d:Int, c:Int): Money = new Money(d,c)

    def apply (d:Float): Money = {
      val s = d.toString.split('.')
      new Money(s(0).toInt,s(1).toInt)
    }
  }

  val cur40 = Money(3,6)
  println(cur40.toFloat)


  val cur41 = Money(2,4)
  println((cur40+cur41).toFloat)

  println(cur40 == cur41)

  println(cur40 < cur41)


  /**
    * 5. Provide operators that construct an HTML table. For example,
  Table() | "Java" | "Scala" || "Gosling" | "Odersky" || "JVM" | "JVM, .NET"
  should produce
    <table><tr><td>Java</td><td>Scala</td></tr><tr><td>Gosling...
    */

  println("Ex5")

  class Table {
    val items = ArrayBuffer[ArrayBuffer[String]](ArrayBuffer())

    def |(s: String): Table = {
      items(items.length - 1).append(s)
      this
    }

    def ||(s: String): Table = {
      items += ArrayBuffer(s)
      this
    }

    override def toString = items.map(_.mkString("<td>", "</td><td>", "</td>")).mkString("<table><tr>", "</tr><tr>", "</tr></table>")
  }

  object Table {
    def apply() = new Table()
  }

  val result = Table() | "Java" | "Scala" || "Gosling" | "Odersky" || "JVM" | "JVM, .NET"
  println(result.items)
  println(result.toString)
  //  result.toString





  /**
    *  6. Provide a class ASCIIArt whose objects contain figures such as
  /\_/\
  ( ' ' )
  ( - )
  | | |
  (__|__)
  Supply operators for combining two ASCIIArt figures horizontally
  /\_/\ -----
  ( ' ' ) / Hello \
  ( - ) < Scala |
  | | | \ Coder /
  (__|__) -----
    or vertically. Choose operators with appropriate precedence.
    */

  println("Ex6")
  println("not finished")

  class ASCIIArt ( val str: Array[String]) {
    def this(str:String){
      this(str.split("\n"))
    }

    override def toString = str.mkString("\n")
  }

  val arr60 = new ASCIIArt(""" /\_/\
( ' ' )
(  -  )
 | | |
(__|__)""")

  println(arr60.str)
  println(arr60.toString)





  /**
    * 7. Implement a class BitSequence that stores a sequence of 64 bits packed in a Long
    value. Supply apply and update operators to get and set an individual bit.
    */

  println("Ex7")
  println("todo")



  /**
    * 8. Provide a class Matrix. Choose whether you want to implement 2 × 2 matrices,
  square matrices of any size, or m × n matrices. Supply operations + and *. The
  latter should also work with scalars, for example, mat * 2. A single element
    should be accessible as mat(row, col).
    */

  println("Ex8")

  class Matrix (val mt: Array[Array[Double]]){
    val rows = mt.length
    val columns = mt(0).length

    def apply(r:Int, c:Int) = mt(r)(c)
    def update(r:Int, c:Int, value:Double) = { mt(r)(c) = value}
    def dim = (rows,columns)

    def *(num:Double):Matrix = {
      Matrix( ( for (i <- mt.indices) yield(mt(i).map(_*num)) ).toArray)
    }

    def computeElemByElem(other:Matrix, f:(Double,Double) => Double): Array[Array[Double]] = {
      require(this.dim == other.dim, "arrays must have same dimension")
      (for (i<-mt.indices) yield(mt(i).zip(other.mt(i)).map((pr  => f(pr._1,pr._2))))).toArray
      //(for (i<-mt.indices) yield(mt(i).zip(other.mt(i)).map(((a,b) => f(a,b))))).toArray
    }

    def +(other:Matrix): Matrix = Matrix(computeElemByElem(other, (a,b) => a+b))
    def -(other:Matrix): Matrix = Matrix(computeElemByElem(other, (a,b) => a-b))

    override def toString = mt.map(_.mkString(", ")).mkString("\n")

  }

  object Matrix {
    def apply(mt: Array[Array[Double]]) = new Matrix(mt: Array[Array[Double]])
  }

  val mat80 =  Matrix(Array(Array(1.0,1.0,3.0), Array(4.0,5.0,6.0)))
  println(mat80.toString)
  println("Dim: " + mat80.dim)
  println("Elem 0,1: " + mat80(0,1))
  println("Update 0,1" + mat80.update(0,1,2))
  println((mat80).toString)
  println("multiplication * 2")
  println((mat80*2).toString)

  println("addition")
  val mat81 =  Matrix(Array(Array(1.0,2.0,3.0), Array(4.0,5.0,6.0)))
  println((mat80+mat81).toString)

  println("TODO: multiplication (trasnverse a matrix)")

  /**
    * 9. Define an object PathComponents with an unapply operation class that extracts
    the directory path and file name from an java.nio.file.Path. For example, the
  file /home/cay/readme.txt has directory path /home/cay and file name readme.txt.
    */

  println("Ex9")

  object PathsComponents {
    import java.nio.file.Path
    import java.nio.file.Paths
    def unapply(s: String) = {
      val r = """(.+?)/([^/]+)$""".r
      val r(p, n) = s
      Some(p, n)
    }
  }

  import java.nio.file.Path
  import java.nio.file.Paths

  val path: Path = Paths.get("/home/cay/readme.txt")
  println(path)

  //val PathsComponents(pat,name) = path.toString
  //println(pat)
  println("TOFIX")


  /**
    * 10. Modify the PathComponents object of the preceding exercise to instead define an
    unapplySeq operation that extracts all path segments. For example, for the file
    /home/cay/readme.txt, you should produce a sequence of three segments: home,
  cay, and readme.txt.
    */

  /**
    * 11. Improve the dynamic property selector in Section 11.11, “Dynamic Invocation,”
  on page 150 so that one doesn’t have to use underscores. For example,
  sysProps.java.home should select the property with key "java.home". Use a helper
  class, also extending Dynamic, that contains partially completed paths.
    */

  /**
    * 12. Define a class XMLElement that models an XML element with a name, attributes,
  and child elements. Using dynamic selection and method calls, make it possible
  to select paths such as rootElement.html.body.ul(id="42").li, which should
  return all li elements inside ul with id attribute 42 inside body inside html.
    */

  /**
    * 13. Provide an XMLBuilder class for dynamically building XML elements, as
  builder.ul(id="42", style="list-style: lower-alpha;"), where the method name
    becomes the element name and the named arguments become the attributes.
    Come up with a convenient way of building nested elements.
    */



}
