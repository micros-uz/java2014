package uz.micros.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
    private PrintWriter out;
    private BufferedReader in;

    public void start(Socket socket) {
        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream())));
            out = new PrintWriter(socket.getOutputStream(), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
