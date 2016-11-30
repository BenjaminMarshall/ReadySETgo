package readySETgo.components.panels;

import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JViewport;

public class StageViewPanel extends JPanel {
	private StagePanel stagePanel;
	
	public StageViewPanel(StagePanel sp){
		//TODO Create viewport to stagePanel
		super(new GridLayout(1,1));
		
		JViewport port = new JViewport();
		JScrollPane scroll = new JScrollPane();
		port.add(sp);
		scroll.setViewport(port);
		scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS); 
		add(scroll);
	}
	
	
}
