package readySETgo.panels;

import backend.ComponentManager;
import backend.StageManager;
import backend.UndoManager;
import backend.models.Asset;
import backend.models.Stage;
import readySETgo.PrintManager;
import readySETgo.User;
import readySETgo.User.MouseState;
import readySETgo.User.SelectedState;
import readySETgo.contextmenus.StagePopUp;

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

        addMouseListener(StagePopUp.createAdapter(stage));
        addMouseListener(new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent e) { //TODO Store objects original position so if invalid placement it is put back
                Asset a;
                if (SwingUtilities.isLeftMouseButton(e)) {
                    if ((a = stage.eventOnObject(e)) != null) {
                        UndoManager.get().storeDragStart(a, a.getxPos(), a.getyPos(), User.getSelectedState(), User.getSelected());
                        User.setSelected(a);
                        User.setSelectedState(SelectedState.DRAGGING);
                    } else {
                        User.setSelectedState(SelectedState.EMPTY);
                        User.setSelected(null);
                    }
                } else if (SwingUtilities.isRightMouseButton(e) && (a = stage.eventOnObject(e)) != null) {
                    User.setSelected(a);
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (User.getSelectedState().equals(SelectedState.DRAGGING)) {
                    UndoManager.get().storeDragEnd();
                    User.setSelectedState(SelectedState.SELECTED);
                }

            }


            @Override
            public void mouseEntered(MouseEvent e) {
                System.out.println("Entered stage");
                if (User.getSelectedState().equals(SelectedState.DRAGGING)) {
                    if (User.getMouseState(e).equals(MouseState.UP)) {
                        User.setSelectedState(SelectedState.SELECTED);
                    } else if (!stage.getAssets().contains(User.getSelected())) {
                        UndoManager.get().storeObjectPlacement(User.getSelected());
                        stage.getAssets().add(User.getSelected());
                        System.out.println("Added asset");
                    }
                }
            }
        });

        addMouseMotionListener(new MouseMotionAdapter() {

            public void mouseDragged(MouseEvent e) {
                if (User.getSelectedState().equals(SelectedState.DRAGGING)) {
                    User.getSelected().setxPos(e.getX());
                    User.getSelected().setyPos(e.getY());
                }
            }

            public void mouseMoved(MouseEvent e) {
                if (User.getSelectedState().equals(SelectedState.DRAGGING)) {
                    User.getSelected().setxPos(e.getX());
                    User.getSelected().setyPos(e.getY());
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
