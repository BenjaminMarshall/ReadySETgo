package readySETgo.components.panels;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

import readySETgo.managers.ComponentManager;
import readySETgo.managers.PrintManager;
import readySETgo.managers.StageManager;
import readySETgo.managers.UndoManager;
import readySETgo.managers.UserManager;
import readySETgo.managers.UserManager.MouseState;
import readySETgo.managers.UserManager.SelectedState;
import readySETgo.models.Stage;
import readySETgo.models.assets.Asset;
import readySETgo.models.assets.TextBox;
import readySETgo.rightclickmenus.StagePanelRCM;

public class StagePanel extends JPanel implements Printable {
    private Stage stage;

    public StagePanel() {
        super();
        PrintManager.getManager().register(this);
        stage = new Stage();
        StageManager.get().registerStage(stage);
        ComponentManager.registerComp("StagePanel", this);

        Timer t = new Timer(10, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                repaint();
            }
        });
        t.start();

        addMouseListener(StagePanelRCM.createAdapter(stage));
        addMouseListener(new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent e) { 
                Asset a;
                if (SwingUtilities.isLeftMouseButton(e)) {
                    if ((a = stage.eventOnObject(e)) != null) {
                        UndoManager.get().storeDragStart(a, a.getxPos(), a.getyPos(), UserManager.getSelectedState(), UserManager.getSelected());
                        UserManager.setSelected(a);
                        UserManager.setSelectedState(SelectedState.DRAGGING);
                    } else {
                        UserManager.setSelectedState(SelectedState.EMPTY);
                        UserManager.setSelected(null);
                    }
                } else if (SwingUtilities.isRightMouseButton(e) && (a = stage.eventOnObject(e)) != null) {
                    UserManager.setSelected(a);
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (UserManager.getSelectedState().equals(SelectedState.DRAGGING)) {
                	if(e.getX() < 0 || e.getY() < 0 || e.getX() > StagePanel.this.getWidth() || e.getY() > StagePanel.this.getHeight()){
                		stage.deleteSelected();
                		
                        ComponentManager.getComp("MainFrame").
                    	setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                        
                	} else {
                		UndoManager.get().storeDragEnd();
                        UserManager.setSelectedState(SelectedState.SELECTED);
                	}
                    
                }

            }

            public void mouseClicked(MouseEvent e){
            	if(e.getClickCount()==2){
                    if(UserManager.getSelectedState().equals(SelectedState.SELECTED)){
                    	if(UserManager.getSelected() instanceof TextBox){
                    		stage.editSelectedTextBox();
                    	}
                    }
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                if (UserManager.getSelectedState().equals(SelectedState.DRAGGING)) {
                    if (UserManager.getMouseState(e).equals(MouseState.UP)) {
                        UserManager.setSelectedState(SelectedState.EMPTY);
                    } else if (!stage.getAssets().contains(UserManager.getSelected())) {
                        UndoManager.get().storeObjectPlacement(UserManager.getSelected());
                        stage.getAssets().add(UserManager.getSelected());
                    }
                }
            }
        });

        addMouseMotionListener(new MouseMotionAdapter() {

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
                	Stage s = StageManager.get().getStage();
                	Double scale = s.getScale();
                    UserManager.getSelected().setxPos(e.getX() / scale);
                    UserManager.getSelected().setyPos(e.getY() / scale);
                } 
            }

            public void mouseMoved(MouseEvent e) {
                if (UserManager.getSelectedState().equals(SelectedState.DRAGGING)) {
                	Stage s = StageManager.get().getStage();
                	Double scale = s.getScale();
                	UserManager.getSelected().setxPos(e.getX() / scale);
                    UserManager.getSelected().setyPos(e.getY() / scale);
                } 
            }

        });
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, getWidth(), getHeight());
        stage.draw(g);
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @Override
    public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
        if (pageIndex > 0) {
            return NO_SUCH_PAGE;
        }
        graphics.getClip();
        Graphics2D g2d = (Graphics2D) graphics;
        double xScale = 0.79;
        double yScale = 0.9;

        g2d.scale(xScale, yScale);
        pageFormat.setOrientation(PageFormat.LANDSCAPE);
        g2d.translate(pageFormat.getImageableX(), pageFormat.getImageableY());
        printAll(graphics);
        return PAGE_EXISTS;
    }

    public void loadStage(Stage s) {
        this.stage = s;
        StageManager.get().registerStage(s);
    }

}
