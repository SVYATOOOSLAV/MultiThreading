package org.example.multithreading.concurrentCollection

import java.util.concurrent.ArrayBlockingQueue

class ArrayBlockingQueueEx {
}
fun main(){
    val queue = ArrayBlockingQueue<Int>(4) // всегда огрниченное число элементов

    //Producer
    Thread{
        var i = 0
        while (true){
            try{
                queue.put(++i)
                println("Producer add: $i; Queue: $queue")
                Thread.sleep(3000)
            } catch (e: InterruptedException){
                e.printStackTrace()
            }
        }
    }.start()

    //Consumer
    Thread{
        while (true){
            try{
                val j = queue.take()
                println("Consumer take: $j; Queue: $queue")
                Thread.sleep(9000)
            } catch (e: InterruptedException){
                e.printStackTrace()
            }
        }
    }.start()
}