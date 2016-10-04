package readySETgo;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class MockColorPanel extends JPanel{
	private Color color;
	
	public MockColorPanel(Color color){
		super();
		this.color = color;
	}
	
	public void paint(Graphics g){
		g.setColor(getColor());
		g.fillRect(0,0,this.getWidth(),this.getHeight());
	}
	
	public Color getColor(){
		return this.color;
	}
	
	public void setColor(Color c){
		this.color = c;
	}
}
