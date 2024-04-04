package org.example.multithreading.threadSaveCollections

import java.util.Collections

fun main(){
    val source = ArrayList<Int>()
    for(i in 0 until 5){
        source.add(i)
    }
    //val target = ArrayList<Int>()
    val synchList = Collections.synchronizedList(ArrayList<Int>())
    //используем несинхронизированную коллекцию и помещаем ее в обертку
    // в обертке под капотом на каждом методе стоит lock
    val runnable = Runnable {synchList.addAll(source)}

    val thread1 = Thread(runnable)
    val thread2 = Thread(runnable)

    thread1.start()
    thread2.start()
    thread1.join()
    thread2.join()

    println(synchList)
}