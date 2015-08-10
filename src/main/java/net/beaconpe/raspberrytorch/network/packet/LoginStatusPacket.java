package net.beaconpe.raspberrytorch.network.packet;

import net.beaconpe.raspberrytorch.io.RaspberryOutputStream;
import net.beaconpe.raspberrytorch.network.DataPacket;
import net.beaconpe.raspberrytorch.network.NetworkIds;

import java.io.IOException;

/**
 * LoginStatusPacket (0x83)
 *
 * @author jython234
 */
public class LoginStatusPacket extends DataPacket{


    public int status;

    @Override
    protected void _encode(RaspberryOutputStream out) throws IOException {
        out.writeInt(status);
    }

    @Override
    public byte getPID() {
        return NetworkIds.LOGIN_STATUS_PACKET;
    }

    @Override
    public int getLength() {
        return 5;
    }
}
