package readySETgo.components.panels;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JPanel;

import readySETgo.models.Stage;

public class ContainerPanel extends JPanel {
	private JPanel leftPanel;
	private StageViewPanel centerPanel;
	private JPanel rightPanel;
	private StagePanel stagePanel;
	
	public ContainerPanel(){
		
		stagePanel = new StagePanel();
		leftPanel = new FlyRailPanel();
        rightPanel = new ObjectPanel();

        centerPanel = new StageViewPanel(stagePanel);
        
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = .2;
        c.weighty = 1;  
        c.fill = c.BOTH;
        add(leftPanel, c);
       
        
        c.gridx = 1;
        c.weightx = .6;
        add(centerPanel, c);
        
        
        c.gridx = 2;
        c.weightx = .2;
        add(rightPanel, c);
	}
}
