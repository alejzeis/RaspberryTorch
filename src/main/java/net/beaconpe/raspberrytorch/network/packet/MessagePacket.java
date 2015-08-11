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
