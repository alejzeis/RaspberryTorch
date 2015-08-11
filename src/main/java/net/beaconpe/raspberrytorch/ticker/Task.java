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
package net.beaconpe.raspberrytorch.ticker;

/**
 * Abstract class that represents a task. You can extend this class to create a complex task, or use <code>CallableTask</code> for a simple one.
 *
 * @author jython234
 */
public abstract class Task implements Runnable{
    private int taskId;
    private int tickDelay;
    private long lastTickRan = -1;

    public Task(int tickDelay){
        this.tickDelay = tickDelay;
    }

    /**
     * Get the tick that this task was last ran at.
     * @return Tick last ran at.
     */
    public long getLastTickRan() {
        return lastTickRan;
    }

    protected void setLastTickRan(long lastTickRan){
        this.lastTickRan = lastTickRan;
    }

    /**
     * Get the delay that this task will run.
     * @return
     */
    public int getTickDelay() {
        return tickDelay;
    }

    /**
     * Get this task's task ID.
     * @return
     */
    public int getTaskId() {
        return taskId;
    }

    protected void setTaskId(int id){
        taskId = id;
    }
}
