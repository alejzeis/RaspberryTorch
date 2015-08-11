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
 * Represents a <code>Position</code> inside a <code>Level</code>.
 *
 * @author jython234
 */
public class Location extends Position{
    private Level level;

    /**
     * Create a new Location instance within the specified Level.
     * @param level The level that this location belongs to.
     */
    public Location(Level level){
        super();
        this.level = level;
    }

    /**
     * Create a new Location instance with the specified coordinates and Level.
     * @param x The X coordinate.
     * @param y The Y coordinate.
     * @param z The Z coordinate.
     * @param level The Level this location belongs to.
     */
    public Location(double x, double y, double z, Level level){
        super(x, y, z);
        this.level = level;
    }

    /**
     * Get the Level instance associated with this Location.
     * @return The Level instance.
     */
    public Level getLevel() {
        return level;
    }
}
