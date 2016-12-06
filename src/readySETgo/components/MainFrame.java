package readySETgo.components;

import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
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

import readySETgo.components.panels.ContainerPanel;
import readySETgo.dialogs.UnsavedChangesDialog;
import readySETgo.managers.ComponentManager;
import readySETgo.managers.FileManager;
import readySETgo.managers.PrintManager;
import readySETgo.managers.StageManager;
import readySETgo.managers.UndoManager;
import readySETgo.models.Stage;
import readySETgo.toolbarmenus.FileMenu;
import readySETgo.toolbarmenus.ToolsMenu;

/**
 * 
 * MainFrame class for Stage Plan
 * 
 * Initializes subpanels, registers self with the 
 * ComponentManager, binds application wide keyboard
 * shortcuts, and creates app toolbars and close action
 * 
 * @author ReadySETgo
 * @version Beta 3
 * @since 2016-12-04
 * 
 */
public class MainFrame extends JFrame {

	private ContainerPanel container;
    // Switched to false whenever a dialog is open
	private boolean areKbShortcutsEnabled;
    
	/**
	 * MainFrame Constructor
	 * 
	 * Initializes subpanels, registers self with the 
	 * ComponentManager, binds application wide keyboard
	 * shortcuts, and creates app toolbars and close action
	 * 
	 * @param width Desired frame width
	 * @param height Desired frame height
	 */
    public MainFrame(int width, int height) {
        super();
        ComponentManager.registerComp("MainFrame", this);
        this.areKbShortcutsEnabled = true;
        this.setSize(width, height);
        try { this.setIconImage(ImageIO.read(new File("res/logo.png"))); }
        catch (IOException e) { /* TODO - Error Logging */ }
        this.setTitle("Stage Plan");
        // Create/add subPanel
        this.fill();
        // Create/add file & tools menus
        this.createMenuBar();
        this.bindKeyboardShortcuts();
        // Enables save on close option, disables KB shortcuts when dialogs are open
        this.addWindowListener(new MainFrameWindowAdapter());
        // For our windowListener to work properly we must disable the default close action
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    /**
     * Creates subPanel and adds it to frame
     */
    private void fill() {
        container = new ContainerPanel();
        this.add(container);
    }

    /**
     * Creates and adds file and tools menus
     */
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
        this.setJMenuBar(menuBar);
    }

    /**
     * Creates and binds keyboard shortcuts to main frame
     *
     * Thanks to Daniel Kullman whose solution on StackOverflow
     * we adapted to fit our needs
     * http://stackoverflow.com/a/8485873
     */
    private void bindKeyboardShortcuts() {

    	HashMap<KeyStroke, Action> actionMap = new HashMap<KeyStroke, Action>();

    	// Ctrl X => Cut
    	KeyStroke ctrlX = KeyStroke.getKeyStroke(KeyEvent.VK_X, KeyEvent.CTRL_DOWN_MASK);
    	actionMap.put(ctrlX, new AbstractAction("cut") {
    		@Override
    		public void actionPerformed(ActionEvent e) {
    			Stage s = StageManager.getStage();
    			s.cutSelected();
    		}
    	});

    	// Ctrl C => Copy
    	KeyStroke ctrlC = KeyStroke.getKeyStroke(KeyEvent.VK_C, KeyEvent.CTRL_DOWN_MASK);
    	actionMap.put(ctrlC, new AbstractAction("copy") {
    		@Override
    		public void actionPerformed(ActionEvent e) {
    			Stage s = StageManager.getStage();
    			s.copySelected();
    		}
    	});
    	
    	// Ctrl V => Paste
    	KeyStroke ctrlV = KeyStroke.getKeyStroke(KeyEvent.VK_V, KeyEvent.CTRL_DOWN_MASK);
    	actionMap.put(ctrlV, new AbstractAction("paste") {
    		@Override
    		public void actionPerformed(ActionEvent e) {
    			Stage s = StageManager.getStage();
    			s.pasteFromClipboard();
    		}
    	});
    	
    	// Ctrl Z => Undo
    	KeyStroke ctrlZ = KeyStroke.getKeyStroke(KeyEvent.VK_Z, KeyEvent.CTRL_DOWN_MASK);
    	actionMap.put(ctrlZ, new AbstractAction("undo") {
    		@Override
    		public void actionPerformed(ActionEvent e) {
    			UndoManager.undo();
    		}
    	});

    	// Ctrl Y => Redo
    	KeyStroke ctrlY = KeyStroke.getKeyStroke(KeyEvent.VK_Y, KeyEvent.CTRL_DOWN_MASK);
    	actionMap.put(ctrlY, new AbstractAction("redo") {
    		@Override
    		public void actionPerformed(ActionEvent e) {
    			UndoManager.redo();
    		}
    	});
    	
    	// Ctrl P => Print
    	KeyStroke ctrlP = KeyStroke.getKeyStroke(KeyEvent.VK_P, KeyEvent.CTRL_DOWN_MASK);
    	actionMap.put(ctrlP, new AbstractAction("print") {
    		@Override
    		public void actionPerformed(ActionEvent e) {
    			PrintManager.print();
    		}
    	});
    	
    	// Ctrl S => Save
    	KeyStroke ctrlS = KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_DOWN_MASK);
    	actionMap.put(ctrlS, new AbstractAction("save") {
    		@Override
    		public void actionPerformed(ActionEvent e) {
    			FileManager.attemptSaveSilently();
    		}
    	});

