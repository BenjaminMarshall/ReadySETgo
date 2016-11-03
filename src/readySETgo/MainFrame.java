package readySETgo;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;

<<<<<<< HEAD
import panels.StagePanel;

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
		JPanel panel2 = new StagePanel();
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
=======
import readySETgo.toolbar.FileMenu;
import readySETgo.toolbar.ToolsMenu;


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
        // Create MenuBar
        JMenuBar menuBar = new JMenuBar();

        // Create menus
        JMenu fileMenu = new FileMenu();
        JMenu toolsMenu = new ToolsMenu();
>>>>>>> jason-develop

        // Fill Menus   
        menuBar.add(fileMenu);
        menuBar.add(toolsMenu);

        // Register menu bar
        setJMenuBar(menuBar);
    }   
}
