package net.beaconpe.raspberrytorch.network.packet;

import net.beaconpe.raspberrytorch.io.RaspberryInputStream;
import net.beaconpe.raspberrytorch.network.DataPacket;
import net.beaconpe.raspberrytorch.network.NetworkIds;

import java.io.IOException;

/**
 * ReadyPacket (0x84)
 *
 * @author jython234
 */
public class ReadyPacket extends DataPacket{
    public final static byte STATUS_SPAWN = 1;

    public byte status;

    @Override
    protected void _decode(RaspberryInputStream in) throws IOException {
        status = in.readByte();
    }

    @Override
    public byte getPID() {
        return NetworkIds.READY_PACKET;
    }

    @Override
    public int getLength() {
        return 2;
    }
}
