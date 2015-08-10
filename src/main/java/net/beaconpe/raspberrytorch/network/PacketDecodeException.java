package net.beaconpe.raspberrytorch.network;

/**
 * Exception thrown while packet decoding.
 *
 * @author jython234
 */
public class PacketDecodeException extends RuntimeException{

    public PacketDecodeException(String message){
        super(message);
    }

    public PacketDecodeException(Exception e){
        super(e);
    }
}
