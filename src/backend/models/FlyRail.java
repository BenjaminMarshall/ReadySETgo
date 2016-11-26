package backend.models;

import java.awt.Image;

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
	
	public double getW() {
		return w;
	}

	public void setW(double w) {
		this.w = w;
		this.updateDrawable();
	}

	public double getL() {
		return l;
	}

	public void setL(double l) {
		this.l = l;
		this.updateDrawable();
	}

	private void updateDrawable() {
		System.out.println(imageRef);
		this.drawable = new StageObject(this.name, this.w, this.l, this.posx, this.posy, 0, imageRef);
	} 
	
	public StageObject getDrawable() {
		return this.drawable;
	}
	
	public FlyRail(String name, double w, double l, double posx, double posy, boolean isFlownIn, String imageRef){
		System.out.println(imageRef);
		this.w = w;
		this.l = l;
		this.name = name;
		this.posx = posx;
		this.posy = posy;
		this.isFlownIn = isFlownIn;
		this.imageRef = imageRef;
		this.updateDrawable();
	}

	public boolean isFlownIn() {
		return isFlownIn;
	}

	public void setFlownIn(boolean isFlownIn) {
		this.isFlownIn = isFlownIn;
	}

	public double getPosx() {
		return posx;
	}

	public void setPosx(double posx) {
		this.posx = posx;
		this.updateDrawable();
	}

	public double getPosy() {
		return posy;
	}

	public void setPosy(double posy) {
		this.posy = posy;
		this.updateDrawable();
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
		this.updateDrawable();
	}

	public String getImageRef() {
		return imageRef;
	}

	public void setImageRef(String imageRef) {
		this.imageRef = imageRef;
		this.updateDrawable();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
		this.updateDrawable();
	}
}
