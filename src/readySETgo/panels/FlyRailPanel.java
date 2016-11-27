package readySETgo.panels;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import backend.models.FlyRail;
import backend.models.Stage;

public class FlyRailPanel extends JPanel{
	
	public FlyRailPanel(Stage s) {
		super();
	
		JPanel contentPanel = this.loadFlyRails(s);
	
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
	}

	public JPanel loadFlyRails(Stage s) {
		
		JPanel ret = new JPanel();
		ret.setLayout(new BoxLayout(ret, BoxLayout.PAGE_AXIS));
		
		List<FlyRail> rails = s.getFlyRails();
		this.removeAll();
		for (FlyRail f : rails) { ret.add(new SingleRailPanel(f)); }
		
		return ret;
	}

}
