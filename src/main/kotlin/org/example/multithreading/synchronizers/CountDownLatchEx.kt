package org.example.multithreading.synchronizers

import java.util.concurrent.CountDownLatch

class CountDownLatchEx {
    companion object {
        var countDownLatch = CountDownLatch(3)
        fun marketStaffIsOnPlace() {
            Thread.sleep(2000)
            println("Market staff came to work")
            countDownLatch.countDown() // уменьшаем счетчик задач на 1
            println("CountDownLatch = ${countDownLatch.count}")
        }

        fun everythingIsReady() {
            Thread.sleep(3000)
            println("Everything is ready, so let's open market")
            countDownLatch.countDown() // уменьшаем счетчик задач на 1
            println("CountDownLatch = ${countDownLatch.count}")
        }

        fun openMarket() {
            Thread.sleep(4000)
            println("Market is open")
            countDownLatch.countDown() // уменьшаем счетчик задач на 1
            println("CountDownLatch = ${countDownLatch.count}")
        }
    }
}

class Friend(private val name: String, private val countDownLatch: CountDownLatch) : Thread() {
    init {
        start()
    }

    override fun run() {
        // Если счетчик > 0, то поток будет заблокирован до тех пор, пока счетчик != 0
        // Если счетчик = 0, то поток будет выполнять свою работу
        countDownLatch.await()
        println("$name starts to buy something")
    }
}

fun main() {
    Friend("friend-1", CountDownLatchEx.countDownLatch)
    Friend("friend-2", CountDownLatchEx.countDownLatch)
    Friend("friend-3", CountDownLatchEx.countDownLatch)
    Friend("friend-4", CountDownLatchEx.countDownLatch)
    Friend("friend-5", CountDownLatchEx.countDownLatch)

    CountDownLatchEx.marketStaffIsOnPlace()
    CountDownLatchEx.everythingIsReady()
    CountDownLatchEx.openMarket()
}