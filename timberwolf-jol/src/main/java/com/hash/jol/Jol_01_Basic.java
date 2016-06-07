package com.hash.jol;

import org.openjdk.jol.info.ClassLayout;
import org.openjdk.jol.info.GraphLayout;
import org.openjdk.jol.vm.VM;

import static java.lang.System.out;

/**
 * Created by 862911 on 2016/6/2.
 */
public class Jol_01_Basic {
    public static void main(String[] args) throws Exception {
        out.println(VM.current().details());
        out.println("********************** 1. Simple Class **********************");
        out.println(ClassLayout.parseClass(SimpleClass.class).toPrintable());
        out.println(ClassLayout.parseClass(extendClass.class).toPrintable());
        out.println("********************** 2. Sequence Extends **********************");
        out.println(ClassLayout.parseClass(LhsPadding.class).toPrintable());
        out.println(ClassLayout.parseClass(Value.class).toPrintable());
        out.println(ClassLayout.parseClass(RhsPadding.class).toPrintable());
        out.println(ClassLayout.parseClass(Sequence.class).toPrintable());
        long []array = new long[100];
        out.println("********************** 3. Array **********************");
        out.println( ClassLayout.parseClass(array.getClass()).toPrintable());
    }
}
class SimpleClass{
    long l;

}

class extendClass extends SimpleClass{
    boolean a;
    byte b;
    int c;
    long d;
    float e;
    double f;
    char g;
}

class LhsPadding
{
    protected long p1, p2, p3, p4, p5, p6, p7;
}

class Value extends LhsPadding
{
    protected long value;
}

class RhsPadding extends Value
{
    protected long p9, p10, p11, p12, p13, p14, p15;
}

class Sequence extends RhsPadding{

}
