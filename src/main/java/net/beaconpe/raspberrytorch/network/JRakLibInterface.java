package net.beaconpe.raspberrytorch.network;

import net.beaconpe.jraklib.JRakLib;
import net.beaconpe.jraklib.Logger;
import net.beaconpe.jraklib.protocol.EncapsulatedPacket;
import net.beaconpe.jraklib.server.JRakLibServer;
import net.beaconpe.jraklib.server.ServerHandler;
import net.beaconpe.jraklib.server.ServerInstance;
import net.beaconpe.raspberrytorch.Player;
import net.beaconpe.raspberrytorch.RaspberryTorch;
import net.beaconpe.raspberrytorch.Server;
import net.beaconpe.raspberrytorch.network.packet.LoginPacket;
import net.beaconpe.raspberrytorch.network.packet.LoginStatusPacket;
import net.beaconpe.raspberrytorch.utility.Utils;

import java.util.HashMap;
import java.util.Map;

/**
 * Network interface for JRakLib.
 *
 * @author jython234
 */
public class JRakLibInterface implements ServerInstance{

    private Server server;
    private net.beaconpe.raspberrytorch.logging.Logger logger;

    private Map<Byte, Class<? extends DataPacket>> packets = new HashMap<>();

    private JRakLibServer rakLibServer;
    private ServerHandler handler;

    public JRakLibInterface(Server server){
        this.server = server;

        rakLibServer = new JRakLibServer(new JRakLibLogger(), server.getBindPort(), server.getBindInterface());
        handler = new ServerHandler(rakLibServer, this);

        logger = new net.beaconpe.raspberrytorch.logging.Logger("JRakLibInterface", server.isDebugMode());
        logger.debug("JRakLibInterface ready.");
        registerPackets();
    }

    private void registerPackets() {
        logger.debug("Registering packets...");
        packets.put(NetworkIds.LOGIN_PACKET, LoginPacket.class);
        packets.put(NetworkIds.LOGIN_STATUS_PACKET, LoginStatusPacket.class);
        logger.info(packets.size()+" packets registered.");
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

    public void sendDataPacket(String identifier, DataPacket packet, boolean immediate){
        if(server.getPlayerByIdentifier(identifier) != null){
            byte[] buffer = packet.encode();
            logger.debug("("+identifier+") PACKET OUT: "+ Utils.dumpHexBytes(buffer));
            EncapsulatedPacket pk = new EncapsulatedPacket();
            pk.buffer = buffer;
            pk.messageIndex = 0;
            if(packet.getChannel() != NetworkChannel.CHANNEL_NONE){
                pk.reliability = 3;
                pk.orderChannel = packet.getChannel().getAsByte();
                pk.orderIndex = 0;
            } else {
                pk.reliability = 2;
            }
            handler.sendEncapsulated(identifier, pk, (byte) ((byte) 0 | (immediate ? JRakLib.PRIORITY_IMMEDIATE : JRakLib.PRIORITY_NORMAL)));
        }
    }

    /**
     * Get a new instance of a DataPacket by a packet ID.
     * @param pid The packet's ID to lookup.
     * @return A new instance of the DataPacket if found, null if not.
     */
    public DataPacket getPacket(byte pid){
        if(packets.containsKey(pid)){
            try {
                return packets.get(pid).newInstance();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public void openSession(String identifier, String address, int port, long clientID) {
        logger.debug("("+identifier+") OPEN SESSION: {clientID: "+clientID+"}");
        Player player = new Player(server, this, identifier, clientID);
        server.addPlayer(player);
    }

    @Override
    public void closeSession(String identifier, String reason) {
        logger.debug("("+identifier+") CLOSE SESSION: {reason: "+reason+"}");
        Player player = server.getPlayerByIdentifier(identifier);
        if(player != null){
            player.close(player.getUsername()+" left the game", reason, false);
        }
    }

    @Override
    public void handleEncapsulated(String identifier, EncapsulatedPacket encapsulatedPacket, int flags) {
        logger.debug("("+identifier+") PACKET IN: "+ Utils.dumpHexBytes(encapsulatedPacket.buffer));
        Player player = server.getPlayerByIdentifier(identifier);
        if(player != null){
            DataPacket packet = getPacket(encapsulatedPacket.buffer[0]);
            if(packet != null){
                packet.decode(encapsulatedPacket.buffer);
                player.handleDataPacket(packet);
                return;
            }
        }
        logger.warn("Dropped packet 0x"+String.format("%02X", encapsulatedPacket.buffer[0]));
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
