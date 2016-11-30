package readySETgo.models.assets;

import java.awt.Graphics;

public class TextBox extends Asset{
	private String text;
	
	public TextBox(){
		super();
		this.text = "";
	}
	
	@Override
	public void toXML() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void draw(Graphics g, double x, double y) {
		// TODO Auto-generated method stub
		
	}
	
	public TextBox(String text){
		this(0,0,0,0,0,text);
	}
	
	public TextBox(double w, double l, double x, double y, double a, String text){
		super(w, l, x, y, a);
		this.text = text;
	}
	
	public String getText(){
		return text;
	}
	
	public void setText(String text){
		this.text = text;
	}
	

	public Asset copyOf() {
		return new TextBox(this.getPhysicalWidth(),this.getPhysicalLength(),this.getxPos(), this.getyPos(), this.getAngle(), this.getText());
	}
	
}
