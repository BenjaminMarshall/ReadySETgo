package readySETgo;

import java.awt.Dimension;
import java.awt.Toolkit;

public class Driver {
	
	public static void main(String[] args){
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		MainFrame mf = new MainFrame(screenSize.width, screenSize.height);
		
	}
}
