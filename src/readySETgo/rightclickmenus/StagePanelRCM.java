package readySETgo.rightclickmenus;

import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.AbstractAction;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import readySETgo.dialogs.PropsDialog;
import readySETgo.managers.UserManager;
import readySETgo.models.Stage;
import readySETgo.models.assets.Asset;
import readySETgo.models.assets.StageObject;
import readySETgo.models.assets.TextBox;

/**
 * 
 * The right click menu for the StagePanel
 * 
 * @author ReadySETgo
 * @version Beta 3
 * @since 2016-12-04
 * 
 */
public class StagePanelRCM extends JPopupMenu {
	
	private Stage stage;
	
	/**
	 * Default Constructor
	 * @param s The Stage in the StagePanel
	 * @param e The MouseEvent that spawned this menu
	 */
	public StagePanelRCM(Stage s, MouseEvent e){
		stage = s;
		
		double origX = e.getX();
		double origY = e.getY();
		
		Asset obj = stage.eventOnObject(e);
		Boolean isOnObject = (obj != null);
		if(isOnObject) {
			JMenuItem cutItem = new JMenuItem(new AbstractAction("Cut") {
				public void actionPerformed(ActionEvent e) {
					stage.cutSelected();
				}
			});
			add(cutItem);
			
			JMenuItem copyItem = new JMenuItem(new AbstractAction("Copy") {
				public void actionPerformed(ActionEvent e) {
					stage.copySelected();
				}
			});
			add(copyItem);
			
			if(UserManager.getSelected() instanceof TextBox){
				JMenuItem editItem = new JMenuItem(new AbstractAction("Edit"){
					public void actionPerformed(ActionEvent e){
						stage.editSelectedTextBox();
					}
				});
				add(editItem);
			}
			else {
				JMenuItem propsItem = new JMenuItem(new AbstractAction("Properties") {
					public void actionPerformed(ActionEvent e) {
						PropsDialog.createAndShow(((StageObject) (obj)));
					}
				});
				add(propsItem);
			}
			
			JMenuItem rotateItem = new JMenuItem(new AbstractAction("Rotate"){
				public void actionPerformed(ActionEvent e){
					stage.rotateSelectedAsset();
				}
			});
			add(rotateItem);
			
			JMenuItem deleteItem = new JMenuItem(new AbstractAction("Delete") {
				public void actionPerformed(ActionEvent e) {
					stage.deleteSelected();
				}
			});
			add(deleteItem);
		}
		else {
			JMenuItem pasteItem = new JMenuItem(new AbstractAction("Paste") {
				public void actionPerformed(ActionEvent e) {
					stage.pasteFromClipboard(origX, origY);
				}
			});
			pasteItem.setEnabled(UserManager.getClipboard() != null);
			add(pasteItem);
			
			JMenuItem addLabelItem = new JMenuItem(new AbstractAction("Add Textbox") {
				public void actionPerformed(ActionEvent e){
					stage.createTextBox(origX, origY);
				}
			});
			add(addLabelItem);
		}
	}
	
	/**
	 * Creates an adapter which spawns the right click menu
	 * @param s The Stage in the StagePanel
	 * @return The MouseAdapter to attach to the StagePanel
	 */
	public static MouseAdapter createAdapter(Stage s){
		return new MouseAdapter(){
		
			
			 public void mousePressed(MouseEvent e){
				 if(e.isPopupTrigger()){
					 doPop(e);
				 }
			 }
			 
			 public void mouseReleased(MouseEvent e){
				 if(e.isPopupTrigger()){
					 doPop(e);
				 }
			 }
			 
			 private void doPop(MouseEvent e){
				 StagePanelRCM menu = new StagePanelRCM(s, e);
				 menu.show(e.getComponent(), e.getX(), e.getY());
			 }
		};
	}

}
