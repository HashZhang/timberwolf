package io.timberwolf.net.protocols.mysql.data;

import junit.framework.Assert;
import org.junit.Test;

import java.nio.ByteBuffer;

/**
 * Test Integer Types
 *
 * @author Hash Zhang
 * @version 0.0.0
 * @see Int
 */
public class TestInt {
    private ByteBuffer byteBuffer = ByteBuffer.allocate(0);

    @Test
    public void testLength1() {
        int a = 14;
        ByteBuffer byteBuffer = ByteBuffer.allocate(this.byteBuffer.capacity() + 1);
        System.out.println(this.byteBuffer.capacity() + 1);
        byteBuffer.put(this.byteBuffer);
        Int.length1(byteBuffer, a);
        this.byteBuffer = byteBuffer;
        byteBuffer = ByteBuffer.allocate(this.byteBuffer.capacity() + 1);
        System.out.println(this.byteBuffer.capacity() + 1);
        byteBuffer.put(this.byteBuffer);
        Int.length1(byteBuffer, a);
        this.byteBuffer = byteBuffer;
        this.byteBuffer.flip();
        System.out.println(this.byteBuffer.position());
        int b = Int.length1(this.byteBuffer);
        System.out.println("b:"+b);
        System.out.println(this.byteBuffer.position());
        this.byteBuffer.flip();
        int c = Int.length1(this.byteBuffer);
        System.out.println("c:"+c);
        Assert.assertEquals(a, b);
        Assert.assertEquals(a, c);
    }
}
