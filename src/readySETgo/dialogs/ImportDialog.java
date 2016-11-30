package readySETgo.dialogs;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class ImportDialog extends JOptionPane {

	public static final String WINDOW_TITLE = "Import new stage object";
	
	private ImportDialog() {}
	
	public static void createAndShow() {

		JTextField objName = new JTextField();
		JTextField objWidth = new JTextField();
		JTextField objLength = new JTextField();
		JTextField objImageRef = new JTextField();
		
		final JComponent[] inputs = new JComponent[] {
		        new JLabel("Name"), objName,
		        new JLabel("Width (in)"), objWidth,
		        new JLabel("Length (in)"), objLength,
		        new JLabel("Image"), objImageRef,
		};
		int res = ImportDialog.showConfirmDialog(null, inputs, ImportDialog.WINDOW_TITLE, JOptionPane.YES_NO_OPTION);
		if (res == JOptionPane.OK_OPTION) {
		    System.out.println("New obj code here!");
		}
	}
	
}
