package io.timberwolf.common.protocol.mysql.data;

import static com.google.common.base.Preconditions.*;

import java.nio.ByteBuffer;

/**
 * MySQL protocol DataType - Integer Types
 *
 * @author Hash Zhang
 * @version 0.0.0
 * @see @http://dev.mysql.com/doc/internals/en/integer.html
 */
public class Int {
    //定长整型数据编码解码
    public static int writeLength1(ByteBuffer buffer, int num) {
        checkArgument(num < 0x100);
        buffer.put((byte) num);
        return 1;
    }

    public static int readLength1(ByteBuffer buffer) {
        checkNotNull(buffer);
        return (int) buffer.get();
    }

    public static int writeLength2(ByteBuffer buffer, int num) {
        checkArgument(num < 0x10000);
        buffer.put((byte) (num));
        buffer.put((byte) (num >>> 8));
        return 2;
    }

    public static int readLength2(ByteBuffer buffer) {
        ;
        int result = (int) buffer.get();
        result += ((int) buffer.get()) << 8;
        return result;
    }

    public static int writeLength3(ByteBuffer buffer, int num) {
        checkArgument(num < 0x1000000);
        buffer.put((byte) (num));
        buffer.put((byte) (num >>> 8));
        buffer.put((byte) (num >>> 16));
        return 3;
    }

    public static int readLength3(ByteBuffer buffer) {
        checkNotNull(buffer);
        int result = (int) buffer.get();
        result += ((int) buffer.get()) << 8;
        result += ((int) buffer.get()) << 16;
        return result;
    }

    public static int writeLength4(ByteBuffer buffer, long num) {
        checkArgument(num < 0x100000000L);
        buffer.put((byte) (num));
        buffer.put((byte) (num >>> 8));
        buffer.put((byte) (num >>> 16));
        buffer.put((byte) (num >>> 24));
        return 4;
    }

    public static long readLength4(ByteBuffer buffer) {
        checkNotNull(buffer);
        long result = (long) buffer.get();
        result += ((long) buffer.get()) << 8;
        result += ((long) buffer.get()) << 16;
        result += ((long) buffer.get()) << 24;
        return result;
    }

    public static int wrietLength6(ByteBuffer buffer, long num) {
        checkArgument(num <= 0x1000000000000L);
        buffer.put((byte) (num));
        buffer.put((byte) (num >>> 8));
        buffer.put((byte) (num >>> 16));
        buffer.put((byte) (num >>> 24));
        buffer.put((byte) (num >>> 32));
        buffer.put((byte) (num >>> 40));
        return 6;
    }

    public static long readLength6(ByteBuffer buffer) {
        checkNotNull(buffer);
        long result = (long) buffer.get();
        result += ((long) buffer.get()) << 8;
        result += ((long) buffer.get()) << 16;
        result += ((long) buffer.get()) << 24;
        result += ((long) buffer.get()) << 32;
        result += ((long) buffer.get()) << 40;
        return result;
    }

    public static int writeLength8(ByteBuffer buffer, long num) {
        buffer.put((byte) num);
        buffer.put((byte) (num >>> 8));
        buffer.put((byte) (num >>> 16));
        buffer.put((byte) (num >>> 24));
        buffer.put((byte) (num >>> 32));
        buffer.put((byte) (num >>> 40));
        buffer.put((byte) (num >>> 48));
        buffer.put((byte) (num >>> 56));
        return 8;
    }

    public static long readLength8(ByteBuffer buffer) {
        checkNotNull(buffer);
        long result = (long) buffer.get();
        result += ((long) buffer.get()) << 8;
        result += ((long) buffer.get()) << 16;
        result += ((long) buffer.get()) << 24;
        result += ((long) buffer.get()) << 32;
        result += ((long) buffer.get()) << 40;
        result += ((long) buffer.get()) << 48;
        result += ((long) buffer.get()) << 56;
        return result;
    }

    //长度编码整型数据编码解码
    public static int writeLengthEncoded(ByteBuffer buffer, long num) {
        if (num < 0xfb) {
            writeLength1(buffer, (int) num);
            return 1;
        } else if (num < 0x10000) {
            buffer.put((byte) 0xfc);
            writeLength2(buffer, (int) num);
            return 3;
        } else if (num < 0x10000) {
            buffer.put((byte) 0xfd);
            writeLength3(buffer, (int) num);
            return 4;
        } else {
            buffer.put((byte) 0xfe);
            writeLength8(buffer, num);
            return 9;
        }
    }

    public static long readLengthEncoded(ByteBuffer buffer) {
        int num = readLength1(buffer);
        long result = 0;
        if(num < 0xfb){
            result += num;
        } else if(num == 0xfc){
            result += readLength2(buffer);
        } else if(num == 0xfd){
            result += readLength3(buffer);
        } else{
            result +=readLength8(buffer);
        }
        return result;
    }
}
