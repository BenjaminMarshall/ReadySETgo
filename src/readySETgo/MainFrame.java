package readySETgo;

import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;

import backend.StageManager;
import backend.models.Stage;
import readySETgo.panels.ContainerPanel;
import readySETgo.toolbar.FileMenu;
import readySETgo.toolbar.ToolsMenu;


public class MainFrame extends JFrame {
    private ContainerPanel container;

    public MainFrame(int width, int height) {
        super();
        setSize(width, height);
        try {
            this.setIconImage(ImageIO.read(new File("res/logo.png")));
        } catch (IOException e) {
        }
        this.setTitle("Stage Plan");
        fill();
        createMenuBar();

        bindKeyboardShortcuts();
        
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void fill() {
        container = new ContainerPanel();

        add(container);
    }

    private void createMenuBar() {
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

    
    private void bindKeyboardShortcuts() {
    	// Keyboard shortcut handling code credit to Daniel Kullmann
    	// http://stackoverflow.com/a/8485873
    	HashMap<KeyStroke, Action> actionMap = new HashMap<KeyStroke, Action>();

    	// Ctrl X => Cut
    	KeyStroke ctrlX = KeyStroke.getKeyStroke(KeyEvent.VK_X, KeyEvent.CTRL_DOWN_MASK);
    	actionMap.put(ctrlX, new AbstractAction("cut") {
    		@Override
    		public void actionPerformed(ActionEvent e) {
    			Stage s = StageManager.get().getStage();
    			s.cutSelected();
    		}
    	});

    	// Ctrl C => Copy
    	KeyStroke ctrlC = KeyStroke.getKeyStroke(KeyEvent.VK_C, KeyEvent.CTRL_DOWN_MASK);
    	actionMap.put(ctrlC, new AbstractAction("copy") {
    		@Override
    		public void actionPerformed(ActionEvent e) {
    			Stage s = StageManager.get().getStage();
    			s.copySelected();
    		}
    	});
    	
    	// Ctrl V => Paste
    	KeyStroke ctrlV = KeyStroke.getKeyStroke(KeyEvent.VK_V, KeyEvent.CTRL_DOWN_MASK);
    	actionMap.put(ctrlV, new AbstractAction("paste") {
    		@Override
    		public void actionPerformed(ActionEvent e) {
    			Stage s = StageManager.get().getStage();
    			s.pasteFromClipboard();
    		}
    	});
    	
    	KeyboardFocusManager kfm = KeyboardFocusManager.getCurrentKeyboardFocusManager();
    	kfm.addKeyEventDispatcher( new KeyEventDispatcher() {
	    	@Override
	    	public boolean dispatchKeyEvent(KeyEvent e) {
	    		KeyStroke keyStroke = KeyStroke.getKeyStrokeForEvent(e);
	    		if ( actionMap.containsKey(keyStroke) ) {
	    			final Action a = actionMap.get(keyStroke);
	    			final ActionEvent ae = new ActionEvent(e.getSource(), e.getID(), null );
	    			SwingUtilities.invokeLater( new Runnable() {
				    	@Override
				    	public void run() {
				    		a.actionPerformed(ae);
				    	}
	    			}); 
	    			return true;
		    	}
	    		return false;
	    	}
	    });    	
    }
    
}
