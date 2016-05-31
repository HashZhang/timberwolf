package com.hash

/**
  * Created by 862911 on 2016/5/31.
  */
class list {
  def testList1(): Unit = {
    val intlist = List(1, 2, 3, 4)
    //Nil结尾是必须的
    val stringList = "a" :: "b" :: "c" :: "d" :: Nil
    val doubleList = List(1.0, 2.0) ::: List(3.0, 4.0)
    println(intlist(2))
    val count1 = stringList.count(s => s.length == 1)
    println("count1:" + count1)
    println("if b exists: " + stringList.exists(s => s == "b"))
    println("all length is 1: " + stringList.forall(s => s.length == 1))
    println("print each element:")
    stringList.foreach(s => print(s + " "))
    stringList.foreach(print)
    println()
    println(stringList.head)
    stringList.init.foreach(s => print(s + " "))
    println()
    println((stringList.map(s => s + "1").mkString(" ")))
    println("tail: "+stringList.tail.mkString(" "))
  }
}
