package net.beaconpe.raspberrytorch.entity;

import net.beaconpe.raspberrytorch.RaspberryTorch;
import net.beaconpe.raspberrytorch.level.Location;

/**
 * Base class for all entities.
 *
 * @author jython234
 */
public abstract class Entity extends Location{
    private float yaw;
    private float pitch;

    public Entity(){
        super(RaspberryTorch.getServerInstance().getMainLevel());
        yaw = 0;
        pitch = 90;
    }

    public float getPitch() {
        return pitch;
    }

    public void setPitch(float pitch) {
        this.pitch = pitch;
    }

    public float getYaw() {
        return yaw;
    }

    public void setYaw(float yaw) {
        this.yaw = yaw;
    }
}
