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
package net.beaconpe.raspberrytorch.io;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Utility I/O class used by RaspberryTorch for Minecraft protocol decoding.
 *
 * @author jython234
 */
public class RaspberryInputStream extends DataInputStream{

    /**
     * Creates a RaspberryInputStream that uses the specified
     * underlying InputStream.
     *
     * @param in the specified input stream
     */
    public RaspberryInputStream(InputStream in) {
        super(in);
    }

    /**
     * Reads a Minecraft: Pi edition String.
     * @return The String.
     * @throws IOException If there is an error while decoding.
     */
    public String readString() throws IOException {
        int len = readUnsignedShort();
        byte[] data = new byte[len];
        read(data);
        return new String(data);
    }
}
