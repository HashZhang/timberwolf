package com.hash

/**
  * Created by 862911 on 2016/5/31.
  */
class map {
  def mapTest1(): Unit = {
    var map1 = Map(1 -> "a", 2 -> "b")
    println(map1.mkString(" "))
    map1 += 3 -> "c"
    println(map1.mkString(" "))
    val map2 = scala.collection.mutable.Map(1 -> "d", 2 -> "e")
    println(map2.mkString(" "))
    map2 += 3 -> "f"
    println(map2.mkString(" "))
  }
}
