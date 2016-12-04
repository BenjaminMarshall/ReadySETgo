package readySETgo.models.stageactions;

import readySETgo.managers.StageManager;
import readySETgo.managers.UserManager;
import readySETgo.models.assets.Asset;

/**
 * 
 * Represents an action where an Asset is cut from Stage
 * 
 * @author ReadySETgo
 * @version Beta 3
 * @since 2016-12-04
 * 
 */
public class CutAction extends StageAction {
	
	Asset cutted;
	UserManager.SelectedState prevState;
	Asset prevSelected;
	
	/**
	 * Default constructor
	 * @param cutted The cut Asset
	 * @param prevState The previous SelectedState
	 * @param prevSelected The previously selected Asset
	 */
	public CutAction(Asset cutted, UserManager.SelectedState prevState, Asset prevSelected) {
		this.cutted = cutted;
		this.prevState = prevState;
		this.prevSelected = prevSelected;
	}
	
	/**
	 * Undo the action
	 */
	public void undoAction() {
		StageManager.getStage().getAssets().add(this.cutted);
		UserManager.setSelectedState(this.prevState);
		UserManager.setSelected(this.prevSelected);
	}

	/**
	 * Redo the action
	 */
	public void redoAction() {
		StageManager.getStage().getAssets().remove(this.cutted);
		UserManager.setSelectedState(UserManager.SelectedState.EMPTY);
		UserManager.setSelected(null);
	}
	
}
