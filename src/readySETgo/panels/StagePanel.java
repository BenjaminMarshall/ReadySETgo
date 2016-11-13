package readySETgo.panels;

import java.awt.Graphics;

import javax.swing.JPanel;

import backend.models.Stage;
import readySETgo.contextmenus.StagePopUp;

public class StagePanel extends JPanel{
	private Stage stage;
	
	public StagePanel(){
		stage = new Stage();

		addMouseListener(StagePopUp.createAdapter());
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
