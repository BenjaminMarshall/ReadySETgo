package readySETgo.models.stageactions;

import readySETgo.managers.StageManager;
import readySETgo.managers.UserManager;
import readySETgo.models.assets.Asset;

/**
 * 
 * Represents an action where an Asset is created on stage
 * 
 * @author ReadySETgo
 * @version Beta 3
 * @since 2016-12-04
 * 
 */
public class CreationAction extends StageAction {

	Asset created;
	
	/**
	 * Default constructor
	 * @param c The created asset
	 */
	public CreationAction(Asset c) {
		this.created = c;
	}
	
	/**
	 * Undo the action
	 */
	public void undoAction() {
		StageManager.getStage().getAssets().remove(this.created);
		UserManager.setSelectedState(UserManager.SelectedState.EMPTY);
		UserManager.setSelected(null);
	}
	
	/**
	 * Redo the action
	 */
	public void redoAction() {
		StageManager.getStage().getAssets().add(this.created);
		UserManager.setSelectedState(UserManager.SelectedState.SELECTED);
		UserManager.setSelected(this.created);
	}

}
