package readySETgo.models.assets;

import java.awt.Graphics;

/**
 * 
 * Represents something which can be placed on a Stage
 * 
 * @author ReadySETgo
 * @version Beta 3
 * @since 2016-12-04
 * 
 */
public abstract class Asset {
    private double physicalWidth; //In inches
    private double physicalLength; //In inches
    private double xPos;
    private double yPos;
    private double angle;
    
    /**
     * Default constructor
     */
    public Asset() {
    	physicalWidth = 0;
    	physicalLength = 0;
    	xPos = 0;
    	yPos = 0;
    	angle = 0;
    }
    
    /**
     * Constructor specifying width, length, x, y , and rotation angle
     * @param w The width
     * @param l The length 
     * @param x The x pos
     * @param y The y pos 
     * @param a The rotation angle
     */
    public Asset(double w, double l, double x, double y, double a) {
    	physicalWidth = w;
    	physicalLength = l;
    	xPos = x;
    	yPos = y;
    	angle = a;
    }
    
    /**
     * Draws Asset
     * @param g Graphics to draw with
     * @param scale Scale factor
     * @param selected Whether the Asset is selected
     */
    public abstract void draw(Graphics g, double scale, boolean selected);
    
    /**
     * Returns a copy of the Asset
     * @return Copy of the Asset
     */
    public abstract Asset copyOf();
    
    /**
     * Returns Asset's width in inches
     * @return Width in inches
     */
    public double getPhysicalWidth() { return this.physicalWidth; }

    /**
     * Sets Asset's width 
     * @param physicalWidth New width in inches
     */
    public void setPhysicalWidth(double physicalWidth) { this.physicalWidth = physicalWidth; }
    
    /**
     * Sets Asset's width
     * @param feet New feet, minus any inches
     * @param inches New inches
     */
    public void setPhysicalWidth(double feet, double inches){
    	inches = inches + (feet * 12);
    	this.physicalWidth = inches;
    }

    /**
     * Gets Asset's length
     * @return Length in inches
     */
    public double getPhysicalLength() {
        return physicalLength;
    }

    /**
     * Sets new length in inches
     * @param physicalLength New Length in inches
     */
    public void setPhysicalLength(double physicalLength) {
        this.physicalLength = physicalLength;
    }
    
    /**
     * Sets Asset's length
     * @param feet New feet, minus any inches
     * @param inches New inches
     */
    public void setPhysicalLength(double feet, double inches){
    	inches = inches + (feet * 12);
    	this.physicalLength = inches;
    }

    /**
     * Gets Asset's x position
     * @return X position
     */
    public double getxPos() { return this.xPos; }

    /**
     * Sets Asset's x position
     * @param xPos New x position
     */
    public void setxPos(double xPos) { this.xPos = xPos; }

    /**
     * Gets Asset's y position
     * @return Y position
     */
    public double getyPos() { return yPos; }

    /**
     * Sets Asset's y position
     * @param yPos New y position
     */
    public void setyPos(double yPos) { this.yPos = yPos; }

    /**
     * Increments Asset's y position by n
     * @param n Increment by this number
     */
    public void incrementYPos(double n) {
    	this.yPos += n;
    }

    /**
     * Increments Asset's x position by n
     * @param n Increment by this number
     */
    public void incrementXPos(double n) {
    	this.xPos += n;
    }
    
    /**
     * Gets the Asset's angle
     * @return The angle
     */
    public double getAngle() {
        return angle;
    }

    /**
     * Sets the Asset's angle
     * @param angle The new angle
     */
    public void setAngle(double angle) {
        this.angle = angle;
    }
}
