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


public class MainFrame extends JFrame {
    private ContainerPanel container;

    public MainFrame(int width, int height) {
        super();
        ComponentManager.registerComp("MainFrame", this);
        
        setSize(width, height);
        try {
            this.setIconImage(ImageIO.read(new File("res/logo.png")));
        } catch (IOException e) {
        }
        this.setTitle("Stage Plan");
        fill();
        createMenuBar();

        bindKeyboardShortcuts();

        JFrame mainFrame = this;
        
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent windowEvent) {
            	if(UndoManager.get().hasUnsavedChanges()){
            		UnsavedChangesDialog.createAndShow();
            	}
            	else {
            		System.exit(0);
            	}
            }
        });
        
        
        
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
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
    			UndoManager.get().undo();
    		}
    	});

    	// Ctrl Y => Redo
    	KeyStroke ctrlY = KeyStroke.getKeyStroke(KeyEvent.VK_Y, KeyEvent.CTRL_DOWN_MASK);
    	actionMap.put(ctrlY, new AbstractAction("redo") {
    		@Override
    		public void actionPerformed(ActionEvent e) {
    			UndoManager.get().redo();
    		}
    	});
    	
    	// Ctrl P => Print
    	KeyStroke ctrlP = KeyStroke.getKeyStroke(KeyEvent.VK_P, KeyEvent.CTRL_DOWN_MASK);
    	actionMap.put(ctrlP, new AbstractAction("print") {
    		@Override
    		public void actionPerformed(ActionEvent e) {
    			PrintManager.getManager().print();
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
    	
    	
    	// Delete or Ctrl + Backspace => Delete Stage Object
    	AbstractAction deleteAction = new AbstractAction("delete") {
    		@Override
    		public void actionPerformed(ActionEvent e) {
    			StageManager.getStage().deleteSelected();
    		}
    	};
    	KeyStroke delete = KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, 0);
    	actionMap.put(delete, deleteAction);
    	KeyStroke ctrlBackspace = KeyStroke.getKeyStroke(KeyEvent.VK_BACK_SPACE, KeyEvent.CTRL_DOWN_MASK);
    	actionMap.put(ctrlBackspace, deleteAction);
    	
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
