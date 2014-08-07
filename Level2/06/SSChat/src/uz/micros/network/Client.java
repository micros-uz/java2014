package uz.micros.network;

import uz.micros.core.ChatManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
    private final ClientSink sink;
    private final boolean isActive;
    private PrintWriter out;
    private BufferedReader in;
    private String name;

    public Client(ClientSink clientSink, boolean active) {
        sink = clientSink;
        isActive = active;
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

            if (isActive){
                out.println(sink.getHostName());
                name = in.readLine();
            }else {
                name = in.readLine();
                out.println(sink.getHostName());
            }
            sink.gotName(name, this);

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
            sink.disconnected(this);
        }catch(IOException e){
            sink.disconnected(this);
        }
    }

    public void sendMsg(String msg) {
        out.println(msg);
    }

    public String getName() {
        return name;
    }
}
