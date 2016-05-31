
package com {
  package hash {

    import java.util.Date

    import scala.util.control.Breaks

    object HelloWorld {

      //public
      def publicMethod() {}

      protected def protectedMethod() {}

      private def privateMethod() {}

      private[hash] def priva() {
        Console.println("haha");
      }

      def printList(list: List[Int]): Unit = {
        for (element <- list) {
          println(element);
        }
      }

      def printSeparator(): Unit = {
        for (i <- 0 until 256) {
          print("*");
        }
        println();
      }

      def time() = {
        println("Getting current nano time");
        System.nanoTime()
      }

      def main(args: Array[String]) {
        val message = "Hello World!";
        Console.println(message);

        val (myval1, myval2) = Pair(40, "StringVal2");
        Console.println(myval1, "|", myval2);
        //输出元组
        var (myvar1, myvar2) = Pair(40, "StringVal2");
        Console.println(myvar1, "|", myvar2);
        myvar1 = 20;
        printSeparator();
        Console.println("Loop start to:");
        for (a <- 0 to 2) {
          Console.println(a);
        }
        printSeparator();
        Console.println("Loop start until:");
        for (a <- 0 until 2) {
          Console.println(a);
        }
        printSeparator();
        Console.println("Loop start double:");
        for (a <- 0 to 2; b <- 0 until 10) {
          Console.println(a + ":" + b);
        }
        printSeparator();
        Console.println("Enumeration of List:");
        var numList = List(1, 2, 3, 4, 5, 6);
        for (number <- numList) {
          Console.println(number);
        }
        printSeparator();
        Console.println("Enumeration of List with condition:");
        for (number <- numList; if number != 1; if number < 5) {
          Console.println(number);
        }
        printSeparator();
        Console.println("Yield Enumeration of List with condition:");
        var conList = for {
          number <- numList; if number != 1; if number < 5
        } yield number;

        for (number <- conList) {
          Console.println(number);
        }
        printSeparator();
        Console.println("Breakable:");
        var loop = new Breaks;
        loop.breakable {
          for (number <- numList) {
            println(number);
            if (number == 3) {
              loop.break;
            }
          }
        }
        printSeparator();
        printList(conList);
        printSeparator();
        println(time());
        printSeparator();
        println(factorial(20));
        printSeparator();
        printArgs("", "java", "scala");
        printSeparator();
        println(add());
        printSeparator();
        println(execute(format[Int], 5));
        printSeparator();
        var multiplex = (x: Int, y: Int) => x * y;
        println(multiplex(3, 4));
        printSeparator();
        var userDir = () => System.getProperty("user.dir");
        println(userDir());
        printSeparator();
        println(strcat("zhx")("hash"));
        printSeparator();
        var date: Date = new Date;
        var logWithDate = log(date, _: String);
        logWithDate("Hello");
        printSeparator();
        val floatVal = 1.203f;
        val intVal = 10;
        val stringVal = "zhxhash";
        val fs = printf("floatVal is %f,intVal is %d,stringVal is %s", floatVal, intVal, stringVal);
        println(fs);
        printSeparator();
        val myList1 = Array(1.2, 3.4, 5.6, 7.8);
        val myList2 = Array[Double](1, 2, 3, 4);
        val myList12 = Array.concat[Double](myList1, myList2);
        for (element <- myList12) {
          println(element);
        }
        printSeparator();
        val myList3 = Range(10,20,3);
        for (element <- myList3) {
          println(element);
        }
        printSeparator();
        val arrayTest = new array;
        arrayTest.testArray1();
        printSeparator();
        val listTest = new list;
        listTest.testList1()
        printSeparator()
        val tupleTest = new tuple
        tupleTest.tupleTest1()
        printSeparator()
        val setTest = new set
        setTest.setTest1()
        printSeparator()
        val mapTest = new map
        mapTest.mapTest1()
        printSeparator()
      }


      def log(date: Date, message: String): Unit = {
        println(date + "------" + message);
      }

      def factorial(n: Long): Long = {
        if (n <= 1) {
          1
        } else {
          n * factorial(n - 1);
        }
      }

      def printArgs(args: String*): Unit = {
        var i: Int = 0;
        for (string <- args) {
          println("args[" + i + "] = " + string);
          i += 1;
        }
      }

      def add(a: Int = 5, b: Int = 6): Int = {
        return a + b;
      }

      def execute(f: Int => String, v: Int) = {
        f(v);
      }

      def format[X](a: X) = {
        "[" + a.toString + "]";
      }

      def strcat(str1: String)(str2: String) = {
        str1 + str2;
      }


    }

  }

}