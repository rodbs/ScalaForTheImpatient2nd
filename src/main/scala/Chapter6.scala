/**
  * Created by user on 4/20/2017.
  */
object Chapter6 extends App{
  /**
    *  1. Write an object Conversions with methods inchesToCentimeters, gallonsToLiters, and
  milesToKilometers.
    */

  println("Ex1")

  object Conversions {
    def inchesToCentimeters(inches: Double) = {
      inches*2.54
    }
    def gallonsToLiters(gallons: Double) = {
      gallons*3.78541178
    }
    def milesToKilometers(miles: Double) = {
      miles*1.609344
    }
  }

  val inch1 = 23.65
  val cm1 = Conversions.inchesToCentimeters(inch1)
  println(inch1 + " inches are " + cm1 + " cm")

  /**
    * 2. The preceding problem wasn’t very object-oriented. Provide a general superclass
    UnitConversion and define objects InchesToCentimeters, GallonsToLiters, and
  MilesToKilometers that extend it.
    */

  println("Ex2")

  abstract class UnitConversion (val conversionFactor:Double) {
    def convert (units:Double) = units * conversionFactor
  }
  object InchesToCentimeters extends UnitConversion(2.54)
  object GallonsToLiters extends UnitConversion(3.78541178)
  object MilesToKilometers extends UnitConversion(1.609344)


  val inch20 = 23.65
  val cm20 = InchesToCentimeters.convert(inch20)
  println(inch20 + " inches are " + cm20 + " cm")

  /**
    * 3. Define an Origin object that extends java.awt.Point. Why is this not actually a
    good idea? (Have a close look at the methods of the Point class.)
   */

  println("Ex3")

  object Origin extends java.awt.Point {}

  // Because Point has mutators, and you wouldn't want to allow moving the Origin.
  Origin.move(2, 2)



  /**
    * 4. Define a Point class with a companion object so that you can construct Point
    instances as Point(3, 4), without using new.
    */
  println("Ex4")

  class Point (val x:Int, val y:Int)

  object Point{
    def apply(x:Int, y:Int) = new Point(x,y)
  }

  val pt40 = Point(3,2)

  /**
    * 5. Write a Scala application, using the App trait, that prints its command-line
  arguments in reverse order, separated by spaces. For example, scala Reverse
    Hello World should print World Hello.
    */

  /**
    * 6. Write an enumeration describing the four playing card suits so that the toString
    method returns ♣, ♦, ♥, or ♠.
    */

  println("Ex6")

  object Cards extends Enumeration {
    type Cards = Value //to avoid uses Cards.Values in the type specification later on
    val Spades = Value("\u2660")
    val Diamonds = Value("\u2666")
    val Hearts = Value("\u2665")
    val Clubs = Value("\u2663")
  }

  println(Cards.values)


  /**
    * 7. Implement a function that checks whether a card suit value from the preceding
    exercise is red.
     */

  println("Ex7")

  import Cards._
  def isRed(card:Cards) = card == Diamonds || card == Hearts

  val card70 = Cards.Spades
  println(card70 + " " + isRed(card70))

  println(for (c <- Cards.values) yield (c, isRed(c)))


  /**
    * 8. Write an enumeration describing the eight corners of the RGB color cube. As
  IDs, use the color values (for example, 0xff0000 for Red).
    */

  println("Ex8")

  object RGBColorCube extends Enumeration {
    val r = Value(0xff0000, "red")
    val g = Value(0x00ff00, "green")
    val b = Value(0x0000ff, "blue")
    val rg = Value(0xffff00, "red-green")
    val rb = Value(0xff00ff, "red-blue")
    val gb = Value(0x00ffff, "green-blue")
    val bl = Value(0x000000, "black")
    val wh = Value(0xffffff, "white")
  }

  for (c <- RGBColorCube.values) println(c.id + ":" + c)










}
