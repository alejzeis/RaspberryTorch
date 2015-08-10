package net.beaconpe.raspberrytorch.io;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Utility I/O class used by RaspberryTorch for Minecraft protocol decoding.
 *
 * @author jython234
 */
public class RaspberryInputStream extends DataInputStream{

    /**
     * Creates a RaspberryInputStream that uses the specified
     * underlying InputStream.
     *
     * @param in the specified input stream
     */
    public RaspberryInputStream(InputStream in) {
        super(in);
    }

    /**
     * Reads a Minecraft: Pi edition String.
     * @return The String.
     * @throws IOException If there is an error while decoding.
     */
    public String readString() throws IOException {
        int len = readUnsignedShort();
        byte[] data = new byte[len];
        read(data);
        return new String(data);
    }
}
