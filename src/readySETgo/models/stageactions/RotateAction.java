package readySETgo.models.stageactions;

import readySETgo.models.assets.Asset;

/**
 * 
 * Represents an action where an Asset on Stage is rotated
 * 
 * @author ReadySETgo
 * @version Beta 3
 * @since 2016-12-04
 * 
 */
public class RotateAction extends StageAction{
	private Asset rotated;
	private double oldAngle;
	private double newAngle;
	
	/**
	 * Default constructor
	 * @param a The rotated Asset
	 * @param oldAngle The old angle
	 * @param newAngle The new angle
	 */
	public RotateAction(Asset a, double oldAngle, double newAngle){
		this.rotated = a;
		this.oldAngle = oldAngle;
		this.newAngle = newAngle;
	}
	
	/**
	 * Undo the action
	 */
	public void undoAction() {
		rotated.setAngle(oldAngle);
	}

	/**
	 * Redo the action
	 */
	public void redoAction() {
		rotated.setAngle(newAngle);
	}

}
