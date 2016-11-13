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

		System.out.println(Arrays.toString(this.getMouseListeners()));
		//addMouseListener(StagePopUp.createAdapter());
		System.out.println(Arrays.toString(this.getMouseListeners()));
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
