/**
  * Created by user on 4/20/2017.
  */
object classes2 extends App{






  class Account private (val id:Int, initialBalance:Double) {  //private, to use always the apply method

    private var privBalance = initialBalance
    def balance = privBalance  //only the getter is visible

    def deposit(amount: Double) = privBalance += amount

    def description = "Account " + id + " with balance " + privBalance

  }

  object Account { // The companion object
    def apply(initialBalance: Double)  =
      new Account(newUniqueNumber(), initialBalance)
      private var lastNumber =  0
      private def newUniqueNumber() ={
        lastNumber += 1
        lastNumber
      }
  }

  val act1 = Account(50)
  act1.deposit(50)
  println(act1.id)
  println(act1.id)
  println(act1.balance)
  act1.deposit(10)
  println(act1.balance)
  println(act1.description)
}
