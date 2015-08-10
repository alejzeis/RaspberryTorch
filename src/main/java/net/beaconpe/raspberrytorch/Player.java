package net.beaconpe.raspberrytorch;

import net.beaconpe.raspberrytorch.entity.Entity;
import net.beaconpe.raspberrytorch.network.DataPacket;
import net.beaconpe.raspberrytorch.network.JRakLibInterface;
import net.beaconpe.raspberrytorch.network.NetworkIds;
import net.beaconpe.raspberrytorch.network.packet.LoginPacket;

/**
 * Represents a player on the server.
 *
 * @author jython234
 */
public class Player extends Entity{

    private Server server;
    private JRakLibInterface rakLibInterface;

    private boolean loggedIn;
    private boolean spawned;

    /**
     * INTERNAL USE ONLY! Use <code>Server.getPlayer(String name)</code> to get a player connected to the server.
     * Create a new player with the specified server and JRakLibInterface.
     * @param server The Server this player belongs to.
     * @param rakLibInterface The JRakLibInterface this player is connecting through.
     */
    public Player(Server server, JRakLibInterface rakLibInterface){
        this.server = server;
        this.rakLibInterface = rakLibInterface;
    }

    /**
     * INTERNAL USE ONLY!
     * Handles a Minecraft: Pi DataPacket.
     * @param packet The DataPacket.
     */
    public void handleDataPacket(DataPacket packet){
        byte id = packet.getPID();
        switch (id){
            case NetworkIds.LOGIN_PACKET:
                if(loggedIn) break;
                LoginPacket lp = (LoginPacket) packet;
                if(lp.protocol != NetworkIds.PROTOCOL){
                    if(lp.protocol < NetworkIds.PROTOCOL){

                    }
                }
                break;

            default:
                server.getLogger().debug("Unknown DataPacket: 0x"+String.format("%02X", id));
                break;
        }
    }
}
