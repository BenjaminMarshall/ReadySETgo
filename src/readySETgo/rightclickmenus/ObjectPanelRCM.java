package readySETgo.rightclickmenus;

import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.AbstractAction;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.SwingUtilities;

import readySETgo.managers.UserManager;
import readySETgo.models.Stage;
import readySETgo.models.assets.Asset;

public class ObjectPanelRCM extends JPopupMenu {
	
	
	public ObjectPanelRCM(MouseEvent e){

		JMenuItem viewPropsItem = new JMenuItem(new AbstractAction("View object properties ...") {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Hello World!");
			}
		});
		this.add(viewPropsItem);
		
		this.addSeparator();	
		
		JMenuItem editItem = new JMenuItem(new AbstractAction("Edit this stage object ...") {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Hello World!");
			}
		});
		this.add(editItem);
		
		JMenuItem cloneItem = new JMenuItem(new AbstractAction("Clone this stage object ...") {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Hello World!");
			}
		});
		this.add(cloneItem);
		
		this.addSeparator();
		
		JMenuItem newObjItem = new JMenuItem(new AbstractAction("Import new stage object ...") {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Hello World!");
			}
		});
		this.add(newObjItem);
		
		this.addSeparator();
		
		JMenuItem deleteItem = new JMenuItem(new AbstractAction("Delete this stage object") {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Goodbye World!");
			}
		});
		this.add(deleteItem);
		
	}
	
	public static MouseAdapter createAdapter(){
		return new MouseAdapter(){
				
			 public void mouseReleased(MouseEvent e){
				 if(SwingUtilities.isRightMouseButton(e)){
					 doPop(e);
				 }
			 }
			 
			 private void doPop(MouseEvent e){
				 ObjectPanelRCM menu = new ObjectPanelRCM(e);
				 menu.show(e.getComponent(), e.getX(), e.getY());
			 }
		};
	}

}