package backend.models;

import readySETgo.panels.SingleRailPanel;

public class RailToggleAction extends StageAction {

	SingleRailPanel p;
	
	public RailToggleAction(SingleRailPanel p) {
		this.p = p;
	}
	
	@Override
	public void undoAction() { p.toggleRailWithoutUndoReport();	}

	@Override
	public void redoAction() { p.toggleRailWithoutUndoReport(); }

}
