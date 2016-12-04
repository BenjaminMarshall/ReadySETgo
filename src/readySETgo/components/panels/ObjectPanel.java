package readySETgo.components.panels;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;

import readySETgo.managers.ComponentManager;
import readySETgo.managers.FileManager;
import readySETgo.managers.StageManager;
import readySETgo.managers.UserManager;
import readySETgo.managers.UserManager.MouseState;
import readySETgo.managers.UserManager.SelectedState;
import readySETgo.models.Stage;
import readySETgo.models.assets.Asset;
import readySETgo.models.assets.StageObject;

/**
 * 
 * Creates subpanels used with dragging and dropping stageobjects
 * 
 * @author ReadySETgo
 * @version Beta 3
 * @since 2016-12-04
 * 
 */
public class ObjectPanel extends JPanel {

	private JScrollPane scroller;
	private JPanel container;
	private ArrayList<SingleObjectPanel> subPanels;
	private ArrayList<StageObject> listModel;
	
	/**
	 * Creates and adds a subpanel for every drag and drop object
	 */
	public ObjectPanel() {
		super();
		ComponentManager.registerComp("ObjectPanel", this);
		this.container = new JPanel();
		this.refresh();
				
		this.scroller = new JScrollPane(container, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		// Increase scroll speed from default
		this.scroller.getVerticalScrollBar().setUnitIncrement(15);

		GridBagLayout gc = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		this.setLayout(gc);
		
		c.gridx = 0;
		c.gridy = 0;
		c.weightx = 1;
		c.weighty = 1;
		c.fill = c.BOTH;
		
		this.add(scroller, c);
	}
	
	/**
	 * Remove panel contents and regenerate subpanels
	 * according to objects in the objects file
	 */
	public void refresh() {
		
		this.container.removeAll();
		this.subPanels = new ArrayList<SingleObjectPanel>();

		this.listModel = FileManager.loadListOfObjects();
		Collections.sort(listModel, StageObject.getAlphabeticComparator());
		this.container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
		
		for(StageObject o : listModel) {
			SingleObjectPanel op = new SingleObjectPanel(o);
			op.setPreferredSize(new Dimension(200, 100));
			op.addMouseListener(new ObjPanelMouseAdapter(op));
			op.addMouseMotionListener(new ObjPanelMotionListener(op));
			
			this.subPanels.add(op);
			this.container.add(op);
		}
		
	}
	
	/**
	 * 
	 * Handles dragging objects onto the stage in tandem with the motionlistener
	 *
	 */
	class ObjPanelMouseAdapter extends MouseAdapter {
		
		private SingleObjectPanel sop;
		
		/**
		 * 
		 * Constructor for ObjPanelMouseAdapter
		 * 
		 * @param sop The SingleObjectPanel that this Adapter will be attached to
		 */
		public ObjPanelMouseAdapter(SingleObjectPanel sop) { this.sop = sop; }
		
		
		// TODO - Have Ben javadoc
		@Override
		public void mousePressed(MouseEvent e) {
			UserManager.setSelected(((SingleObjectPanel) e.getComponent()).getCopyOfStageObject());
			UserManager.setSelectedState(SelectedState.DRAGGING);
			
		}

		//TODO - Have ben javadoc
		@Override
		public void mouseReleased(MouseEvent e) {
				
				if(UserManager.getSelectedState().equals(SelectedState.DRAGGING)) {
					double scale = StageManager.getStage().getScale();
					
					Point p = new Point(e.getX(), e.getY());
					
					StagePanel sp = (StagePanel) ComponentManager.getComp("StagePanel");
					
					SwingUtilities.convertPointToScreen(p, sop);
					SwingUtilities.convertPointFromScreen(p,  sp);		
					
					if(sp.getStage().getAssets().contains(UserManager.getSelected())) {
						if(p.getX() < 0 || p.getY() < 0 || p.getX() > sp.getWidth() || p.getY() > sp.getHeight()) {
							sp.getStage().deleteSelected();
							ComponentManager.getComp("MainFrame").
	                		setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
	                	} 
					}
				}
				
				UserManager.setSelectedState(SelectedState.SELECTED);
		}

		// Todo - Is this necesary?
		@Override
		public void mouseEntered(MouseEvent e) {
			if(UserManager.getSelectedState().equals(SelectedState.DRAGGING) && UserManager.getMouseState(e).equals(MouseState.UP)) {
				UserManager.setSelectedState(SelectedState.SELECTED);
			}
		}
	}
	
	/**
	 * 
	 * Handles dragging objects onto the stage in tandem with the mouseadapter
	 *
	 */
	class ObjPanelMotionListener extends MouseMotionAdapter {

		private SingleObjectPanel op;
		
		/**
		 * 
		 * Default Constructor
		 * 
		 * @param sop The SingleObjectPanel that this will be attached to
		 */
		public ObjPanelMotionListener(SingleObjectPanel sop) {
			this.op = sop;
		}
		
		// Todo - Have ben javadoc
		public void mouseDragged(MouseEvent e) {
			if(UserManager.getSelectedState().equals(SelectedState.DRAGGING)) {
				double scale = StageManager.getStage().getScale();
				
				Point p = new Point(e.getX(), e.getY());
				
				StagePanel sp = (StagePanel) ComponentManager.getComp("StagePanel");
				
				SwingUtilities.convertPointToScreen(p, op);
				SwingUtilities.convertPointFromScreen(p,  sp);
										
				UserManager.getSelected().setxPos(p.getX() / scale);
				UserManager.getSelected().setyPos(p.getY() / scale);
				
				
				List<Asset> assets = StageManager.getStage().getAssets();
				if(assets != null && assets.contains(UserManager.getSelected())) {
					if(p.getX() < 0 || p.getY() < 0 || p.getX() > sp.getWidth() || p.getY() > sp.getHeight()) {
                		ComponentManager.getComp("MainFrame").
                		setCursor(Toolkit.getDefaultToolkit().createCustomCursor(
                				new ImageIcon("res/no.png").getImage(),
                				new Point(0,0),"custom cursor"));
                	}
					else {
                		ComponentManager.getComp("MainFrame").
                		setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                	}
				}
			}
		}
	}
}
