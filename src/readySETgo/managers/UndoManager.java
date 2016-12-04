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
 * 
 * Manager for Undo/Redo'ing user actions
 * 
 * @author ReadySETgo
 * @version Beta 3
 * @since 2016-12-04
 * 
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
		this.undoStack = new Stack<StageAction>();
		this.redoStack = new Stack<StageAction>();
	}
	
	/**
	 * Resets the manager, clearing both stacks
	 */
    public static void reset() {
    	manager.undoStack = new Stack<StageAction>();
		manager.redoStack = new Stack<StageAction>();
		manager.savedAtAction = null;
    }
    
    /**
     * Returns whether the stage has unsaved changes
     * @return Whether the stage has unsaved changes
     */
    public static boolean hasUnsavedChanges() {
    	if(manager.undoStack.isEmpty()) { return false; }
    	else if (manager.undoStack.peek() == manager.savedAtAction) { return false; }
    	return true;
    }
    
    /**
     * Registers saving the stage
     */
    public static void registerSave() {
    	if(!manager.undoStack.isEmpty()) manager.savedAtAction = manager.undoStack.peek();
    	else manager.savedAtAction = null;
    }
    
    /**
     * Stores a drag start for use in creating a Drag Action on completion
     * @param dragged The dragged Asset
     * @param origX The original x
     * @param origY The original y
     * @param origState The original selected state
     * @param prevSelected The previously selected Asset
     */
    public static void storeDragStart(Asset dragged, double origX, double origY, UserManager.SelectedState origState, Asset prevSelected) {
    	manager.dragged = dragged;
    	manager.origX = origX;
    	manager.origY = origY;
    	manager.origState = origState;
    	manager.prevSelected = prevSelected;
    	manager.redoStack.removeAllElements();
    }
 
    /**
     * Signals manager to create a Drag Action, using previously stored data
     */
    public static void storeDragEnd() {
    	manager.undoStack.push(new DragAction(manager.dragged,
    										  manager.origX,
    										  manager.origY,
    										  manager.dragged.getxPos(),
    										  manager.dragged.getyPos(),
    										  manager.origState,
    										  manager.prevSelected));
    	manager.redoStack.removeAllElements();
    }
 
    /**
     * Stores an action where an Asset is created on stage
     * @param created The created Asset
     */
    public static void storeObjectPlacement(Asset created) {
    	manager.undoStack.push(new CreationAction(created));
    	manager.redoStack.removeAllElements();
    }
    
    /**
     * Stores an action where an Asset is cut
     * @param cutted The cut Asset
     * @param origState The original SelectedState
     * @param prevSelected The originally selected Asset
     */
    public static void storeCut(Asset cutted, UserManager.SelectedState origState, Asset prevSelected) {
    	manager.undoStack.push(new CutAction(cutted, origState, prevSelected));
    	manager.redoStack.removeAllElements();
    }
    
    /**
     * Stores a paste action
     * @param pasted The pasted Asset
     * @param origState The original SelectedState
     * @param prevSelected The originally selected Asset
     */
    public static void storePaste(Asset pasted, UserManager.SelectedState origState, Asset prevSelected) {
    	manager.undoStack.push(new PasteAction(pasted, origState, prevSelected));
    	manager.redoStack.removeAllElements();
    }
    
    /**
     * Stores an Asset deletion
     * @param deleted The deleted Asset
     * @param origState The original SelectedState
     * @param prevSelected The originally selected Asset
     */
    public static void storeDelete(Asset deleted, UserManager.SelectedState origState, Asset prevSelected) {
    	manager.undoStack.push(new DeleteAction(deleted, origState, prevSelected));
    	manager.redoStack.removeAllElements();
    }
    
    /**
     * Stores a RailToggle action
     * @param panel The toggled SingleRailPanel
     */
    public static void storeRailToggle(SingleRailPanel panel) {
    	manager.undoStack.push(new RailToggleAction(panel));
    	manager.redoStack.removeAllElements();
    }
    
    /**
     * Stores a TextBox edit action
     * @param l The TextBox
     * @param oldText The old text
     * @param newText The new text
     * @param oldScale The old scale
     * @param newScale The new scale
     */
    public static void storeLabelEdit(TextBox l, String oldText, String newText, double oldScale, double newScale) {
		manager.undoStack.push(new EditLabelAction(l, oldText, newText, oldScale, newScale));
		manager.redoStack.removeAllElements();
	}
    
    /**
     * Stores a rotation action
     * @param a The rotated Asset
     * @param angle The old angle
     * @param newAngle The new angle
     */
	public static void storeRotate(Asset a, double angle, double newAngle) {
		manager.undoStack.push(new RotateAction(a, angle, newAngle));
		manager.redoStack.removeAllElements();
	}
	
	/**
	 * Pops an Asset off the undo stack, calls its undo method, and pushes it on the redo stack
	 */
    public static void undo() {
    	if(!manager.undoStack.isEmpty()) {
    		StageAction a = manager.undoStack.pop();
    		a.undoAction();
    		manager.redoStack.push(a);
    	}
    }
    
    /**
     * Pops an Asset off the redo stack, calls its redo method, and pushes it on the undo stack
     */
    public static void redo() {
    	if(!manager.redoStack.isEmpty()) {
    		StageAction a = manager.redoStack.pop();
    		a.redoAction();
    		manager.undoStack.push(a);
    	}
    }
}
