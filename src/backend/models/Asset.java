package backend.models;

import java.io.Serializable;

/**
 * @author Ksenia Belikova
 * @version 11/3/2016.
 */
public abstract class Asset implements Serializable {
    private double physicalWidth;
    private double physicalLength;
    private double xPos;
    private double yPos;
    private double angle;

    public double getPhysicalWidth() {
        return physicalWidth;
    }

    public void setPhysicalWidth(double physicalWidth) {
        this.physicalWidth = physicalWidth;
    }

    public double getPhysicalLength() {
        return physicalLength;
    }

    public void setPhysicalLength(double physicalLength) {
        this.physicalLength = physicalLength;
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
