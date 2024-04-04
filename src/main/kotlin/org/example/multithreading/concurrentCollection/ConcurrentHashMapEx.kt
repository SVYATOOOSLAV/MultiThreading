package org.example.multithreading.concurrentCollection

import java.util.Collections
import java.util.HashMap
import java.util.concurrent.ConcurrentHashMap

class ConcurrentHashMapEx {
}

fun main() {
    val map = ConcurrentHashMap<Int,String>()
    map.put(1, "user1")
    map.put(2, "user2")
    map.put(3, "user3")
    map.put(4, "user4")
    map.put(5, "user5")
    println(map)

    val runnable1 = Runnable{
        val iterator = map.keys.iterator()
        while (iterator.hasNext()){
            Thread.sleep(100)
            var i = iterator.next()
            println("Key: $i, Value:${map.get(i)}")
        }
    }

    val runnable2 = Runnable{
        Thread.sleep(300)
        map.put(6, "user6")
    }

    val thread1 = Thread(runnable1)
    val thread2 = Thread(runnable2)

    thread1.start()
    thread2.start()
    thread1.join()
    thread2.join()
    println(map)

    // hashMap1 лучше не использовать, так как при чтение / изменении лочится весь HashMap
    val hashMap1 = Collections.synchronizedMap(HashMap<Int, Int>())
    // при чтение / изменении лочится только определенный bucket (сегмент массива) HashMap
    val hashMap2 = ConcurrentHashMap<Int, Int>()
}