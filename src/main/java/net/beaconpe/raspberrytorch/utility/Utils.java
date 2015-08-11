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
package net.beaconpe.raspberrytorch.utility;

/**
 * A mix of utility methods used by RaspberryTorch.
 *
 * @author jython234
 */
public class Utils {

    /**
     * Converts a byte array to a string of hex bytes.
     * @param bytes Raw byte array.
     * @return Hex byte string (example: 01 02 AA)
     */
    public static String dumpHexBytes(byte[] bytes){
        StringBuilder sb = new StringBuilder();
        for(byte b: bytes){
            sb.append(String.format("%02X", b)+" ");
        }
        return sb.toString();
    }

    public static String stackTraceToString(StackTraceElement[] trace) {
        StringBuilder sb = new StringBuilder();
        for (StackTraceElement e : trace) {
            sb.append(e.toString() + "\n");
        }
        return sb.toString();
    }
}
