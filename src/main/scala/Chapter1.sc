
//1. In the Scala REPL, type 3. followed by the Tab key. What methods can beapplied?


//2
3 - math.pow(math.sqrt(3),2)


//3. Are the res variables val or var?


//4. Scala lets you multiply a string with a number—try out "crazy" * 3 in the REPL.
//What does this operation do? Where can you find it in Scaladoc?
"crazy" * 3


//5. What does 10 max 2 mean? In which class is the max method defined?
10 max 2
10.max(2)
//max is method of the integer class (like in 1)


//6. Using BigInt, compute 21024.
BigInt(2) pow 1024
BigInt(2).pow(1024)
math.pow(2,1024)   // not valid


//7. What do you need to import so that you can get a random prime as
//probablePrime(100, Random), without any qualifiers before probablePrime and Random?
import BigInt.probablePrime
import util.Random
probablePrime(100, Random)


//8. One way to create random file or directory names is to produce a random
//BigInt and convert it to base 36, yielding a string such as "qsnvbevtomcj38o06kul".
//  Poke around Scaladoc to find a way of doing this in Scala.
BigInt(100,Random).toString(36)



//9. How do you get the first character of a string in Scala? The last character?
"hello".apply(0)
"hello"(0)
"hello".last



//10. What do the take, drop, takeRight, and dropRight string functions do? What
//  advantage or disadvantage do they have over using substring?

"hello".take(2)
"hello".dropRight(3)

"hello".drop(2)
"hello".takeRight(3)

//chaining methods
"hello".drop(2).take(2)

"hello".substring(2,4)

/*The main advantage is that it allows  to treat a String as a sequential collection of characters,
much like any other Seq or List instance.

In fact, these methods (and other transformational functions like map, flatMap and filter)
are not implemented in String itself (which is, in fact, simply the Java String class),
but in the StringOps class.
*/

