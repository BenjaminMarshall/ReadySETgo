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

import readySETgo.components.MainFrame;
import readySETgo.managers.ComponentManager;
import readySETgo.managers.FileManager;
import readySETgo.managers.StageManager;
import readySETgo.managers.UserManager;
import readySETgo.managers.UserManager.MouseState;
import readySETgo.managers.UserManager.SelectedState;
import readySETgo.models.Stage;
import readySETgo.models.assets.Asset;
import readySETgo.models.assets.StageObject;

public class ObjectPanel extends JPanel {
	private JScrollPane scroller;
	private JPanel container;
	private ArrayList<SingleObjectPanel> subPanels;
	private ArrayList<StageObject> listModel;
	
	public ObjectPanel(){
		super();
		ComponentManager.registerComp("ObjectPanel", this);
		container = new JPanel();
		refresh();
				
		scroller = new JScrollPane(container, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		// Increase scroll speed from default
		scroller.getVerticalScrollBar().setUnitIncrement(15);

		GridBagLayout gc = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		setLayout(gc);
		
		c.gridx = 0;
		c.gridy = 0;
		c.weightx = 1;
		c.weighty = 1;
		c.fill = c.BOTH;
		
		add(scroller, c);
	}
	
	public void loadObjects(Stage s){
		// Reset container
		container.removeAll();
		container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
		// Reset subPanels
		subPanels = new ArrayList<SingleObjectPanel>();
		// Combine stage object list with the default object list, remove dupes
		listModel = FileManager.getListOfObjects();
		// Combination disabled for now
		//listModel.addAll((ArrayList<StageObject>)(ArrayList<?>) (s.getAssets()));
		// TODO - Figure out why this isn't removing duplicates
		Set<StageObject> dupeRemover = new HashSet<>();
		dupeRemover.addAll(listModel);
		listModel.clear();
		listModel.addAll(dupeRemover);
		// Sort the list
		Collections.sort(listModel, StageObject.getAlphabeticComparator());
		
		// Add the new subPanels
		for(StageObject o: listModel) {
			SingleObjectPanel op = new SingleObjectPanel(o);
			op.setPreferredSize(new Dimension(200, 100));
			op.addMouseListener(new ObjPanelMouseAdapter());
			op.addMouseMotionListener(new ObjPanelMotionListener(op));
			subPanels.add(op);
			container.add(op);
		}
		this.validate();
		this.repaint();
	}

	
	private void refresh(){
		
		container.removeAll();
		subPanels = new ArrayList<SingleObjectPanel>();
		
		
		if(listModel != null){
			FileManager.saveListOfObjects(listModel);
		}
		
		listModel = FileManager.getListOfObjects();
		
		Collections.sort(listModel, StageObject.getAlphabeticComparator());
		
		container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
		
		for(StageObject o: listModel){
			SingleObjectPanel op = new SingleObjectPanel(o);
			op.setPreferredSize(new Dimension(200, 100));
			op.addMouseListener(new ObjPanelMouseAdapter());
			op.addMouseMotionListener(new ObjPanelMotionListener(op));
			
			subPanels.add(op);
			container.add(op);
		}
		
	}
	
	
	class ObjPanelMouseAdapter extends MouseAdapter {
		@Override
		public void mousePressed(MouseEvent e) {
			UserManager.setSelected(((SingleObjectPanel) e.getComponent()).getCopyOfStageObject());
			UserManager.setSelectedState(SelectedState.DRAGGING);
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
				
				
				if(UserManager.getSelectedState().equals(SelectedState.DRAGGING)){
					double scale = StageManager.get().getStage().getScale();
					
					Point p = new Point(e.getX(), e.getY());
					
					StagePanel sp = (StagePanel) ComponentManager.getComp("StagePanel");
					
					SwingUtilities.convertPointToScreen(p, ObjectPanel.this);
					SwingUtilities.convertPointFromScreen(p,  sp);
											
					UserManager.getSelected().setxPos(p.getX() / scale);
					UserManager.getSelected().setyPos(p.getY() / scale);		
					
					if(sp.getStage().getAssets().contains(UserManager.getSelected())){
						if(p.getX() < 0 || p.getY() < 0 || p.getX() > sp.getWidth() || p.getY() > sp.getHeight()) {
							sp.getStage().deleteSelected();
							ComponentManager.getComp("MainFrame").
	                		setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
	                	} 
					}
				}
				
				UserManager.setSelectedState(SelectedState.SELECTED);
				
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			if(UserManager.getSelectedState().equals(SelectedState.DRAGGING) && UserManager.getMouseState(e).equals(MouseState.UP)){
				UserManager.setSelectedState(SelectedState.SELECTED);
			}
		}
	}
	
	class ObjPanelMotionListener extends MouseMotionAdapter {

		private SingleObjectPanel op;
		
		public ObjPanelMotionListener(SingleObjectPanel _op) {
			this.op = _op;
		}
		
		public void mouseDragged(MouseEvent e) {
			if(UserManager.getSelectedState().equals(SelectedState.DRAGGING)){
				double scale = StageManager.get().getStage().getScale();
				
				Point p = new Point(e.getX(), e.getY());
				
				StagePanel sp = (StagePanel) ComponentManager.getComp("StagePanel");
				
				SwingUtilities.convertPointToScreen(p, op);
				SwingUtilities.convertPointFromScreen(p,  sp);
										
				UserManager.getSelected().setxPos(p.getX() / scale);
				UserManager.getSelected().setyPos(p.getY() / scale);
				
				
				List<Asset> assets = StageManager.get().getStage().getAssets();
				if(assets != null && assets.contains(UserManager.getSelected())){
					if(p.getX() < 0 || p.getY() < 0 || p.getX() > sp.getWidth() || p.getY() > sp.getHeight()) {
                		ComponentManager.getComp("MainFrame").
                		setCursor(Toolkit.getDefaultToolkit().createCustomCursor(
                				new ImageIcon("res/no.png").getImage(),
                				new Point(0,0),"custom cursor"));
                	} else {
                		ComponentManager.getComp("MainFrame").
                		setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                	}
				}
			}
		}

	}
	
}
