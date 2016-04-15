package io.timberwolf.net.protocols.mysql.packet.impl;

import io.timberwolf.net.protocols.mysql.packet.MySQLPacket;

import java.nio.ByteBuffer;

/**
 * OK_Packet
 * Structure:
 *|Type	         |Name	            |Description
 *|int<1>	     |header	        |[00] or [fe] the OK packet header
 *|int<lenenc>	 |affected_rows	    |affected rows
 *|int<lenenc>	 |last_insert_id	|last insert-id
 *if capabilities & CLIENT_PROTOCOL_41 {
 *|int<2>	     |status_flags	    |Status Flags
 *|int<2>	     |warnings	        |number of warnings
 *} elseif capabilities & CLIENT_TRANSACTIONS {
 *|int<2>	     |status_flags	    |Status Flags
 *}
 *if capabilities & CLIENT_SESSION_TRACK {
 *|string<lenenc>|info	            |human readable status information
 *if status_flags & SERVER_SESSION_STATE_CHANGED {
 *|string<lenenc>|session_state_changes|session state info
 *}
 *} else {
 *|string<EOF>	|info	|human readable status information
 *}
 * <p/>
 * Sending more than 16Mbyte,If the payload is larger than or equal to 224−1 bytes the length is set to 224−1 (ff ff ff) and
 * a additional packets are sent with the rest of the payload until the payload of a packet is less than 224−1 bytes.
 *
 * @author Hash Zhang
 * @version 0.0.0
 * @see http://dev.mysql.com/doc/internals/en/mysql-packet.html
 */
public class OK_Packet extends MySQLPacket {
    private int header;
    private long affected_rows;
    private long last_insert_id;

}