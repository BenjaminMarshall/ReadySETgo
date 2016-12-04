package readySETgo.managers;

import java.util.Stack;

import readySETgo.components.panels.SingleRailPanel;
import readySETgo.models.assets.Asset;
import readySETgo.models.assets.TextBox;
import readySETgo.models.stageactions.CreationAction;
import readySETgo.models.stageactions.CutAction;
import readySETgo.models.stageactions.DeleteAction;
import readySETgo.models.stageactions.DragAction;
import readySETgo.models.stageactions.EditLabelAction;
import readySETgo.models.stageactions.PasteAction;
import readySETgo.models.stageactions.RailToggleAction;
import readySETgo.models.stageactions.RotateAction;
import readySETgo.models.stageactions.StageAction;

/**
 * RSG
 */
public class UndoManager {

	private Stack<StageAction> undoStack;
	private Stack<StageAction> redoStack;
		
	private Asset dragged;
	private double origX;
	private double origY;
	private UserManager.SelectedState origState;
	private Asset prevSelected;
	
	private StageAction savedAtAction;
	
	private static UndoManager manager = new UndoManager();

	private UndoManager() {
		undoStack = new Stack<StageAction>();
		redoStack = new Stack<StageAction>();
	}
	
    public static UndoManager get() { return manager; }
    
    public void reset() {
    	undoStack = new Stack<StageAction>();
		redoStack = new Stack<StageAction>();
		savedAtAction = null;
    }
    
    public boolean hasUnsavedChanges() {
    	if(undoStack.isEmpty()) { return false; }
    	else if (undoStack.peek() == savedAtAction) { return false; }
    	return true;
    }
    
    public void registerSave() {
    	if(!undoStack.isEmpty()) savedAtAction = undoStack.peek();
    	else savedAtAction = null;
    }
    
    public void storeDragStart(Asset dragged, double origX, double origY, UserManager.SelectedState origState, Asset prevSelected) {
    	this.dragged = dragged;
    	this.origX = origX;
    	this.origY = origY;
    	this.origState = origState;
    	this.prevSelected = prevSelected;
    	redoStack.removeAllElements();
    }
 
    public void storeDragEnd() {
    	undoStack.push(new DragAction(dragged, origX, origY, dragged.getxPos(), dragged.getyPos(), origState, prevSelected));
    	redoStack.removeAllElements();
    }
 
    public void storeObjectPlacement(Asset created) {
    	undoStack.push(new CreationAction(created));
    	redoStack.removeAllElements();
    }
    
    public void storeCut(Asset cutted, UserManager.SelectedState origState, Asset prevSelected) {
    	undoStack.push(new CutAction(cutted, origState, prevSelected));
    	redoStack.removeAllElements();
    }
    
    public void storePaste(Asset pasted, UserManager.SelectedState origState, Asset prevSelected) {
    	undoStack.push(new PasteAction(pasted, origState, prevSelected));
    	redoStack.removeAllElements();
    }
    
    public void storeDelete(Asset deleted, UserManager.SelectedState origState, Asset prevSelected) {
    	undoStack.push(new DeleteAction(deleted, origState, prevSelected));
    	redoStack.removeAllElements();
    }
    
    public void storeRailToggle(SingleRailPanel panel) {
    	undoStack.push(new RailToggleAction(panel));
    	redoStack.removeAllElements();
    }
    
    public void storeLabelEdit(TextBox l, String oldText, String newText, double oldScale, double newScale) {
		undoStack.push(new EditLabelAction(l, oldText, newText, oldScale, newScale));
		redoStack.removeAllElements();
	}
    
	public void storeRotate(Asset a, double angle, double newAngle) {
		undoStack.push(new RotateAction(a, angle, newAngle));
		redoStack.removeAllElements();
	}
	
    public void undo() {
    	if(!undoStack.isEmpty()) {
    		StageAction a = undoStack.pop();
    		a.undoAction();
    		redoStack.push(a);
    	}
    }
    
    public void redo() {
    	if(!redoStack.isEmpty()) {
    		StageAction a = redoStack.pop();
    		a.redoAction();
    		undoStack.push(a);
    	}
    }



	
    
}

