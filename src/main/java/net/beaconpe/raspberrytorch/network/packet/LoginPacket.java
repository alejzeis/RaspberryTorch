package net.beaconpe.raspberrytorch.network.packet;

import net.beaconpe.raspberrytorch.io.RaspberryInputStream;
import net.beaconpe.raspberrytorch.network.DataPacket;
import net.beaconpe.raspberrytorch.network.NetworkIds;

import java.io.IOException;

/**
 * LoginPacket (0x82)
 *
 * @author jython234
 */
public class LoginPacket extends DataPacket{
    public final static byte ID = NetworkIds.LOGIN_PACKET;

    public String username;
    public int protocol;
    public int protocol2;

    @Override
    protected void _decode(RaspberryInputStream in) throws IOException {
        username = in.readString();
        protocol = in.readInt();
        protocol2 = in.readInt();
    }

    @Override
    public byte getPID() {
        return NetworkIds.LOGIN_PACKET;
    }

    @Override
    public int getLength() {
        return 0;
    }
}
