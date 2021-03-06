package readySETgo.components.panels;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

import readySETgo.managers.ComponentManager;
import readySETgo.managers.StageManager;
import readySETgo.managers.UndoManager;
import readySETgo.managers.UserManager;
import readySETgo.managers.UserManager.MouseState;
import readySETgo.managers.UserManager.SelectedState;
import readySETgo.models.Stage;
import readySETgo.models.assets.Asset;
import readySETgo.models.assets.TextBox;
import readySETgo.rightclickmenus.StagePanelRCM;

/**
 * 
 * Stage Panel
 * 
 * @author ReadySETgo
 * @version Beta 3
 * @since 2016-12-04
 * 
 */
public class StagePanel extends JPanel {

	private Stage stage;

	/**
	 * Default Constructor
	 */
    public StagePanel() {
        super();
        this.stage = new Stage();
        StageManager.registerStage(this.stage);
        ComponentManager.registerComp("StagePanel", this);

        Timer t = new Timer(10, new ActionListener() {
            public void actionPerformed(ActionEvent e) { StagePanel.this.repaint(); }
        });
        t.start();

        // Add right click menu
        addMouseListener(StagePanelRCM.createAdapter());
        // Handle dragging
        addMouseListener(new MouseAdapter() {

        	/**
        	 * Handle drag starts and selections, and de-selections
        	 */
            @Override
            public void mousePressed(MouseEvent e) { 
                Asset a;
                if (SwingUtilities.isLeftMouseButton(e)) {
                    if ((a = stage.eventOnObject(e)) != null) {
                        UndoManager.storeDragStart(a, a.getxPos(), a.getyPos(), UserManager.getSelectedState(), UserManager.getSelected());
                        UserManager.setSelected(a);
                        UserManager.setSelectedState(SelectedState.DRAGGING);
                        
                        double s = stage.getScale();
                        UserManager.setDragOffsetX((e.getX() / s) - a.getxPos());
                        UserManager.setDragOffsetY((e.getY() / s) - a.getyPos());
            
                    } else {
                        UserManager.setSelectedState(SelectedState.EMPTY);
                        UserManager.setSelected(null);
                    }
                } else if (SwingUtilities.isRightMouseButton(e) && (a = stage.eventOnObject(e)) != null) {
                    UserManager.setSelected(a);
                }
            }

            
            /**
             * Handle drag ends.
             */
            @Override
            public void mouseReleased(MouseEvent e) {
                if (UserManager.getSelectedState().equals(SelectedState.DRAGGING)) {
                	if(e.getX() < 0 || e.getY() < 0 || e.getX() > StagePanel.this.getWidth() || e.getY() > StagePanel.this.getHeight()){
                		stage.deleteSelected();
                		
                        ComponentManager.getComp("MainFrame").
                    	setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                        
                	} else {
                		UndoManager.storeDragEnd();
                        UserManager.setSelectedState(SelectedState.SELECTED);
                        UserManager.setDragOffsetX(0);
                        UserManager.setDragOffsetY(0);
                	}
                    
                }

            }

            /**
             * If a TextBox is double-clicked, open an edit dialog.
             */
            public void mouseClicked(MouseEvent e){
            	if(e.getClickCount()==2){
                    if(UserManager.getSelectedState().equals(SelectedState.SELECTED)){
                    	if(UserManager.getSelected() instanceof TextBox){
                    		stage.editSelectedTextBox();
                    	}
                    }
                }
            }

            /**
             * Handle a drag coming from off stage. Also, if mouse events are interrupted for some reason,
             * this will ensure the state is correct.
             */
            @Override
            public void mouseEntered(MouseEvent e) {
                if (UserManager.getSelectedState().equals(SelectedState.DRAGGING)) {
                    if (UserManager.getMouseState(e).equals(MouseState.UP)) {
                        UserManager.setSelectedState(SelectedState.EMPTY);
                        UserManager.setDragOffsetX(0);
                        UserManager.setDragOffsetY(0);
                    } else if (!stage.getAssets().contains(UserManager.getSelected())) {
                        UndoManager.storeObjectPlacement(UserManager.getSelected());
                        stage.getAssets().add(UserManager.getSelected());
                    }
                }
            }
        });
        
        //Handle dragging
        addMouseMotionListener(new MouseMotionAdapter() {

        	/**
        	 * Handle dragging
        	 */
            public void mouseDragged(MouseEvent e) {
                if (UserManager.getSelectedState().equals(SelectedState.DRAGGING)) {
                	if(e.getX() < 0 || e.getY() < 0 || e.getX() > getWidth() || e.getY() > getHeight()) {
                		ComponentManager.getComp("MainFrame").
                		setCursor(Toolkit.getDefaultToolkit().createCustomCursor(
                				new ImageIcon("res/no.png").getImage(),
                				new Point(0,0),"custom cursor"));
                	} else {
                		ComponentManager.getComp("MainFrame").
                		setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                	}
                	Stage s = StageManager.getStage();
                	Double scale = s.getScale();
                    UserManager.getSelected().setxPos((e.getX() - UserManager.getDragOffsetX()) / scale);
                    UserManager.getSelected().setyPos((e.getY() - UserManager.getDragOffsetY()) / scale);
                } 
            }
        });
    }

    /**
     * Paints the panel
     * @param g The Graphics object to paint with
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, getWidth(), getHeight());
        stage.draw(g);
    }

    /**
     * Returns the panel's Stage
     * @return The panel's Stage
     */
    public Stage getStage() { return this.stage; }



    /**
     * Loads the specified Stage into the panel and registers it with the StageManager
     * @param s The Stage to load
     */
    public void loadStage(Stage s) {
        this.stage = s;
        StageManager.registerStage(s);
    }

}
