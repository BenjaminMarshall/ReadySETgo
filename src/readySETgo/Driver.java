package readySETgo;

import java.awt.Dimension;
import java.awt.Toolkit;

import readySETgo.components.MainFrame;

/**
 * Driver class for Stage Plan
 * Written by Team 4 AKA ReadySETgo as our 
 * final project for Software Engineering during
 * Fall 2016 semester.
 * 
 * ReadySETgo dev team members:
 * Ben Marshall, Jason Milloff, Ksenia Belikova
 * ReadySETgo non-dev team members:
 * Lane Addison, Milton Moore
 * 
 * Thanks to:
 *  Daniel Kullman: 
 *   We learned how to do keyboard shortcuts from his post at
 *   http://stackoverflow.com/a/8485873
 * 
 * @author ReadySETgo
 * @version Beta 3
 * @since 2016-12-04
 *
 */
public class Driver {
	
	public static void main(String[] args) {
		// Create the main frame, sized to a large portion of the screen, but not the whole thing
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int frameWidth = (int) (screenSize.width * .85);
		int frameHeight = (int) (screenSize.height * .85);
		MainFrame main = new MainFrame(frameWidth, frameHeight);		
	}
}
