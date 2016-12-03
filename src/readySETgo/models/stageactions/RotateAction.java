package readySETgo.models.stageactions;

import readySETgo.models.assets.Asset;

public class RotateAction extends StageAction{
	private Asset rotated;
	private double oldAngle;
	private double newAngle;
	
	public RotateAction(Asset a, double oldAngle, double newAngle){
		this.rotated = a;
		this.oldAngle = oldAngle;
		this.newAngle = newAngle;
	}
	
	public void undoAction() {
		rotated.setAngle(oldAngle);
	}

	@Override
	public void redoAction() {
		rotated.setAngle(newAngle);
	}

}
