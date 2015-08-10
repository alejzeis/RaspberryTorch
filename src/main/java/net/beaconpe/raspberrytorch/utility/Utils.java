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
