package readySETgo.managers;

import readySETgo.models.Stage;

/**
 * RSG
 */
public class StageManager {
	private Stage stage;
	private static StageManager manager = new StageManager();

    public static Stage getStage() { return manager.stage; }
    
    public static void registerStage(Stage s) { manager.stage = s; } 
}

