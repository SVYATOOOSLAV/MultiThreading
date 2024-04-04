package org.example.multithreading.threadSaveCollections

import java.util.*
import kotlin.collections.ArrayList

fun main() {
    val arrayList = ArrayList<Int>()
    for (i in 0 until 100) {
        arrayList.add(i)
    }

    val synchList = Collections.synchronizedList(arrayList)

    val runnable = Runnable {

        // синхронизировать необходимо из-за того, что у итератора под капотом не ставится lock при
        // многопоточной обработке

        synchronized(synchList){
            val iterator: Iterator<Int> = synchList.iterator()
            while (iterator.hasNext()) {
                println(iterator.next())
            }
        }
    }
    val runnable2 = Runnable {
        synchList.remove(10)
    }

    val thread1 = Thread(runnable)
    val thread2 = Thread(runnable2)
    thread1.start()
    thread2.start()
    thread1.join()
    thread1.join()

    println(synchList)
}