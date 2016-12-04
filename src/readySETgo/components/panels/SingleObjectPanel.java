package readySETgo.components.panels;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

import readySETgo.models.assets.StageObject;
import readySETgo.rightclickmenus.ObjectPanelRCM;

/**
 * 
 * Panel for one drag and drop object
 * 
 * @author ReadySETgo
 * @version Beta 3
 * @since 2016-12-04
 * 
 */
public class SingleObjectPanel extends JPanel {

	private StageObject stageObject;
	
	/**
	 * 
	 * Default Constructor
	 * 
	 * @param o The panel's StageObject
	 */
	public SingleObjectPanel(StageObject o) {
		super();
		this.setStageObject(o);
		// Link the Right Click Menu to this panel
		this.addMouseListener(ObjectPanelRCM.createAdapter(o));			
		this.setSize(50, 100);
		this.repaint();
	}
	
	/**
	 * Paints the panel
	 * @param g The graphics object to paint with
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		g.setColor(Color.BLACK);
		g.drawRect(0, 0, getWidth(), getHeight());
		
		int xOffset = 5;
		int yOffset = 15;
		
		g.drawString(stageObject.getName(), xOffset, yOffset);
		
		double desiredImageBoxLength = 50;
		double greaterDimension = Math.max(stageObject.getPhysicalLength(), stageObject.getPhysicalWidth());
		double scale = desiredImageBoxLength / greaterDimension;
		stageObject.draw(g, scale, xOffset, yOffset + 3);
		
		g.drawString("Actual Length: "+stageObject.getPhysicalLength()+ "in", xOffset, (int) (30+desiredImageBoxLength));
		g.drawString("Actual Width: "+stageObject.getPhysicalWidth()+ "in", xOffset, (int) (30+desiredImageBoxLength+12));
	}

	/**
	 * Returns the panel's stage object
	 * @return The StageObject contained by the panel
	 */
	public StageObject getStageObject() { return stageObject; }

	/**
	 * Sets the panel to contain a new StageObject
	 * @param stageObject The StageObject to be contained by the panel
	 */
	public void setStageObject(StageObject stageObject) { this.stageObject = stageObject; }

	/**
	 * Returns a copy of the panel's StageObject
	 * @return A copy of the panel's StageObject
	 */
	public StageObject getCopyOfStageObject() { return (StageObject) this.stageObject.copyOf(); }
}
