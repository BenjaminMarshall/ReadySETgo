package backend.models;

import readySETgo.User;

public class DragAction extends StageAction {
	
	Asset dragged;
	double origX;
	double origY;
	double newX;
	double newY;
	User.SelectedState origState;
	Asset prevSelected;
	
	public DragAction(Asset dragged, double origX, double origY, double newX, double newY, User.SelectedState origState, Asset prevSelected) {
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
		User.setSelectedState(origState);
		User.setSelected(prevSelected);
	}
	
	public void redoAction() {
		dragged.setxPos(newX);
		dragged.setyPos(newY);
		User.setSelectedState(User.SelectedState.SELECTED);
		User.setSelected(dragged);
	}
	
}
