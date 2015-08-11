package net.beaconpe.raspberrytorch.network.packet;

import net.beaconpe.raspberrytorch.io.RaspberryInputStream;
import net.beaconpe.raspberrytorch.io.RaspberryOutputStream;
import net.beaconpe.raspberrytorch.network.DataPacket;
import net.beaconpe.raspberrytorch.network.NetworkIds;

import java.io.IOException;

/**
 * MovePlayerPacket (0x94)
 *
 * @author jython234
 */
public class MovePlayerPacket extends DataPacket{
    public final static byte ID = NetworkIds.MOVE_PLAYER_PACKET;

    public int eid;
    public float x;
    public float y;
    public float z;
    public float yaw;
    public float pitch;

    @Override
    protected void _encode(RaspberryOutputStream out) throws IOException {
        out.writeInt(eid);
        out.writeFloat(x);
        out.writeFloat(y);
        out.writeFloat(z);
        out.writeFloat(yaw);
        out.writeFloat(pitch);
    }

    @Override
    protected void _decode(RaspberryInputStream in) throws IOException {
        eid = in.readInt();
        x = in.readFloat();
        y = in.readFloat();
        z = in.readFloat();
        yaw = in.readFloat();
        pitch = in.readFloat();
    }

    @Override
    public byte getPID() {
        return NetworkIds.MOVE_PLAYER_PACKET;
    }

    @Override
    public int getLength() {
        return 24;
    }
}
