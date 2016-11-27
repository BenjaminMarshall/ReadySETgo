package backend.models;

import backend.StageManager;
import readySETgo.User;

public class CutAction extends StageAction {
	
	Asset cutted;
	User.SelectedState prevState;
	Asset prevSelected;
	
	public CutAction(Asset cutted, User.SelectedState prevState, Asset prevSelected) {
		this.cutted = cutted;
		this.prevState = prevState;
		this.prevSelected = prevSelected;
	}
	
	@Override
	public void undoAction() {
		StageManager.get().getStage().getAssets().add(this.cutted);
		User.setSelectedState(this.prevState);
		User.setSelected(this.prevSelected);
	}

	public void redoAction() {
		StageManager.get().getStage().getAssets().remove(this.cutted);
		User.setSelectedState(User.SelectedState.EMPTY);
		User.setSelected(null);
	}
	
}
