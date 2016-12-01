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
		setText("Default Text");
		
	}
	
	private String convertTextToTag(String text) {
		String[] lines = text.split("\n");
		String tag = "";
		for(String s: lines){
			String line = "<p>" + s + "<p>";
			tag += line;
		}
		return tag;
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
		setText(text);
	}
	
	public JLabel getLabel(){
		return label;
	}
	
	public void setLabel(JLabel label){
		this.label = label;
	}
	
	public void setText(String text){
		this.text = text;
		String formatText = convertTextToTag(text);
		
		this.label = new JLabel("<html><body style='padding: 5px;'>"
                + formatText + "</html>");
		
		label.setSize(label.getPreferredSize());
		 Dimension d = label.getPreferredSize();
		 if(d.getWidth() > 157){
			 this.label = new JLabel("<html><body style='width:157px; padding: 5px;'>"
		                + formatText + "</html>");
				 d = label.getPreferredSize();
				 this.setPhysicalWidth(d.getWidth());
				 this.setPhysicalLength(d.getHeight());
		 } else {
			 this.setPhysicalWidth(d.getWidth());
			 this.setPhysicalLength(d.getHeight());
		 }
		 
	}
	

	public Asset copyOf() {
		return new TextBox(this.getPhysicalWidth(),this.getPhysicalLength(),this.getxPos(), this.getyPos(), this.getAngle(), this.getLabel().getText());
	}

	public String getText() {
		return text;
	}
	
}
