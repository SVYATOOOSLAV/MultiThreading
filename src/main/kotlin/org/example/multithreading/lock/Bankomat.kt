package org.example.multithreading.lock

import java.util.concurrent.locks.Lock
import java.util.concurrent.locks.ReentrantLock

fun main(){
    // trylock используется как условие, если монитор свободный, то поток ставит лок,
    // если нет, то пропускает синхронизацию

    val lock: Lock = ReentrantLock()
    Employee("Svyat", lock)
    Employee("Kirill", lock)
    Employee("Den", lock)
    Thread.sleep(5000)
    Employee("Helena", lock)
    Employee("Mary", lock)
}

class Employee(
    private val name: String,
    private val lock: Lock
) : Thread() {

    init{
        this.start()
    }

    override fun run(){
       if (lock.tryLock()){
           try{
               println("$name пользуется банкоматом")
               Thread.sleep(2000)
               println("$name отошел(а) от банкомата")
           }
           finally{
               lock.unlock()
           }
       }
        else{
            println("$name не хочет ждать в очереди")
       }
    }
}