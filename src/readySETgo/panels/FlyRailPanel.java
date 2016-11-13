package readySETgo.panels;

import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

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
		}
	}

	public FlyRailPanel() {
		super();
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		this.loadFlyRails();
	}

	public void loadFlyRails(){
		ArrayList<FlyRail> rails = FileManager.getListOfFlyRails();
		this.removeAll();
		for (FlyRail f : rails) { this.add(new SingleRailPanel(f)); }
	}

}
