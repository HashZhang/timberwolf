package io.timberwolf.common.serialization;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The serialization interface
 *
 * @author Hash Zhang
 * @version 0.0.0
 * @see @https://github.com/terrymanu/miracle-framework
 */
public interface Serialization {
    void serialize(OutputStream out, Object message) throws IOException;
    Object deserialize(InputStream in) throws IOException;
}
