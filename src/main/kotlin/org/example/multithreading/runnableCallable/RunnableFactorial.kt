package org.example.multithreading.runnableCallable

import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

class RunnableFactorial {
    companion object {
        var factorialRes: Int = 1
    }
}

class Factorial(private var f: Int) : Runnable {

    override fun run() {
        if (f <= 0) {
            println("Number must be greater than 0")
            return
        }
        var result: Int = 1
        for (i in 1..f) {
            result *= i
        }
        RunnableFactorial.factorialRes = result
    }

}

fun main() {
    val executorService = Executors.newSingleThreadExecutor()
    val factorial: Factorial = Factorial(5)
    executorService.execute(factorial)
    executorService.shutdown()
    executorService.awaitTermination(10, TimeUnit.SECONDS)
    println(RunnableFactorial.factorialRes)
}