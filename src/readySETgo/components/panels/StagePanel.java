package readySETgo.components.panels;

import readySETgo.managers.ComponentManager;
import readySETgo.managers.PrintManager;
import readySETgo.managers.StageManager;
import readySETgo.managers.UndoManager;
import readySETgo.managers.UserManager;
import readySETgo.managers.UserManager.MouseState;
import readySETgo.managers.UserManager.SelectedState;
import readySETgo.models.Stage;
import readySETgo.models.assets.Asset;
import readySETgo.rightclickmenus.StagePanelRCM;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;

public class StagePanel extends JPanel implements Printable {
    private Stage stage;

    public StagePanel() {
        super();
        PrintManager.getManager().register(this);
        stage = new Stage();
        StageManager.get().registerStage(stage);
        ComponentManager.registerComp("StagePanel", this);
        setPreferredSize(new Dimension(900, 300));

        Timer t = new Timer(10, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                repaint();
            }
        });
        t.start();

        addMouseListener(StagePanelRCM.createAdapter(stage));
        addMouseListener(new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent e) { //TODO Store objects original position so if invalid placement it is put back
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
                    UndoManager.get().storeDragEnd();
                    UserManager.setSelectedState(SelectedState.SELECTED);
                }

            }


            @Override
            public void mouseEntered(MouseEvent e) {
                System.out.println("Entered stage");
                if (UserManager.getSelectedState().equals(SelectedState.DRAGGING)) {
                    if (UserManager.getMouseState(e).equals(MouseState.UP)) {
                        UserManager.setSelectedState(SelectedState.SELECTED);
                    } else if (!stage.getAssets().contains(UserManager.getSelected())) {
                        UndoManager.get().storeObjectPlacement(UserManager.getSelected());
                        stage.getAssets().add(UserManager.getSelected());
                        System.out.println("Added asset");
                    }
                }
            }
        });

        addMouseMotionListener(new MouseMotionAdapter() {

            public void mouseDragged(MouseEvent e) {
                if (UserManager.getSelectedState().equals(SelectedState.DRAGGING)) {
                    UserManager.getSelected().setxPos(e.getX());
                    UserManager.getSelected().setyPos(e.getY());
                }
            }

            public void mouseMoved(MouseEvent e) {
                if (UserManager.getSelectedState().equals(SelectedState.DRAGGING)) {
                    UserManager.getSelected().setxPos(e.getX());
                    UserManager.getSelected().setyPos(e.getY());
                }
            }

        });
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

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