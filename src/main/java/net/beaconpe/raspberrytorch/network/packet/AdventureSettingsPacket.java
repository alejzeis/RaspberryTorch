package net.beaconpe.raspberrytorch.network.packet;

import net.beaconpe.raspberrytorch.io.RaspberryOutputStream;
import net.beaconpe.raspberrytorch.network.DataPacket;
import net.beaconpe.raspberrytorch.network.NetworkIds;

import java.io.IOException;

/**
 * AdventureSettingsPacket (0xb6)
 *
 * @author jython234
 */
public class AdventureSettingsPacket extends DataPacket{
    public final static byte ID = NetworkIds.ADVENTURE_SETTINGS_PACKET;

    public int flags;

    @Override
    protected void _encode(RaspberryOutputStream out) throws IOException {
        out.writeInt(flags);
    }

    @Override
    public byte getPID() {
        return NetworkIds.ADVENTURE_SETTINGS_PACKET;
    }

    @Override
    public int getLength() {
        return 5;
    }
}
