package uz.micros.gui;

import com.sun.org.apache.xml.internal.serializer.Version;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TabPaneManager {
    private final JTabbedPane tabbedPane;

    public TabPaneManager(JTabbedPane pane) {
        tabbedPane = pane;

        tabbedPane.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (e.getClickCount() == 2)
                    closeTab();
            }
        });
    }

    private void closeTab() {
        removeTab(getSelTabIndex());
    }

    private void removeTab(int tabIndex) {
        if (tabIndex > 0)
            tabbedPane.remove(tabIndex);
    }

    private int getSelTabIndex() {
        return tabbedPane.getSelectedIndex();
    }

    public void selectTab(String title) {
        int n = findTab(title);

        if (n == -1)
            addTab(title);
        else
            tabbedPane.setSelectedIndex(n);
    }

    public void addTab(String title) {
        JTextPane textPane = new JTextPane();
        textPane.setEditable(false);

        tabbedPane.addTab(title, new JScrollPane(textPane));
    }

    private int findTab(String title) {
        return tabbedPane.indexOfTab(title);
    }

    public void delTab(String title) {
        int n = findTab(title);
        removeTab(n);
    }

    public void newMsg(String msg, String name) {
        int n = msg.indexOf(":");

        if (n > -1){
            int tabIndex = n == 0 ? 0 : findTab(name);

            if (tabIndex > -1){
                JTextPane textPane = getTextPane(tabIndex);

                setTabText(textPane, msg.substring(n + 1, msg.length()), name);
            }
        }
    }

    private JTextPane getTextPane(int tabIndex) {
        JScrollPane scrollPane = (JScrollPane)tabbedPane.getComponentAt(tabIndex);

        return (JTextPane) scrollPane.getViewport().getComponent(0);
    }

    public void setTabText(JTextPane textPane, String msg, String userName) {
        if (textPane == null)
            textPane = getTextPane(getSelTabIndex());

        StyledDocument doc = textPane.getStyledDocument();
        SimpleAttributeSet attr = new SimpleAttributeSet();

        StyleConstants.setForeground(attr, Color.BLUE);
        StyleConstants.setBold(attr, true);

        try {
            doc.insertString(doc.getLength(), userName + ": \n", attr);
            doc.insertString(doc.getLength(), msg + "\n", null);
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
    }

    public String getActiveTabTitle() {
        int n = getSelTabIndex();

        return n == 0 ? null : tabbedPane.getTitleAt(n);
    }
}
