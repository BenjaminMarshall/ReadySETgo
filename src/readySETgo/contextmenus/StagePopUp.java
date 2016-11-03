package readySETgo.contextmenus;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

public class StagePopUp extends JPopupMenu {
	
	public StagePopUp(){
		JMenuItem pasteItem = new JMenuItem("Paste");
		add(pasteItem);
		
		JMenuItem zoomInItem = new JMenuItem("Zoom in");
		add(zoomInItem);
		
		JMenuItem zoomOutItem = new JMenuItem("Zoom out");
		add(zoomOutItem);
	}
	
	public static MouseAdapter createAdapter(){
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
				 StagePopUp menu = new StagePopUp();
				 menu.show(e.getComponent(), e.getX(), e.getY());
			 }
		};
	}
}
