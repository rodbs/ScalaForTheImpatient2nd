/**
  * Created by user on 4/21/2017.
  */
object Chapter9 extends App {

  import scala.io.Source._
  import java.io.PrintWriter
  /**
    * 1. Write a Scala code snippet that reverses the lines in a file (making the last
line the first one, and so on).
    */
  println("Ex1")
  val url10 = getClass.getResource("/myfile.txt")
  val it10 = fromURL(url10)
  val lines10 = it10.getLines().toArray.reverse.mkString("\n")


  println(lines10)


  /**
    * 2. Write a Scala program that reads a file with tabs, replaces each tab with spaces
so that tab stops are at n-column boundaries, and writes the result to the
same file.
    */

  println("Ex2")

  val url20 = getClass.getResource("myfileTabs.txt")
  val arr20 = fromURL(url20).getLines().toArray

  for(line <- arr20){
    val words = line.split("""\t""")
    for (w <- words){
      println(w + " " * (10-w.length) )
    }

  }


  /**
    * 3. Write a Scala code snippet that reads a file and prints all words with more
than 12 characters to the console. Extra credit if you can do this in a single line.
    */

  println("Ex3")

  val url30 = getClass.getResource("myfile12.txt")
  val arr30 = fromURL(url30).getLines.mkString.split("\\W+").filter(i=> i.length > 12)

  arr30.foreach(println)



  /**
    * 4. Write a Scala program that reads a text file containing only floating-point
numbers. Print the sum, average, maximum, and minimum of the numbers
in the file.
    */


  /**
    * 5. Write a Scala program that writes the powers of 2 and their reciprocals to a
file, with the exponent ranging from 0 to 20. Line up the columns:
1 1
2 0.5
4 0.25
... ...*/

  println("Ex5")

  for (i <- 0 to 20) println (math.pow(2,i), 1/math.pow(2,i))

  //val file50 = new PrintWriter("ch09-ex05.txt")

  (0 to 20).map(math.pow(2,_)).foreach(i => println("%7d   ".format(i.toInt) + 1/i))

  /**
    * 6. Make a regular expression searching for quoted strings "like this, maybe with
\" or \\" in a Java or C++ program. Write a Scala program that prints out all
such strings in a source file.
    */


  /**
    * 7. Write a Scala program that reads a text file and prints all tokens in the file
that are not floating-point numbers. Use a regular expression.
    */


  /**
    * 8. Write a Scala program that prints the src attributes of all img tags of a web
page. Use regular expressions and groups.
    */

}
