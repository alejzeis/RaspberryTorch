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

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Utility I/O class used by RaspberryTorch for Minecraft protocol encoding.
 *
 * @author jython234
 */
public class RaspberryOutputStream extends DataOutputStream {

    /**
     * Creates a new raspberry output stream to write data to the specified
     * underlying output stream. The counter <code>written</code> is
     * set to zero.
     *
     * @param out the underlying output stream, to be saved for later
     *            use.
     * @see java.io.FilterOutputStream#out
     */
    public RaspberryOutputStream(OutputStream out) {
        super(out);
    }

    /**
     * Write a Minecraft: Pi edition String.
     * @param s The String to be written
     * @throws IOException If there is an error while writing.
     */
    public void writeString(String s) throws IOException {
        writeShort(s.getBytes().length);
        write(s.getBytes());
    }
}
