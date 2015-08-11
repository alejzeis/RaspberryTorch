package net.beaconpe.raspberrytorch.network.packet;

import net.beaconpe.raspberrytorch.io.RaspberryInputStream;
import net.beaconpe.raspberrytorch.io.RaspberryOutputStream;
import net.beaconpe.raspberrytorch.network.DataPacket;
import net.beaconpe.raspberrytorch.network.NetworkIds;

import java.io.IOException;

/**
 * Created by jython234 on 8/10/2015.
 *
 * @author jython234
 */
public class MessagePacket extends DataPacket{
    public final static byte ID = NetworkIds.MESSAGE_PACKET;

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
        return NetworkIds.MESSAGE_PACKET;
    }

    @Override
    public int getLength() {
        return 3 + message.getBytes().length;
    }
}
