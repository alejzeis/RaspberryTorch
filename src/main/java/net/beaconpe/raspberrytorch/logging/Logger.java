package net.beaconpe.raspberrytorch.logging;

import java.util.Stack;
import java.util.logging.ConsoleHandler;
import java.util.logging.Handler;
import java.util.logging.Level;

/**
 * The wrapper logger class used by RaspberryTorch. It uses the <code>java.util.logging</code> package for logging.
 *
 * @author jython234
 */
public class Logger {
    private java.util.logging.Logger logger;

    static {
        java.util.logging.Logger rootLogger = java.util.logging.Logger.getLogger("");
        Handler[] handlers = rootLogger.getHandlers();
        if (handlers[0] instanceof ConsoleHandler) {
            rootLogger.removeHandler(handlers[0]);
        }
    }

    /**
     * INTERNAL USE ONLY! Please use the <code>Logger(String name, boolean debug)</code> contructor instead.
     * Create a new wrapper logging class with the name "RaspberryTorch". This is intended only for the internal use of the server.
     * @param debug If this logger should be in debug mode.
     */
    public Logger(boolean debug){
        logger = java.util.logging.Logger.getLogger("RaspberryTorch");
        ConsoleHandler handler = new ConsoleHandler();
        if(debug){
            logger.setLevel(Level.FINE);
            handler.setLevel(Level.FINE);
        } else {
            logger.setLevel(Level.INFO);
            handler.setLevel(Level.INFO);
        }

        handler.setFormatter(new LoggerFormatter());
        logger.addHandler(handler);
    }

    /**
     * Creates a new Logger with the specified <code>name</code> and debug mode.
     * @param name The name for this logger.
     * @param debug If this logger should print debug messages.
     */
    public Logger(String name, boolean debug){
        logger = java.util.logging.Logger.getLogger(name);
        ConsoleHandler handler = new ConsoleHandler();
        if(debug){
            logger.setLevel(Level.FINE);
            handler.setLevel(Level.FINE);
        } else {
            logger.setLevel(Level.INFO);
            handler.setLevel(Level.INFO);
        }

        handler.setFormatter(new LoggerFormatter());
        logger.addHandler(handler);
    }

    public void debug(String message){
        logger.fine(message);
    }

    public void info(String message){
        logger.info(message);
    }

    public void warn(String message){
        logger.warning(message);
    }

    public void severe(String message){
        logger.severe(message);
    }

    public void printStackTrace(StackTraceElement[] trace){
        for(StackTraceElement e : trace){
            severe(trace.toString());
        }
    }
}
