package readySETgo.models.stageactions;

import readySETgo.managers.UserManager;
import readySETgo.models.assets.Asset;

public class DragAction extends StageAction {
	
	Asset dragged;
	double origX;
	double origY;
	double newX;
	double newY;
	UserManager.SelectedState origState;
	Asset prevSelected;
	
	public DragAction(Asset dragged, double origX, double origY, double newX, double newY, UserManager.SelectedState origState, Asset prevSelected) {
		this.dragged = dragged;
		this.origX = origX;
		this.origY = origY;
		this.newX = newX;
		this.newY = newY;
		this.origState = origState;
		this.prevSelected = prevSelected;
	}
	
	public void undoAction() {
		dragged.setxPos(origX);
		dragged.setyPos(origY);
		UserManager.setSelectedState(origState);
		UserManager.setSelected(prevSelected);
	}
	
	public void redoAction() {
		dragged.setxPos(newX);
		dragged.setyPos(newY);
		UserManager.setSelectedState(UserManager.SelectedState.SELECTED);
		UserManager.setSelected(dragged);
	}
	
}
