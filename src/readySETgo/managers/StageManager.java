package readySETgo.managers;

import readySETgo.components.panels.FlyRailPanel;
import readySETgo.components.panels.StagePanel;
import readySETgo.models.Stage;

/**
 * 
 * Manager for registering and retrieving the stage
 * 
 * @author ReadySETgo
 * @version Beta 3
 * @since 2016-12-04
 * 
 */
public class StageManager {
	
	private Stage stage;
	
	private static StageManager manager = new StageManager();

	/**
	 * Returns the registered Stage
	 * @return The registered Stage
	 */
    public static Stage getStage() { return manager.stage; }
    
    public static void makeNewStage() {
    	Stage newStage = new Stage();
    	((StagePanel) ComponentManager.getComp("StagePanel")).loadStage(newStage);
    	UndoManager.reset();
    	UserManager.setSelectedState(UserManager.SelectedState.EMPTY);
    	UserManager.setSelected(null);
    	((FlyRailPanel) ComponentManager.getComp("FlyRailPanel")).loadFlyRails(newStage);
    }
    
    /**
     * Registers a stage to be returned by getStage
     * @param s The Stage to register
     */
    public static void registerStage(Stage s) { manager.stage = s; } 
}

