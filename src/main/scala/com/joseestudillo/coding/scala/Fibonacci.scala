package com.joseestudillo.coding.scala

/**
  *
  * Created on 27/07/2017.
  *
  * @author Jose Estudillo
  */

import scala.collection.mutable

object Fibonacci {

  val FIB0 = 0
  val FIB1 = 1

  def newBaseFibMap(): mutable.Map[Int, Int] = mutable.Map[Int, Int](
    Fibonacci.FIB0 -> Fibonacci.FIB0,
    Fibonacci.FIB1 -> Fibonacci.FIB1
  )
}

class Fibonacci {

  def fibonacciRec(n: Int): Int = if (n < 2) n else fibonacciRec(n-1) + fibonacciRec(n-2)

  def fibonacciIter(n: Int): Int = {
    val map = Fibonacci.newBaseFibMap()
    (2 to n).foreach( i => map.put(i, map(i) + map(i)) )
    map(n)
  }
}
