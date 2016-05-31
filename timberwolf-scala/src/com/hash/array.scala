package com.hash

/**
  * Created by 862911 on 2016/5/31.
  */
class array {
  def testArray1(): Unit ={
    val greetings = Array("Hello","world","scala");
    val greetings2 = Array.apply("Hello","world","scala");
    for(element <- greetings)
      println(element);
    for(element <- greetings2)
      println(element);
  }
}
