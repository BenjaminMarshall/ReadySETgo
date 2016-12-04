package readySETgo.models.assets;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Stroke;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Comparator;

import javax.imageio.ImageIO;

/**
 * 
 * Represents an object which can be placed on a Stage
 * 
 * @author ReadySETgo
 * @version Beta 3
 * @since 2016-12-04
 * 
 */
public class StageObject extends Asset {
	private Image image;
	private String imageRef;
	private String name;
	private boolean defaultPic = true;
	
	/**
	 * Default constructor
	 */
	public StageObject(){
		super();
	}
	
	/**
	 * Constructor specifying name, width, length, x, y, rotation angle, and imageref
	 * @param name StageObject's name
	 * @param w  StageObject's width
	 * @param l StageObject's length
	 * @param x StageObject's x pos
	 * @param y StageObject's y pos
	 * @param a StageObject's rotation angle
	 * @param imageRef StageObject's image ref
	 */
	public StageObject(String name, double w, double l, double x, double y, double a, String imageRef) {
		super(w, l, x, y, a);
		
		try {
			this.image = ImageIO.read(new File(imageRef));
			defaultPic = false;
		} catch (IOException e){
			try {
				this.image = ImageIO.read(new File("res/stripes.png"));
			} catch (IOException e1) {
				this.image = null;
			}
		}
		
		this.imageRef = imageRef;
		this.name = name;
	}
	
	/**
	 * Constructor specifying name, width, length, and imageref
	 * @param name StageObject's name
	 * @param w StageObject's width
	 * @param l StageObject's length
	 * @param imageRef StageObject's imageref
	 */
	public StageObject(String name, double w, double l, String imageRef) {
		this(name, w, l, 0, 0, 0, imageRef);
	}
	
