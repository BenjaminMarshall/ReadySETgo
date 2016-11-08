package readySETgo.panels;

import javax.swing.JPanel;

import readySETgo.contextmenus.StagePopUp;

public class StageViewPanel extends JPanel {
	private StagePanel stagePanel;
	
	public StageViewPanel(){
		//TODO Create viewport to stagePanel
		
		
		
		
		addMouseListener(StagePopUp.createAdapter());
	}
}
