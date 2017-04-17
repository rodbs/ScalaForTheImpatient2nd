
//1. The signum of a number is 1 if the number is positive, –1 if it is negative, and
//0 if it is zero. Write a function that computes this value.

def signum(x: Int) = {
  if (x<0) -1 else if (x==0) 0 else -1
}

signum(34)
signum(-2)
signum(0)


//2. What is the value of an empty block expression {}? What is its type?
{}
// Type = Unit, value = () 'no useful value'


//3. Come up with one situation where the assignment x = y = 1 is valid in Scala.
//(Hint: Pick a suitable type for x.)

var y:Int = 0
val x: Unit = y = 1

//In Scala assignments have no value (the value of y=1 is Unit)


//4. Write a Scala equivalent for the Java loop
//for (int i = 10; i >= 0; i--) System.out.println(i);

for (i <- 10 to (0,-1)) println(i)



//5. Write a procedure countdown(n: Int) that prints the numbers from n to 0.
def countdown(n: Int): Unit = {
  for(i <- n to (0,-1)) println(i)
}
countdown(6)

//procedures return no value, or type Unit


//6. Write a for loop for computing the product of the Unicode codes of all letters
//  in a string. For example, the product of the characters in "Hello" is 9415087488L.

def multUnicode(st: String) = {
  var result:Long = 1
  for (c <- st)
    result *= c.toLong
  result
}
multUnicode("Hello")



//7. Solve the preceding exercise without writing a loop. (Hint: Look at the StringOps
//  Scaladoc.)
"Hello".map(_.toLong).product
"Hello".map(_.toLong).reduce(_*_)




//8. Write a function product(s : String) that computes the product, as described
//  in the preceding exercises.

def product(s: String) = {
  s.map(_.toLong).reduce(_*_)
}

product("Hello")


//9. Make the function of the preceding exercise a recursive function.
def product2(s: String) : Long = {
  if (s.tail == "") s.head.toLong
  else s.head.toLong * product2(s.tail)
}

product2("Hello")



/*10. Write a function that computes xn, where n is an integer. Use the following
recursive definition:
• xn = y · y if n is even and positive, where y = xn / 2.
• xn = x · xn – 1 if n is odd and positive.
• x0 = 1.
• xn = 1 / x–n if n is negative.
  Don’t use a return statement. */

def compute(x:Double, n:Int) = {
  if (n>0 && n%2 == 0) math.pow(math.pow(x, n/2), 2)
  else if (n>0 && n%2!=0 ) x * math.pow(x, n-1)
  else if (n<0) 1/math.pow(x,-n)
  else 1.0
}

assert(compute(5, 0) == 1)
assert(compute(5, 2) == 25)
assert(compute(5, -2) == .04)


/*11. Define a string interpolator date so that you can define a java.time.LocalDate as
  date"$year-$month-$day". You need to define an “implicit” class with a date method,
like this:
implicit class DateInterpolator(val sc: StringContext) extends AnyVal {
  def date(args: Any*): LocalDate = . . .
}
args(i) is the value of the ith expression. Convert each to a string and then to
  an integer, and pass them to the LocalDate.of method. If you already know
  some Scala, add error handling. Throw an exception if there aren’t three
  arguments, or if they aren’t integers, or if they aren’t separated by dashes.
(You get the strings in between the expressions as sc.parts.)*/