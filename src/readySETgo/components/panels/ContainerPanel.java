package readySETgo.components.panels;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.Timer;

import readySETgo.managers.ComponentManager;

public class ContainerPanel extends JPanel {
	private JPanel leftPanel;
	private JPanel rightPanel;
	private StagePanel stagePanel;
	
	public ContainerPanel(){
        this.setLayout(new BorderLayout());
		
		this.stagePanel = new StagePanel();
		this.leftPanel = new FlyRailPanel();
        this.rightPanel = new ObjectPanel();

        this.leftPanel.setPreferredSize(new Dimension(215, 0));
        this.rightPanel.setPreferredSize(new Dimension(150, 0));
        
        this.add(leftPanel, BorderLayout.WEST);
        this.add(stagePanel, BorderLayout.CENTER);
        this.add(rightPanel, BorderLayout.EAST);
        
        Timer t = new Timer(10, new ActionListener() {
            private int i = 0;
        	
        	public void actionPerformed(ActionEvent e) {
        		boolean initialDrag = ((ObjectPanel) ComponentManager.getComp("ObjectPanel")).getInitialDrag();
                if(initialDrag) { ContainerPanel.this.rightPanel.repaint(); }
                else { ContainerPanel.this.stagePanel.repaint(); }
                i++;
            }
        });
        t.start();
        
	}
}
