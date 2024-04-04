package org.example.multithreading

import java.util.concurrent.atomic.AtomicInteger
import java.util.stream.IntStream

class AtomicIntegerEx {
    companion object {
        //var counter = 0
        var counter: AtomicInteger = AtomicInteger()
        fun increment() {
            counter.incrementAndGet()
            counter.addAndGet(5)
            counter.decrementAndGet()
        }
    }
}

class RunnableImpl : Runnable {
    override fun run() {
        IntStream.range(0, 100).forEach { AtomicIntegerEx.increment() }
    }
}

fun main() {
    val thread1 = Thread(RunnableImpl())
    val thread2 = Thread(RunnableImpl())
    thread1.start()
    thread2.start()

    thread1.join()
    thread2.join()

    println(AtomicIntegerEx.counter)
}