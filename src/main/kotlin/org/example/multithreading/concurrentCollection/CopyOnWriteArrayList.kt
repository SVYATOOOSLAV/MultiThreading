package org.example.multithreading.concurrentCollection

import java.util.concurrent.CopyOnWriteArrayList
import java.util.concurrent.CopyOnWriteArraySet

class CopyOnWriteArrayList {
}

fun main(){
    val list = CopyOnWriteArrayList<String>()
    list.add("user1")
    list.add("user2")
    list.add("user3")
    list.add("user4")
    list.add("user5")
    println(list)

    val runnable = Runnable{
        val iterator: Iterator<String> = list.iterator()
        // здесь итератор запоминает состояние листа на момент инициализации самого итератора
        // (ему не важно что происходит в других потоках)
        while (iterator.hasNext()){
            Thread.sleep(100)
            println(iterator.next())
        }
    }

    val runnable2 = Runnable{
        Thread.sleep(200)
        list.removeAt(4) // тут используется другая копия
        list.add("user6")
    }

    val thread1 = Thread(runnable)
    val thread2 = Thread(runnable2)

    thread1.start()
    thread2.start()
    thread1.join()
    thread2.join()

    println(list)

    // также существует класс CopyOnWriteArraySet

}