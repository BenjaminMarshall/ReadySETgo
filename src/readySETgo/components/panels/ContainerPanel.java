package readySETgo.components.panels;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JPanel;

/**
 * 
 * ContainerPanel creates and displays the 
 * Flyrail, stage, and Drag and Drop Object panels
 * 
 * @author ReadySETgo
 * @version Beta 3
 * @since 2016-12-04
 * 
 */
public class ContainerPanel extends JPanel {
	
	private JPanel leftPanel;
	private JPanel rightPanel;
	private StagePanel stagePanel;
	
	/**
	 * Creates and displays the Flyrail, stage, and Drag and Drop Object panels
	 */
	public ContainerPanel() {
        this.setLayout(new BorderLayout());
		
		this.stagePanel = new StagePanel();
		this.leftPanel = new FlyRailPanel();
        this.rightPanel = new ObjectPanel();

        this.leftPanel.setPreferredSize(new Dimension(215, 0));
        this.rightPanel.setPreferredSize(new Dimension(150, 0));
        
        this.add(leftPanel, BorderLayout.WEST);
        this.add(stagePanel, BorderLayout.CENTER);
        this.add(rightPanel, BorderLayout.EAST);
	}
}
