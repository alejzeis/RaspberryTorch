package net.beaconpe.raspberrytorch.network;

/**
 * Network Constants class.
 *
 * @author jython234
 */
public abstract class NetworkIds {
    /**
     * The protocol of Minecraft: Pi Edition that this server implements.
     */
    public final static int PROTOCOL = 9;

    public final static byte LOGIN_PACKET = (byte) 0x82;
    public final static byte LOGIN_STATUS_PACKET = (byte) 0x83;
}
