package readySETgo.panels;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

public class ContainerPanel extends JPanel {
	private JPanel leftPanel;
	private JPanel centerPanel;
	private JPanel rightPanel;
	
	public ContainerPanel(){
		leftPanel = new FlyRailPanel();
        centerPanel = new StagePanel();
        rightPanel = new ObjectPanel();
        
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
