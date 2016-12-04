package readySETgo.models.assets;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import javax.swing.JLabel;

public class TextBox extends Asset{
	private JLabel label;
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
	public void draw(Graphics stageGraphics, double scale, boolean selected) {
		
		Graphics2D stg2D = (Graphics2D) stageGraphics.create();
		stg2D.setColor(Color.BLACK);
		
		label.setSize(label.getPreferredSize());
		Dimension d = label.getPreferredSize();
        BufferedImage bi = new BufferedImage((int) (d.width), (int) (d.height), BufferedImage.TYPE_INT_ARGB);
		Graphics g = bi.createGraphics();
		 
		//BufferedImage drawing
        g.setColor(Color.black);
        label.paint(g);
         
        //Rotate Transform stg2D
     	AffineTransform old = stg2D.getTransform();
		AffineTransform rotateTransform = new AffineTransform();
		rotateTransform.rotate(Math.toRadians(getAngle()), (int) (this.getxPos()*scale), (int) (this.getyPos()*scale));
		stg2D.transform(rotateTransform);
         
        stg2D.drawImage(bi, (int) (this.getxPos() * scale), (int) (this.getyPos() * scale), (int) (this.getPhysicalWidth() * scale), (int)(this.getPhysicalLength() * scale), null);
        
        if(selected){
        	Graphics2D g3 = (Graphics2D) stg2D.create();
			g3.setColor(Color.BLACK);
			Stroke dashed = new BasicStroke(2, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_MITER, 1, new float[]{5}, 0);
			g3.setStroke(dashed);
			g3.drawRect((int) (this.getxPos()*scale) - 4, (int) (this.getyPos()*scale) - 4, (int) (this.getPhysicalWidth() * scale) + 8, (int) (this.getPhysicalLength() * scale) + 8);
			g3.dispose();
		}   
        
        stg2D.transform(old);
        g.dispose();
        stg2D.dispose();
	}
	
	public TextBox(String text){
		this(0,0,0,0,0,text);
	}
	
	public TextBox(double w, double l, double x, double y, double a, String text){
		super(w, l, x, y, a);
		setText(text);
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
		return new TextBox(this.getPhysicalWidth(),this.getPhysicalLength(),this.getxPos(), this.getyPos(), this.getAngle(), this.getText());
	}

	public String getText() {
		return text;
	}
	
}
