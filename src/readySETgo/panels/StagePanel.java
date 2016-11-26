package readySETgo.panels;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;

import javax.swing.JPanel;
import javax.swing.Timer;

import backend.models.Asset;
import backend.models.Stage;
import readySETgo.PrintManager;
import readySETgo.User;
import readySETgo.User.MouseState;
import readySETgo.User.SelectedState;
import readySETgo.contextmenus.StagePopUp;

public class StagePanel extends JPanel implements Printable {
    private Stage stage;

    public StagePanel() {
        super();
        PrintManager.getManager().register(this);
        stage = new Stage();

        Timer t = new Timer(10, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                repaint();
            }
        });
        t.start();

        addMouseListener(StagePopUp.createAdapter());
        addMouseListener(new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent e) { //TODO Store objects original position so if invalid placement it is put back
                Asset a;
                if ((a = stage.eventOnObject(e)) != null) {
                    User.setSelected(a);
                    User.setSelectedState(SelectedState.DRAGGING);
                } else {
                    User.setSelectedState(SelectedState.EMPTY);
                    User.setSelected(null);
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (User.getSelectedState().equals(SelectedState.DRAGGING)) {
                    User.setSelectedState(SelectedState.SELECTED);
                }

            }


            @Override
            public void mouseEntered(MouseEvent e) {
                System.out.println("Entered stage");
                if (User.getSelectedState().equals(SelectedState.DRAGGING)) {
                    if (User.getMouseState(e).equals(MouseState.UP)) {
                        User.setSelectedState(SelectedState.SELECTED);
                    } else {
                        //TODO probably need to make sure it hasnt already been added
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
        Graphics2D g2d = (Graphics2D) graphics;
        double scale = 0.6;
        g2d.scale(scale,scale);

        g2d.translate(pageFormat.getImageableX(), pageFormat.getImageableY());
        printAll(graphics);
        return PAGE_EXISTS;
    }
}
