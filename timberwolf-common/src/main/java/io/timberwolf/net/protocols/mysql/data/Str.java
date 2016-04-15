package io.timberwolf.net.protocols.mysql.data;

import com.google.common.base.Preconditions;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;

/**
 * MySQL protocol DataType - String Types
 *
 * @author Hash Zhang
 * @version 0.0.0
 * @see http://dev.mysql.com/doc/internals/en/string.html
 */
public class Str {
    public static int lengthEncoded(ByteBuffer buffer, String string, String charSet) throws UnsupportedEncodingException {
        Preconditions.checkNotNull(string);
        int length = string.getBytes(charSet).length;
        length += Int.lengthEncoded(buffer,length);
        buffer.put(string.getBytes(charSet));
        return length;
    }
}
