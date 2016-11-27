package backend.models;

import backend.StageManager;
import readySETgo.User;

public class DeleteAction extends StageAction {

	Asset deleted;
	User.SelectedState prevState;
	Asset prevSelected;
	
	public DeleteAction(Asset deleted, User.SelectedState prevState, Asset prevSelected) {
		this.deleted = deleted;
		this.prevState = prevState;
		this.prevSelected = prevSelected;
	}
	
	@Override
	public void undoAction() {
		StageManager.get().getStage().getAssets().add(this.deleted);
		User.setSelectedState(this.prevState);
		User.setSelected(this.prevSelected);
	}

}
