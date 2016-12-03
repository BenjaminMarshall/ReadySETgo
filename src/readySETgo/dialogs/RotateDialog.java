package readySETgo.dialogs;

import java.math.RoundingMode;
import java.text.NumberFormat;

import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;

import readySETgo.managers.ComponentManager;
import readySETgo.managers.FileManager;
import readySETgo.managers.UndoManager;
import readySETgo.models.assets.Asset;

public class RotateDialog extends JOptionPane {

	public static final String WINDOW_TITLE = "Rotate Object";
	public static final String TEXT_LABEL_TXT = "Angle (Degrees)";

			
	private RotateDialog() {}

	public static void createAndShow(Asset a){
		RotateDialog.createDialog(a, null, null);
	}
	
	private static void createDialog(Asset a, String angleText, String angleInput) {
		// Custom yes/no button text
		UIManager.put("OptionPane.yesButtonText", "Confirm");
    	UIManager.put("OptionPane.noButtonText", "Cancel");
			
    	NumberFormat doublesOnly = NumberFormat.getNumberInstance();
    	doublesOnly.setGroupingUsed(false);
    	doublesOnly.setMaximumIntegerDigits(3);
    	doublesOnly.setMaximumFractionDigits(2);
    	doublesOnly.setMinimumFractionDigits(0);
    	doublesOnly.setRoundingMode(RoundingMode.HALF_UP);
    	
    	JTextField text = new JFormattedTextField(doublesOnly);
    	if(angleInput != null) { text.setText(angleInput); }
    	
    	if(angleText == null) { angleText = TEXT_LABEL_TXT; }
    	
		final JComponent[] inputs = new JComponent[] {
		        new JLabel(angleText), text
		};
		
		int res = EditTextDialog.showConfirmDialog(ComponentManager.getComp("MainFrame"), inputs, EditTextDialog.WINDOW_TITLE, JOptionPane.YES_NO_OPTION);
		if (res == JOptionPane.OK_OPTION) {
			boolean noErrors = true;
			
			String labelText = RotateDialog.TEXT_LABEL_TXT;
			
			if(text.getText().isEmpty()){
				noErrors = false;
				labelText = RotateDialog.generateErrorStr(labelText, "Cannot be empty.");
			} else if(Double.parseDouble(text.getText()) < 0) {
				noErrors = false;
				labelText = RotateDialog.generateErrorStr(labelText, "Cannot be Negative");
			}
			
			if(noErrors) {
				UndoManager.get().storeRotate(a, a.getAngle(), Double.parseDouble(text.getText()));
				a.setAngle(Double.parseDouble(text.getText()));
			}
			else {				
				RotateDialog.createDialog(a, labelText, text.getText());
			}
		}
	}
	
	private static String generateErrorStr(String label, String error) {
		return String.format("<html>%s <font color=red>%s</font></html>", label, error);
	}
}

