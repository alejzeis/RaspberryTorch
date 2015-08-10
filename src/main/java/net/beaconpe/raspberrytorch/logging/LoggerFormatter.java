package net.beaconpe.raspberrytorch.logging;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Formatter;
import java.util.logging.Level;
import java.util.logging.LogRecord;

/**
 * The logging formatter used by RaspberryTorch.
 *
 * @author jython234
 */
public class LoggerFormatter extends Formatter{

    @Override
    public String format(LogRecord record) {
        StringBuilder sb = new StringBuilder();
        sb.append("["+getTimeString(record.getMillis())+"] ");
        sb.append("["+record.getLoggerName()+"/");
        if(record.getLevel() == Level.FINE){
            sb.append("DEBUG]: ");
        } else {
            sb.append(record.getLevel().toString()+"]: ");
        }
        sb.append(record.getMessage());
        sb.append("\n");
        return sb.toString();
    }

    private String getTimeString(long millisecs){
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMM dd,yyyy HH:mm:ss");
        Date resultdate = new Date(millisecs);
        return dateFormat.format(resultdate);
    }
}
