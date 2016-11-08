package readySETgo.panels;

import javax.swing.JPanel;

import readySETgo.contextmenus.StagePopUp;

public class StagePanel extends JPanel {
	
	public StagePanel(){
		
		
		
		
		
		addMouseListener(StagePopUp.createAdapter());
	}
}
