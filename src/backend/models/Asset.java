package backend.models;

import java.awt.Graphics;

/**
 * @author Ksenia Belikova
 * @version 11/3/2016.
 * 
 * Changes made by Benjamin Marshall
 */
public abstract class Asset{
    private double physicalWidth; //In inches
    private double physicalLength; //In inches
    private double xPos;
    private double yPos;
    private double angle;
    
    
    public Asset(){
    	physicalWidth = 0;
    	physicalLength = 0;
    	xPos = 0;
    	yPos = 0;
    	angle = 0;
    }
    
    public Asset(double w, double l, double x, double y, double a){
    	physicalWidth = w;
    	physicalLength = l;
    	xPos = x;
    	yPos = y;
    	angle = a;
    }
    
    public abstract void toXML();

    public abstract void draw(Graphics g, double x, double y);
    
    public double getPhysicalWidth() {
        return physicalWidth;
    }

    public void setPhysicalWidth(double physicalWidth) {
        this.physicalWidth = physicalWidth;
    }
    
    public void setPhysicalWidth(double feet, double inches){
    	inches = inches + (feet * 12);
    	this.physicalWidth = inches;
    }

    public double getPhysicalLength() {
        return physicalLength;
    }

    public void setPhysicalLength(double physicalLength) {
        this.physicalLength = physicalLength;
    }
    
    public void setPhysicalLength(double feet, double inches){
    	inches = inches + (feet * 12);
    	this.physicalLength = inches;
    }

    public double getxPos() {
        return xPos;
    }

    public void setxPos(double xPos) {
        this.xPos = xPos;
    }

    public double getyPos() {
        return yPos;
    }

    public void setyPos(double yPos) {
        this.yPos = yPos;
    }

    public double getAngle() {
        return angle;
    }

    public void setAngle(double angle) {
        this.angle = angle;
    }

}
