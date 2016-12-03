package readySETgo.models.stageactions;

import readySETgo.managers.StageManager;
import readySETgo.managers.UserManager;
import readySETgo.models.assets.Asset;

public class PasteAction extends StageAction {

	Asset pasted;
	UserManager.SelectedState prevState;
	Asset prevSelected;
	
	public PasteAction(Asset pasted, UserManager.SelectedState prevState, Asset prevSelected) {
		this.pasted = pasted;
		this.prevState = prevState;
		this.prevSelected = prevSelected;
	}
	
	@Override
	public void undoAction() {
		StageManager.getStage().getAssets().remove(this.pasted);
		UserManager.setSelectedState(this.prevState);
		UserManager.setSelected(this.prevSelected);
	}

	public void redoAction() {
		StageManager.getStage().getAssets().add(this.pasted);
		UserManager.setSelectedState(UserManager.SelectedState.SELECTED);
		UserManager.setSelected(this.pasted);
	}
	
}
