package readySETgo.components.panels;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JViewport;

import readySETgo.managers.ComponentManager;

public class StageViewPanel extends JPanel {
	private StagePanel stagePanel;
	private JScrollPane scroll;
	
	public StageViewPanel(StagePanel sp){
		//TODO Create viewport to stagePanel
		super(new GridLayout(1,1));
		
		ComponentManager.registerComp("StageViewPanel", this);
		JViewport port = new JViewport();
		scroll = new JScrollPane();
		port.add(sp);
		scroll.setViewport(port);
		scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS); 
		add(scroll);
	}

	public JScrollPane getScrollPane() {
		return scroll;
	}
	
	
}
