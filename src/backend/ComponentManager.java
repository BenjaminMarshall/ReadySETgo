package backend;

import javax.swing.JFrame;

import readySETgo.panels.StagePanel;

public class ComponentManager {

	private JFrame mainFrame;
	private StagePanel stagePanel;
	private static ComponentManager manager = new ComponentManager();

    public static ComponentManager get() {
        return manager;
    }

    public JFrame getMainFrame() {
    	return this.mainFrame;
    }
    
    public void registerMainFrame(JFrame f) {
        this.mainFrame = f;
    }

    public StagePanel getStagePanel() {
    	return this.stagePanel;
    }
    
    public void registerStagePanel(StagePanel j) {
        this.stagePanel = j;
    }
    
    
    
}
