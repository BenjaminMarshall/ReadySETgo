package readySETgo.models;

import java.awt.Image;

import readySETgo.models.assets.StageObject;

/**
 * 
 * Represents one of the toggleable flyrail lines on a stage
 * 
 * @author ReadySETgo
 * @version Beta 3
 * @since 2016-12-04
 * 
 */
public class FlyRail {

	private boolean isFlownIn;
	private double w;
	private double l;
	private double posx; //TOP LEFT
	private double posy; //TOP LEFT
	private Image image;
	private String imageRef;
	private String name;
	private StageObject drawable;
	
	/**
	 * Gets width
	 * @return width
	 */
	public double getW() {
		return w;
	}

	/**
	 * Sets width
	 * @param w The width
	 */
	public void setW(double w) {
		this.w = w;
		this.updateDrawable();
	}

	/**
	 * Gets length
	 * @return The length
	 */
	public double getL() {
		return l;
	}

	/**
	 * Sets length
	 * @param l The length
	 */
	public void setL(double l) {
		this.l = l;
		this.updateDrawable();
	}

	/**
	 * Updates the FlyRail's drawable StageObject
	 */
	private void updateDrawable() {
		this.drawable = new StageObject(this.name, this.w, this.l, this.posx, this.posy, 0, imageRef);
	} 
	
	/**
	 * Returns the FlyRail's StageObject
	 * @return The FlyRail's StageObject
	 */
	public StageObject getDrawable() {
		return this.drawable;
	}
	
	/**
	 * Default constructor
	 * @param name The rail's name
	 * @param w The rail's width
	 * @param l The rail's length
	 * @param posx The rail's x position
	 * @param posy The rail's y position
	 * @param isFlownIn Whether the rail is flown in
	 * @param imageRef The rail's image ref
	 */
	public FlyRail(String name, double w, double l, double posx, double posy, boolean isFlownIn, String imageRef){
		this.w = w;
		this.l = l;
		this.name = name;
		this.posx = posx;
		this.posy = posy;
		this.isFlownIn = isFlownIn;
		this.imageRef = imageRef;
		this.updateDrawable();
	}

	/**
	 * Returns whether the line is flown in
	 * @return Whether the line is flown in
	 */
	public boolean isFlownIn() {
		return this.isFlownIn;
	}

	/**
	 * Sets the line's flown in state
	 * @param isFlownIn Whether the line will be flown in or not
	 */
	public void setFlownIn(boolean isFlownIn) {
		this.isFlownIn = isFlownIn;
	}

	/**
	 * Returns the line's x position
	 * @return The line's x position
	 */
	public double getPosx() {
		return posx;
	}

	/**
	 * Sets the line's x position
	 * @param posx The new x position
	 */
	public void setPosx(double posx) {
		this.posx = posx;
		this.updateDrawable();
	}

	/**
	 * Gets the line's y position
	 * @return The y position
	 */
	public double getPosy() {
		return posy;
	}

	/**
	 * Sets the line's y position
	 * @param posy The new y position
	 */
	public void setPosy(double posy) {
		this.posy = posy;
		this.updateDrawable();
	}

	/**
	 * Returns the line's image
	 * @return The image
	 */
	public Image getImage() {
		return image;
	}

	/**
	 * Sets the line's image
	 * @param image The image to set
	 */
	public void setImage(Image image) {
		this.image = image;
		this.updateDrawable();
	}

	/**
	 * Returns the line's image ref
	 * @return The image ref
	 */
	public String getImageRef() {
		return imageRef;
	}

	/**
	 * Sets the line's image ref
	 * @param imageRef The new image ref
	 */
	public void setImageRef(String imageRef) {
		this.imageRef = imageRef;
		this.updateDrawable();
	}

	/**
	 * Gets the line's name
	 * @return The line's name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the line's name
	 * @param name The new name
	 */
	public void setName(String name) {
		this.name = name;
		this.updateDrawable();
	}
}
