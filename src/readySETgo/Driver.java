package readySETgo;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Driver {
	
	public static void main(String[] args){
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		MainFrame mf = new MainFrame(screenSize.width, screenSize.height);
		
	}
}
