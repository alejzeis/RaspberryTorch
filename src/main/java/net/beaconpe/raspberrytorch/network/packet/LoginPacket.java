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
