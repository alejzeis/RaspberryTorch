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
