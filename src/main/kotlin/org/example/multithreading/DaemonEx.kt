package org.example.multithreading

class UserThread: Thread(){
    override fun run() {
        println("${Thread.currentThread().name} id daemon: $isDaemon")
        for(i in 'A'..'J'){
            sleep(300)
            println(i)
        }
    }
}

class DaemonThread : Thread(){
    override fun run() {
        println("${Thread.currentThread().name} id daemon: $isDaemon")
        for(i in 1..1000){
            sleep(100)
            println(i)
        }
    }
}

fun main(){
    println("Main thread starts")

    val userThread: UserThread = UserThread()
    userThread.setName("user_thread")
    val daemonThread: DaemonThread = DaemonThread()

    daemonThread.setName("daemon_thread")
    daemonThread.setDaemon(true)

    userThread.start()
    daemonThread.start()

    println("Main thread ends")

    //Daemon - это не основной поток, jvm может не дожидаться его завершение а просто закончить прогу
    // обычно необходим для фоновых задач
    // jvm зависит от основных потоков (user thread)
}