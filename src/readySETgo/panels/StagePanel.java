package readySETgo.panels;

import java.awt.Graphics;
import java.util.Arrays;

import javax.swing.JPanel;

import backend.models.Stage;
import readySETgo.contextmenus.StagePopUp;

public class StagePanel extends JPanel{
	private Stage stage;
	
	public StagePanel(){
		super();
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
