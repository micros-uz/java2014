package uz.micros.network;

import uz.micros.core.ChatManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
    private final ClientSink sink;
    private PrintWriter out;
    private BufferedReader in;

    public Client(ClientSink clientSink) {
        sink = clientSink;
    }

    public void start(final Socket socket) {
        new Thread(){
            @Override
            public void run() {
                startInternal(socket);
            }
        }.start();
    }

    private void startInternal(Socket socket) {
        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);

            listen();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void listen() {
        String msg = "";

        try {
            while (msg != null) {
                msg = in.readLine();
                sink.gotMsg(msg, this);
            }
        }catch(IOException e){

        }
    }

    public void sendMsg(String msg) {
        out.println(msg);
    }
}
