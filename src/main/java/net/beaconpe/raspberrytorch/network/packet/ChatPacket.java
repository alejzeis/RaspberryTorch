package net.beaconpe.raspberrytorch.network.packet;

import net.beaconpe.raspberrytorch.io.RaspberryInputStream;
import net.beaconpe.raspberrytorch.io.RaspberryOutputStream;
import net.beaconpe.raspberrytorch.network.DataPacket;
import net.beaconpe.raspberrytorch.network.NetworkIds;

import java.io.IOException;

/**
 * ChatPacket (0xb4)
 *
 * @author jython234
 */
@Deprecated
public class ChatPacket extends DataPacket{
    public final static byte ID = NetworkIds.CHAT_PACKET;

    public String message;

    @Override
    protected void _encode(RaspberryOutputStream out) throws IOException {
        out.writeString(message);
    }

    @Override
    protected void _decode(RaspberryInputStream in) throws IOException {
        message = in.readString();
    }

    @Override
    public byte getPID() {
        return NetworkIds.CHAT_PACKET;
    }

    @Override
    public int getLength() {
        return 3 + message.getBytes().length;
    }
}
