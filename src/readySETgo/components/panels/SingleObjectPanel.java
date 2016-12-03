package readySETgo.components.panels;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

import readySETgo.models.assets.Asset;
import readySETgo.models.assets.StageObject;
import readySETgo.rightclickmenus.ObjectPanelRCM;

public class SingleObjectPanel extends JPanel {
	private StageObject stageObject;
	
	public SingleObjectPanel(StageObject o){
		super();
		this.setStageObject(o);
		// Link the Right Click Menu to this panel
		addMouseListener(ObjectPanelRCM.createAdapter(o));			
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
		
		stageObject.draw(g, 1, 5, 15);
	}

	public StageObject getStageObject() {
		return stageObject;
	}

	public void setStageObject(StageObject stageObject) {
		this.stageObject = stageObject;
	}

	public StageObject getCopyOfStageObject() {
		return (StageObject) stageObject.copyOf();
	}
}
