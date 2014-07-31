package uz.micros.core;

import uz.micros.gui.GuiSink;
import uz.micros.gui.MainWindow;
import uz.micros.network.Client;
import uz.micros.network.ClientSink;
import uz.micros.network.Server;
import uz.micros.network.ServerSink;

import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ChatManager implements ServerSink, ClientSink, GuiSink {
    private List<Client> clients = new ArrayList<Client>();

    public void start() {
        SettingManager settingManager = new SettingManager();

        new MainWindow(settingManager, this).display();
        new Server(settingManager, this).start();
    }

    @Override
    public void newClient(Socket socket) {
        Client client = new Client(this);
        client.start(socket);

        clients.add(client);
    }

    @Override
    public void gotMsg(String msg, Client client) {
        client.sendMsg("ANSWER : " + msg);

        for (Client c : clients)
            if (c != client)
                c.sendMsg("ONE SAYS: " + msg);
    }

    @Override
    public void sendMsg(String msg) {
        for (Client c : clients)
            c.sendMsg(msg);
    }
}