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
