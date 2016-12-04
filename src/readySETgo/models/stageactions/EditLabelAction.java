package readySETgo.models.stageactions;
import readySETgo.models.assets.TextBox;

/**
 * 
 * Represents an action where a TextBox on the Stage is edited
 * 
 * @author ReadySETgo
 * @version Beta 3
 * @since 2016-12-04
 * 
 */
public class EditLabelAction extends StageAction {
	private TextBox edited;
	private String origText;
	private String newText;
	private double oldScale;
	private double newScale;
	
	/**
	 * Default constructor
	 * @param edited The edited TextBox
	 * @param origText The original text
	 * @param newText The new text
	 * @param oldScale The old font scale
	 * @param newScale The new font scale
	 */
	public EditLabelAction(TextBox edited, String origText, String newText, double oldScale, double newScale){
		this.edited = edited;
		this.origText = origText;
		this.newText = newText;
		this.oldScale = oldScale;
		this.newScale = newScale;
	}
	
	/**
	 * Undo the action
	 */
	public void undoAction() {
		edited.setText(origText);
		edited.setFontScale(oldScale);
	}
	
	/**
	 * Redo the action
	 */
	public void redoAction() {
		edited.setText(newText);
		edited.setFontScale(newScale);
	}
	
}

