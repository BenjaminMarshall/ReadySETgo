package readySETgo.dialogs;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.UIManager;

import readySETgo.managers.UndoManager;
import readySETgo.models.assets.TextBox;

public class EditTextDialog extends JOptionPane {

	public static final String WINDOW_TITLE = "Edit Label Text";
	public static final String TEXT_LABEL_TXT = "Text";

			
	
	private EditTextDialog() {}

	public static void createAndShow(TextBox l) {
		// Custom yes/no button text
		UIManager.put("OptionPane.yesButtonText", "Import");
    	UIManager.put("OptionPane.noButtonText", "Cancel");
			
    	JTextField text = new JTextField();
    	text.setText(l.getText());
    	
		final JComponent[] inputs = new JComponent[] {
		        new JLabel(TEXT_LABEL_TXT), text
		};
		
		int res = ImportDialog.showConfirmDialog(null, inputs, ImportDialog.WINDOW_TITLE, JOptionPane.YES_NO_OPTION);
		if (res == JOptionPane.OK_OPTION) {
			
			UndoManager.get().storeEditText(l, l.getText(), text.getText());
			l.setText(text.getText());
		}
	}
	
	private static String generateErrorStr(String label, String error) {
		return String.format("<html>%s <font color=red>%s</font></html>", label, error);
	}
}

