package readySETgo.dialogs;

import javax.swing.JOptionPane;
import javax.swing.UIManager;

import readySETgo.managers.ComponentManager;
import readySETgo.managers.FileManager;
import readySETgo.models.assets.StageObject;

/**
 * 
 * Dialog for confirming StageObject deletion
 * 
 * @author ReadySETgo
 * @version Beta 3
 * @since 2016-12-04
 * 
 */
public class DeleteConfirmationDialog extends JOptionPane {

	public static final String WINDOW_TITLE = "Permanently delete %s";
	public static final String WINDOW_MSG = "Are you sure you want to permanently delete the %s stage object? Any copies will remain on the stage, but you will no longer be able to add more from the right panel.";
	
	// Disable default constructor
	private DeleteConfirmationDialog() {}
	
	/**
	 * Create and show dialog
	 * @param obj The object to delete if user confirms deletion
	 */
	public static void createAndShow(StageObject obj) {
		// Custom yes/no button text
		UIManager.put("OptionPane.yesButtonText", "DELETE");
    	UIManager.put("OptionPane.noButtonText", "Cancel");
    	
    	String name = obj.getName();
    	
    	int response = JOptionPane.showConfirmDialog(ComponentManager.getComp("MainFrame"), 
    			String.format(DeleteConfirmationDialog.WINDOW_MSG, name),
    			String.format(DeleteConfirmationDialog.WINDOW_TITLE, name), 
                JOptionPane.YES_NO_OPTION,
                JOptionPane.ERROR_MESSAGE);
    	if (response == JOptionPane.YES_OPTION) {
            FileManager.removeObjectFromDefaults(obj);
        }
	}
	
}
