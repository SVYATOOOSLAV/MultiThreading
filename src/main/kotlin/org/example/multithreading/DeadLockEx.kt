package org.example.multithreading

class DeadLockEx {
    companion object {
        val lock1: Object = Object()
        val lock2: Object = Object()
    }
}

class ThreadDeadLock1 : Thread() {
    override fun run() {
        println("ThreadDeadLock1 Попытка захватить монитор объекта lock1")
        synchronized(DeadLockEx.lock1) {
            println("ThreadDeadLock1 монитор объекта lock1 захвачен")
            println("ThreadDeadLock1 Попытка захватить монитор объекта lock2")
            synchronized(DeadLockEx.lock2) {
                println("ThreadDeadLock1 мониторы объектов lock1 и lock2 захвачены")
            }
        }
    }
}

class ThreadDeadLock2 : Thread() {
    override fun run() {
        println("ThreadDeadLock2 Попытка захватить монитор объекта lock2")
        synchronized(DeadLockEx.lock2) {
            println("ThreadDeadLock2 монитор объекта lock2 захвачен")
            println("ThreadDeadLock2 Попытка захватить монитор объекта lock1")
            synchronized(DeadLockEx.lock1) {
                println("ThreadDeadLock2 мониторы объектов lock1 и lock2 захвачены")
            }
        }
    }
}

fun main() {
    val thread1 = ThreadDeadLock1()
    val thread2 = ThreadDeadLock2()
    thread1.start()
    thread2.start()

    // Для того, чтобы выйти из DeadLock необходимо сделать так,
    // чтобы потоки захватывали последовательно одинаковые мониторы
}