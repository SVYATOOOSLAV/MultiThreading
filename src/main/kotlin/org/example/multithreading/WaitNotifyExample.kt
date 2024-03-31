package org.example.multithreading

import java.util.Objects

class WaitNotifyExample {
}

class Market{
    private val lock = Object()
    private var breadCount = 0

    // wait освобождает монитор, а notify нет
    // на том мониторе объекта, на котором мы синхронизируемся, на нем же и вызываем wait и notify
    // while мы используем для того, чтобы перепроверять условие (поток может проснуться без notify)
    fun getBread(){
        synchronized(lock){
            while(breadCount < 1){
                lock.wait() // текущий поток получения засыпает
            }
            breadCount--
            println("Хлеб куплен")
            println("Осталось хлеба: $breadCount \n")
            lock.notify() // будим поток производителя (если он спит)
        }
    }

    fun putBread(){
        synchronized(lock){
            while(breadCount >= 5){
                lock.wait() // поток производителя засыпает
            }
            breadCount++
            println("Добавлен хлеб")
            println("Всего хлеба: $breadCount \n")
            lock.notify() // будим поток потребителя (если он спит)
        }
    }
}

class Producer(private val market: Market) : Runnable{
    override fun run() {
        for(i in 1 until 10){
            market.putBread()
        }
    }
}

class Consumer(private val market: Market) : Runnable{
    override fun run() {
        for(i in 1 until 10){
            market.getBread()
        }
    }
}

fun main(){
    val market = Market()
    val producer = Producer(market)
    val consumer = Consumer(market)
    val thread1 = Thread(producer)
    val thread2 = Thread(consumer)

    thread1.start()
    thread2.start()
}