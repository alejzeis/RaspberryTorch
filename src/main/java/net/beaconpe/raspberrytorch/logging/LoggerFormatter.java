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
