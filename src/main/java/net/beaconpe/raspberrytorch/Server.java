package net.beaconpe.raspberrytorch;

import net.beaconpe.raspberrytorch.logging.Logger;
import net.beaconpe.raspberrytorch.network.JRakLibInterface;
import net.beaconpe.raspberrytorch.ticker.CallableTask;
import net.beaconpe.raspberrytorch.ticker.RaspberryTicker;

import java.net.InetSocketAddress;
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

        logger.info("RaspberryTorch version "+RaspberryTorch.VERSION+" implementing MCPI protocol version 9"); //TODO: Change version
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
}
