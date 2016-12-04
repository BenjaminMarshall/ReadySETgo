package readySETgo.models.stageactions;

/**
 * 
 * Represents an action where something happens to the Stage
 * 
 * @author ReadySETgo
 * @version Beta 3
 * @since 2016-12-04
 * 
 */
public abstract class StageAction {

	/**
	 * Undo the action
	 */
	public abstract void undoAction();
	
	/**
	 * Redo the action
	 */
	public abstract void redoAction();
}
