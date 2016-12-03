package readySETgo.components.panels;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import readySETgo.managers.ComponentManager;
import readySETgo.managers.StageManager;
import readySETgo.models.FlyRail;
import readySETgo.models.Stage;

public class FlyRailPanel extends JPanel{
	
	public FlyRailPanel() {
		super();
		ComponentManager.registerComp("FlyRailPanel", this);
		this.loadFlyRails(StageManager.getStage());
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
		
		// Increase scroll speed from default
		scrollPane.getVerticalScrollBar().setUnitIncrement(15);

		this.add(scrollPane, c);		
		this.validate();
		this.repaint();
	}

}
