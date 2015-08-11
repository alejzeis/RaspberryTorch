/**
 * This file is part of RaspberryTorch.
 *
 * RaspberryTorch is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * RaspberryTorch is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with RaspberryTorch.  If not, see <http://www.gnu.org/licenses/>.
 */
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
