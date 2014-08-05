package uz.micros.network;

import uz.micros.core.SettingManager;

import java.io.IOException;
import java.net.Socket;

public class Connector {
    private final int serverPort;
    private final String serverIp;
    private final ConnectorSink sink;

    public Connector(SettingManager settingManager, ConnectorSink connSink) {
        serverPort = settingManager.getServerPort();
        serverIp = settingManager.getServerIp();
        sink = connSink;
    }

    public void start() {
        try {
            System.out.println("Connector started");

            Socket socket = new Socket(serverIp, serverPort);

            sink.connected(socket);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
