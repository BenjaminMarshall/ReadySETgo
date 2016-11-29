package readySETgo.panels;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import backend.ComponentManager;
import backend.StageManager;
import backend.models.FlyRail;
import backend.models.Stage;

public class FlyRailPanel extends JPanel{
	
	public FlyRailPanel() {
		super();
		ComponentManager.registerComp("FlyRailPanel", this);
		this.loadFlyRails(StageManager.get().getStage());
	}

	public void loadFlyRails(Stage s) {
		this.removeAll();
		
		JPanel contentPanel = new JPanel();
		contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.PAGE_AXIS));
		
		List<FlyRail> rails = s.getFlyRails();
		this.removeAll();
		for (FlyRail f : rails) { contentPanel.add(new SingleRailPanel(f)); }
		
		GridBagLayout gc = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		this.setLayout(gc);
		
		c.gridx = 0;
		c.gridy = 0;
		c.weightx = 1;
		c.weighty = 1;
		c.fill = c.BOTH;
		
		JScrollPane scrollPane = new JScrollPane(contentPanel, 
				   ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,  
				   ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		this.add(scrollPane, c);		
		this.validate();
		this.repaint();
	}

}
