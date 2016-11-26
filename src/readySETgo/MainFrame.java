package readySETgo;

import readySETgo.panels.ContainerPanel;
import readySETgo.toolbar.FileMenu;
import readySETgo.toolbar.ToolsMenu;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.*;


public class MainFrame extends JFrame {
    private ContainerPanel container;

    public MainFrame(int width, int height) {
        super();
        setSize(width, height);
        try {
            this.setIconImage(ImageIO.read(new File("res/logo.png")));
        } catch (IOException e) {
        }
        this.setTitle("Stage Plan");
        fill();
        createMenuBar();

        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void fill() {
        container = new ContainerPanel();

        add(container);
    }

    private void createMenuBar() {
        // Create MenuBar
        JMenuBar menuBar = new JMenuBar();

        // Create menus
        JMenu fileMenu = new FileMenu();
        JMenu toolsMenu = new ToolsMenu();

        // Fill Menus
        menuBar.add(fileMenu);
        menuBar.add(toolsMenu);

        // Register menu bar
        setJMenuBar(menuBar);
    }

}
