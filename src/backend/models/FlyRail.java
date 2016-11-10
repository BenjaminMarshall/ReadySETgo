package backend.models;

import java.awt.Image;

public class FlyRail {

	private boolean isFlownIn;
	private double posx; //TOP LEFT
	private double posy; //TOP LEFT
	private Image image;
	private String imageRef;
	private String name;
	
	public FlyRail(String name, double posx, double posy, boolean isFlownIn, String imageRef){
		this.setName(name);
		this.setPosx(posx);
		this.setPosy(posy);
		this.setFlownIn(isFlownIn);
		this.setImageRef(imageRef);
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
	}

	public double getPosy() {
		return posy;
	}

	public void setPosy(double posy) {
		this.posy = posy;
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	public String getImageRef() {
		return imageRef;
	}

	public void setImageRef(String imageRef) {
		this.imageRef = imageRef;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
