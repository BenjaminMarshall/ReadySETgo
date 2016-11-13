package readySETgo;

import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;

import readySETgo.panels.ContainerPanel;
import readySETgo.toolbar.FileMenu;
import readySETgo.toolbar.ToolsMenu;


public class MainFrame extends JFrame{
	private ContainerPanel container;
	
    public MainFrame(int width, int height){
        super();
        setSize(width, height);
        
        fill();
        createMenuBar();
        
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
    
    private void fill(){
    	container = new ContainerPanel();
    	
    	add(container);
    }
    
    private void createMenuBar(){
        // Create MenuBar
        JMenuBar menuBar = new JMenuBar();

        // Create menus
        JMenu fileMenu = new FileMenu();
        JMenu toolsMenu = new ToolsMenu();

        // Fill Menus   
        menuBar.add(fileMenu);
        menuBar.add(toolsMenu);

        // Register menu bar
        setJMenuBar(menuBar);
    }

	public void registerMouseListener(MouseListener m) {
		container.registerMouseListener(m);
		
	}   
}
