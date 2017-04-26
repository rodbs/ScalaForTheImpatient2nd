import java.awt.geom.{AffineTransform, PathIterator}

import scala.collection.mutable.ArrayBuffer

/**
  * Created by user on 4/20/2017.
  */
object Chapter8 extends App {

  /**
    *  1. Extend the following BankAccount class to a CheckingAccount class that charges $1
  for every deposit and withdrawal.
  class BankAccount(initialBalance: Double) {
    private var balance = initialBalance
    def currentBalance = balance
    def deposit(amount: Double) = { balance += amount; balance }
    def withdraw(amount: Double) = { balance -= amount; balance }
  }
    */

  println("Ex1")

  class BankAccount(initialBalance: Double) {
    protected var balance = initialBalance   // change to protected
    def currentBalance = balance
    def deposit(amount: Double) = { balance += amount; balance }
    def withdraw(amount: Double) = { balance -= amount; balance }
  }
  class CheckingAccount(initialBalance: Double) extends BankAccount(initialBalance) {
    def charge  { balance -= 1}
    override def deposit(amount: Double): Double = { charge; super.deposit(amount) }
    override def withdraw(amount: Double): Double = {charge; super.withdraw(amount)}
  }

  val chkact10= new CheckingAccount(45)
  chkact10.deposit(5)
  println(chkact10.currentBalance)
  chkact10.withdraw(10)
  println(chkact10.currentBalance)


  /**
    * 2. Extend the BankAccount class of the preceding exercise into a class SavingsAccount
  that earns interest every month (when a method earnMonthlyInterest is called)
  and has three free deposits or withdrawals every month. Reset the transaction
  count in the earnMonthlyInterest method.
    */

  println("Ex2")

  class SavingsAccount(initialBalance: Double) extends BankAccount(initialBalance ){
    private var freeTransactions = 3

    def charge = {
      if (freeTransactions == 0) balance-=1
      else balance
      freeTransactions -= 1
    }
    def earnMonthlyInterest(interestRate: Double)= {
      balance = balance * (1 + interestRate)
      freeTransactions = 3
    }

    override def deposit(amount: Double): Double = {charge;super.deposit(amount)}
    override def withdraw(amount: Double): Double = {charge;super.withdraw(amount) }
  }

  val savact20= new SavingsAccount(45)
  savact20.deposit(5)
  println(savact20.currentBalance)
  savact20.withdraw(5)
  println(savact20.currentBalance)
  savact20.deposit(5)
  println(savact20.currentBalance)
  savact20.deposit(5)
  println(savact20.currentBalance)
  savact20.earnMonthlyInterest(0.1)
  println(savact20.currentBalance)
  savact20.deposit(5)
  println(savact20.currentBalance)


  /**
    * 3. Consult your favorite Java or C++ textbook which is sure to have an example
    of a toy inheritance hierarchy, perhaps involving employees, pets, graphical
  shapes, or the like. Implement the example in Scala.
    */



  /**
    * 4. Define an abstract class Item with methods price and description. A SimpleItem is
    an item whose price and description are specified in the constructor. Take
  advantage of the fact that a val can override a def. A Bundle is an item that
    contains other items. Its price is the sum of the prices in the bundle. Also
  provide a mechanism for adding items to the bundle and a suitable description
  method.
    */

  println("Ex4")

  abstract class Item {
    def price:Double   //abstract field, can be overwritten by val
    def description:String
  }

  class SimpleItem(val price:Double, val description:String) extends Item {

  }

  class Bundle extends Item {
    private var items = ArrayBuffer[Item]()

    def price(): Double = items.foldLeft(0.0)(_+_.price)
    def description(): String = items.map(_.description).mkString(",")

    def addItem(i: Item) {
      items += i
    }
  }

  val splitm40 = new SimpleItem(250,"tv")
  val splitm41 = new SimpleItem(300,"bed")
  val splitm42 = new SimpleItem(200,"mattress")


  val bund40 = new Bundle
  bund40.addItem(splitm41)
  bund40.addItem(splitm42)
  println(bund40.price)
  println(bund40.description())


  /**
    *  5. Design a class Point whose x and y coordinate values can be provided in a
    constructor. Provide a subclass LabeledPoint whose constructor takes a label
  value and x and y coordinates, such as
    new LabeledPoint("Black Thursday", 1929, 230.07)
    */

  println("Ex5")

  class Point (val x:Double, val y:Double)

  class LabeledPoint (val label:String, x:Double,  y:Double) extends Point(x,y)   /// x, y don't need val

