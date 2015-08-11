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
    public final static int STATUS_OK = 0;
    public final static int STATUS_SERVER_OUTDATED = 1;
    public final static int STATUS_CLIENT_OUTDATED = 2;

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
