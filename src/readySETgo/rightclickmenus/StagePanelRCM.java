package readySETgo.rightclickmenus;

import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.AbstractAction;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import readySETgo.managers.UserManager;
import readySETgo.models.Stage;
import readySETgo.models.assets.Asset;

public class StagePanelRCM extends JPopupMenu {
	
	private Stage stage;
	
	public StagePanelRCM(Stage s, MouseEvent e){
		stage = s;
		
		double origX = e.getX();
		double origY = e.getY();
		
		Boolean isOnObject = stage.eventOnObject(e) != null;
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
			
			JMenuItem zoomInItem = new JMenuItem("Zoom in");
			add(zoomInItem);
			
			JMenuItem zoomOutItem = new JMenuItem("Zoom out");
			add(zoomOutItem);
		}
	}
	
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
