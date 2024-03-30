package org.example.multithreading

fun main() {
    Thread.sleep(1000)
    val myThread1: MyThread1 = MyThread1()
    val myThread2: Thread = Thread(MyThread2())
    val myThread3: Thread = Thread {
        for (i in 1..100) {
            println("${Thread.currentThread().name} $i")
        }
    }
    Thread.sleep(1000)
    myThread1.start()
    myThread2.start()
    myThread3.start()

    // myThread1.run() так нельзя, из-за того, что здесь не применяется многопоточность,
    // работа происходит просто в потоке main (мы не можем запустить отдельный поток)
    // start() позволяет создать отдельный поток на выполнение (ответвление)

    // join() необходим для того, чтобы поток main понимал, что ему необходимо дождаться выполнения
    // задач в других поток, только потом он сможет выполнять свой код дальше
    myThread1.join()
    myThread2.join()
    myThread3.join(1500) // параметр показывает, что main ждет окнчания выполнения задачи в потоке,
    // если она по времени меньше чем заданный параметр

    println("Name ${myThread3.name}")
}

class MyThread1 : Thread() {
    override fun run() {
        for (i in 1..100) {
            println("${Thread.currentThread().name} $i")
        }
    }
}

class MyThread2 : Runnable {
    override fun run() {
        for (i in 1..100) {
            println("${Thread.currentThread().name} $i")
        }
    }
}
