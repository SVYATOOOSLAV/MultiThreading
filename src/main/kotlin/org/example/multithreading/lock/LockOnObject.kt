package org.example.multithreading.lock


class ExCall{
    companion object{
        val lock: Object = Object()
    }

    fun mobileCall(){
        synchronized(lock){
            println("Start mobile call")
            Thread.sleep(2000)
            println("Stop mobile call")
        }
    }

    fun whatsAppCall(){
        synchronized(lock){
            println("Start whatsApp call")
            Thread.sleep(3000)
            println("Stop whatsApp call")
        }
    }
    fun telegramCall(){
        synchronized(lock){
            println("Start telegram call")
            Thread.sleep(4000)
            println("Stop telegram call")
        }
    }
}

fun main(){
    val thread1: Thread = Thread {
        ExCall().mobileCall()
    }
    val thread2: Thread = Thread {
        ExCall().whatsAppCall()
    }
    val thread3: Thread = Thread {
        ExCall().telegramCall()
    }

    thread1.start()
    thread2.start()
    thread3.start()

}