	/**
	 * Draws StageObject
	 * @param stageGraphics Graphics object to draw with
	 * @param scale The scale to draw with
	 * @param selected Whether to draw selected border
	 */
	@Override
	public void draw(Graphics stageGraphics, double scale, boolean selected) {
	
		Graphics2D stg2D = (Graphics2D) stageGraphics.create();
		stg2D.setColor(Color.BLACK);
		
		BufferedImage bi = new BufferedImage(
	            (!defaultPic ? (int) (getPhysicalWidth() * scale) : (int) (getPhysicalWidth() * scale) + 1),
	            (!defaultPic ? (int) (getPhysicalLength() * scale) : (int) (getPhysicalLength() * scale) + 1),
	            BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = bi.createGraphics();
		
		
		//BufferedImage drawing
		if(image == null){
			g.setColor(Color.BLACK);
			g.fillRect(0, 0, (int) (getPhysicalWidth()*scale), (int) (getPhysicalLength()*scale));
		} else if (!defaultPic){
			g.drawImage(image, 0, 0, (int) (getPhysicalWidth()*scale), (int) (getPhysicalLength()*scale), null);
		} else {
			g.setColor(Color.WHITE);
			g.fillRect(0, 0, (int) (getPhysicalWidth()*scale), (int) (getPhysicalLength()*scale));
			g.setColor(Color.BLACK);
			g.drawRect(0, 0, (int) (getPhysicalWidth()*scale), (int) (getPhysicalLength()*scale));
			g.drawImage(image, 0, 0, (int) (getPhysicalWidth()*scale), (int) (getPhysicalLength()*scale), null);
		}	
		
		//Rotation Transform
		AffineTransform old = stg2D.getTransform();
		AffineTransform rotateTransform = new AffineTransform();
		rotateTransform.rotate(Math.toRadians(getAngle()), (int) (this.getxPos()*scale), (int) (this.getyPos()*scale));
		stg2D.transform(rotateTransform);
		
		stg2D.drawImage(bi, (int) (this.getxPos()*scale), (int) (this.getyPos()*scale), null);
		
		
		if(selected){
			Graphics2D g3 = (Graphics2D) stg2D.create();
			g3.setColor(Color.BLACK);
			Stroke dashed = new BasicStroke(2, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_MITER, 1, new float[]{5}, 0);
			g3.setStroke(dashed);
			g3.drawRect((int) (this.getxPos()*scale) - 4, (int) (this.getyPos()*scale) - 4, bi.getWidth() + 8, bi.getHeight() + 8);
			g3.dispose();
		}
		
		//Reset and dispose
		stg2D.setTransform(old);
		g.dispose();
		stg2D.dispose();
	}
	
	/**
	 * Draws StageObject
	 * @param g2 Graphics object to draw with
	 * @param scale The scale to draw with
	 * @param x The x position to draw at
	 * @param y The y position to draw at
	 */
	public void draw(Graphics g2, double scale, double x, double y) {
       
		BufferedImage bi = new BufferedImage(
            (!defaultPic ? (int) (getPhysicalWidth() * scale) + 1 : (int) (getPhysicalWidth() * scale) + 1),
            (!defaultPic ? (int) (getPhysicalLength() * scale) +1 : (int) (getPhysicalLength() * scale) + 1),
            BufferedImage.TYPE_INT_ARGB);
		 Graphics g = bi.createGraphics();
		
		if(image == null){
			g.setColor(Color.BLACK);
			g.fillRect(0, 0, (int) (getPhysicalWidth()*scale), (int) (getPhysicalLength()*scale));
		} else if (!defaultPic){
			g.drawImage(image, 0, 0, (int) (getPhysicalWidth()*scale), (int) (getPhysicalLength()*scale), null);
		} else {
			g.setColor(Color.WHITE);
			g.fillRect(0, 0, (int) (getPhysicalWidth()*scale), (int) (getPhysicalLength()*scale));
			g.setColor(Color.BLACK);
			g.drawImage(image, 0, 0, (int) (getPhysicalWidth()*scale), (int) (getPhysicalLength()*scale), null);
			g.drawRect(0, 0, (int) (getPhysicalWidth()*scale), (int) (getPhysicalLength()*scale));
		}	
		
		g2.drawImage(bi, (int) (x), (int) (y), null);
	}
		
	/**
	 * Returns the StageObject's Image
	 * @return The Image
	 */
	public Image getImage() {
		return this.image;
	}

	/**
	 * Sets the StageObject's Image
	 * @param image The Image to set
	 */
	public void setImage(Image image) {
		this.image = image;
	}

	/**
	 * Returns the StageObject's name
	 * @return The name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the StageObject's name
	 * @param name The name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the StageObject's imageref
	 * @return The imageref
	 */
	public String getImageRef() {
		return imageRef;
	}

	/**
	 * Sets the StageObject's imageref
	 * @param imageRef The imageref
	 */
	public void setImageRef(String imageRef) {
		this.imageRef = imageRef;
	}
	
	/**
	 * Returns a new SizeComparator
	 * @return The new SizeComparator
	 */
	public static SizeComparator getSizeComparator(){
		return new SizeComparator();
	}
	
	/**
	 * Returns a new AlphabeticComparator
	 * @return The new AlphabeticComparator
	 */
	public static AlphabeticComparator getAlphabeticComparator(){
		return new AlphabeticComparator();
	}
	
	/**
	 * Compares StageObjects by combined length and width
	 */
	static class SizeComparator implements Comparator<StageObject> {
		public int compare(StageObject o1, StageObject o2) {
			return (int) ((o1.getPhysicalWidth() * o1.getPhysicalLength()) - (o2.getPhysicalWidth() * o2.getPhysicalLength()));
		}	
	}
	
	/**
	 * Compares StageObjects by name
	 */
	static class AlphabeticComparator implements Comparator<StageObject> {
		public int compare(StageObject o1, StageObject o2) {
			return o1.getName().compareTo(o2.getName());
		}	
	}
	
	/**
	 * Returns String representation of StageObject
	 * @return String representation of StageObject
	 */
	public String toString(){
		return (getName() + getPhysicalWidth() + getPhysicalLength() + getxPos() + getyPos() + getAngle() + getImageRef());
	}

	/**
	 * Return copy of StageObject
	 * @return Copy of StageObject
	 */
	public Asset copyOf() {
		return new StageObject(getName(), getPhysicalWidth(), getPhysicalLength(), getxPos(), getyPos(), getAngle(), getImageRef());
	}

	/**
	 * Return whether this is equal to _that
	 * @return Whether this is equal to _that
	 */
	public boolean equals(Object _that) {
		if(this.getClass().equals(_that.getClass())) {
			StageObject that = (StageObject) _that;
			return (this.getName().equals(that.getName()) &&
					this.getPhysicalWidth() == that.getPhysicalWidth() &&
					this.getPhysicalLength() == that.getPhysicalLength() &&
					this.getyPos() == that.getyPos() &&
					this.getxPos() == that.getxPos() &&
					this.getAngle() == that.getAngle() &&
					this.getImageRef().equals(that.getImageRef()));
		}
		else return false;
	}
}
