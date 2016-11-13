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
		context.registerMouseListener(createMouseListener());
		
		this.draggingState = 0;
		this.dragging = null;
		this.clipboard = null;
	}

	public MouseListener createMouseListener() {
		MouseListener m = new MouseListener(){

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mousePressed(MouseEvent e) {
				System.out.println("Pressed: " + e.getPoint().getX() + ", " +  e.getPoint().getY());
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				System.out.println("Released: " + e.getX() + ", " + e.getY());
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		};
		return m;
	}
}
