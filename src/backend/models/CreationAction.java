package backend.models;

import backend.StageManager;
import readySETgo.User;

public class CreationAction extends StageAction {

	Asset created;
	
	public CreationAction(Asset c) {
		this.created = c;
	}
	
	public void undoAction() {
		StageManager.get().getStage().getAssets().remove(this.created);
		User.setSelectedState(User.SelectedState.EMPTY);
		User.setSelected(null);
	}
	
	public void redoAction() {
		StageManager.get().getStage().getAssets().add(this.created);
		User.setSelectedState(User.SelectedState.SELECTED);
		User.setSelected(this.created);
	}

}
