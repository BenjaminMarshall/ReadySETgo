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
import readySETgo.managers.UndoManager;
import readySETgo.models.assets.TextBox;

/**
 * 
 * Dialog for editing labels aka Textboxes
 * 
 * @author ReadySETgo
 * @version Beta 3
 * @since 2016-12-04
 * 
 */
public class EditLabelDialog extends JOptionPane {

	public static final String WINDOW_TITLE = "Edit Label";
	public static final String SCALE_LABEL_TXT = "Multiplicative Factor (Format x.xx):";
	public static final String TEXT_LABEL_TXT = "Text:";

	// Disable default constructor
	private EditLabelDialog() {}

	/**
	 * Create and show edit dialog
	 * @param t The TextBox to edit
	 */
	public static void createAndShow(TextBox t) {
		EditLabelDialog.createDialog(t, null, "" + t.getFontScale(), null, t.getText());
	}
	
	private static void createDialog(TextBox t, String scaleText, String scaleInput, String textAreaText, String textAreaInput) {
		// Custom yes/no button text
		UIManager.put("OptionPane.yesButtonText", "Confirm");
    	UIManager.put("OptionPane.noButtonText", "Cancel");
			
    	NumberFormat doublesOnly = NumberFormat.getNumberInstance();
    	doublesOnly.setGroupingUsed(false);
    	doublesOnly.setMaximumIntegerDigits(1);
    	doublesOnly.setMaximumFractionDigits(2);
    	doublesOnly.setMinimumFractionDigits(0);
    	doublesOnly.setRoundingMode(RoundingMode.HALF_UP);
    	
    	JTextField scaleField = new JFormattedTextField(doublesOnly);
    	
    	JTextArea textArea = new JTextArea();
    	textArea.setRows(5);
    	textArea.setLineWrap(true);
    	textArea.setWrapStyleWord(true);
    	textArea.setText(textAreaInput);
    	
    	
    	if(scaleInput != null) { scaleField.setText(scaleInput); }
    	if(textAreaInput != null) { textArea.setText(textAreaInput); }
    	
    	if(scaleText == null) { scaleText = SCALE_LABEL_TXT; }
    	if(textAreaText == null) { textAreaText = TEXT_LABEL_TXT; }
    	
		final JComponent[] inputs = new JComponent[] {
		        new JLabel(textAreaText), new JScrollPane(textArea),
				new JLabel(scaleText), scaleField
		};
		
		int res = EditLabelDialog.showConfirmDialog(ComponentManager.getComp("MainFrame"), inputs, EditLabelDialog.WINDOW_TITLE, JOptionPane.YES_NO_OPTION);
		if (res == JOptionPane.OK_OPTION) {
			boolean noErrors = true;
			
			String scaleLabelText = EditLabelDialog.SCALE_LABEL_TXT;
			String textAreaLabelText = EditLabelDialog.TEXT_LABEL_TXT;
			

			if(textArea.getText().replaceAll("\\s","").isEmpty()) {
				noErrors = false;
				textAreaLabelText = EditLabelDialog.generateErrorStr(textAreaLabelText, "Cannot be empty.");
			}
			
			if(scaleField.getText().isEmpty()){
				noErrors = false;
				scaleLabelText = EditLabelDialog.generateErrorStr(scaleLabelText, "Cannot be empty.");
			} else if (Double.parseDouble(scaleField.getText()) == 0) {
				noErrors = false;
				scaleLabelText = EditLabelDialog.generateErrorStr(scaleLabelText, "Cannot be Zero");
			} else if(Double.parseDouble(scaleField.getText()) < 0) {
				noErrors = false;
				scaleLabelText = EditLabelDialog.generateErrorStr(scaleLabelText, "Cannot be Negative");
			}
			
			
			
			if(noErrors) {
				float scale = Float.parseFloat(scaleField.getText());
				UndoManager.storeLabelEdit(t, t.getText(), textArea.getText(), t.getFontScale(), scale);
				t.setText(textArea.getText());
				t.setFontScale(scale);
			}
			else {				
				EditLabelDialog.createDialog(t, scaleLabelText, scaleField.getText(), textAreaLabelText, textArea.getText());
			}
		}
	}
	
	private static String generateErrorStr(String label, String error) {
		return String.format("<html>%s <font color=red>%s</font></html>", label, error);
	}
}

