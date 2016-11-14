package readySETgo.panels;

import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.JPanel;

import backend.models.Asset;
import backend.models.Stage;
import readySETgo.User;
import readySETgo.User.MouseState;
import readySETgo.User.SelectedState;
import readySETgo.contextmenus.StagePopUp;

public class StagePanel extends JPanel{
	private Stage stage;
	
	public StagePanel(){
		super();
		stage = new Stage();

		addMouseListener(StagePopUp.createAdapter());
		addMouseListener(new MouseAdapter(){

			@Override
			public void mousePressed(MouseEvent e) { //TODO Store objects original position so if invalid placement it is put back
				Asset a;
				if((a = stage.eventOnObject(e)) != null){
					User.setSelected(a);
					User.setSelectedState(SelectedState.DRAGGING);
				} else {
					User.setSelectedState(SelectedState.EMPTY);
					User.setSelected(null);
				}
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				if(User.getSelectedState().equals(SelectedState.DRAGGING)){
					User.setSelectedState(SelectedState.SELECTED);
				}
				
			}

			
			@Override
			public void mouseEntered(MouseEvent e) { 
				System.out.println("Entered stage");
				if(User.getSelectedState().equals(SelectedState.DRAGGING) ){
					if(User.getMouseState(e).equals(MouseState.UP)){
						User.setSelectedState(SelectedState.SELECTED);
					} else {
						//TODO probably need to make sure it hasnt already been added
						stage.getAssets().add(User.getSelected());
						System.out.println("Added asset");
					}
				} 
				
				
			}
			
		});
		
		addMouseMotionListener(new MouseMotionAdapter(){

			public void mouseDragged(MouseEvent e){
				if(User.getSelectedState().equals(SelectedState.DRAGGING)){
					User.getSelected().setxPos(e.getX());
					User.getSelected().setyPos(e.getY());
				}
				repaint();
			}
			
			@Override
			public void mouseMoved(MouseEvent e) {
				if(User.getSelectedState().equals(SelectedState.DRAGGING)){
					User.getSelected().setxPos(e.getX());
					User.getSelected().setyPos(e.getY());
				}
				repaint();
			}
			
		});
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		
		stage.draw(g);
	}

	public Stage getStage() {
		return stage;
	}

	public void setStage(Stage stage) {
		this.stage = stage;
	}
}
