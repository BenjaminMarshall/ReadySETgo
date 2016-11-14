package readySETgo.panels;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import backend.FileManager;
import backend.models.StageObject;

public class ObjectPanel extends JPanel {
	private JScrollPane scroller;
	private JPanel container;
	private ArrayList<SingleObjectPanel> objects;
	private ArrayList<StageObject> listModel;
	
	public ObjectPanel(){
		super();
		container = new JPanel();
		refresh();
		
		
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
			op.addMouseListener(new MouseListener(){

				@Override
				public void mouseClicked(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void mousePressed(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void mouseReleased(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void mouseEntered(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void mouseExited(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}
				
			});
			objects.add(op);
			container.add(op);
		}
		
	}
	
}
