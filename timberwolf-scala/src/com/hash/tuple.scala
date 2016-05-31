package com.hash

/**
  * Created by 862911 on 2016/5/31.
  * similar to list, tuple is unchangeable;
  */
class tuple {
  def tupleTest1(): Unit ={
    val tup = ("a",1,3.0)
    println(tup._1)
    println(tup._2)
    println(tup._3)
  }
}
