package readySETgo.models.stageactions;

import readySETgo.managers.UserManager;
import readySETgo.models.assets.Asset;

/**
 * 
 * Represents an action where an Asset is dragged on the Stage
 * 
 * @author ReadySETgo
 * @version Beta 3
 * @since 2016-12-04
 * 
 */
public class DragAction extends StageAction {
	
	Asset dragged;
	double origX;
	double origY;
	double newX;
	double newY;
	UserManager.SelectedState origState;
	Asset prevSelected;
	
	/**
	 * Default constructor
	 * @param dragged The dragged Asset
	 * @param origX The original x pos
	 * @param origY The original y pos
	 * @param newX The new x pos
	 * @param newY The new y pos
	 * @param origState The original SelectedState
	 * @param prevSelected The originally selected Asset
	 */
	public DragAction(Asset dragged, double origX, double origY, double newX, double newY, UserManager.SelectedState origState, Asset prevSelected) {
		this.dragged = dragged;
		this.origX = origX;
		this.origY = origY;
		this.newX = newX;
		this.newY = newY;
		this.origState = origState;
		this.prevSelected = prevSelected;
	}
	
	/**
	 * Undo the action
	 */
	public void undoAction() {
		dragged.setxPos(origX);
		dragged.setyPos(origY);
		UserManager.setSelectedState(origState);
		UserManager.setSelected(prevSelected);
	}
	
	/**
	 * Redo the action
	 */
	public void redoAction() {
		dragged.setxPos(newX);
		dragged.setyPos(newY);
		UserManager.setSelectedState(UserManager.SelectedState.SELECTED);
		UserManager.setSelected(dragged);
	}
	
}
