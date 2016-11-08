package readySETgo.panels;

import javax.swing.JPanel;

import backend.models.Stage;

public class StagePanel extends JPanel{
	private Stage stage;
	
	public StagePanel(){
		
	}

	public Stage getStage() {
		return stage;
	}

	public void setStage(Stage stage) {
		this.stage = stage;
	}
}
