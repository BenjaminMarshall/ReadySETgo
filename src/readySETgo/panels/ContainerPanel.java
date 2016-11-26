package readySETgo.panels;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JPanel;

import backend.models.Stage;

public class ContainerPanel extends JPanel {
	private JPanel leftPanel;
	private StageViewPanel centerPanel;
	private JPanel rightPanel;
	private StagePanel stagePanel;
	
	public ContainerPanel(){
		
		stagePanel = new StagePanel();
		Stage s = stagePanel.getStage();
		leftPanel = new FlyRailPanel(s);
        rightPanel = new ObjectPanel(stagePanel);

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
