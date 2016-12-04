package readySETgo.dialogs;

import javax.swing.JOptionPane;
import javax.swing.UIManager;

import readySETgo.managers.ComponentManager;
import readySETgo.managers.FileManager;

/**
 * 
 * Dialog for informing the user there are unsaved changes on an exit attempt
 * 
 * @author ReadySETgo
 * @version Beta 3
 * @since 2016-12-04
 * 
 */
public class UnsavedChangesDialog extends JOptionPane {

	public static final String WINDOW_TITLE = "Save unsaved changes?";
	public static final String WINDOW_MSG = "You have unsaved changes. Would you like to save?";
	
	// Disable default constructor
	private UnsavedChangesDialog() {}
	
	/**
	 * Creates and shows the dialog
	 */
	public static void createAndShow() {
		// Custom yes/no button text
		UIManager.put("OptionPane.yesButtonText", "Save");
    	UIManager.put("OptionPane.noButtonText", "Don't Save");
    	int response = JOptionPane.showConfirmDialog(ComponentManager.getComp("MainFrame"), 
    			UnsavedChangesDialog.WINDOW_MSG,
    			UnsavedChangesDialog.WINDOW_TITLE, 
                JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.WARNING_MESSAGE);
    	if (response == JOptionPane.YES_OPTION){
            if(FileManager.attemptSaveSilently()) { System.exit(0); }
        }
    	if (response == JOptionPane.NO_OPTION) { System.exit(0); }
	}	
}
