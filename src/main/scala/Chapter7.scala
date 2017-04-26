import scala.collection.mutable


/**
  * Created by user on 4/20/2017.
  */

  /**
    * 1. Write an example program to demonstrate that
  package com.horstmann.impatient
  is not the same as
  package com
  package horstmann
  package impatient
    */

package com {
  package horstmann{
    object Utils {
      val PI = 3.14
    }

  }
}

package com {  //relative path
  package horstmann {
    package impatient{
      object myMath {
        def circle (radius:Double) = Utils.PI * math.pow(radius,2)
      }
    }
  }
}

package com.horstmann.impatient { //absolute path
  object myMath2 {
    def circunference (radius:Double) = 2 * com.horstmann.Utils.PI * radius
  }
}



  /**
    * 2. Write a puzzler that baffles your Scala friends, using a package com that isn’t
  at the top level.
    */




  /**
    * 3. Write a package random with functions nextInt(): Int, nextDouble(): Double,
  and setSeed(seed: Int): Unit. To generate random numbers, use the linear
  congruential generator
    next = (previous × a + b) mod 2n,
  where a = 1664525, b = 1013904223, n = 32, and the initial value of previous
    is seed.
    */

  package object random {

    private var previous = 0
    val a = 1664525
    val b = 1013904223
    val n = 32

    def setSeed(seed: Int): Unit = previous = seed

    def nexInt(): Int = {
      previous = (previous * a + b) % math.pow(2, n).toInt
      previous
    }

    def nextDouble(): Double = nexInt().toDouble
  }





object Chapter7 extends App{

  println("Ex1")
    println(com.horstmann.impatient.myMath.circle(2))
    println(com.horstmann.impatient.myMath2.circunference(2))




    println ("Ex3")
    println("seed: " + random.setSeed(3))
    println("nextInt: " + random.nexInt())
    println("nextInt: " + random.nexInt())
    println("nextDouble: " + random.nextDouble())

    /**
      * 4. Why do you think the Scala language designers provided the package object
  syntax instead of simply letting you add functions and variables to a package?
      */

    //Not possible because a limitation of the JVM


    /**
      * 5. What is the meaning of private[com] def giveRaise(rate: Double)? Is it useful?
      * */

    // It means the function is only accessible through a child package of com
    // It's not very useful if everything is in com



    /**
      * 6. Write a program that copies all elements from a Java hash map into a Scala
    hash map. Use imports to rename both classes.
      */

  println("Ex6")
  import java.util.{HashMap => JavaHashMap}
  import scala.collection.mutable._


  val jm = new JavaHashMap[Int,String]()
  jm.put(1,"Bob")
  jm.put(2,"Peter")

  val sm =  HashMap[Int,String]()

   val it = jm.entrySet().iterator()
  while (it.hasNext()) {
    val pairs = it.next()
    sm += (pairs.getKey -> pairs.getValue)
  }
  print(sm)


    /**
      *  7. In the preceding exercise, move all imports into the innermost scope possible.
      */


    /**
      * 8. What is the effect of
        import java._
        import javax._
        Is this a good idea?
      */
  //it makes all sub-packages available in the current context.
  //it's dangerous since it might create collision problems


    /**
      * 9. Write a program that imports the java.lang.System class, reads the user name
    from the user.name system property, reads a password from the StdIn object,
  and prints a message to the standard error stream if the password is not
    "secret". Otherwise, print a greeting to the standard output stream. Do not
    use any other imports, and do not use any qualified names (with dots).
      */
  println("Ex9")

  import scala.io.Source
  import java.lang.System._
  val username = getProperties.getProperty("user.name")

  println("user.name " + username)

  print("Password: ")
  val pass = scala.io.StdIn.readLine()


  if (pass != "secret") System.err.println("password incorrect")
  else println("right password")


    /**
      * 10. Apart from StringBuilder, what other members of java.lang does the scala
  package override?
      */
  //java.lang.StringBuilder
  //scala.StringBuilder


}
