package com.hash.scala.recipe

/**
  * Created by Hash Zhang on 2016/6/2.
  */
object ControlLiteralTest {
  def conditionExpr(x: Int) {
    //var a = if (x > 0) 1 可能没有值
    var a = if (x > 0) 1 else ()
    var b = if (x > 0) 1 else "hello"
    println(a + " | " + b)
  }

  def advancedFor() {

  }
}
