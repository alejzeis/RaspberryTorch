package net.beaconpe.raspberrytorch;

import net.beaconpe.raspberrytorch.level.Level;
import net.beaconpe.raspberrytorch.logging.Logger;
import net.beaconpe.raspberrytorch.network.JRakLibInterface;
import net.beaconpe.raspberrytorch.network.NetworkIds;
import net.beaconpe.raspberrytorch.ticker.CallableTask;
import net.beaconpe.raspberrytorch.ticker.RaspberryTicker;

import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Created by jython234 on 8/9/2015.
 *
 * @author jython234
 */
public class Server implements Runnable{

    private RaspberryTicker ticker;
    private Logger logger;
    private JRakLibInterface rakLibInterface;

    private InetSocketAddress bindAddress;
    private String serverName;
    private String motd;
    private boolean debugMode;

    private List<Player> players = new ArrayList<>();
    private Level mainLevel;
    private int nextEntityID = -1;

    Server(Properties serverProperties){ //package-private constructor.
        try {
            loadProperties(serverProperties);
        } catch(Exception e){
            System.err.println("Invalid server.properties! Exception: "+e.getMessage());
            e.printStackTrace(System.err);
            System.exit(1);
        }
        logger = new Logger(debugMode);
        ticker = new RaspberryTicker(this, 20);

        logger.info("RaspberryTorch version "+RaspberryTorch.VERSION+" implementing MCPI protocol version "+ NetworkIds.PROTOCOL);
        RaspberryTorch.setServerInstance(this);
        rakLibInterface = new JRakLibInterface(this);
        rakLibInterface.setName(serverName);
        logger.info("Server started on "+bindAddress.toString());
        ticker.registerTask(new CallableTask("process", rakLibInterface, 0));
    }

    private void loadProperties(Properties properties) {
        String ip = properties.getProperty("serverInterface");
        int port = Integer.parseInt(properties.getProperty("serverPort"));
        bindAddress = new InetSocketAddress(ip, port);
        serverName = properties.getProperty("serverName");
        motd = properties.getProperty("motd");
        debugMode = Boolean.parseBoolean(properties.getProperty("debug"));
    }

    @Override
    public void run() {
        ticker.start();
    }

    public void broadcastMessage(String message){
        synchronized (players){
            for(Player player : players){
                player.sendMessage(message);
            }
        }
        logger.info("[Chat] "+message);
    }

    /**
     * INTERNAL USE ONLY!
     * <br>
     * Adds a player to the server, only for use in JRakLibInterface.
     * @param player
     */
    public void addPlayer(Player player){
        synchronized (players){
            players.add(player);
        }
    }

    public List<Player> getPlayers(){
        synchronized (players) {
            return players;
        }
    }

    public Player getPlayerByIdentifier(String identifier){
        synchronized (players) {
            for (Player player : players) {
                if(player.getIdentifier().equals(identifier)){
                    return player;
                }
            }
        }
        return null;
    }

    protected void removePlayer(Player player) {
        synchronized (players){
            players.remove(player);
        }
    }

    public boolean isDebugMode() {
        return debugMode;
    }

    public String getBindInterface() {
        return bindAddress.getHostString();
    }

    public int getBindPort() {
        return bindAddress.getPort();
    }

    public Logger getLogger() {
        return logger;
    }

    public Level getMainLevel() {
        return mainLevel;
    }

    public int getNextEntityID() {
        return nextEntityID++;
    }
}
