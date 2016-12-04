package readySETgo.models.stageactions;

import readySETgo.managers.StageManager;
import readySETgo.managers.UserManager;
import readySETgo.models.assets.Asset;

/**
 * 
 * Represents an action where an Asset is deleted from the Stage
 * 
 * @author ReadySETgo
 * @version Beta 3
 * @since 2016-12-04
 * 
 */

public class DeleteAction extends StageAction {

	Asset deleted;
	UserManager.SelectedState prevState;
	Asset prevSelected;
	
	/**
	 * Default constructor
	 * @param deleted The deleted Asset
	 * @param prevState The previous SelectedState
	 * @param prevSelected The previously selected Asset
	 */
	public DeleteAction(Asset deleted, UserManager.SelectedState prevState, Asset prevSelected) {
		this.deleted = deleted;
		this.prevState = prevState;
		this.prevSelected = prevSelected;
	}
	
	/**
	 * Undo the action
	 */
	public void undoAction() {
		StageManager.getStage().getAssets().add(this.deleted);
		UserManager.setSelectedState(this.prevState);
		UserManager.setSelected(this.prevSelected);
	}

	/**
	 * Redo the action
	 */
	public void redoAction() {
		StageManager.getStage().getAssets().remove(this.deleted);
		UserManager.setSelectedState(UserManager.SelectedState.EMPTY);
		UserManager.setSelected(null);
	}
	
}
