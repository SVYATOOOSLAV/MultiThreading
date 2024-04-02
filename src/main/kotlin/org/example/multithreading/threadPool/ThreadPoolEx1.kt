package org.example.multithreading.threadPool

import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

fun main() {
    // для того чтобы создать ThreadPool (переиспользование существующих в пуле потоков)
    val executorService: ExecutorService = Executors.newFixedThreadPool(5)
    for(i in 1 .. 10){
        // передаем наше задание в ThreadPool (оно выполняется одним из доступных потоков)
        executorService.execute(RunnableTask())
    }
    executorService.shutdown() // для того, чтобы остановить executorService, иначе он будет ждать новые задачи
    executorService.awaitTermination(5, TimeUnit.SECONDS)
    // ожидание завершения работы executorService
    // если пройдет указанное время, тогда основной поток просто продолжит свою работу
    println("Main Thread ends")
}

class  RunnableTask : Runnable{
    override fun run() {
        println(Thread.currentThread().name + " begin")
        Thread.sleep(500)
        println(Thread.currentThread().name + " end")
    }
}