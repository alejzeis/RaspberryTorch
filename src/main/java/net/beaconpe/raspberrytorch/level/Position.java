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
