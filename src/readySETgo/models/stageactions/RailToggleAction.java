package readySETgo.models.stageactions;

import readySETgo.components.panels.SingleRailPanel;

/**
 * 
 * Represents an action where one of the Stage's FlyRails is toggled
 * 
 * @author ReadySETgo
 * @version Beta 3
 * @since 2016-12-04
 * 
 */
public class RailToggleAction extends StageAction {

	SingleRailPanel p;
	
	/**
	 * Default constructor
	 * @param p The SingleRailPanel containing the toggled FlyRail
	 */
	public RailToggleAction(SingleRailPanel p) {
		this.p = p;
	}
	
	/**
	 * Undo the action
	 */
	public void undoAction() { p.toggleRailWithoutUndoReport();	}

	/**
	 * Redo the action
	 */
	public void redoAction() { p.toggleRailWithoutUndoReport(); }

}
