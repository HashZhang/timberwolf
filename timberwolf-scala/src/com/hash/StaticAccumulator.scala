package com.hash

/**
  * Created by 862911 on 2016/5/31.
  */
class StaticAccumulator {

}

object StaticAccumulator {
  private val cache = scala.collection.mutable.WeakHashMap[String,Int]()

  def calculate(comment: String) = {
    if (cache.contains(comment)) {
      cache(comment)
    } else{
      val a = comment.hashCode;
      cache += comment -> a;
      a
    }
  }
}