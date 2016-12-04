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

/**
 * 
 * Represents a textbox which can be placed on a Stage
 * 
 * @author ReadySETgo
 * @version Beta 3
 * @since 2016-12-04
 * 
 */
public class TextBox extends Asset{
	private JLabel label;
	private String text;
	private double fontScale;
	
	/**
	 * Default constructor
	 */
	public TextBox() {
		super();
		setText("Default Text");
		this.setFontScale(1);
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

	/**
	 * Draws TextBox
	 * @param stageGraphics Graphics object to use
	 * @param scale The scale factor to draw with
	 * @param selected Whether to draw with a selection border
	 */
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
         
        stg2D.drawImage(bi, (int) (this.getxPos() * scale), (int) (this.getyPos() * scale), (int) (this.getPhysicalWidth() * scale * fontScale), (int)(this.getPhysicalLength() * scale * fontScale), null);
        
        if(selected){
        	Graphics2D g3 = (Graphics2D) stg2D.create();
			g3.setColor(Color.BLACK);
			Stroke dashed = new BasicStroke(2, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_MITER, 1, new float[]{5}, 0);
			g3.setStroke(dashed);
			g3.drawRect((int) (this.getxPos()*scale) - 4, (int) (this.getyPos()*scale) - 4, (int) (this.getPhysicalWidth() * scale * fontScale) + 8, (int) (this.getPhysicalLength() * scale * fontScale) + 8);
			g3.dispose();
		}   
        
        stg2D.transform(old);
        g.dispose();
        stg2D.dispose();
	}
	
	/**
	 * Constructor specifying text
	 * @param text The text inside the textbox
	 */
	public TextBox(String text){
		this(0,0,0,0,0,text, 1.0);
	}
	
	/**
	 * Constructor specifying width, length, x pos, y pos, rotation angle, text, and fontScale
	 * @param w The width
	 * @param l The length
	 * @param x The x position
	 * @param y The y position
	 * @param a The rotation angle
	 * @param text The contained text
	 * @param fontScale The font scale
	 */
	public TextBox(double w, double l, double x, double y, double a, String text, double fontScale){
		super(w, l, x, y, a);
		setText(text);
		setFontScale(fontScale);
	}
	
	/**
	 * Returns String representation of TextBox
	 * @return String representation of TextBox
	 */
	public String toString() {
		return String.format("w: %s, l: %s, x: %s, y: %s, a: %s, text: %s", this.getPhysicalWidth(), this.getPhysicalLength(), this.getxPos(), this.getyPos(), this.getAngle(), this.text);
	}
	
	/**
	 * Sets the text inside the TextBox
	 * @param text The text to set
	 */
	public void setText(String text){
		this.text = text;
		String formatText = convertTextToTag(text);
		
		this.label = new JLabel("<html><body style='padding: 5px;'>"
                				+ formatText + "</body></html>");
		
		
		label.setSize(label.getPreferredSize());
		
		Dimension d = label.getPreferredSize();
		if(d.getWidth() > 157){
		
			this.label = new JLabel("<html><body style='width:157px; padding: 5px;'>"
									+ formatText + "</body></html>");
			
			
			d = label.getPreferredSize();
			
			this.setPhysicalWidth(d.getWidth());
			this.setPhysicalLength(d.getHeight());
		} else {
			this.setPhysicalWidth(d.getWidth());
			this.setPhysicalLength(d.getHeight());
		}	 
	}
	
	/**
	 * Returns a copy of the TextBox
	 * @return A copy of the TextBox
	 */
	public Asset copyOf() {
		return new TextBox(this.getPhysicalWidth(),this.getPhysicalLength(),this.getxPos(), this.getyPos(), this.getAngle(), this.getText(), this.getFontScale());
	}

	/**
	 * Gets the TextBox's text
	 * @return The TextBox's text
	 */
	public String getText() {
		return text;
	}

	/**
	 * Gets the TextBox's scale
	 * @return The font scale
	 */
	public double getFontScale() {
		return fontScale;
	}

	/**
	 * Sets the TextBox's font scale
	 * @param fontScale The scale to set
	 */
	public void setFontScale(double fontScale) {
		this.fontScale = fontScale;
	}
	
}
