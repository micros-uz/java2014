package uz.micros.core;

import uz.micros.gui.MainWindow;
import uz.micros.network.Client;
import uz.micros.network.Server;
import uz.micros.network.ServerSink;

import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ChatManager implements ServerSink {
    private List<Client> clients = new ArrayList<Client>();

    public void start() {
        SettingManager settingManager = new SettingManager();

        new MainWindow(settingManager).display();
        new Server(settingManager, this).start();
        //new MainWindow2().setVisible(true);
    }

    @Override
    public void newClient(Socket socket) {
        Client client = new Client();
        client.start(socket);

        clients.add(client);
    }
}
