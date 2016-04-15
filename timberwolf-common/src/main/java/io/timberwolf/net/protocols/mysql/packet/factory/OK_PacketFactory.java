package io.timberwolf.net.protocols.mysql.packet.factory;

import io.timberwolf.net.protocols.mysql.packet.MySQLPacket;
import io.timberwolf.net.protocols.mysql.packet.impl.OK_Packet;

import java.nio.ByteBuffer;

/**
 * Factory for encoding,decoding and generate OK_Packet
 * @author Hash Zhang
 * @version 0.0.0
 * @see http://dev.mysql.com/doc/internals/en/mysql-packet.html
 */
public class OK_PacketFactory {

    public static ByteBuffer encode(MySQLPacket mySQLPacket) {
        return null;
    }

    public static MySQLPacket decode(ByteBuffer byteBuffer) {
        return null;
    }
}
