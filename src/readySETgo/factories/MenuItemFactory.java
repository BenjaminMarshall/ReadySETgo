package readySETgo.factories;

import java.awt.event.ActionListener;

import javax.swing.JMenuItem;

/**
 * 
 * Factory for creating JMenuItems
 * 
 * @author ReadySETgo
 * @version Beta 3
 * @since 2016-12-04
 * 
 */
public class MenuItemFactory {

	/**
	 * 
	 * Creates JMenuItem
	 * 
	 * @param name Item name
	 * @param accessibilityString Item accessibility string
	 * @return Created JMenuItem
	 */
	public static JMenuItem createJMenuItem(String name, String accessibilityString){
		JMenuItem menuItem = new JMenuItem(name);
		menuItem.getAccessibleContext().setAccessibleDescription(accessibilityString);
		return menuItem;
	}

	/**
	 * 
	 * Creates JMenuItem
	 * 
	 * @param name Item name
	 * @param accessibilityString Item accessibility string
	 * @param a The ActionListener for the JMenuItem
	 * @return Created JMenuItem
	 */
	public static JMenuItem createJMenuItem(String name, String accessibilityString, ActionListener a){
		JMenuItem menuItem = createJMenuItem(name, accessibilityString);
		menuItem.addActionListener(a);
		return menuItem;
	}
}
