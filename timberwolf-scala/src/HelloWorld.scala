import java.util
package com {
  package hash {

    object HelloWorld {

      //public
      def publicMethod() {}

      protected def protectedMethod() {}

      private def privateMethod() {}

      private[hash] def priva() {
        Console.println("haha");
      }

      def main(args: Array[String]) {
        val message = "Hello World!";
        Console.println(message);
        var stringArray = new util.ArrayList[String]();
        var X: Int = 6;
        Console.println(X);

        val (myval1, myval2) = Pair(40, "StringVal2");
        Console.println(myval1, "|", myval2);
        //输出元组
        var (myvar1, myvar2) = Pair(40, "StringVal2");
        Console.println(myvar1, "|", myvar2);
        myvar1 = 20;

      }
    }
  }
}
