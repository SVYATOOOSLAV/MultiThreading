package org.example.multithreading.threadPool

import java.util.concurrent.Executor
import java.util.concurrent.Executors
import java.util.concurrent.ScheduledExecutorService
import java.util.concurrent.TimeUnit

fun main() {
    val scheduledExecutorService = Executors.newScheduledThreadPool(1)
//    for(i in 1 .. 10){
//        scheduledExecutorService.execute(RunnableTask2()) // Выполняется также как и обычный ThreadPool
//    }

    // scheduledExecutorService позволяет выполнять задачи через определенные интервалы (тут только одна задача)
    // scheduledExecutorService.schedule(RunnableTask2(), 3, TimeUnit.SECONDS)

    // планируем задачу для переодического выполнения, впервые эта задача обработается через 3 секунды,
    // следующие задачи через 1 секунду от начала предыдущего таска
    // может быть что следующий сразу запустится после завершения предыдущего
    // тут постоянно, как в цикле, крутится один и тот же таск
    //scheduledExecutorService.scheduleAtFixedRate(RunnableTask2(), 3, 1, TimeUnit.SECONDS)

    // планируем задачу для переодического выполнения, впервые эта задача обработается через 3 секунды,
    // следующие задачи через 1 секунду от конца предыдущего таска
    // следущий таск ожидает время, указанное в delay
    // тут постоянно, как в цикле, крутится один и тот же таск
    scheduledExecutorService.scheduleWithFixedDelay(RunnableTask2(), 3, 1, TimeUnit.SECONDS)

    Thread.sleep(10000)
    scheduledExecutorService.shutdown()

    // создание в пуле потоков при необходимости + переиспользование
    val executorService1 = Executors.newCachedThreadPool()

    // пул с одним потоком
    val executorService2 = Executors.newSingleThreadExecutor()
}

class  RunnableTask2 : Runnable{
    override fun run() {
        println(Thread.currentThread().name + " begin")
        //Thread.sleep(5000)
        println(Thread.currentThread().name + " end")
    }
}