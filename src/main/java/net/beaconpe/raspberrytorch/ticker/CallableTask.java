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
