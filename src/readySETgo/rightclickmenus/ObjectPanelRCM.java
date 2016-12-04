package readySETgo.rightclickmenus;

import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.AbstractAction;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.SwingUtilities;

import readySETgo.dialogs.CloneDialog;
import readySETgo.dialogs.DeleteConfirmationDialog;
import readySETgo.dialogs.EditDialog;
import readySETgo.dialogs.ImportDialog;
import readySETgo.dialogs.PropsDialog;
import readySETgo.models.assets.StageObject;

/**
 * 
 * The right click menu for the ObjectPanel
 * 
 * @author ReadySETgo
 * @version Beta 3
 * @since 2016-12-04
 * 
 */
public class ObjectPanelRCM extends JPopupMenu {
	
	/**
	 * Default Constructor
	 * @param e The MouseEvent that spawned this menu
	 * @param obj The StageObject from the panel which was clicked
	 */
	public ObjectPanelRCM(MouseEvent e, StageObject obj){

		JMenuItem viewPropsItem = new JMenuItem(new AbstractAction("View object properties ...") {
			public void actionPerformed(ActionEvent e) {
				PropsDialog.createAndShow(obj);
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
				CloneDialog.createAndShow(obj);
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
				DeleteConfirmationDialog.createAndShow(obj);
			}
		});
		this.add(deleteItem);
		
	}
	
	/**
	 * Creates an adapter which spawns the right click menu
	 * @param obj The StageObject from the panel which this Adapter was attached to
	 * @return The MouseAdapter to attach to a SingleObjectPanel
	 */
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
