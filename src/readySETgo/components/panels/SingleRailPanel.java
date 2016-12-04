package readySETgo.components.panels;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import readySETgo.managers.UndoManager;
import readySETgo.models.FlyRail;

/**
 * 
 * Panel for one flyrail
 * 
 * @author ReadySETgo
 * @version Beta 3
 * @since 2016-12-04
 * 
 */
public class SingleRailPanel extends JPanel {
		
		private FlyRail rail;
		private JLabel railTitle;
		private JLabel railState;
		private JButton railToggle;
		
		private String getTitleLabel() { return rail.getName() + ": "; }
		
		private String getStateLabel() {
			return (rail.isFlownIn() ? "<html><font color=green>Flown In</font></html>" : "<html><font color=red>Flown Out</font></html>");
		}
		
		private String getToggleLabel() { return (rail.isFlownIn() ? "Fly Out" : "Fly In"); }
		
		/**
		 * Toggles the rail's state between flown in and flown out. Creates an entry in the UndoManager
		 */
		public void toggleRail() {
			UndoManager.storeRailToggle(this);
			toggleRailWithoutUndoReport();
		}
		
		/**
		 * Toggle the rail's state without creating an entry in the UndoManager
		 */
		public void toggleRailWithoutUndoReport() {
			this.rail.setFlownIn(!this.rail.isFlownIn());
			this.updateLabels();
		}
		
		private void updateLabels() {
			this.railTitle.setText(this.getTitleLabel());
			this.railToggle.setText(this.getToggleLabel());
			this.railState.setText(this.getStateLabel());
		}
		
		/**
		 * Default Constructor
		 * @param f The FlyRail to be contained by the panel
		 */
		public SingleRailPanel(FlyRail f) {
			super();
			this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
			this.rail = f;
			this.railTitle = new JLabel(this.getTitleLabel());
			this.railState = new JLabel(this.getStateLabel());
			
			JPanel labelPanelHolder = new JPanel();
			labelPanelHolder.setLayout(new BoxLayout(labelPanelHolder, BoxLayout.Y_AXIS));
			JPanel labelPanel = new JPanel();
			labelPanel.setLayout(new BoxLayout(labelPanel, BoxLayout.Y_AXIS));
			labelPanel.add(this.railTitle);
			labelPanel.add(this.railState);
			labelPanelHolder.add(labelPanel);
			
			this.railToggle = new JButton(this.getToggleLabel());
			this.railToggle.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) { toggleRail(); }
			});
			this.railToggle.setPreferredSize(new Dimension(75, 30));
			JPanel toggleHolder = new JPanel();
			//toggleHolder.setLayout(new BoxLayout(toggleHolder, BoxLayout.Y_AXIS));
			toggleHolder.add(this.railToggle);
			
			this.add(labelPanelHolder);
			this.add(toggleHolder);
			this.setPreferredSize(new Dimension(0, 35));
		}
}
