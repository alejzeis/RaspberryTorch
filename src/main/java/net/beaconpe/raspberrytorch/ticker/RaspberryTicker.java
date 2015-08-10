package net.beaconpe.raspberrytorch.ticker;

import net.beaconpe.raspberrytorch.Server;

import java.util.ArrayList;
import java.util.List;

/**
 * The server's ticker class. This class handles all the tasks ran at certain times. Each second is equal to 20 ticks.
 *
 * @author jython234
 */
public class RaspberryTicker implements Runnable{

    private Server server;

    private int tps;
    private long currentTick;

    private boolean running;

    private int nextTaskId = 0;
    private List<Task> tasks;

    /**
     * Create a new ticker belonging to the specified <code>server</code> with the specified TPS.
     * @param server The server this ticker belongs to.
     * @param tps The amount of ticks per second (TPS). The value used by minecraft is 20.
     */
    public RaspberryTicker(Server server, int tps){
        if(tps < 0){
            throw new IllegalArgumentException("TPS (Ticks per second) must be positive!");
        }
        this.server = server;
        this.tps = tps;
        this.running = false;
        this.currentTick = -1;
        this.tasks = new ArrayList<>();
    }

    @Override
    public void run() {
        while(running){
            long start = System.currentTimeMillis();
            currentTick++;
            for(Task task : tasks){
                if(task.getLastTickRan() == -1){
                    task.run();
                    task.setLastTickRan(currentTick);
                    continue;
                }
                if((currentTick - task.getLastTickRan()) >= task.getTickDelay()){
                    task.run();
                    task.setLastTickRan(currentTick);
                }
            }
            long diff = System.currentTimeMillis() - start;
            if(diff <= 50){
                try {
                    Thread.currentThread().sleep(50 - diff);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                server.getLogger().warn("Can't keep up! ("+diff+" > 50) Did the system time change or is the server overloaded?");
            }
        }
    }

    /**
     * Register a task to the ticker.
     * @param task
     */
    public void registerTask(Task task){
        task.setTaskId(nextTaskId);
        nextTaskId++;
        synchronized (tasks){
            tasks.add(task);
        }
    }

    /**
     * Unregister
     * @param task
     */
    public void unregisterTask(Task task){
        synchronized (tasks) {
            tasks.remove(task);
        }
    }

    /**
     * Start this ticker. This method will block forever (while the ticker is running).
     */
    public void start() {
        if(running){
            throw new UnsupportedOperationException("Ticker is already running!");
        }
        running = true;
        run();
    }

    /**
     * Stop this ticker. The ticker will stop after completing the current tick it is in.
     */
    public void stop() {
        if(!running){
            throw new UnsupportedOperationException("Ticker is not running!");
        }
        running = false;
    }
}
