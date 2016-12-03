package readySETgo.models.stageactions;

import readySETgo.managers.StageManager;
import readySETgo.managers.UserManager;
import readySETgo.models.assets.Asset;

public class CreationAction extends StageAction {

	Asset created;
	
	public CreationAction(Asset c) {
		this.created = c;
	}
	
	public void undoAction() {
		StageManager.getStage().getAssets().remove(this.created);
		UserManager.setSelectedState(UserManager.SelectedState.EMPTY);
		UserManager.setSelected(null);
	}
	
	public void redoAction() {
		StageManager.getStage().getAssets().add(this.created);
		UserManager.setSelectedState(UserManager.SelectedState.SELECTED);
		UserManager.setSelected(this.created);
	}

}
