package readySETgo.dialogs;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.UIManager;

import readySETgo.managers.FileManager;

public class UnsavedChangesDialog extends JOptionPane {

	public static final String WINDOW_TITLE = "Save unsaved changes?";
	public static final String WINDOW_MSG = "You have unsaved changes. Would you like to save?";
	
	private UnsavedChangesDialog() {}
	
	public static void createAndShow() {
		// Custom yes/no button text
		UIManager.put("OptionPane.yesButtonText", "Save");
    	UIManager.put("OptionPane.noButtonText", "Don't Save");
    	int response = JOptionPane.showConfirmDialog(null, 
    			UnsavedChangesDialog.WINDOW_MSG,
    			UnsavedChangesDialog.WINDOW_TITLE, 
                JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.WARNING_MESSAGE);
    	if (response == JOptionPane.YES_OPTION){
            if(FileManager.attemptSaveSilently()) {
        		System.exit(0);
            }
        }
    	if (response == JOptionPane.NO_OPTION){
            System.exit(0);
        }
	}
	
}
