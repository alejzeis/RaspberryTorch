package net.beaconpe.raspberrytorch.network;

import net.beaconpe.jraklib.Logger;
import net.beaconpe.jraklib.protocol.EncapsulatedPacket;
import net.beaconpe.jraklib.server.JRakLibServer;
import net.beaconpe.jraklib.server.ServerHandler;
import net.beaconpe.jraklib.server.ServerInstance;
import net.beaconpe.raspberrytorch.RaspberryTorch;
import net.beaconpe.raspberrytorch.Server;
import net.beaconpe.raspberrytorch.utility.Utils;

/**
 * Network interface for JRakLib.
 *
 * @author jython234
 */
public class JRakLibInterface implements ServerInstance{

    private Server server;
    private net.beaconpe.raspberrytorch.logging.Logger logger;

    private JRakLibServer rakLibServer;
    private ServerHandler handler;

    public JRakLibInterface(Server server){
        this.server = server;

        rakLibServer = new JRakLibServer(new JRakLibLogger(), server.getBindPort(), server.getBindInterface());
        handler = new ServerHandler(rakLibServer, this);

        logger = new net.beaconpe.raspberrytorch.logging.Logger("JRakLibInterface", server.isDebugMode());
        logger.debug("JRakLibInterface ready.");

    }

    /**
     * INTERNAL USE ONLY!
     * Process the library data. This method is called every tick by the server.
     */
    public void process(){
        if(handler.handlePacket()){
            while(handler.handlePacket()){
            }
        }
        if(rakLibServer.getState() == Thread.State.TERMINATED){ //Check if the library crashed
            logger.severe("JRakLib crashed!");
            logger.printStackTrace(rakLibServer.getStackTrace());
            //TODO: Dump queues.
        }
    }

    /**
     * INTERNAL USE ONLY! Please set the server name in <code>server.properties</code> instead.
     * Sets the name that shows up on the broadcast list.
     * @param name
     */
    public void setName(String name){
        handler.sendOption("name", "MCCPP;Demo;"+name);
    }

    @Override
    public void openSession(String identifier, String address, int port, long clientID) {
        logger.debug("("+identifier+") OPEN SESSION: {clientID: "+clientID+"}");
    }

    @Override
    public void closeSession(String identifier, String reason) {
        logger.debug("("+identifier+") CLOSE SESSION: {reason: "+reason+"}");
    }

    @Override
    public void handleEncapsulated(String identifier, EncapsulatedPacket encapsulatedPacket, int flags) {
        logger.debug("("+identifier+") PACKET IN: "+ Utils.dumpHexBytes(encapsulatedPacket.buffer));
    }

    @Override
    public void handleRaw(String address, int port, byte[] payload) {
        logger.debug("("+address+":"+port+") PACKET RAW: "+Utils.dumpHexBytes(payload));
    }

    @Override
    public void notifyACK(String identifier, int identifierACK) {
        //Not needed
    }

    @Override
    public void handleOption(String option, String value) {
        //TODO: Update bandwith counts.
    }

    public static class JRakLibLogger implements Logger {
        private net.beaconpe.raspberrytorch.logging.Logger logger;

        public JRakLibLogger() {
            logger = new net.beaconpe.raspberrytorch.logging.Logger("JRakLib", RaspberryTorch.getServerInstance().isDebugMode());
            logger.debug("JRakLib Logger ready.");
        }

        @Override
        public void notice(String s) {
            logger.info("[NOTICE]: "+s);
        }

        @Override
        public void critical(String s) {
            logger.severe("[CRITICAL]: "+s);
        }

        @Override
        public void emergency(String s) {
            logger.severe("[EMERGENCY]: "+s);
        }
    }
}
