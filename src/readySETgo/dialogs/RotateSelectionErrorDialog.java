package readySETgo.dialogs;

import javax.swing.JOptionPane;
import javax.swing.UIManager;

import readySETgo.managers.ComponentManager;
import readySETgo.managers.FileManager;
import readySETgo.models.assets.StageObject;

/**
 * 
 * Dialog for informing the user they must select an asset before rotating
 * 
 * @author ReadySETgo
 * @version Beta 3
 * @since 2016-12-04
 * 
 */
public class RotateSelectionErrorDialog extends JOptionPane {

	public static final String WINDOW_TITLE = "Nothing Selected";
	public static final String WINDOW_MSG = "You must select a stage object before rotating.";
	
	// Disable default constructor
	private RotateSelectionErrorDialog() {}
	
	/**
	 * Creates and shows the dialog
	 */
	public static void createAndShow() {
    	JOptionPane.showConfirmDialog(ComponentManager.getComp("MainFrame"), 
    			String.format(RotateSelectionErrorDialog.WINDOW_MSG),
    			String.format(RotateSelectionErrorDialog.WINDOW_TITLE), 
                JOptionPane.PLAIN_MESSAGE,
                JOptionPane.ERROR_MESSAGE);
	}
	
}
