package org.example.multithreading.synchronizers

import java.util.concurrent.Exchanger

fun main(){
    var exchanger = Exchanger<Action>()
    val friend1 = BestFriend("friend-1", arrayListOf(Action.NOJNICI, Action.BUMAGA, Action.NOJNICI), exchanger)
    val friend2 = BestFriend("friend-2", arrayListOf(Action.BUMAGA, Action.KAMEN, Action.KAMEN), exchanger)

}

enum class Action {
    KAMEN,
    NOJNICI,
    BUMAGA
}


class BestFriend(
    private val name: String,
    private val myActions: ArrayList<Action>,
    private val exchanger: Exchanger<Action>
) : Thread()
{
    init {
        start()
    }

    private fun whoWins (myAction: Action, friendAction: Action){
        if(myAction == Action.KAMEN && friendAction == Action.NOJNICI ||
            myAction == Action.NOJNICI && friendAction == Action.BUMAGA ||
            myAction == Action.BUMAGA && friendAction == Action.KAMEN){
            println("$name wins!!!")
        }
    }

    override fun run() {
        var reply: Action
        for(action in myActions){
            // обмениваюсь с другим поток одинаковой инфомрацией
            // текущий поток лочится до момента пока другой не передаст в exchanger информацию
            reply = exchanger.exchange(action)
            whoWins(action, reply)
            Thread.sleep(2000)
        }
    }
}
