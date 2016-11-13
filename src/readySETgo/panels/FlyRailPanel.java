package readySETgo.panels;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import backend.FileManager;
import backend.models.FlyRail;

public class FlyRailPanel extends JPanel{
	
	class SingleRailPanel extends JPanel {
		
		public SingleRailPanel(FlyRail f) {
			super();
			JLabel railTitle = new JLabel(f.getName() + ": " + (f.isFlownIn() ? "Flown In" : "Flown Out"));
			JButton railToggle = new JButton((f.isFlownIn() ? "Fly Out" : "Fly In"));
			this.add(railTitle);
			this.add(railToggle);
			this.setPreferredSize(new Dimension(200, 50));
		}
	}

	public FlyRailPanel() {
		super();
	
		JPanel contentPanel = this.loadFlyRails();
	
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

	public JPanel loadFlyRails() {
		
		JPanel ret = new JPanel();
		ret.setLayout(new BoxLayout(ret, BoxLayout.PAGE_AXIS));
		
		ArrayList<FlyRail> rails = FileManager.getListOfFlyRails();
		this.removeAll();
		for (FlyRail f : rails) { ret.add(new SingleRailPanel(f)); }
		
		return ret;
	}

}
