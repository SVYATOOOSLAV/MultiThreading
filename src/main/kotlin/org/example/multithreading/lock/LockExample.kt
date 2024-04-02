package org.example.multithreading.lock

import java.lang.Exception
import java.util.concurrent.locks.Lock
import java.util.concurrent.locks.ReentrantLock

fun main(){
    //ReentrantLock и synchronized блок делают тоже самое
    // на объекте ReentrantLock всегда необходимо открывать и закрывать монитор объекта
    val call: Call = Call()
    val thread1: Thread = Thread{
        call.mobileCall()
    }
    val thread2: Thread = Thread{
        call.whatsAppCall()
    }
    val thread3: Thread = Thread{
        call.telegramCall()
    }

    thread1.start()
    thread2.start()
    thread3.start()
}

class Call{
    private val lock: Lock = ReentrantLock()

    fun mobileCall(){
        lock.lock()
        try{
            println("Mobile call starts")
            Thread.sleep(3000)
            println("Mobile call ends")
        }catch (e: Exception){
            e.printStackTrace()
        }
        finally {
            lock.unlock() //unlock всегда необходимо писать в finally для открытия монитора
        }
    }

    fun whatsAppCall(){
        lock.lock()
        try{
            println("whatsApp call starts")
            Thread.sleep(4000)
            println("whatsApp call ends")
        }catch (e: Exception){
            e.printStackTrace()
        }
        finally {
            lock.unlock() //unlock всегда необходимо писать в finally для открытия монитора
        }
    }

    fun telegramCall(){
        lock.lock()
        try{
            println("telegram call starts")
            Thread.sleep(5000)
            println("telegram call ends")
        }catch (e: Exception){
            e.printStackTrace()
        }
        finally {
            lock.unlock() //unlock всегда необходимо писать в finally для открытия монитора
        }
    }
}