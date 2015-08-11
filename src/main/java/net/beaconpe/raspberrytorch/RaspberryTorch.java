/**
 * This file is part of RaspberryTorch.
 *
 * RaspberryTorch is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * RaspberryTorch is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with RaspberryTorch.  If not, see <http://www.gnu.org/licenses/>.
 */
package net.beaconpe.raspberrytorch;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Main run class for RaspberryTorch.
 *
 * @author jython234
 */
public class RaspberryTorch {
    public static final String VERSION = "v0.1.0-ALPHA";
    private static Server SERVER_INSTANCE;

    public static void main(String[] args){
        try {
            File propertiesFile = new File("server.properties");
            Properties p = null;
            if (!propertiesFile.exists() || propertiesFile.isDirectory()) {
                propertiesFile.createNewFile();
                p = writeDefaultProperties(propertiesFile); //Write the default properties so we can create the server.
            }
            if(p == null){
                p = new Properties();
                p.load(new FileInputStream(propertiesFile));
            }
            new Server(p).run(); //Create the server
        } catch(IOException e){
            System.err.println("Failed to create server! Could not create properties\njava.io.IOException: "+e.getMessage());
            e.printStackTrace(System.err);
            System.exit(1); //Exit due to properties error
        }
    }

    private static Properties writeDefaultProperties(File storeTo) throws IOException{
        Properties p = new Properties();
        p.put("serverInterface", "0.0.0.0");
        p.put("serverPort", "19132");
        p.put("serverName", "A MCPI Server, created with RaspberryTorch!");
        p.put("motd", "Welcome to the MCPI server!");
        p.put("debug", "false");
        p.store(new FileOutputStream(storeTo), "RaspberryTorch server configuration.");
        return p;
    }

    protected static void setServerInstance(Server server){
        SERVER_INSTANCE = server;
    }

    /**
     * Get the global server instance for this runtime. This is not recommended, as there may be multiple servers running per runtime.
     * @return Global server instance for the current runtime.
     */
    public static Server getServerInstance(){
        return SERVER_INSTANCE;
    }
}
