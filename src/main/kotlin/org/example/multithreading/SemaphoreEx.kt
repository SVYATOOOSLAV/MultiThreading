package org.example.multithreading

import java.util.concurrent.Semaphore

fun main() {
    val callBox = Semaphore(2)
    val person = Person("person-1", callBox)
    val person2 = Person("person-2", callBox)
    val person3 = Person("person-3", callBox)
    val person4 = Person("person-4", callBox)
    val person5 = Person("person-5", callBox)
}

class Person(private val name: String, private val callBox: Semaphore) : Thread() {
    init {
        start()
    }

    override fun run() {
        try {
            println("$name ждет....")
            // Пытаемся получить разрешение от семафора, есть разрешение, идем дальше (счетчик - 1)
            // Если нет, то лочимся на этом моменте пока ресурс не будет доступен
            callBox.acquire()
            println("$name пользуется телефоном")
            Thread.sleep(2000)
            println("$name завершил звонок")
        } finally {
            callBox.release() // освобождаем ресурс, счетчик + 1
            // пишем его в finally, так как если произойдет ошибка, то доступ освободится
        }

    }
}