package readySETgo.models.stageactions;
import readySETgo.models.assets.TextBox;

public class EditLabelAction extends StageAction {
	private TextBox edited;
	private String origText;
	private String newText;
	private float oldScale;
	private float newScale;
	
	public EditLabelAction(TextBox edited, String origText, String newText, float oldScale, float newScale){
		this.edited = edited;
		this.origText = origText;
		this.newText = newText;
		this.oldScale = oldScale;
		this.newScale = newScale;
	}
	
	public void undoAction() {
		edited.setText(origText);
		edited.setFontScale(oldScale);
	}
	
	public void redoAction() {
		edited.setText(newText);
		edited.setFontScale(newScale);
	}
	
}

