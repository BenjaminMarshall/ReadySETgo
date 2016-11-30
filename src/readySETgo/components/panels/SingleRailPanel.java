package readySETgo.components.panels;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import readySETgo.managers.UndoManager;
import readySETgo.models.FlyRail;

public class SingleRailPanel extends JPanel {
		
		private FlyRail rail;
		private JLabel railTitle;
		private JButton railToggle;
		
		private String getTitleLabel() {
			return rail.getName() + ": " + (rail.isFlownIn() ? "Flown In" : "Flown Out");
		}
		
		private String getToggleLabel() {
			return (rail.isFlownIn() ? "Fly Out" : "Fly In");
		}
		
		public void toggleRail() {
			UndoManager.get().storeRailToggle(this);
			toggleRailWithoutUndoReport();
		}
		
		public void toggleRailWithoutUndoReport() {
			this.rail.setFlownIn(!this.rail.isFlownIn());
			this.updateLabels();
		}
		
		private void updateLabels() {
			this.railTitle.setText(this.getTitleLabel());
			this.railToggle.setText(this.getToggleLabel());
		}
		
		public SingleRailPanel(FlyRail f) {
			super();
			this.rail = f;
			this.railTitle = new JLabel(this.getTitleLabel());
			this.railToggle = new JButton(this.getToggleLabel());
			railToggle.addActionListener(new ActionListener()
			{
			  public void actionPerformed(ActionEvent e)
			  {
			    toggleRail();
			  }
			});
			this.add(railTitle);
			this.add(railToggle);
			this.setPreferredSize(new Dimension(200, 50));
		}

}
