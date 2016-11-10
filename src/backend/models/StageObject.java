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
	
	public StageObject(String name, double w, double l, double x, double y, double a, String imageRef) throws IOException{
		super(w, l, x, y, a);
		File file = new File(imageRef);
		System.out.println(file.getAbsolutePath());
		
		this.image = ImageIO.read(new File(imageRef));
		this.imageRef = imageRef;
		this.name = name;
	}
	
	public StageObject(String name, double w, double l, String imageRef) throws IOException{
		this(name, w, l, 0, 0, 0, imageRef);
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
