package readySETgo.models.assets;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JLabel;

public class TextBox extends Asset{
	private JLabel label;
	private int width;
	private String text;
	
	public TextBox(){
		super();
		this.width = 100;
		this.text = "Default Text";
		this.label = new JLabel("<html><body style='width: " + width + "px; padding: 5px;'>"
                + text + "</html>");
	}
	
	@Override
	public void toXML() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void draw(Graphics g, double x, double y) {
		g.setColor(Color.BLACK);
		
		label.setSize(label.getPreferredSize());
		 Dimension d = label.getPreferredSize();
		 this.setPhysicalWidth(d.getWidth());
		 this.setPhysicalLength(d.getHeight());
         BufferedImage bi = new BufferedImage(
             d.width,
             d.height,
             BufferedImage.TYPE_INT_ARGB);
		 Graphics g2 = bi.createGraphics();
         g2.setColor(Color.black);
         label.paint(g2);
         
         g.drawImage(bi, (int) x, (int) y, null);
         
         
	}
	
	public TextBox(String text){
		this(0,0,0,0,0,text);
	}
	
	public TextBox(double w, double l, double x, double y, double a, String text){
		super(w, l, x, y, a);
		this.width = 100;
		this.label = new JLabel(text);
	}
	
	public JLabel getLabel(){
		return label;
	}
	
	public void setLabel(JLabel label){
		this.label = label;
	}
	
	public void setText(String text){
		this.text = text;
		label = new JLabel("<html><body style='width: " + width + "px; padding: 5px;'>"
                + text + "</html>");
	}
	

	public Asset copyOf() {
		return new TextBox(this.getPhysicalWidth(),this.getPhysicalLength(),this.getxPos(), this.getyPos(), this.getAngle(), this.getLabel().getText());
	}

	public String getText() {
		return text;
	}
	
}
