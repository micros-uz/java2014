package uz.micros;

import uz.micros.gui.MainWindow;
import uz.micros.network.Server;

public class ChatManager {
    public void start() {
        Settings settings = new Settings();

        new MainWindow(settings).display();
        new Server(settings).start();
    }
}
