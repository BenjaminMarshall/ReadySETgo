package readySETgo;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class MainFrame extends JFrame{

	public MainFrame(int width, int height){
		super();
		setSize(width, height);
		
		fill();
		createMenuBar();
		
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	
	private void fill(){
		JPanel panel1 = new MockColorPanel(Color.RED);
		JPanel panel2 = new MockColorPanel(Color.BLUE);
		JPanel panel3 = new MockColorPanel(Color.GREEN);
		
		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		c.gridx = 0;
		c.gridy = 0;
		c.weightx = .2;
		c.weighty = 1;	
		c.fill = c.BOTH;
		add(panel1, c);
		
		c.gridx = 1;
		c.weightx = .6;
		add(panel2, c);
		
		c.gridx = 2;
		c.weightx = .2;
		add(panel3, c);
	}
	
	private void createMenuBar(){
		JMenuBar menuBar = new JMenuBar();
		MenuItemFactory mf = new MenuItemFactory();

		JMenu programMenu = new JMenu("Program");
		programMenu.getAccessibleContext().setAccessibleDescription(
		        "Program");
		menuBar.add(programMenu);

		JMenuItem backMenuItem = new JMenuItem("Back");
		backMenuItem.getAccessibleContext().setAccessibleDescription(
		        "Go to the previous screen");
		programMenu.add(backMenuItem);
		
		JMenuItem exitMenuItem = new JMenuItem("Exit");
		exitMenuItem.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
			    System.exit(0);
			}
		});
		programMenu.add(exitMenuItem);
		
		
		JMenu fileMenu = new JMenu("File");
		fileMenu.getAccessibleContext().setAccessibleDescription("File Menu");
		menuBar.add(fileMenu);
		
		
		
		fileMenu.add(mf.createJMenuItem("Open", "Open a File"));
		
		fileMenu.add(mf.createJMenuItem("Save", "Save current file"));
		
		fileMenu.add(mf.createJMenuItem("Save as", "Save current file as..."));
		
		
		JMenu toolsMenu = new JMenu("Tools");
		toolsMenu.getAccessibleContext().setAccessibleDescription("Tools for stage");
		menuBar.add(toolsMenu);
		
		toolsMenu.add(mf.createJMenuItem("Undo","Undo last action"));
		
		toolsMenu.add(mf.createJMenuItem("Redo", "Redo last action"));
		
		toolsMenu.add(mf.createJMenuItem("Cut", "Cut something idk"));
		
		toolsMenu.add(mf.createJMenuItem("Copy", "Copy something"));
		
		toolsMenu.add(mf.createJMenuItem("Paste", "Paste whatcha copied"));
		
		toolsMenu.add(mf.createJMenuItem("Zoom", "Zoom in or out probs add stuff here"));
		
		toolsMenu.add(mf.createJMenuItem("Rotate", "Rotate stage view"));
		
		toolsMenu.add(mf.createJMenuItem("Edit Label", "Edit a text label"));
		
		
		setJMenuBar(menuBar);
	}
	
}
