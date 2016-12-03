package readySETgo.components.panels;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JPanel;

public class ContainerPanel extends JPanel {
	private JPanel leftPanel;
	private StageViewPanel centerPanel;
	private JPanel rightPanel;
	private StagePanel stagePanel;
	
	public ContainerPanel(){
        this.setLayout(new BorderLayout());
		
		this.stagePanel = new StagePanel();
		this.leftPanel = new FlyRailPanel();
        this.rightPanel = new ObjectPanel();

        this.leftPanel.setPreferredSize(new Dimension(225, 0));
        this.rightPanel.setPreferredSize(new Dimension(150, 0));
        
        this.add(leftPanel, BorderLayout.WEST);
        this.add(stagePanel, BorderLayout.CENTER);
        this.add(rightPanel, BorderLayout.EAST);
	}
}
