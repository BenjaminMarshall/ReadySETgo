package readySETgo.managers;

import readySETgo.models.Stage;

/**
 * RSG
 */
public class StageManager {
	private Stage stage;
	private static StageManager manager = new StageManager();

    public static StageManager get() {
        return manager;
    }

    public Stage getStage() {return stage;}
    
    public void registerStage(Stage s) {
        this.stage = s;
    }

 
}

