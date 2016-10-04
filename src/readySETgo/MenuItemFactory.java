package readySETgo;

import java.awt.event.ActionListener;

import javax.swing.JMenuItem;

public class MenuItemFactory {

	public JMenuItem createJMenuItem(String name, String accessibilityString){
		JMenuItem menuItem = new JMenuItem(name);
		menuItem.getAccessibleContext().setAccessibleDescription(accessibilityString);
		return menuItem;
	}
	
	public JMenuItem createJMenuItem(String name, String accessibilityString, ActionListener a){
		JMenuItem menuItem = createJMenuItem(name, accessibilityString);
		menuItem.addActionListener(a);
		return menuItem;
	}
}
