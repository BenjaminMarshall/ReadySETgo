package readySETgo.toolbarmenus;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;

import readySETgo.MenuItemFactory;
import readySETgo.components.panels.StagePanel;
import readySETgo.managers.ComponentManager;
import readySETgo.managers.StageManager;
import readySETgo.managers.UndoManager;
import readySETgo.models.Stage;

public class ToolsMenu extends JMenu {
	public ToolsMenu() {
		super("Tools");

        this.add(MenuItemFactory.createJMenuItem("Undo","Undo last action", new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UndoManager.get().undo();
			}
		}));
        this.add(MenuItemFactory.createJMenuItem("Redo", "Redo last action", new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UndoManager.get().redo();
			}
		}));
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
        
        // TODO - Link to rotation implementation
        this.add(MenuItemFactory.createJMenuItem("Rotate", "Rotate stage view"));
        
        this.add(MenuItemFactory.createJMenuItem("Add Textbox", "Create a new textbox", new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Stage s = StageManager.get().getStage();
				StagePanel sp = (StagePanel) ComponentManager.getComp("StagePanel");
				s.createTextBox(sp.getWidth()/2, sp.getHeight()/2);
			}
		}));
                
	}

}