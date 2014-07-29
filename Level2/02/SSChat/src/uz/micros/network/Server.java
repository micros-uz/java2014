package uz.micros.network;

import uz.micros.core.SettingManager;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private final SettingManager settingManager;
    private final ServerSink serverSink;

    public Server(SettingManager sm, ServerSink sink) {
        settingManager = sm;
        serverSink = sink;
    }

    public void start() {

        try {
            ServerSocket socket = new ServerSocket(settingManager.getServerPort());

            System.out.println("Server started");

            while(true){
                Socket clientSocket = socket.accept();

                serverSink.newClient(clientSocket);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
