package readySETgo.panels;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

import backend.models.Asset;
import backend.models.StageObject;

public class SingleObjectPanel extends JPanel {
	private StageObject stageObject;
	
	public SingleObjectPanel(StageObject o){
		super();
		this.setStageObject(o);
			
		this.setSize(50, 100);
		repaint();
	}
	
	public Dimension getMinimumSize(){
		return new Dimension(300, 300);
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		
		g.setColor(Color.BLACK);
		g.drawRect(0, 0, getWidth(), getHeight());
		
		g.drawString(stageObject.getName(), 5, 10);
		
		stageObject.draw(g, 5, 25);
	}

	public StageObject getStageObject() {
		return stageObject;
	}

	public void setStageObject(StageObject stageObject) {
		this.stageObject = stageObject;
	}

	public StageObject getCopyOfStageObject() {
		return stageObject.copyOf();
	}
}
