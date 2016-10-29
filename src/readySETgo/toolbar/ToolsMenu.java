package toolbar;

import readySETgo.MenuItemFactory;

class ToolsMenu extends Jmenu {
	
	public ToolsMenu() {
		super("Tools");

        toolsMenu.add(mf.createJMenuItem("Undo","Undo last action"));
        toolsMenu.add(mf.createJMenuItem("Redo", "Redo last action"));
        toolsMenu.add(mf.createJMenuItem("Cut", "Cut something idk"));
        toolsMenu.add(mf.createJMenuItem("Copy", "Copy something"));
        toolsMenu.add(mf.createJMenuItem("Paste", "Paste whatcha copied"));
        toolsMenu.add(mf.createJMenuItem("Zoom", "Zoom in or out probs add stuff here"));
        toolsMenu.add(mf.createJMenuItem("Rotate", "Rotate stage view"));
        toolsMenu.add(mf.createJMenuItem("Edit Label", "Edit a text label"));

	}

}