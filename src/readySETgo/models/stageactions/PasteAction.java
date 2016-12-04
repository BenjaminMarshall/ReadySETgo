package readySETgo.models.stageactions;

import readySETgo.managers.StageManager;
import readySETgo.managers.UserManager;
import readySETgo.models.assets.Asset;

/**
 * 
 * Represents an action where an Asset is pasted onto the Stage
 * 
 * @author ReadySETgo
 * @version Beta 3
 * @since 2016-12-04
 * 
 */
public class PasteAction extends StageAction {

	Asset pasted;
	UserManager.SelectedState prevState;
	Asset prevSelected;
	
	/**
	 * Default constructor
	 * @param pasted The pasted Asset
	 * @param prevState The original SelectedState
	 * @param prevSelected The originally selected Asset
	 */
	public PasteAction(Asset pasted, UserManager.SelectedState prevState, Asset prevSelected) {
		this.pasted = pasted;
		this.prevState = prevState;
		this.prevSelected = prevSelected;
	}
	
	/**
	 * Undo the action
	 */
	public void undoAction() {
		StageManager.getStage().getAssets().remove(this.pasted);
		UserManager.setSelectedState(this.prevState);
		UserManager.setSelected(this.prevSelected);
	}

	/**
	 * Redo the action
	 */
	public void redoAction() {
		StageManager.getStage().getAssets().add(this.pasted);
		UserManager.setSelectedState(UserManager.SelectedState.SELECTED);
		UserManager.setSelected(this.pasted);
	}
	
}
