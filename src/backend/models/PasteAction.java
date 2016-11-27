package backend.models;

import backend.StageManager;
import readySETgo.User;

public class PasteAction extends StageAction {

	Asset pasted;
	User.SelectedState prevState;
	Asset prevSelected;
	
	public PasteAction(Asset pasted, User.SelectedState prevState, Asset prevSelected) {
		this.pasted = pasted;
		this.prevState = prevState;
		this.prevSelected = prevSelected;
	}
	
	@Override
	public void undoAction() {
		StageManager.get().getStage().getAssets().remove(this.pasted);
		User.setSelectedState(this.prevState);
		User.setSelected(this.prevSelected);
	}

}
