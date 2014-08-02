package uz.micros.gui;

import com.sun.org.apache.xml.internal.serializer.Version;

import javax.swing.*;
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
}
