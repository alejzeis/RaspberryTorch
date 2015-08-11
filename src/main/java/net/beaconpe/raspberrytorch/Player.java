package net.beaconpe.raspberrytorch;

import net.beaconpe.raspberrytorch.entity.Entity;
import net.beaconpe.raspberrytorch.network.DataPacket;
import net.beaconpe.raspberrytorch.network.JRakLibInterface;
import net.beaconpe.raspberrytorch.network.NetworkIds;
import net.beaconpe.raspberrytorch.network.packet.*;

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
        super(server);
        this.server = server;
        this.rakLibInterface = rakLibInterface;
        this.identifier = identifier;
        this.clientID = clientID;

        setX(128.5f);
        setY(76);
        setZ(128.5f);
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
                loggedIn = true;

                for(Player p : server.getPlayers()){
                    if(p != this && p.username.equalsIgnoreCase(username)){
                        p.close("left the game", "logged in from other location", true);
                    }
                }

                server.getLogger().info(username+"["+identifier+"] logged in with entityID: "+getEntityID()+" at [x: "+getX()+", y: "+getY()+", z: "+getZ()+"]");

                StartGamePacket sgp = new StartGamePacket();
                sgp.seed = 1422740056;
                sgp.gamemode = 1;
                sgp.entityID = 0; //Entity ID for the player is always zero
                sgp.x = (float) getX();
                sgp.y = (float) getY();
                sgp.z = (float) getZ();
                sendDataPacket(sgp);
                break;

            case NetworkIds.READY_PACKET: //Spawned! :)
                ReadyPacket rp = (ReadyPacket) packet;
                if(rp.status == ReadyPacket.STATUS_SPAWN){
                    server.getLogger().debug(username+"["+identifier+"] spawned in at "+getX()+", "+getY()+", "+getZ());
                    server.broadcastMessage(username+" joined the game.");

                    int flags = 0;
                    flags |= 0x20; //nametags
                    AdventureSettingsPacket asp = new AdventureSettingsPacket();
                    asp.flags = flags;
                    sendDataPacket(asp);
                } else {
                    server.getLogger().warn(username+"["+identifier+"] got ReadyPacket with status: "+rp.status);
                }
                break;

            case NetworkIds.MOVE_PLAYER_PACKET:
                MovePlayerPacket mp = (MovePlayerPacket) packet; //TODO: If MCPI updates (survival) check movement.
                setX(mp.x);
                setY(mp.y);
                setZ(mp.z);
                setYaw(mp.yaw);
                setPitch(mp.pitch);
                break;

            default:
                server.getLogger().debug("Unknown DataPacket: 0x"+String.format("%02X", id));
                break;
        }
    }

    /**
     * Send a message to the player. Only this player will be able to see it (In chat).
     * @param message The message to be sent.
     */
    public void sendMessage(String message){
        MessagePacket mp = new MessagePacket();
        mp.message = message;
        sendDataPacket(mp);
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
        server.broadcastMessage(leaveMessage);
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
