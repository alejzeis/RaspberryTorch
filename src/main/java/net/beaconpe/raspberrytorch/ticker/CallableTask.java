package net.beaconpe.raspberrytorch.ticker;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Represents a task that calls a specific method belonging to a class on run.
 *
 * @author jython234
 */
public class CallableTask extends Task{

    private Method method;
    private Object instance;

    public CallableTask(String methodName, Object instance, int delay) {
        super(delay);
        this.instance = instance;
        try {
            method = instance.getClass().getMethod(methodName);
        } catch (NoSuchMethodException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public void run() {
        try {
            method.invoke(instance);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }
}
