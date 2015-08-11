package net.beaconpe.raspberrytorch.entity;

import net.beaconpe.raspberrytorch.Server;
import net.beaconpe.raspberrytorch.level.Location;

/**
 * Base class for all entities.
 *
 * @author jython234
 */
public abstract class Entity extends Location{
    private float yaw;
    private float pitch;

    private int entityID;

    public Entity(Server server){
        super(server.getMainLevel());
        yaw = 0;
        pitch = 90;
        entityID = server.getNextEntityID();
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

    public int getEntityID() {
        return entityID;
    }
}
