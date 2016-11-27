package readySETgo.contextmenus;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import backend.models.Stage;

public class StagePopUp extends JPopupMenu {
	
	public StagePopUp(Boolean isOnObject){
		if(isOnObject) {
			JMenuItem cutItem = new JMenuItem("Cut");
			add(cutItem);
			
			JMenuItem copyItem = new JMenuItem("Copy");
			add(copyItem);
			
			JMenuItem deleteItem = new JMenuItem("Delete");
			add(deleteItem);
		}
		else {
			JMenuItem pasteItem = new JMenuItem("Paste");
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
				 StagePopUp menu = new StagePopUp(s.eventOnObject(e) != null);
				 menu.show(e.getComponent(), e.getX(), e.getY());
			 }
		};
	}

}
