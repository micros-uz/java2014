import network.Server;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JMain extends JFrame{
    private Action acExit;

    public JMain() {
        setSize(800, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        createActions();
        createMenu();

        setVisible(true);

        new Server().start();
    }

    private void createActions() {
        acExit = new AbstractAction("Exit") {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        };
    }

    private void createMenu() {
        JMenuBar menuBar = new JMenuBar();

        setJMenuBar(menuBar);

        JMenu mnFile = new JMenu("File");
        mnFile.setMnemonic('f');

        JMenuItem mniExit = new JMenuItem("Exit");
        mniExit.setMnemonic('x');
        mniExit.addActionListener(acExit);

        mnFile.add(mniExit);

        menuBar.add(mnFile);
    }
}
