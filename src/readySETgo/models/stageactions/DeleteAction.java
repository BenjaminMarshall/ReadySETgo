package readySETgo.models.stageactions;

import readySETgo.managers.StageManager;
import readySETgo.managers.UserManager;
import readySETgo.models.assets.Asset;

public class DeleteAction extends StageAction {

	Asset deleted;
	UserManager.SelectedState prevState;
	Asset prevSelected;
	
	public DeleteAction(Asset deleted, UserManager.SelectedState prevState, Asset prevSelected) {
		this.deleted = deleted;
		this.prevState = prevState;
		this.prevSelected = prevSelected;
	}
	
	@Override
	public void undoAction() {
		StageManager.get().getStage().getAssets().add(this.deleted);
		UserManager.setSelectedState(this.prevState);
		UserManager.setSelected(this.prevSelected);
	}

	public void redoAction() {
		StageManager.get().getStage().getAssets().remove(this.deleted);
		UserManager.setSelectedState(UserManager.SelectedState.EMPTY);
		UserManager.setSelected(null);
	}
	
}
