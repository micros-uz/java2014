package uz.micros;

import com.sun.deploy.util.StringUtils;

import java.io.*;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class HttpHandler {
    private final Socket socket;
    private PrintWriter out;
    private BufferedReader in;

    public HttpHandler(Socket socket) {
        this.socket = socket;

        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);
        } catch (IOException e) {
            //e.printStackTrace();
        }
    }

    public void readPacket() {
        ArrayList<String> packet = new ArrayList<String>();

        try {
            String msg;

            do {
                msg = in.readLine();
                packet.add(msg);
            }while (msg != null && !msg.equals(""));

            handlePacket(packet);

        } catch (IOException e) {
            //e.printStackTrace();
        }
    }

    private void handlePacket(ArrayList<String> packet) {
        if (packet != null && packet.size() > 0){
            String req = packet.get(0);

            if (req != null) {
                String[] parts = req.split(" ");

                if (parts.length > 1) {
                    switch (parts[0]) {
                        case "GET":
                            handleGet(parts[1]);
                            break;
                        case "POST":
                            handlePost(parts);
                            break;
                    }
                }
            }
        }
    }

    private void handlePost(String[] parts) {

    }

    private void handleGet(String url) {
        System.out.println(url);

        String page = loadPage();

        out.println("HTTP/1.1 200 OK");
        out.println("Server: JServer 1.0");
        out.println("Content-Type: text/html");
        out.println("Content-Length: " + page.length());
        out.println();
        out.println(page);
    }

    private String loadPage() {
        Path path = Paths.get("src/uz/micros/index.html");
        try {
            List<String> lines = Files.readAllLines(path);

            StringBuilder sb = new StringBuilder();
            for (String s: lines)
                sb.append(s);

            return sb.toString();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public void handle() {
        new Thread(){
            public void run(){
                readPacket();
            }
        }.start();
    }
}
