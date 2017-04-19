/**
  * Created by user on 4/19/2017.
  */
object Chapter5 extends App {

  /**
    * 1. Improve the Counter class in Section 5.1, “Simple Classes and Parameterless
    Methods,” on page 55 so that it doesn’t turn negative at Int.MaxValue.
    */

  println("Ex1")

  class Counter {
    private var value = 0 // You must initialize the field
    def increment() {
      if (value + 1 <= Int.MaxValue) {
        value += 1
      } // Methods are public by default
    }
    def current() = value
  }

  val myCounter = new Counter
  myCounter.increment
  myCounter.increment
  println(myCounter.current)



  /**
    *   2. Write a class BankAccount with methods deposit and withdraw, and a read-only
  property balance.
    */
  println("Ex2")
  class BankAccount {
    var balance = 0.0
    def deposit (amount:Double) {
      balance += amount
    }
    def withdraw (amount: Double): Unit = {
      balance -= amount
    }
  }

  val act1 = new BankAccount
  act1.deposit(45.5)
  println(act1.balance)
  act1.withdraw(15.5)
  println(act1.balance)

  /**
    *   3. Write a class Time with read-only properties hours and minutes and a method
    before(other: Time): Boolean that checks whether this time comes before the
  other. A Time object should be constructed as new Time(hrs, min), where hrs is in
    military time format (between 0 and 23).
    */
  println("Ex3")

  class Time(val hrs:Int, val min:Int) {
    def before(other:Time) = (this.hrs < other.hrs) ||  (this.hrs == other.hrs && this.min < other.min)
    override def toString = hrs + ":" + min
  }

  val tm1 = new Time(23, 34)
  val tm2 = new Time(22,34)
  val tm3 = new Time(13,23)

  println(tm1.toString + " before " + tm2.toString + " = " +tm1.before(tm2) )
  println(tm3.toString + " before " + tm2.toString + " = " +tm3.before(tm2) )



  /**
    * 4. Reimplement the Time class from the preceding exercise so that the internal
    representation is the number of minutes since midnight (between 0 and 24 ×
    60 – 1). Do not change the public interface. That is, client code should be
    unaffected by your change.
    */

  println("Ex4")

  class Time4(val hrs:Int, val min:Int) {
    private val minTotal = hrs * min

    def before(other:Time4) = (this.minTotal < other.minTotal)

    override def toString = hrs + ":" + min
  }

  val tm41 = new Time4(23, 34)
  val tm42 = new Time4(22,34)
  val tm43 = new Time4(13,23)

  println(tm41.toString + " before " + tm42.toString + " = " +tm41.before(tm42) )
  println(tm43.toString + " before " + tm42.toString + " = " +tm43.before(tm42) )


  /**
    *  5. Make a class Student with read-write JavaBeans properties name (of type String)
  and id (of type Long). What methods are generated? (Use javap to check.) Can
  you call the JavaBeans getters and setters in Scala? Should you?
    */

  println("Ex5")

  import scala.beans.BeanProperty

  class Student (@BeanProperty var name: String,@BeanProperty var id: Long)


  val nm50 = new Student ("Bob", 50)
  //nm50.setName("Bob")
  nm50.setId(45)
  println(nm50.getName() + " " + nm50.getId )

  /*
  Yes, you can call getters and setters
  But you should not use them directly since it's only necessary for compatibility purpose with Java libraries that expects these setters and getters
   */



  /**
    *   6. In the Person class of Section 5.1, “Simple Classes and Parameterless Methods,”
  on page 55, provide a primary constructor that turns negative ages to 0.
    */

  println("Ex6")

  class Person6 (val name: String, private var privateAge: Int){
    if (privateAge < 0) privateAge = 0

    def age:Int = privateAge

    def age_= (newValue: Int) {
      if(newValue > privateAge) privateAge = newValue
    }
  }

  val ps60 = new Person6("Bob", -9)
  println(ps60.name + " " + ps60.age)

  val ps61 = new Person6("Peter", 19)
  println(ps61.name + " " + ps61.age)
  ps61.age = 34
  println(ps61.name + " " + ps61.age)




  /**
    *   7. Write a class Person with a primary constructor that accepts a string containing
    a first name, a space, and a last name, such as new Person("Fred Smith"). Supply
  read-only properties firstName and lastName. Should the primary constructor
    parameter be a var, a val, or a plain parameter? Why?
    */
  println("Ex7")

  class Person7(fullName: String) {
    val name = fullName.split(" ")
    def firstName = name(0)
    def lastName = name(1)
  }

  val nm70 = new Person7("Michael Jackson")
  println(nm70.firstName)
  println(nm70.lastName)

  class Person71(fullName:String) {
    val (firstName, lastName) =  fullName.split(" ") match {
      case Array(x:String, y:String, _*) => (x,y)
      case _ => (null,null)
    }
  }

  val nm71 = new Person71("Barak Obama")
  println(nm71.firstName)
  println(nm71.lastName)


  /*
 Primary constructor parameter should be a plain parameter because it doesn't need to have memory (getter/setters)
  */



  /**
    * 8. Make a class Car with read-only properties for manufacturer, model name,
  and model year, and a read-write property for the license plate. Supply four
    constructors. All require the manufacturer and model name. Optionally,
  model year and license plate can also be specified in the constructor. If not,
  the model year is set to -1 and the license plate to the empty string. Which
  constructor are you choosing as the primary constructor? Why?
    */

  println("Ex8")
  class Car (val manufacturer: String, val modelName:String) {
    private var modelYear = -1  //private to make it read-only (don't generate setters/getters)
    var licensePlate = ""


    def this(manufacturer: String,  modelName:String, modelYear: Int) {
      this(manufacturer, modelName)
      this.modelYear = modelYear
    }

    def this(manufacturer: String,  modelName:String, licensePlate: String) {
      this(manufacturer, modelName)
      this.licensePlate = licensePlate
    }

    def this(manufacturer: String,  modelName:String, modelYear: Int, licensePlate: String) {
      this(manufacturer, modelName)
      this.modelYear = modelYear
      this.licensePlate = licensePlate
    }

    override def toString() = {
      "Manufacturer: " + manufacturer + ", Model: " + modelName + ", Year: " + modelYear + ", Plate: " + licensePlate
    }
  }


  val car80 = new Car("Ford", "Focus")
  val car81 = new Car("Nissan", "Patrol", 2010)
  val car82 = new Car("BMW", "320", 2015, "0343")

  println(car80.toString())
  println(car81.toString())
  println(car82.toString())



  /**
    *   9. Reimplement the class of the preceding exercise in Java, C#, or C++ (your
    choice). How much shorter is the Scala class?
    */




  /**
    *   10. Consider the class
  class Employee(val name: String, var salary: Double) {
    def this() { this("John Q. Public", 0.0) }
  }
  Rewrite it to use explicit fields and a default primary constructor. Which form
  do you prefer? Why?
    */

  println("Ex10")

  class Employee(val name: String, var salary: Double) {
    def this() {
      this("John Q. Public", 0.0)
    }
  }

  class Employee2(val name: String = "John Q. Public", var salary: Double = 0.0) {}

  val empl100 = new Employee2("Bob",1000.0)
  println(empl100.name + " " + empl100.salary)

}
