package org.example.multithreading

import kotlin.jvm.Volatile

class RaceCond{
    @Volatile
    private var k : Long = 0

    fun getK(): Long{
        return k
    }
    @Synchronized
    fun increment(){
        k++
    }
    @Synchronized
    fun decrement(){
        k--
    }
}

fun main(){
    val raceCond: RaceCond = RaceCond()
    val thread1: Thread = Thread{
        for(i in 1 .. 1000){
            raceCond.increment()
        }
    }
    val thread2: Thread = Thread{
        for(i in 1 .. 1000){
            raceCond.decrement()
        }
    }

    thread1.start()
    thread2.start()

    thread1.join()
    thread2.join()

    println(raceCond.getK())
}
