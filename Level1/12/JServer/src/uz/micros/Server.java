package uz.micros;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public void run() {
        try {
            ServerSocket socket = new ServerSocket(8080);

            for(;;) {
                Socket client = socket.accept();

                new HttpHandler(client).handle();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Server shutdown");
    }
}
