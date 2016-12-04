package readySETgo.dialogs;

import java.math.RoundingMode;
import java.text.NumberFormat;

import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import readySETgo.managers.ComponentManager;
import readySETgo.models.assets.StageObject;

/**
 * 
 * Dialog for displaying StageObject properties
 * 
 * @author ReadySETgo
 * @version Beta 3
 * @since 2016-12-04
 * 
 */
public class PropsDialog extends JOptionPane {

	public static final String WINDOW_TITLE = "Stage object properties";
	
	public static final String NAME_LABEL_TXT = "Name";
	public static final String WIDTH_LABEL_TXT = "Width (in)";
	public static final String LENGTH_LABEL_TXT = "Length (in)";
	public static final String IMAGE_LABEL_TXT = "Image Location";
			
	// Disable default constructor
	private PropsDialog() {}
	
	/**
	 * Creates and shows the dialog
	 * @param obj The StageObject to display properties of
	 */
	public static void createAndShow(StageObject obj) {
		PropsDialog.createDialog(obj);
	}

	private static void createDialog(StageObject obj) {
		    	
    	NumberFormat doublesOnly = NumberFormat.getNumberInstance();
    	doublesOnly.setGroupingUsed(false);
    	doublesOnly.setMaximumIntegerDigits(10);
    	doublesOnly.setMaximumFractionDigits(4);
    	doublesOnly.setMinimumFractionDigits(0);
    	doublesOnly.setRoundingMode(RoundingMode.HALF_UP);
    	
		JTextField objName = new JTextField(obj.getName());
		objName.setEditable(false);
		JTextField objWidth = new JFormattedTextField(doublesOnly);
		objWidth.setText(Double.toString(obj.getPhysicalWidth()));
		objWidth.setEditable(false);
		JTextField objLength = new JFormattedTextField(doublesOnly);
		objLength.setText(Double.toString(obj.getPhysicalLength()));
		objLength.setEditable(false);
		JTextField objImageRef = new JTextField(obj.getImageRef());
		objImageRef.setEditable(false);
					
		final JComponent[] inputs = new JComponent[] {
		        new JLabel(PropsDialog.NAME_LABEL_TXT), objName,
		        new JLabel(PropsDialog.WIDTH_LABEL_TXT), objWidth,
		        new JLabel(PropsDialog.LENGTH_LABEL_TXT), objLength,
		        new JLabel(PropsDialog.IMAGE_LABEL_TXT), objImageRef
		};
		
		PropsDialog.showConfirmDialog(ComponentManager.getComp("MainFrame"), inputs, PropsDialog.WINDOW_TITLE, JOptionPane.DEFAULT_OPTION);
	}
}
