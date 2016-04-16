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
        int a = 14,b=15;
        ByteBuffer byteBuffer = ByteBuffer.allocate(this.byteBuffer.capacity() + 1);
        this.byteBuffer.flip();
        byteBuffer.put(this.byteBuffer);
        Int.writeLength1(byteBuffer, a);
        this.byteBuffer = byteBuffer;
        byteBuffer = ByteBuffer.allocate(this.byteBuffer.capacity() + 1);
        this.byteBuffer.flip();
        byteBuffer.put(this.byteBuffer);
        Int.writeLength1(byteBuffer, b);
        this.byteBuffer = byteBuffer;
        this.byteBuffer.flip();
        int c = Int.readLength1(this.byteBuffer);
        int d = Int.readLength1(this.byteBuffer);
        Assert.assertEquals(a, c);
        Assert.assertEquals(b, d);
    }

    @Test
    public void testLength2() {
        int a = 0x100,b=0x101;
        ByteBuffer byteBuffer = ByteBuffer.allocate(this.byteBuffer.capacity() + 2);
        this.byteBuffer.flip();
        byteBuffer.put(this.byteBuffer);
        Int.writeLength2(byteBuffer, a);
        this.byteBuffer = byteBuffer;
        byteBuffer = ByteBuffer.allocate(this.byteBuffer.capacity() + 2);
        this.byteBuffer.flip();
        byteBuffer.put(this.byteBuffer);
        Int.writeLength2(byteBuffer, b);
        this.byteBuffer = byteBuffer;
        this.byteBuffer.flip();
        int c = Int.readLength2(this.byteBuffer);
        int d = Int.readLength2(this.byteBuffer);
        Assert.assertEquals(a, c);
        Assert.assertEquals(b, d);
    }
}
