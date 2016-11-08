package backend.models;

import java.awt.Image;

public class StageObject extends Asset {
	private Image image;
	private String name;
	
	public StageObject(){
		super();
	}
	
	public StageObject(double w, double l, double x, double y, double a, String name, Image image){
		super(w, l, x, y, a);
		this.image = image;
		this.name = name;
	}
	
	public StageObject(double w, double l, String name, Image image){
		this(w, l, 0, 0, 0, name, image);
	}
	
	@Override
	public void draw() {
		// TODO Auto-generated method stub
		
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	

}
