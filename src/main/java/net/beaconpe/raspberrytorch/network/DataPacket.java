package net.beaconpe.raspberrytorch.network;

import net.beaconpe.raspberrytorch.io.RaspberryInputStream;
import net.beaconpe.raspberrytorch.io.RaspberryOutputStream;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * Represents a minecraft DataPacket.
 *
 * @author jython234
 */
public abstract class DataPacket {
    public abstract byte getPID();
    public abstract int getLength();

    public final byte[] encode() {
        ByteArrayOutputStream bout = new ByteArrayOutputStream(getLength());
        RaspberryOutputStream out = new RaspberryOutputStream(bout);

        byte[] data = null;
        try {
            out.writeByte(getPID());
            _encode(out);
            data = bout.toByteArray();
            bout.close();
            out.close();
        } catch (IOException e) {
            throw new PacketEncodeException(e);
        } finally {
            return data;
        }
    }

    public final void decode(byte[] bytes) {
        ByteArrayInputStream bin = new ByteArrayInputStream(bytes);
        RaspberryInputStream in = new RaspberryInputStream(bin);
        try {
            in.readByte();
            _decode(in);
            bin.close();
            in.close();
        } catch (IOException e) {
            throw new PacketDecodeException(e);
        }
    }

    protected void _encode(RaspberryOutputStream out) throws IOException{
        throw new PacketEncodeException("Encoding for this packet is not implemented!");
    }

    protected void _decode(RaspberryInputStream in) throws IOException {
        throw new PacketDecodeException("Decoding for this packet is not implemented!");
    }
}
