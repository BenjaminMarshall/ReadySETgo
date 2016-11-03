package readySETgo.toolbar;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

import readySETgo.MenuItemFactory;

public class FileMenu extends JMenu {
	
	public FileMenu() {
		super("File");
        MenuItemFactory mf = new MenuItemFactory();
        this.add(mf.createJMenuItem("Open", "Open a File"));
        this.add(mf.createJMenuItem("Save", "Save current file"));
        this.add(mf.createJMenuItem("Save as", "Save current file as..."));
		this.add(this.createExitMenuItem()); 
	}

	private JMenuItem createExitMenuItem() {
		JMenuItem exitMenuItem = new JMenuItem("Exit");
		exitMenuItem.addActionListener(new ActionListener() {
            								public void actionPerformed(ActionEvent e) {
                							System.exit(0);
                						}});
		return exitMenuItem;
	}
}