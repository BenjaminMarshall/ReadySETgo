package readySETgo.dialogs;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.math.RoundingMode;
import java.text.NumberFormat;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.filechooser.FileNameExtensionFilter;

import readySETgo.managers.ComponentManager;
import readySETgo.managers.FileManager;
import readySETgo.models.assets.StageObject;

public class EditDialog extends JOptionPane {

	public static final String WINDOW_TITLE = "Edit stage object";
	
	public static final String NAME_LABEL_TXT = "Name";
	public static final String WIDTH_LABEL_TXT = "Width (in)";
	public static final String LENGTH_LABEL_TXT = "Length (in)";
	public static final String IMAGE_LABEL_TXT = "Image";
			
	private EditDialog() {}
	
	public static void createAndShow(StageObject obj) {
		EditDialog.createDialog(obj, null, null, null, null, null, null, null, null);
	}

	private static void createDialog(StageObject obj, String _nameText, String _widthText, String _lengthText, String _imgText,
			String nameInput, String widthInput, String lengthInput, String imgInput) {
		// Custom yes/no button text
		UIManager.put("OptionPane.yesButtonText", "Save");
    	UIManager.put("OptionPane.noButtonText", "Cancel");
		
    	
    	NumberFormat doublesOnly = NumberFormat.getNumberInstance();
    	doublesOnly.setGroupingUsed(false);
    	doublesOnly.setMaximumIntegerDigits(10);
    	doublesOnly.setMaximumFractionDigits(4);
    	doublesOnly.setMinimumFractionDigits(0);
    	doublesOnly.setRoundingMode(RoundingMode.HALF_UP);
    	
		JTextField objName = new JTextField();
		if(nameInput != null) {objName.setText(nameInput);}
		else{ objName.setText(obj.getName()); }
		JTextField objWidth = new JFormattedTextField(doublesOnly);
		if(widthInput != null) {objWidth.setText(widthInput);}
		else{ objWidth.setText(Double.toString(obj.getPhysicalWidth())); }
		JTextField objLength = new JFormattedTextField(doublesOnly);
		if(lengthInput != null) {objLength.setText(lengthInput);}
		else{ objLength.setText(Double.toString(obj.getPhysicalLength())); }
		JTextField objImageRef = new JTextField();
		objImageRef.setEnabled(false);
		if(imgInput != null) {objImageRef.setText(imgInput);}
		else{ objImageRef.setText(obj.getImageRef()); }
		System.out.println(obj.getImageRef());
		
		
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
		JButton imageWipe = new JButton("Clear Image");
		imageWipe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				objImageRef.setText("");
			}
		});
		JPanel imgButtons = new JPanel(new GridLayout(0,2));
		imgButtons.add(imageSelect);
		imgButtons.add(imageWipe);
		
		
		if(_nameText == null) {_nameText = EditDialog.NAME_LABEL_TXT;}
		if(_widthText == null) {_widthText = EditDialog.WIDTH_LABEL_TXT;}
		if(_lengthText == null) {_lengthText = EditDialog.LENGTH_LABEL_TXT;}
		if(_imgText == null) {_imgText = EditDialog.IMAGE_LABEL_TXT;}
			
		final JComponent[] inputs = new JComponent[] {
		        new JLabel(_nameText), objName,
		        new JLabel(_widthText), objWidth,
		        new JLabel(_lengthText), objLength,
		        new JLabel(_imgText), objImageRef, imgButtons
		};
		int res = EditDialog.showConfirmDialog(null, inputs, EditDialog.WINDOW_TITLE, JOptionPane.YES_NO_OPTION);
		if (res == JOptionPane.OK_OPTION) {
			// Save the object, or create a new dialog showing errors
			// TODO - Fix this horrible mess
			boolean noErrors = true;
			String nameText = EditDialog.NAME_LABEL_TXT;
			String widthText = EditDialog.WIDTH_LABEL_TXT;
			String lengthText = EditDialog.LENGTH_LABEL_TXT;
			String imgText = EditDialog.IMAGE_LABEL_TXT;
			
			// Check for empty inputs / Invalid input
			if(objName.getText().isEmpty()) {
				noErrors = false;
				nameText = EditDialog.generateErrorStr(nameText, "Cannot be empty");
			}
			if(objWidth.getText().isEmpty()) {
				noErrors = false;
				widthText = EditDialog.generateErrorStr(widthText, "Cannot be empty");
			}
			else if(Double.parseDouble(objWidth.getText()) == 0) {
				noErrors = false;
				widthText = EditDialog.generateErrorStr(widthText, "Cannot be Zero");
			}
			if(objLength.getText().isEmpty()) {
				noErrors = false;
				lengthText = EditDialog.generateErrorStr(lengthText, "Cannot be empty");
			}
			else if(Double.parseDouble(objLength.getText()) == 0) {
				noErrors = false;
				lengthText = EditDialog.generateErrorStr(lengthText, "Cannot be Zero");
			}
			if(!objImageRef.getText().isEmpty() && !(new File(objImageRef.getText())).exists() ) {
				noErrors = false;
				imgText = EditDialog.generateErrorStr(imgText, "File does not exist");
			}
			// TODO - Get image validation working
//			else if(!isImage(new File(objImageRef.getText()))) {
//				noErrors = false;
//				imgText = ImportDialog.generateErrorStr(imgText, "File is not an image");
//			}
			
			if(noErrors) {
				FileManager.replaceObjectInDefaults(obj, objName.getText(), Double.parseDouble(objWidth.getText()), Double.parseDouble(objLength.getText()), objImageRef.getText());
			}
			// Create another dialog displaying errors
			else {				
				EditDialog.createDialog(obj, nameText, widthText, lengthText, imgText,
						objName.getText(), objWidth.getText(), objLength.getText(), objImageRef.getText());
			}
		}
	}
	
	private static String generateErrorStr(String label, String error) {
		return String.format("<html>%s <font color=red>%s</font></html>", label, error);
	}
}
