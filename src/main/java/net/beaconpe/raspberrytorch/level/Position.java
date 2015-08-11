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
package net.beaconpe.raspberrytorch.level;

/**
 * Represents a position (double based).
 *
 * @author jython234
 */
public class Position {
    private double x;
    private double y;
    private double z;

    /**
     * Create a new empty position, with the coordinates all set to zero.
     */
    public Position(){
        x = 0;
        y = 0;
        z = 0;
    }

    /**
     * Create a new position with the specified coordinates.
     * @param x The X coordinate.
     * @param y The Y coordinate.
     * @param z The Z coordinate.
     */
    public Position(double x, double y, double z){
        this.x = x;
        this.y = y;
        this.z = z;
    }

    /**
     * Get the X coordinate of this position.
     * @return The X coordinate.
     */
    public double getX() {
        return x;
    }

    /**
     * Set the X coordinate of this position.
     * @param x The X coordinate.
     */
    public void setX(double x) {
        this.x = x;
    }

    /**
     * Get the Y coordinate of this position.
     * @return The Y coordinate.
     */
    public double getY() {
        return y;
    }

    /**
     * Set the Y coordinate of this position.
     * @param y The Y coordinate.
     */
    public void setY(double y) {
        this.y = y;
    }

    /**
     * Get the Z coordinate of this position.
     * @return The Z coordinate.
     */
    public double getZ() {
        return z;
    }

    /**
     * Set the Z coordinate of this position.
     * @param z The Z coordinate.
     */
    public void setZ(double z) {
        this.z = z;
    }
}
