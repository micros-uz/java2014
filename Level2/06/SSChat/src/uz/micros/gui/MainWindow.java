package uz.micros.gui;

import uz.micros.core.SettingManager;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainWindow extends JFrame {
    private final GuiSink sink;
    private final SettingManager settingManager;
    private JTextPane msgTextPane;
    private JTextPane mainTextPane;
    private JTabbedPane mainTabbedPane;
    private JList contactList;
    private DefaultListModel<String> contacts;
    private TabPaneManager tabPaneManager;

    public MainWindow(SettingManager sm, GuiSink guiSink) {
        sink = guiSink;
        settingManager = sm;
        setTitle(sm.isServer() ? "Server" : "Client");
    }

    public void display() {

        createControls();
        addListeners();

        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);

        msgTextPane.requestFocus();
        mainTextPane.setEditable(false);

        tabPaneManager = new TabPaneManager(mainTabbedPane);
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

        contactList.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (e.getClickCount() == 2 && contactList.getSelectedValue() != null)
                    tabPaneManager.selectTab(contactList.getSelectedValue().toString());
            }
        });

    }

    private void sendMsg() {
        String msg = msgTextPane.getText();

        if (msg.length() > 0){

            sink.sendMsg(msg, tabPaneManager.getActiveTabTitle());

            msgTextPane.setText("");

            tabPaneManager.setTabText(null, msg, settingManager.getUserName());
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

        contacts = new DefaultListModel<String>();
        contactList.setModel(contacts);
        contactList.setCellRenderer(new ContactsListCellRenderer());
    }

    public void newClient(String name) {
        tabPaneManager.addTab(name);
        contacts.addElement(name);
    }

    public void disconnected(String name) {
        contacts.removeElement(name);
        tabPaneManager.delTab(name);
    }

    public void newMsg(String msg, String name) {
        tabPaneManager.newMsg(msg, name);
    }
}
