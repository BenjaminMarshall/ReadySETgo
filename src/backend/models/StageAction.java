package backend.models;

public abstract class StageAction {

	public abstract void undoAction();
	
	public abstract void redoAction();
}
