package readySETgo.contextmenus;

import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.AbstractAction;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import backend.models.Asset;
import backend.models.Stage;
import readySETgo.User;

public class StagePopUp extends JPopupMenu {
	
	private Stage stage;
	
	public StagePopUp(Stage s, MouseEvent e){
		stage = s;
		
		double origX = e.getX();
		double origY = e.getY();
		
		Boolean isOnObject = stage.eventOnObject(e) != null;
		if(isOnObject) {
			JMenuItem cutItem = new JMenuItem(new AbstractAction("Cut") {
				public void actionPerformed(ActionEvent e) {
					User.setClipboard(User.getSelected().copyOf());
					stage.trashAsset(User.getSelected());
				}
			});
			add(cutItem);
			
			JMenuItem copyItem = new JMenuItem(new AbstractAction("Copy") {
				public void actionPerformed(ActionEvent e) {
					User.setClipboard(User.getSelected().copyOf());
				}
			});
			add(copyItem);
			
			JMenuItem deleteItem = new JMenuItem(new AbstractAction("Delete") {
				public void actionPerformed(ActionEvent e) {
					stage.trashAsset(User.getSelected());
				}
			});
			add(deleteItem);
		}
		else {
			JMenuItem pasteItem = new JMenuItem(new AbstractAction("Paste") {
				public void actionPerformed(ActionEvent e) {
					Asset a = User.getClipboard().copyOf();
					a.setxPos(origX);
					a.setyPos(origY);
					stage.getAssets().add(a);
					User.setSelected(a);
				}
			});
			pasteItem.setEnabled(User.getClipboard() != null);
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
				 StagePopUp menu = new StagePopUp(s, e);
				 menu.show(e.getComponent(), e.getX(), e.getY());
			 }
		};
	}

}
