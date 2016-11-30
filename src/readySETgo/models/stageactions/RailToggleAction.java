package readySETgo.models.stageactions;

import readySETgo.components.panels.SingleRailPanel;

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
