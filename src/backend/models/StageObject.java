package backend.models;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class StageObject extends Asset {
	private Image image;
	private String imageRef;
	private String name;
	
	public StageObject(){
		super();
	}
	
	public StageObject(double w, double l, double x, double y, double a, String name, String imageRef) throws IOException{
		super(w, l, x, y, a);
		this.image = ImageIO.read(new File(imageRef));
		this.name = name;
	}
	
	public StageObject(double w, double l, String name, String imageRef) throws IOException{
		this(w, l, 0, 0, 0, name, imageRef);
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

	public String getImageRef() {
		return imageRef;
	}

	public void setImageRef(String imageRef) {
		this.imageRef = imageRef;
	}

	

}
