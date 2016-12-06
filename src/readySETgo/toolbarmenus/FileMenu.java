package readySETgo.toolbarmenus;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

import readySETgo.dialogs.UnsavedChangesDialog;
import readySETgo.factories.MenuItemFactory;
import readySETgo.managers.FileManager;
import readySETgo.managers.PrintManager;
import readySETgo.managers.StageManager;
import readySETgo.managers.UndoManager;

/**
 * 
 * The File menu for the MainFrame
 * 
 * @author ReadySETgo
 * @version Beta 3
 * @since 2016-12-04
 * 
 */
public class FileMenu extends JMenu {
	
	/**
	 * Default constructor
	 */
	public FileMenu() {
		super("File");
        MenuItemFactory mf = new MenuItemFactory();
        
        // "new" is a keyword lmao
        JMenuItem nu = mf.createJMenuItem("New", "Create a new File");
        nu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(UndoManager.hasUnsavedChanges()) {
	        		UnsavedChangesDialog.createAndShow(UnsavedChangesDialog.Operation.NEWSTAGE);
	        	}
	        	else { StageManager.makeNewStage(); }
			}});
        this.add(nu);
        
        JMenuItem open = mf.createJMenuItem("Open", "Open a File");
        open.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FileManager.displayOpenPrompt();
			}});
        this.add(open);
        
        JMenuItem save = mf.createJMenuItem("Save", "Save current file");
        save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FileManager.attemptSaveSilently();
			}});
        this.add(save);
        
        JMenuItem saveAs = mf.createJMenuItem("Save as", "Save current file as...");
        saveAs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FileManager.displaySavePrompt();
			}});
        this.add(saveAs);
        
        this.addSeparator();
        
		JMenuItem printMenuItem = new JMenuItem("Print...");
		printMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PrintManager.print();
			}});
		this.add(printMenuItem);
		this.addSeparator();

		JMenuItem exitMenuItem = new JMenuItem("Exit");
		exitMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(UndoManager.hasUnsavedChanges()) {
	        		UnsavedChangesDialog.createAndShow(UnsavedChangesDialog.Operation.EXIT);
	        	}
	        	else { System.exit(0); }
			}});
		this.add(exitMenuItem);

	}
}