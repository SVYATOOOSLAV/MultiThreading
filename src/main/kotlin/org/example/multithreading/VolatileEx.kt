package org.example.multithreading

import kotlin.jvm.Volatile


class VolatileEx : Thread() {
    @Volatile
    var b: Boolean = true

    override fun run(){
        var counter: Long = 0
        while(b){
            counter++
        }
        println("Loop is finished. counter = $counter")
    }
}



fun main(){
    val thread = VolatileEx()
    thread.start()
    Thread.sleep(3000)
    println("Wake up")
    thread.b = false // оно изменилось только в рамках кэша потока main, но не thread
    thread.join()
    println("Its end")

    // Volatile полезен в тех случаях, когда один поток обновляет данные, а другие только читают
}