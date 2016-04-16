package io.timberwolf.net.protocols.mysql.data;

import com.google.common.base.Preconditions;

import java.nio.ByteBuffer;

/**
 * MySQL protocol DataType - Integer Types
 *
 * @author Hash Zhang
 * @version 0.0.0
 * @see @http://dev.mysql.com/doc/internals/en/integer.html
 */
public class Int {
    //定长整型数据写入
    public static int writeLength1(ByteBuffer buffer, int num) {
        Preconditions.checkArgument(num < 0x100);
        buffer.put((byte) num);
        return 1;
    }

    public static int readLength1(ByteBuffer buffer) {
        Preconditions.checkNotNull(buffer);
        return (int) buffer.get();
    }

    public static int writeLength2(ByteBuffer buffer, int num) {
        Preconditions.checkArgument(num < 0x10000);
        buffer.put((byte) (num));
        buffer.put((byte) (num >>> 8));
        return 2;
    }

    public static int readLength2(ByteBuffer buffer) {
        Preconditions.checkNotNull(buffer);
        int result = (int) buffer.get();
        result += ((int) buffer.get()) << 8;
        return result;
    }

    public static int writeLength3(ByteBuffer buffer, int num) {
        Preconditions.checkArgument(num < 0x1000000);
        buffer.put((byte) (num));
        buffer.put((byte) (num >>> 8));
        buffer.put((byte) (num >>> 16));
        return 3;
    }

    public static int readLength3(ByteBuffer buffer) {
        Preconditions.checkNotNull(buffer);
        int result = (int) buffer.get();
        result += ((int) buffer.get()) << 8;
        result += ((int) buffer.get()) << 16;
        return result;
    }

    public static int writeLength4(ByteBuffer buffer, long num) {
        Preconditions.checkArgument(num < 0x100000000L);
        buffer.put((byte) (num));
        buffer.put((byte) (num >>> 8));
        buffer.put((byte) (num >>> 16));
        buffer.put((byte) (num >>> 24));
        return 4;
    }

    public static long readLength4(ByteBuffer buffer) {
        Preconditions.checkNotNull(buffer);
        long result = (long) buffer.get();
        result += ((long) buffer.get()) << 8;
        result += ((long) buffer.get()) << 16;
        result += ((long) buffer.get()) << 24;
        return result;
    }

    public static int length6(ByteBuffer buffer, long num) {
        Preconditions.checkArgument(num <= 0x1000000000000L);
        buffer.put((byte) (num));
        buffer.put((byte) (num >>> 8));
        buffer.put((byte) (num >>> 16));
        buffer.put((byte) (num >>> 24));
        return 6;
    }

    public static int length8(ByteBuffer buffer, long num) {
        buffer.put((byte) num);
        return 8;
    }

    //长度编码整型数据写入
    public static int lengthEncoded(ByteBuffer buffer, long num) {
        if (num < 0xfb) {
            writeLength1(buffer, (int) num);
            return 1;
        } else if (num < 0x10000) {
            buffer.put((byte) 0xfc);
            writeLength2(buffer, (int) num);
            return 2;
        } else if (num < 0x10000) {
            buffer.put((byte) 0xfd);
            writeLength3(buffer, (int) num);
            return 3;
        } else {
            buffer.put((byte) 0xfe);
            length8(buffer, num);
            return 4;
        }
    }

}
