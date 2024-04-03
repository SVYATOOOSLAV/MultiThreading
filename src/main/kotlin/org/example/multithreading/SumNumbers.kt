package org.example.multithreading

import java.util.concurrent.Callable
import java.util.concurrent.Executors
import java.util.concurrent.Future

class SumNumbers{
    companion object{
        val value : Long = 1_000_000_000L
        var sum: Long = 0
    }
}

fun main() {
    val executorService = Executors.newFixedThreadPool(10)
    val list = ArrayList<Future<Long>>()
    val range = SumNumbers.value / 10
    for(i in 0 .. 10){
        val from = range * i + 1
        val to = range * (i+1)
        val partialSum: PartialSum = PartialSum(from, to)
        val future =  executorService.submit(partialSum)
        list.add(future)
    }
    executorService.shutdown()
    for(el in list){
        SumNumbers.sum += el.get()
    }
    println(SumNumbers.sum)
}

class PartialSum(private var from: Long, private var to: Long) : Callable<Long> {
    private var localSum: Long = 0

    override fun call(): Long {
        for(i in from .. to){
            localSum += i
        }
        println("Sum from $from to $to is $localSum")
        return localSum
    }
}