package readySETgo.toolbar;

import javax.swing.JMenu;

import readySETgo.MenuItemFactory;

public class ToolsMenu extends JMenu {
	public ToolsMenu() {
		super("Tools");

        this.add(MenuItemFactory.createJMenuItem("Undo","Undo last action"));
        this.add(MenuItemFactory.createJMenuItem("Redo", "Redo last action"));
        this.add(MenuItemFactory.createJMenuItem("Cut", "Cut something idk"));
        this.add(MenuItemFactory.createJMenuItem("Copy", "Copy something"));
        this.add(MenuItemFactory.createJMenuItem("Paste", "Paste whatcha copied"));
        this.add(MenuItemFactory.createJMenuItem("Zoom", "Zoom in or out probs add stuff here"));
        this.add(MenuItemFactory.createJMenuItem("Rotate", "Rotate stage view"));
        this.add(MenuItemFactory.createJMenuItem("Edit Label", "Edit a text label"));

	}

}