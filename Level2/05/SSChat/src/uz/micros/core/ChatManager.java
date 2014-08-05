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
    private SettingManager settingManager;
    private Client client;

    public void start(String[] args) {
        settingManager = new SettingManager(args);

        mainWindow = new MainWindow(settingManager, this);
        mainWindow.display();

        if (settingManager.isServer())
            new Server(settingManager, this).start();
        else
            new Connector(settingManager, this).start();
    }

    @Override
    public void newClient(Socket socket) {
        Client client = new Client(this, false);
        client.start(socket);

        clients.add(client);
    }

    @Override
    public void gotMsg(String msg, Client sender) {
        if (settingManager.isServer()) {
            int n = msg.indexOf(":");

            if (n > -1) {
                if (n == 0) {
                    sendToAll(msg, sender);

                    mainWindow.newMsg(msg, sender.getName());
                } else {
                    String name = msg.substring(0, n - 1);

                    if (name.equals(settingManager.getUserName()))
                        mainWindow.newMsg(msg, sender.getName());
                    else
                        sendTo(msg.substring(n + 1, msg.length() - 1), name);
                }
            }
        } else {
            mainWindow.newMsg(msg, sender.getName());
        }
    }

    private void sendTo(String msg, String name) {
        for (Client c : clients)
            if (c.getName().equals(name)) {
                c.sendMsg(msg);
                break;
            }
    }

    private void sendToAll(String msg, Client sender) {
        for (Client c : clients)
            if (c != sender)
                c.sendMsg(msg);
    }

    @Override
    public void gotName(String name, Client client) {
        mainWindow.newClient(name);
    }

    @Override
    public void disconnected(Client client) {
        Client clientToRemove = null;

        for (Client c : clients)
            if (c == client) {
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
    public void sendMsg(String msg, String target) {
        if (settingManager.isServer()){
            if (target == null)
                sendToAll(":" + msg, null);
            else{
                sendTo(target + ":" + msg, target);
            }
        }else{
            if (target == null)
                target = "";

            client.sendMsg(target + ":" + msg);
        }
    }

    @Override
    public void connected(Socket socket) {
        client = new Client(this, true);
        client.start(socket);
    }
}