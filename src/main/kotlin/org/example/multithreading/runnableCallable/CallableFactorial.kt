package org.example.multithreading.runnableCallable

import java.util.concurrent.Callable
import java.util.concurrent.ExecutionException
import java.util.concurrent.Executors
import java.util.concurrent.Future
import java.util.concurrent.TimeUnit

class Factorial2(private var f: Int) : Callable<Int> {

    override fun call(): Int {
        if (f <= 0) {
            throw IllegalArgumentException("Number must be greater than 0")
        }
        var result: Int = 1
        for (i in 1..f) {
            result *= i
        }
        return result
    }
}

class CallableFactorial() {
    companion object {
        var factorialRes: Int = 1
    }
}


fun main() {
    val executorService = Executors.newSingleThreadExecutor()
    val factorial: Factorial2 = Factorial2(3)
    val future: Future<Int> = executorService.submit(factorial) // submit необходим для того, чтобы возвращать значения
    try {
        CallableFactorial.factorialRes = future.get()
    } catch (e: ExecutionException) {
        println(e.cause)
    } finally {
        executorService.shutdown()
    }

    println(CallableFactorial.factorialRes)
}