    	// Ctrl O => Open
    	KeyStroke ctrlO = KeyStroke.getKeyStroke(KeyEvent.VK_O, KeyEvent.CTRL_DOWN_MASK);
    	actionMap.put(ctrlO, new AbstractAction("open") {
    		@Override
    		public void actionPerformed(ActionEvent e) {
    			FileManager.displayOpenPrompt();
    		}
    	});

    	// Ctrl R => Rotate
    	KeyStroke ctrlR = KeyStroke.getKeyStroke(KeyEvent.VK_R, KeyEvent.CTRL_DOWN_MASK);
    	actionMap.put(ctrlR, new AbstractAction("rotate") {
    		@Override
    		public void actionPerformed(ActionEvent e) {
    			StageManager.getStage().rotateSelectedAsset();
    		}
    	});
    	
    	// Ctrl N => New Stage
    	KeyStroke ctrlN = KeyStroke.getKeyStroke(KeyEvent.VK_N, KeyEvent.CTRL_DOWN_MASK);
    	actionMap.put(ctrlN, new AbstractAction("new") {
    		@Override
    		public void actionPerformed(ActionEvent e) {
    			if(UndoManager.hasUnsavedChanges()) {
    	    		UnsavedChangesDialog.createAndShow(UnsavedChangesDialog.Operation.NEWSTAGE);
    	    	}
    	    	else { StageManager.makeNewStage(); }
    		}
    	});
    	
    	// Delete Key => Delete Stage Object
    	KeyStroke delete = KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, 0);
    	actionMap.put(delete, new AbstractAction("delete") {
    		@Override
    		public void actionPerformed(ActionEvent e) {
    			StageManager.getStage().deleteSelected();
    		}
    	});
    	
    	KeyboardFocusManager kfm = KeyboardFocusManager.getCurrentKeyboardFocusManager();
    	kfm.addKeyEventDispatcher(new KeyEventDispatcher() {
	    	@Override
	    	public boolean dispatchKeyEvent(KeyEvent e) {
	    		KeyStroke keyStroke = KeyStroke.getKeyStrokeForEvent(e);
	    		if (actionMap.containsKey(keyStroke) && MainFrame.this.areKbShortcutsEnabled) {
	    			final Action a = actionMap.get(keyStroke);
	    			final ActionEvent ae = new ActionEvent(e.getSource(), e.getID(), null);
	    			SwingUtilities.invokeLater(new Runnable() {
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

    /**
     * Custom WindowAdapter to handle prompting to save on exit,
     * as well as disabling keyboard shortcuts when dialogs are open
     */
    private class MainFrameWindowAdapter extends WindowAdapter {

		/**
		 * Prompt to save on exit if there are unsaved changes
		 */
        @Override
        public void windowClosing(WindowEvent windowEvent) {
        	if(UndoManager.hasUnsavedChanges()) {
        		UnsavedChangesDialog.createAndShow(UnsavedChangesDialog.Operation.EXIT);
        	}
        	else { System.exit(0); }
        }
        
        /**
         * Enable keyboard shortcuts when dialogs are closed
         */
        @Override
        public void windowActivated(WindowEvent windowEvent) {
        	MainFrame.this.areKbShortcutsEnabled = true;
        }

        /**
         * Disable keyboard shortcuts when dialogs are opened
         */
        @Override
        public void windowDeactivated(WindowEvent windowEvent) {
        	MainFrame.this.areKbShortcutsEnabled = false;
        }
    }
}