  val lbpt50 = new LabeledPoint("Black Thursday", 1929, 230.07)


  /**
    * 6. Define an abstract class Shape with an abstract method centerPoint and subclasses
    Rectangle and Circle. Provide appropriate constructors for the subclasses and
  override the centerPoint method in each subclass.
    */

  println("Ex6")

  abstract class Shape {
    def centerPoint: (Double,Double)
  }

  class Rectangle(val p1:(Double,Double), val p2:(Double,Double)) extends Shape {
    override def centerPoint: (Double,Double) = ((p1._1+p2._1)/2,(p1._2+p2._2)/2)
  }

  class Circle(val centerPoint:(Double,Double), radius:Double) extends Shape   //centerPoint overrided in the constructor




  /**
    * 7. Provide a class Square that extends java.awt.Rectangle and has three constructors:
    one that constructs a square with a given corner point and width, one
  that constructs a square with corner (0, 0) and a given width, and one that
  constructs a square with corner (0, 0) and width 0.
    */

  println("Ex7")

  class Square(x:Int, y:Int, width:Int) extends java.awt.Rectangle(x,y,width,width) {  //height = width

    def this(width:Int){
      this(0,0,width)
    }

    def this() {
      this(0, 0, 0)
    }
  }

  val sq70 = new Square(2,2,10)
  val sq71 = new Square(34)
  val sq72 = new Square()



  /**
    * 8. Compile the Person and SecretAgent classes in Section 8.6, “Overriding Fields,”
  on page 95 and analyze the class files with javap. How many name fields are
  there? How many name getter methods are there? What do they get? (Hint:
    Use the -c and -private options.)
    */

  println("Ex8")

  class Person(val name: String) {
    override def toString = getClass.getName + "[name=" + name + "]"
  }

  class SecretAgent(codename: String) extends Person(codename) {
    override val name = "secret" // Don’t want to reveal name . . .
    override val toString = "secret" // . . . or class name
  }

  val fred = new Person("Fred")
  println(fred.name)
  val james = new SecretAgent("007")
  println(james.name)

/*
  Chapter8$Person.class
  Compiled from "Chapter8.scala"
  public class Chapter8$Person {
    public java.lang.String name();
    public java.lang.String toString();
    public Chapter8$Person(java.lang.String);
  }

  Chapter8$SecretAgent.class
  Compiled from "Chapter8.scala"
  public class Chapter8$SecretAgent extends Chapter8$Person {
    public java.lang.String name();
    public java.lang.String toString();
    public Chapter8$SecretAgent(java.lang.String);
  }
*/
  /**
    * 9. In the Creature class of Section 8.10, “Construction Order and Early Definitions,”
  on page 98, replace val range with a def. What happens when you also use a def
  in the Ant subclass? What happens when you use a val in the subclass? Why?
    */

  println("Ex9")

  class Creature {
    def range: Int = 10
    val env: Array[Int] = new Array[Int](range)
  }

  class Ant1 extends Creature {
    override val range = 2
  }

  class Ant2 extends Creature {
    override def range = 2
  }

  val ant91 = new Ant1
  val ant92= new Ant2

  println(ant91.env.size)   //val range is defined after primary constructor call
  println(ant92.env.size)  //def range is defined before primary constructor call


  /**
    * 10. The file scala/collection/immutable/Stack.scala contains the definition
  class Stack[A] protected (protected val elems: List[A])
  Explain the meanings of the protected keywords. (Hint: Review the discussion
    of private constructors in Chapter 5.)
    */

  println("Ex10")

  class Person10 protected (protected val name:String){
    def this (name:String, age: Int){
      this(name)

   }
  }

  class Employee10(name:String, val department:String) extends Person10(name)

  //A protected constructor can only be accessed by an auxiliary constructor from the same class o
  // or from a descendant class

  val ps100 = new Person10("Bob", 45)
  val ps101 = new Employee10("Bob", "Sales")




  /**
    * 11. Define a value class Point that packs integer x and y coordinates into a Long
  (which you should make private).
    */

  println("Ex11")
/**
  class Point11 private (val coor:Long) extends AnyVal {
    def x = coor.toLong
    def y = coor.toLong
  }

  val coor110 = new Point11(34)
*/
/**
  class MilTime(val time: Int) extends AnyVal {
    def minutes = time % 100
    def hours = time / 100
    override def toString = f"$time"
  }
  val lunch = new MilTime(1230)
  println(lunch.hours)


  case class Money(amount: Int) extends AnyVal
  val sd:Money = 34
  def multiply(factor: Int, amount: Money): Money = factor * amount
*/



}
