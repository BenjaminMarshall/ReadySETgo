package readySETgo.panels;

import java.util.ArrayList;

import javax.swing.JPanel;

import backend.models.StageObject;

public class ObjectPanel extends JPanel {
	private ArrayList<StageObject> possibleObjects;
	
	
	public ObjectPanel(){
		
		
	}

	public void loadPossibleObjects(){
		//TODO Load from XML in /res, sort by something
	}

	public ArrayList<StageObject> getPossibleObjects() {
		return possibleObjects;
	}


	public void setPossibleObjects(ArrayList<StageObject> possibleObjects) {
		this.possibleObjects = possibleObjects;
	}
	
}
