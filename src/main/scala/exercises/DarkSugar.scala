package exercises

object DarkSugar extends App {

    def singleArgMethod(n: Int): String = s"I am " + n

    val res = singleArgMethod {
        if (22 > 11) 22 else 11
    }
    println(res)

    List(1, 2, 3).map { x =>
        if (x % 2 != 0) x * 2 else x - x
    }.foreach(y => println(y))

    trait Action {
        def act(x: Int): Int
    }

    val anInstance: Action = new Action {
        override def act(x: Int): Int = x + 1
    }

    val aFunkyInstance: Action = (x: Int) => x + 1

    val aThread = new Thread(new Runnable {
        override def run(): Unit = println("Hello, Scala!")
    })
    aThread.run()

    val aSweetThread = new Thread(() => println("Sweet thread!"))
    aSweetThread.run()

    abstract class AnAbstractType {
        def implemented: Int = 23

        def f(a: Int): Unit
    }

    val anAbstractInstance: AnAbstractType = (x: Int) => println("sweet" + x)

    val prependList = 1 :: List(2, 3)
    println(prependList)
    println(1 :: 2 :: 3 :: List(4, 5, 6))

    class MyStream[T] {
        def -->:(value: T): MyStream[T] = this
    }

    val myStream = 1 -->: 2 -->: 3 -->: new MyStream[Int]
    println(myStream)

    class TeenGirl(name: String) {
        def `and then said`(gossip: String): Unit = println(s"$name said $gossip")
    }

    val lily = new TeenGirl("Lily")
    lily `and then said` ("I love Scala!")

    class Composite[A, B]

    val composite: Int Composite String = ???

    class -->[A, B]
    val towards: Int --> String = ???

}
