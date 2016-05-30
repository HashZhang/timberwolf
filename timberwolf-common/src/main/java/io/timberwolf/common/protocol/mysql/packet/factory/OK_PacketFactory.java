package io.timberwolf.common.protocol.mysql.packet.factory;

import io.timberwolf.common.protocol.mysql.packet.MySQLPacket;

import java.nio.ByteBuffer;

/**
 * Factory for encoding,decoding and generate OK_Packet
 * @author Hash Zhang
 * @version 0.0.0
 * @see @http://dev.mysql.com/doc/internals/en/mysql-packet.html
 */
public class OK_PacketFactory {

    public static ByteBuffer encode(MySQLPacket mySQLPacket) {
        return null;
    }

    public static MySQLPacket decode(ByteBuffer byteBuffer) {
        return null;
    }
}
