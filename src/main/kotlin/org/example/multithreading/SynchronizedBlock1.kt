package org.example.multithreading

class Counter {
    companion object {
        @Volatile
        var count: Int = 0
    }
}

class MyRunnableImpl1 : Runnable {

    private fun doSomething(){
        println("${Thread.currentThread().name} Yes")
    }

    private fun increment() {
        doSomething()
        synchronized(this){ //закрываем и открывам монитор у объекта
            Counter.count++
            println("${Counter.count}")
        }
    }

    override fun run() {
        for (i in 1..3) {
            increment()
        }
    }
}

fun main() {
    val runnable: MyRunnableImpl1 = MyRunnableImpl1()
    val thread1: Thread = Thread(runnable)
    val thread2: Thread = Thread(runnable)
    val thread3: Thread = Thread(runnable)
    thread1.start()
    thread2.start()
    thread3.start()
}