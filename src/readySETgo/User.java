package readySETgo;

import java.awt.event.MouseEvent;

import backend.models.Asset;

public class User {
	public static enum DraggingState {
		EMPTY, SELECTED, DRAGGING;
	}
	public static enum MouseState {
		UP, DOWN;
	}
	
	private static DraggingState state;
	private static Asset dragging;
	private static Asset clipboard;

	public static MouseState getMouseState(MouseEvent e){
		int mask = MouseEvent.BUTTON1_DOWN_MASK;
		if((e.getModifiersEx() & mask) != 0){
			return MouseState.DOWN;
		} else {
			return MouseState.UP;
		}
	}
	
	public static DraggingState getDraggingState(){
		return state;
	}
	
	public static void setDraggingState(DraggingState s){
		state = s;
	}
	
	public static Asset getDragging(){
		return dragging;
	}
	
	public static void setDragging(Asset a){
		dragging = a;
	}

	public static Asset getClipboard() {
		return clipboard;
	}

	public static void setClipboard(Asset clipboard) {
		User.clipboard = clipboard;
	}
	
}
