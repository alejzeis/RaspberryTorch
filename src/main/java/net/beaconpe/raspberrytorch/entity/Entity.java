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
