package readySETgo;

import panels.StagePanel;
import readySETgo.toolbar.FileMenu;
import readySETgo.toolbar.ToolsMenu;

import javax.swing.*;
import java.awt.*;
import java.net.URL;


public class MainFrame extends JFrame {
    public MainFrame(int width, int height) {
        super();
        setSize(width, height);
        this.setTitle("Stage Plan");

        URL iconUrl = getClass().getResource("/logo.jpg");
        ImageIcon logo = new ImageIcon(iconUrl);
        this.setIconImage(logo.getImage());
        fill();
        createMenuBar();

        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    private void fill() {
        JPanel panel1 = new StagePanel();
        JPanel panel2 = new StagePanel();
        JPanel panel3 = new StagePanel();

        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        c.gridx = 0;
        c.gridy = 0;
        c.weightx = .2;
        c.weighty = 1;
        c.fill = c.BOTH;
        add(panel1, c);

        c.gridx = 1;
        c.weightx = .6;
        add(panel2, c);

        c.gridx = 2;
        c.weightx = .2;
        add(panel3, c);
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
