package readySETgo.managers;

import java.awt.event.MouseEvent;

import readySETgo.models.assets.Asset;

/**
 * 
 * Manager class for dealing with user selection and clipboard
 * 
 * @author ReadySETgo
 * @version Beta 3
 * @since 2016-12-04
 * 
 */
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

	/**
	 * Returns mouse state given MouseEvent
	 * @param e The given MouseEvent
	 * @return The MouseState
	 */
	public static MouseState getMouseState(MouseEvent e){
		int mask = MouseEvent.BUTTON1_DOWN_MASK;
		if((e.getModifiersEx() & mask) != 0){
			return MouseState.DOWN;
		} else {
			return MouseState.UP;
		}
	}
	
	/**
	 * Gets SelectedState
	 * @return The SelectedState
	 */
	public static SelectedState getSelectedState(){
		return state;
	}
	
	/**
	 * Sets the SelectedState
	 * @param s The SelectedState
	 */
	public static void setSelectedState(SelectedState s){
		state = s;
	}
	
	/**
	 * Gets the selected Asset
	 * @return The selected Asset
	 */
	public static Asset getSelected(){
		return selected;
	}
	
	/**
	 * Sets the selected Asset
	 * @param a The Asset to select
	 */
	public static void setSelected(Asset a){
		selected = a;
	}

	/**
	 * Gets the Asset in the clipboard
	 * @return
	 */
	public static Asset getClipboard() {
		return clipboard;
	}

	/**
	 * Sets the Asset in the clipboard
	 * @param clipboard
	 */
	public static void setClipboard(Asset clipboard) {
		UserManager.clipboard = clipboard;
	}
	
	/**
	 * Gets the x drag offset
	 * @return The x drag offset
	 */
	public static double getDragOffsetX(){
		return dragOffsetX;
	}
	
	/**
	 * Sets the x drag offset
	 * @param x The x drag offset
	 */
	public static void setDragOffsetX(double x){
		dragOffsetX = x;
	}
	
	/**
	 * Gets the y drag offset
	 * @return The y drag offset
	 */
	public static double getDragOffsetY(){
		return dragOffsetY;
	}
	
	/**
	 * Sets the y drag offset
	 * @param y The y drag offset
	 */
	public static void setDragOffsetY(double y){
		dragOffsetY = y;
	}
	
}
