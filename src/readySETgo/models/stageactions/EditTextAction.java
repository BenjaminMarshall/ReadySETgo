package readySETgo.models.stageactions;
import readySETgo.models.assets.TextBox;

public class EditTextAction extends StageAction {
	TextBox edited;
	String origText;
	String newText;
	
	public EditTextAction(TextBox edited, String origText, String newText){
		this.edited = edited;
		this.origText = origText;
		this.newText = newText;
	}
	
	public void undoAction() {
		edited.setText(origText);
	}
	
	public void redoAction() {
		edited.setText(newText);
	}
	
}

