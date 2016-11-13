package readySETgo;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import backend.models.Asset;

public class User {
	private int draggingState;
	private Asset dragging;
	private Asset clipboard;
	private MainFrame context;
	
	public User(MainFrame mf){
		this.context = mf;
		
		this.draggingState = 0;
		this.dragging = null;
		this.clipboard = null;
	}

}
