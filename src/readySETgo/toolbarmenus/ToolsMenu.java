package readySETgo.toolbarmenus;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;

import readySETgo.components.panels.StagePanel;
import readySETgo.factories.MenuItemFactory;
import readySETgo.managers.ComponentManager;
import readySETgo.managers.StageManager;
import readySETgo.managers.UndoManager;
import readySETgo.models.Stage;

/**
 * 
 * The Tools menu for the MainFrame
 * 
 * @author ReadySETgo
 * @version Beta 3
 * @since 2016-12-04
 * 
 */
public class ToolsMenu extends JMenu {
	
	/**
	 * Default constructor
	 */
	public ToolsMenu() {
		super("Tools");

        this.add(MenuItemFactory.createJMenuItem("Undo","Undo last action", new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UndoManager.undo();
			}
		}));
        this.add(MenuItemFactory.createJMenuItem("Redo", "Redo last action", new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UndoManager.redo();
			}
		}));
        this.add(MenuItemFactory.createJMenuItem("Cut", "Cut something", new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StageManager.getStage().cutSelected();
			}
		}));
        this.add(MenuItemFactory.createJMenuItem("Copy", "Copy something", new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StageManager.getStage().copySelected();
			}
		}));
        this.add(MenuItemFactory.createJMenuItem("Paste", "Paste whatcha copied", new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StageManager.getStage().pasteFromClipboard();
			}
		}));
        this.add(MenuItemFactory.createJMenuItem("Rotate", "Rotate stage view", new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StageManager.getStage().rotateSelectedAsset();
			}
		}));
        this.add(MenuItemFactory.createJMenuItem("Add Textbox", "Create a new textbox", new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Stage s = StageManager.getStage();
				StagePanel sp = (StagePanel) ComponentManager.getComp("StagePanel");
				s.createTextBox(sp.getWidth()/2, sp.getHeight()/2);
			}
		}));
                
	}

}