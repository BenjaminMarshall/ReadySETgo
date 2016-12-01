package readySETgo.dialogs;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.filechooser.FileNameExtensionFilter;

import readySETgo.managers.ComponentManager;

public class ImportDialog extends JOptionPane {

	public static final String WINDOW_TITLE = "Import new stage object";
	
	private ImportDialog() {}
	
	public static void createAndShow() {
		// Custom yes/no button text
		UIManager.put("OptionPane.yesButtonText", "Import");
    	UIManager.put("OptionPane.noButtonText", "Cancel");
		
		JTextField objName = new JTextField();
		JTextField objWidth = new JTextField();
		JTextField objLength = new JTextField();
		JTextField objImageRef = new JTextField();
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
		
		final JComponent[] inputs = new JComponent[] {
		        new JLabel("Name"), objName,
		        new JLabel("Width (in)"), objWidth,
		        new JLabel("Length (in)"), objLength,
		        new JLabel("Image"), objImageRef, imageSelect
		};
		int res = ImportDialog.showConfirmDialog(null, inputs, ImportDialog.WINDOW_TITLE, JOptionPane.YES_NO_OPTION);
		if (res == JOptionPane.OK_OPTION) {
		    System.out.println("New obj code here!");
		}
	}
	
}
