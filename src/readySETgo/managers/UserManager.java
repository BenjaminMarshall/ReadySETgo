package readySETgo.managers;

import java.awt.event.MouseEvent;

import readySETgo.models.assets.Asset;

public class UserManager {
	public static enum SelectedState {
		EMPTY, SELECTED, DRAGGING;
	}
	public static enum MouseState {
		UP, DOWN;
	}
	
	private static SelectedState state = SelectedState.EMPTY;
	private static Asset selected;
	private static Asset clipboard;
	private static double dragOffsetX;
	private static double dragOffsetY;

	public static MouseState getMouseState(MouseEvent e){
		int mask = MouseEvent.BUTTON1_DOWN_MASK;
		if((e.getModifiersEx() & mask) != 0){
			return MouseState.DOWN;
		} else {
			return MouseState.UP;
		}
	}
	
	public static SelectedState getSelectedState(){
		return state;
	}
	
	public static void setSelectedState(SelectedState s){
		state = s;
	}
	
	public static Asset getSelected(){
		return selected;
	}
	
	public static void setSelected(Asset a){
		selected = a;
	}

	public static Asset getClipboard() {
		return clipboard;
	}

	public static void setClipboard(Asset clipboard) {
		UserManager.clipboard = clipboard;
	}
	
	public static double getDragOffsetX(){
		return dragOffsetX;
	}
	
	public static void setDragOffsetX(double x){
		dragOffsetX = x;
	}
	
	public static double getDragOffsetY(){
		return dragOffsetY;
	}
	
	public static void setDragOffsetY(double y){
		dragOffsetY = y;
	}
	
}
