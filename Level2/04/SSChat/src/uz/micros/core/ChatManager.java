package uz.micros.core;

import uz.micros.gui.GuiSink;
import uz.micros.gui.MainWindow;
import uz.micros.network.*;

import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ChatManager implements ServerSink, ClientSink, GuiSink, ConnectorSink {
    private List<Client> clients = new ArrayList<Client>();
    private MainWindow mainWindow;

    public void start() {
        SettingManager settingManager = new SettingManager();

        mainWindow = new MainWindow(settingManager, this);
        mainWindow.display();

        new Connector(settingManager, this).start();
        new Server(settingManager, this).start();
    }

    @Override
    public void newClient(Socket socket) {
        Client client = new Client(this, false);
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
    public void gotName(String name, Client client) {
        mainWindow.newClient(name);
    }

    @Override
    public void disconnected(Client client) {
        Client clientToRemove = null;

        for (Client c : clients)
            if (c == client){
                clientToRemove = c;
                break;
            }

        if (clientToRemove != null)
            clients.remove(clientToRemove);

        mainWindow.disconnected(client.getName());
    }

    @Override
    public String getHostName() {
        return null;
    }

    @Override
    public void sendMsg(String msg) {
        for (Client c : clients)
            c.sendMsg(msg);
    }

    @Override
    public void connected(Socket socket) {
        Client client = new Client(this, true);
        client.start(socket);
    }
}