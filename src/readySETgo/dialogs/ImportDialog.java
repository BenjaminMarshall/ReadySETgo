package readySETgo.dialogs;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.math.RoundingMode;
import java.text.NumberFormat;

import javax.activation.MimetypesFileTypeMap;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.filechooser.FileNameExtensionFilter;

import readySETgo.managers.ComponentManager;
import readySETgo.managers.FileManager;

public class ImportDialog extends JOptionPane {

	public static final String WINDOW_TITLE = "Import new stage object";
	
	public static final String NAME_LABEL_TXT = "Name";
	public static final String WIDTH_LABEL_TXT = "Width (in)";
	public static final String LENGTH_LABEL_TXT = "Length (in)";
	public static final String IMAGE_LABEL_TXT = "Image";
			
	
	private ImportDialog() {}
	
	public static void createAndShow() {
		ImportDialog.createDialog(null, null, null, null, null, null, null, null);
	}

	private static void createDialog(String _nameText, String _widthText, String _lengthText, String _imgText,
			String nameInput, String widthInput, String lengthInput, String imgInput) {
		// Custom yes/no button text
		UIManager.put("OptionPane.yesButtonText", "Import");
    	UIManager.put("OptionPane.noButtonText", "Cancel");
		
    	
    	NumberFormat doublesOnly = NumberFormat.getNumberInstance();
    	doublesOnly.setGroupingUsed(false);
    	doublesOnly.setMaximumIntegerDigits(10);
    	doublesOnly.setMaximumFractionDigits(4);
    	doublesOnly.setMinimumFractionDigits(0);
    	doublesOnly.setRoundingMode(RoundingMode.HALF_UP);
    	
		JTextField objName = new JTextField();
		if(nameInput != null) {objName.setText(nameInput);}
		JTextField objWidth = new JFormattedTextField(doublesOnly);
		if(widthInput != null) {objWidth.setText(widthInput);}
		JTextField objLength = new JFormattedTextField(doublesOnly);
		if(lengthInput != null) {objLength.setText(lengthInput);}
		JTextField objImageRef = new JTextField();
		if(imgInput != null) {objImageRef.setText(imgInput);}
		objImageRef.setEnabled(false);
		
		JButton imageSelect = new JButton("Select Image");
		imageSelect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser menu = new JFileChooser();
				menu.setFileFilter(new FileNameExtensionFilter("Images", "jpeg", "jpg", "png", "gif", "bmp"));
		    	int retCode = menu.showOpenDialog(ComponentManager.getComp("MainFrame"));
		    	if (retCode == JFileChooser.APPROVE_OPTION) {
		    		File f = menu.getSelectedFile();
		    		String path = f.getAbsolutePath();
		    		// TODO, check that file is actually an image
		    		objImageRef.setText(path);
		    	}
			}
		});
		
		if(_nameText == null) {_nameText = ImportDialog.NAME_LABEL_TXT;}
		if(_widthText == null) {_widthText = ImportDialog.WIDTH_LABEL_TXT;}
		if(_lengthText == null) {_lengthText = ImportDialog.LENGTH_LABEL_TXT;}
		if(_imgText == null) {_imgText = ImportDialog.IMAGE_LABEL_TXT;}
			
		final JComponent[] inputs = new JComponent[] {
		        new JLabel(_nameText), objName,
		        new JLabel(_widthText), objWidth,
		        new JLabel(_lengthText), objLength,
		        new JLabel(_imgText), objImageRef, imageSelect
		};
		int res = ImportDialog.showConfirmDialog(null, inputs, ImportDialog.WINDOW_TITLE, JOptionPane.YES_NO_OPTION);
		if (res == JOptionPane.OK_OPTION) {
			// Save the object, or create a new dialog showing errors
			// TODO - Fix this horrible mess
			boolean noErrors = true;
			String nameText = ImportDialog.NAME_LABEL_TXT;
			String widthText = ImportDialog.WIDTH_LABEL_TXT;
			String lengthText = ImportDialog.LENGTH_LABEL_TXT;
			String imgText = ImportDialog.IMAGE_LABEL_TXT;
			
			// Check for empty inputs / Invalid input
			if(objName.getText().isEmpty()) {
				noErrors = false;
				nameText = ImportDialog.generateErrorStr(nameText, "Cannot be empty");
			}
			if(objWidth.getText().isEmpty()) {
				noErrors = false;
				widthText = ImportDialog.generateErrorStr(widthText, "Cannot be empty");
			}
			else if(Double.parseDouble(objWidth.getText()) == 0) {
				noErrors = false;
				widthText = ImportDialog.generateErrorStr(widthText, "Cannot be Zero");
			}
			if(objLength.getText().isEmpty()) {
				noErrors = false;
				lengthText = ImportDialog.generateErrorStr(lengthText, "Cannot be empty");
			}
			else if(Double.parseDouble(objLength.getText()) == 0) {
				noErrors = false;
				lengthText = ImportDialog.generateErrorStr(lengthText, "Cannot be Zero");
			}
			if(objImageRef.getText().isEmpty()) {
				noErrors = false;
				imgText = ImportDialog.generateErrorStr(imgText, "No file selected");
			}
			else if(!(new File(objImageRef.getText())).exists() ) {
				noErrors = false;
				imgText = ImportDialog.generateErrorStr(imgText, "File does not exist");
			}
			// TODO - Get image validation working
//			else if(!isImage(new File(objImageRef.getText()))) {
//				noErrors = false;
//				imgText = ImportDialog.generateErrorStr(imgText, "File is not an image");
//			}
			
			if(noErrors) {
				System.out.println("Input is valid! Yes!!!!");
				FileManager.addObjectToDefaults(objName.getText(), Double.parseDouble(objWidth.getText()), Double.parseDouble(objLength.getText()), objImageRef.getText());
			}
			// Create another dialog displaying errors
			else {				
				ImportDialog.createDialog(nameText, widthText, lengthText, imgText,
						objName.getText(), objWidth.getText(), objLength.getText(), objImageRef.getText());
			}
		}
	}
	
	private static String generateErrorStr(String label, String error) {
		return String.format("<html>%s <font color=red>%s</font></html>", label, error);
	}
}
