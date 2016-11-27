package backend;

import java.util.Stack;

import backend.models.Asset;
import backend.models.CreationAction;
import backend.models.CutAction;
import backend.models.DeleteAction;
import backend.models.DragAction;
import backend.models.PasteAction;
import backend.models.StageAction;
import readySETgo.User;

/**
 * RSG
 */
public class UndoManager {

	private Stack<StageAction> undoStack;
	private Stack<StageAction> redoStack;
		
	private Asset dragged;
	private double origX;
	private double origY;
	private User.SelectedState origState;
	private Asset prevSelected;
	
	private static UndoManager manager = new UndoManager();

	private UndoManager() {
		undoStack = new Stack<StageAction>();
		redoStack = new Stack<StageAction>();
	}
	
    public static UndoManager get() { return manager; }
    
    public void storeDragStart(Asset dragged, double origX, double origY, User.SelectedState origState, Asset prevSelected) {
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
    
    public void storeCut(Asset cutted, User.SelectedState origState, Asset prevSelected) {
    	undoStack.push(new CutAction(cutted, origState, prevSelected));
    	redoStack.removeAllElements();
    }
    
    public void storePaste(Asset pasted, User.SelectedState origState, Asset prevSelected) {
    	undoStack.push(new PasteAction(pasted, origState, prevSelected));
    	redoStack.removeAllElements();
    }
    
    public void storeDelete(Asset deleted, User.SelectedState origState, Asset prevSelected) {
    	undoStack.push(new DeleteAction(deleted, origState, prevSelected));
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

