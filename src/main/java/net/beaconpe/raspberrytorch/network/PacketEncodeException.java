package net.beaconpe.raspberrytorch.network;


/**
 * Exception thrown while packet encoding.
 *
 * @author jython234
 */
public class PacketEncodeException extends RuntimeException {
    public PacketEncodeException(String message) {
        super(message);
    }

    public PacketEncodeException(Exception e){
        super(e);
    }
}