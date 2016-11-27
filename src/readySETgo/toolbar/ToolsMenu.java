package readySETgo.toolbar;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;

import backend.StageManager;
import readySETgo.MenuItemFactory;

public class ToolsMenu extends JMenu {
	public ToolsMenu() {
		super("Tools");

        this.add(MenuItemFactory.createJMenuItem("Undo","Undo last action"));
        this.add(MenuItemFactory.createJMenuItem("Redo", "Redo last action"));
        this.add(MenuItemFactory.createJMenuItem("Cut", "Cut something", new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StageManager.get().getStage().cutSelected();
			}
		}));
        this.add(MenuItemFactory.createJMenuItem("Copy", "Copy something", new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StageManager.get().getStage().copySelected();
			}
		}));
        this.add(MenuItemFactory.createJMenuItem("Paste", "Paste whatcha copied", new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StageManager.get().getStage().pasteFromClipboard();
			}
		}));
        this.add(MenuItemFactory.createJMenuItem("Zoom", "Zoom in or out probs add stuff here"));
        this.add(MenuItemFactory.createJMenuItem("Rotate", "Rotate stage view"));
        this.add(MenuItemFactory.createJMenuItem("Edit Label", "Edit a text label"));

	}

}