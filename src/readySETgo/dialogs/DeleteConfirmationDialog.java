package readySETgo.dialogs;

import javax.swing.JOptionPane;
import javax.swing.UIManager;

import readySETgo.managers.FileManager;
import readySETgo.models.assets.StageObject;

public class DeleteConfirmationDialog extends JOptionPane {

	public static final String WINDOW_TITLE = "Permanently delete %s";
	public static final String WINDOW_MSG = "Are you sure you want to permanently delete the %s stage object? Any copies will remain on the stage, but you will no longer be able to add more from the right panel.";
	
	private DeleteConfirmationDialog() {}
	
	public static void createAndShow(StageObject obj) {
		// Custom yes/no button text
		UIManager.put("OptionPane.yesButtonText", "DELETE");
    	UIManager.put("OptionPane.noButtonText", "Cancel");
    	
    	String name = obj.getName();
    	
    	int response = JOptionPane.showConfirmDialog(null, 
    			String.format(DeleteConfirmationDialog.WINDOW_MSG, name),
    			String.format(DeleteConfirmationDialog.WINDOW_TITLE, name), 
                JOptionPane.YES_NO_OPTION,
                JOptionPane.ERROR_MESSAGE);
    	if (response == JOptionPane.YES_OPTION){
            FileManager.removeObjectFromDefaults(obj);
        }
	}
	
}