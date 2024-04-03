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
            Thread.sleep(1000)
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

    // классы импл Runnable можно использовать в Thread и Excutor Service
    // классы импл Callable только в Executor Service

    val executorService = Executors.newSingleThreadExecutor()
    val factorial: Factorial2 = Factorial2(5)
    val future: Future<Int> = executorService.submit(factorial)
    // submit необходим для того, чтобы возвращать значения
    // во future возможно, что еще нет никакого результата (смотри ниже)
    try {
        println(future.isDone)
        println("Try to get result")
        CallableFactorial.factorialRes = future.get() // при вызове get() поток main остановится до окончания работы задачи и получения результата
        println("Get a result")
        println(future.isDone)
    } catch (e: ExecutionException) {
        println(e.cause)
    } finally {
        executorService.shutdown()
    }

    println(CallableFactorial.factorialRes)
}