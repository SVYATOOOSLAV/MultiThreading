package org.example.multithreading

import kotlin.math.sqrt

fun main(){
    println("Main starts")
    val thread = InterruptedThread()
    thread.start()
    Thread.sleep(2000)
    thread.interrupt() // так мы лишь отправляем сигнал на поток о том, что мы хотим его прервать
    thread.join()
    println("Main ends")
}

class InterruptedThread : Thread(){
    private var sqrtSum: Double = 0.0
    override fun run() {
        for(i in 1 .. 1_000_000_000){
            if(isInterrupted){   // проверяем сигналы на прерывание, но он не срабатывает во время сна (смотри try catch)
                println("Поток хотят прервать")
                return
            }
            sqrtSum += sqrt(i.toDouble())
            try {
                sleep(10000)
            } catch (e: InterruptedException){
                println("Поток хотят прервать во время сна")
                return
            }
        }
        println(sqrtSum)
    }
}