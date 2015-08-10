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
     * @param level
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
