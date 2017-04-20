import java.io.FileInputStream

/**
  * 5. Write a Scala application, using the App trait, that prints its command-line
  arguments in reverse order, separated by spaces. For example, scala Reverse
    Hello World should print World Hello.
  */
object Chapter6_5 extends App {
  if(args.length > 1) println(args.reverse.mkString(" "))
}


