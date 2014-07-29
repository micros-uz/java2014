package uz.micros.gui;

import uz.micros.Settings;

import javax.swing.*;

public class MainWindow extends JFrame{
    private JPanel rootPanel;
    private JTextPane messageTextPane;
    private JTabbedPane tabbedPane1;
    private JList contactList;
    private JTextPane mainTextPane;

    public MainWindow(Settings settings) {

    }

    public void display() {
        setContentPane(rootPanel);
        setSize(650, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }
}
