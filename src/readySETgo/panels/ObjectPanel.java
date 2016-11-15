package readySETgo.panels;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;

import backend.FileManager;
import backend.models.StageObject;
import readySETgo.User;
import readySETgo.User.MouseState;
import readySETgo.User.SelectedState;

public class ObjectPanel extends JPanel {
	private JScrollPane scroller;
	private JPanel container;
	private ArrayList<SingleObjectPanel> objects;
	private ArrayList<StageObject> listModel;
	private boolean mask;
	private JFrame mainFrame;
	private JPanel stagePanel;
	
	public ObjectPanel(JPanel p){
		super();
		container = new JPanel();
		refresh();
		
		stagePanel = p;
		
		scroller = new JScrollPane(container, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		
		GridBagLayout gc = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		setLayout(gc);
		
		c.gridx = 0;
		c.gridy = 0;
		c.weightx = 1;
		c.weighty = 1;
		c.fill = c.BOTH;
		
		add(scroller, c);
		mask = true;
	}
	

	private void refresh(){
		
		container.removeAll();
		objects = new ArrayList<SingleObjectPanel>();
		
		
		if(listModel != null){
			FileManager.saveListOfObjects(listModel);
		}
		
		listModel = FileManager.getListOfObjects();
		
		Collections.sort(listModel, StageObject.getAlphabeticComparator());
		
		
		container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
		
		for(StageObject o: listModel){
			SingleObjectPanel op = new SingleObjectPanel(o);
			op.setPreferredSize(new Dimension(200, 100));
			op.addMouseListener(new MouseAdapter(){

				@Override
				public void mousePressed(MouseEvent e) {
					User.setSelected(((SingleObjectPanel) e.getComponent()).getCopyOfStageObject());
					User.setSelectedState(SelectedState.DRAGGING);
					
				}

				@Override
				public void mouseReleased(MouseEvent e) {
						User.setSelectedState(SelectedState.SELECTED);
				}

				@Override
				public void mouseEntered(MouseEvent e) {
					if(User.getSelectedState().equals(SelectedState.DRAGGING) && User.getMouseState(e).equals(MouseState.UP)){
						User.setSelectedState(SelectedState.SELECTED);
					}
					
				}
				
				public void mouseExited(MouseEvent e){
					System.out.println("Exited object panel");	
				}
				
			});
			JPanel objPanel = this;
			op.addMouseMotionListener(new MouseMotionListener(){

				@Override
				public void mouseDragged(MouseEvent e) {
					if(User.getSelectedState().equals(SelectedState.DRAGGING)){
						
						Point p = new Point(e.getX(), e.getY());
						
						SwingUtilities.convertPointToScreen(p, objPanel);
						SwingUtilities.convertPointFromScreen(p, stagePanel);
												
						User.getSelected().setxPos(p.getX());
						User.getSelected().setyPos(p.getY());
					}
				}

				@Override
				public void mouseMoved(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}
				
			});
			objects.add(op);
			container.add(op);
		}
		
	}
	
}
