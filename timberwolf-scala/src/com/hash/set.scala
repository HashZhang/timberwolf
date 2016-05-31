package com.hash

/**
  * Created by 862911 on 2016/5/31.
  */
class set {
  def setTest1(): Unit = {
    var set1 = Set(1, 2, 3)
    println(set1.mkString(" "))
    set1 += 5
    println(set1.mkString(" "))
    val set2 = scala.collection.mutable.Set(1, 2, 4)
    set2 += 3
    println(set2.mkString(" "))
  }
}
