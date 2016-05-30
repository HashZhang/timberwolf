package io.timberwolf.common.protocol.mysql.packet;

import io.timberwolf.common.protocol.mysql.data.Int;

import java.nio.ByteBuffer;

/**
 * MySQL Packet
 * Structure:
 * |Type	        ||Name	            ||Description
 * |------------------------------------------------------------------------------------------------------------------------------------------------------
 * |int<3>	    ||payload_length	||Length of the payload. The number of bytes in the packet beyond the initial 4 bytes that make up the packet header.
 * |int<1>	    ||sequence_id	    ||Sequence ID
 * |string<var>	||payload	        ||[len=payload_length] payload of the packet
 * <p/>
 * Sending more than 16Mbyte,If the payload is larger than or equal to 224−1 bytes the length is set to 224−1 (ff ff ff) and
 * a additional packets are sent with the rest of the payload until the payload of a packet is less than 224−1 bytes.
 *
 * @author Hash Zhang
 * @version 0.0.0
 * @see @http://dev.mysql.com/doc/internals/en/mysql-packet.html
 */
public abstract class MySQLPacket {
    protected final static int PAYLOAD_LENGTH_LENGTH = 3;
    protected final static int SEQUENCE_ID_LENGTH = 1;

    protected final static int MAX_PAYLOAD_LENGTH = 1 << (8 * PAYLOAD_LENGTH_LENGTH) - 1;
    protected final static int MAX_SEQUENCE_ID = 1 << (8 * SEQUENCE_ID_LENGTH) - 1;

    protected int payload_Length;
    protected int sequenceId;

    protected ByteBuffer byteBuffer = ByteBuffer.allocate(0);

    protected void encode(){
        ByteBuffer byteBuffer = ByteBuffer.allocate(4);
        Int.writeLength3(byteBuffer,payload_Length);
        Int.writeLength1(byteBuffer,sequenceId);
    }

    protected void decode(){

    }
}
