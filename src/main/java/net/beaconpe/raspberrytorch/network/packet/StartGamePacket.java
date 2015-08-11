package net.beaconpe.raspberrytorch.network.packet;

import net.beaconpe.raspberrytorch.io.RaspberryOutputStream;
import net.beaconpe.raspberrytorch.network.DataPacket;
import net.beaconpe.raspberrytorch.network.NetworkIds;

import java.io.IOException;

/**
 * StartGamePacket (0x87)
 *
 * @author jython234
 */
public class StartGamePacket extends DataPacket{
    public final static byte ID = NetworkIds.START_GAME_PACKET;

    public int seed;
    public int unknown = 0;
    public int gamemode;
    public int entityID;
    public float x;
    public float y;
    public float z;

    @Override
    protected void _encode(RaspberryOutputStream out) throws IOException {
        out.writeInt(seed);
        out.writeInt(unknown);
        out.writeInt(gamemode);
        out.writeInt(entityID);
        out.writeFloat(x);
        out.writeFloat(y);
        out.writeFloat(z);
    }

    @Override
    public byte getPID() {
        return NetworkIds.START_GAME_PACKET;
    }

    @Override
    public int getLength() {
        return 32;
    }
}
