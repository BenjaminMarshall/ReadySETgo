package backend.models;

import java.awt.Image;

public class StageObject extends Asset {
	private Image image;
	
	
	public StageObject(){
		super();
	}
	
	public StageObject(double w, double l, double x, double y, double a, Image image){
		super(w, l, x, y, a);
		this.image = image;
	}
	
	public StageObject(double w, double l, Image image){
		this(w, l, 0, 0, 0, image);
	}
	
	@Override
	public void toXML() {
		// TODO Auto-generated method stub
		
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

}
