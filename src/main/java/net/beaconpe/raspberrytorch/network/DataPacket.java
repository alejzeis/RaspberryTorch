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
    private NetworkChannel channel = NetworkChannel.CHANNEL_NONE;

    /**
     * Get this packet's packet ID.
     * @return The packet's packet ID.
     */
    public abstract byte getPID();

    /**
     * Get this packet's length. This includes the packet ID.
     * @return The packet's length.
     */
    public abstract int getLength();

    /**
     * Encode this packet into binary form.
     * @return The encoded packet, as a byte[].
     */
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

    /**
     * Decode this packet from binary form, and fill in the fields.
     * @param bytes The raw packet data.
     */
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

    /**
     * Get this packet's NetworkChannel.
     * @return The Network Channel.
     */
    public NetworkChannel getChannel() {
        return channel;
    }

    /**
     * Set this packet's NetworkChannel.
     * @param channel The NetworkChannel.
     */
    public void setChannel(NetworkChannel channel) {
        this.channel = channel;
    }
}
