package uz.micros.gui;

import uz.micros.core.SettingManager;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MainWindow extends JFrame {
    private JTextPane msgTextPane;
    private JTextPane mainTextPane;
    private JTabbedPane mainTabbedPane;
    private JList contactList;

    public MainWindow(SettingManager settingManager) {

    }

    public void display() {

        createControls();
        addListeners();

        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);

        msgTextPane.requestFocus();
    }

    private void addListeners() {
        msgTextPane.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER && e.isControlDown()){
                    sendMsg();
                }
            }
        });
    }

    private void sendMsg() {
        String msg = msgTextPane.getText();

        if (msg.length() > 0){
            ///
            msgTextPane.setText("");
        }
    }

    private void createControls() {
        JSplitPane mainSplitter = new JSplitPane();
        mainSplitter.setOrientation(JSplitPane.VERTICAL_SPLIT);
        mainSplitter.setDividerLocation(400);

        msgTextPane = new JTextPane();
        mainSplitter.setRightComponent(new JScrollPane(msgTextPane));

        JSplitPane topSplitter = new JSplitPane();
        topSplitter.setDividerLocation(580);
        mainSplitter.setLeftComponent(topSplitter);

        mainTabbedPane = new JTabbedPane();
        mainTextPane = new JTextPane();
        mainTabbedPane.addTab("Main", new JScrollPane(mainTextPane));
        topSplitter.setLeftComponent(mainTabbedPane);

        contactList = new JList();
        topSplitter.setRightComponent(new JScrollPane(contactList));

        getContentPane().add(mainSplitter);
    }
}
