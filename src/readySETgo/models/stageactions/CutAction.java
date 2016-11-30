package readySETgo.models.stageactions;

import readySETgo.managers.StageManager;
import readySETgo.managers.UserManager;
import readySETgo.models.assets.Asset;

public class CutAction extends StageAction {
	
	Asset cutted;
	UserManager.SelectedState prevState;
	Asset prevSelected;
	
	public CutAction(Asset cutted, UserManager.SelectedState prevState, Asset prevSelected) {
		this.cutted = cutted;
		this.prevState = prevState;
		this.prevSelected = prevSelected;
	}
	
	@Override
	public void undoAction() {
		StageManager.get().getStage().getAssets().add(this.cutted);
		UserManager.setSelectedState(this.prevState);
		UserManager.setSelected(this.prevSelected);
	}

	public void redoAction() {
		StageManager.get().getStage().getAssets().remove(this.cutted);
		UserManager.setSelectedState(UserManager.SelectedState.EMPTY);
		UserManager.setSelected(null);
	}
	
}
