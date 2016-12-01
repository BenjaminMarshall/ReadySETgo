package readySETgo.rightclickmenus;

import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.AbstractAction;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.SwingUtilities;

import readySETgo.dialogs.EditDialog;
import readySETgo.dialogs.ImportDialog;
import readySETgo.models.assets.StageObject;

public class ObjectPanelRCM extends JPopupMenu {
	
	
	public ObjectPanelRCM(MouseEvent e, StageObject obj){

		JMenuItem viewPropsItem = new JMenuItem(new AbstractAction("View object properties ...") {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Hello World!");
			}
		});
		this.add(viewPropsItem);
		
		this.addSeparator();	
		
		JMenuItem editItem = new JMenuItem(new AbstractAction("Edit this stage object ...") {
			public void actionPerformed(ActionEvent e) {
				EditDialog.createAndShow(obj);
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
				ImportDialog.createAndShow();				
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
	
	public static MouseAdapter createAdapter(StageObject obj){
		return new MouseAdapter(){
				
			 public void mouseReleased(MouseEvent e){
				 if(SwingUtilities.isRightMouseButton(e)){
					 doPop(e);
				 }
			 }
			 
			 private void doPop(MouseEvent e){
				 ObjectPanelRCM menu = new ObjectPanelRCM(e, obj);
				 menu.show(e.getComponent(), e.getX(), e.getY());
			 }
		};
	}

}
