package net.beaconpe.raspberrytorch;

import net.beaconpe.raspberrytorch.entity.Entity;
import net.beaconpe.raspberrytorch.network.DataPacket;
import net.beaconpe.raspberrytorch.network.JRakLibInterface;
import net.beaconpe.raspberrytorch.network.NetworkIds;
import net.beaconpe.raspberrytorch.network.packet.LoginPacket;
import net.beaconpe.raspberrytorch.network.packet.LoginStatusPacket;

/**
 * Represents a player on the server.
 *
 * @author jython234
 */
public class Player extends Entity{

    private Server server;
    private JRakLibInterface rakLibInterface;

    private String identifier;
    private long clientID;

    private String username;

    private boolean loggedIn;
    private boolean spawned;

    /**
     * INTERNAL USE ONLY! Use <code>Server.getPlayer(String name)</code> to get a player connected to the server.
     * Create a new player with the specified server and JRakLibInterface.
     * @param server The Server this player belongs to.
     * @param rakLibInterface The JRakLibInterface this player is connecting through.
     * @param identifier The client's identifier (address) in the format of [ip]:[port]
     * @param clientID The client's clientID.
     */
    public Player(Server server, JRakLibInterface rakLibInterface, String identifier, long clientID){
        this.server = server;
        this.rakLibInterface = rakLibInterface;
        this.identifier = identifier;
        this.clientID = clientID;
    }

    public void sendDataPacket(DataPacket packet){
        rakLibInterface.sendDataPacket(getIdentifier(), packet, false);
    }

    public void sendDataPacketImmediate(DataPacket packet){
        rakLibInterface.sendDataPacket(getIdentifier(), packet, true);
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
                username = lp.username;
                LoginStatusPacket lsp = new LoginStatusPacket();
                if(lp.protocol != NetworkIds.PROTOCOL){
                    if(lp.protocol < NetworkIds.PROTOCOL){
                        lsp.status = LoginStatusPacket.STATUS_CLIENT_OUTDATED;
                        sendDataPacket(lsp);
                        close("", "outdated client!", false);
                    } else {
                        lsp.status = LoginStatusPacket.STATUS_SERVER_OUTDATED;
                        sendDataPacket(lsp);
                        close("", "outdated server!", false);
                    }
                    return;
                }
                lsp.status = LoginStatusPacket.STATUS_OK;
                sendDataPacket(lsp);
                break;

            default:
                server.getLogger().debug("Unknown DataPacket: 0x"+String.format("%02X", id));
                break;
        }
    }

    /**
     * INTERNAL USE ONLY! Please use <code>Player.kick()</code> instead.
     * @param leaveMessage
     * @param reason
     * @param notify
     */
    public final void close(String leaveMessage, String reason, boolean notify){
        if(notify){
            //TODO: When Minecraft: Pi updates (never)
        }
        //TODO: Save inventory, position, and other player data.
        //server.broadcastMessage(leaveMessage);
        server.getLogger().info(username+" ["+identifier+"] logged out: "+reason);
        server.removePlayer(this);
    }

    /**
     * Get this player's identifier (address). The identifier is the IP and port combined, example: 127.0.0.1:5678
     * @return
     */
    public String getIdentifier() {
        return identifier;
    }

    /**
     * Get this player's username. This is the original username sent at login.
     * @return The player's username.
     */
    public String getUsername() {
        return username;
    }
}
