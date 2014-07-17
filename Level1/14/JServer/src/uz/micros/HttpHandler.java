package uz.micros;

import uz.micros.servletapi.Request;
import uz.micros.servletapi.Servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HttpHandler {
    private final Socket socket;
    private final Map<String, Servlet> sites;

    private PrintWriter out;
    private BufferedReader in;

    public HttpHandler(Socket socket, Map<String, Servlet> sites) {
        this.socket = socket;
        this.sites = sites;

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
            } while (msg != null && !msg.equals(""));

            handlePacket(packet);

            socket.close();

        } catch (IOException e) {
            //e.printStackTrace();
        }
    }

    private void handlePacket(ArrayList<String> packet) {
        if (packet != null && packet.size() > 0) {
            String req = packet.get(0);

            if (req != null) {
                String[] parts = req.split(" ");

                if (parts.length > 1) {
                    Servlet servlet = getServlet(parts[1]);

                    if (servlet != null) {
                        Request request = new Request(parts[1],
                                socket.getInetAddress().getHostAddress(),
                                packet);

                        switch (parts[0]) {
                            case "GET":
                                sendGetRes(servlet.doGet(request));
                                break;
                            case "POST":
                                String res2 = servlet.doPost(request);
                                handlePost(parts);
                                break;
                        }
                    }
                }
            }
        }
    }

    private Servlet getServlet(String part) {
        Servlet res = null;
        String[] parts = part.split("/");

        if (parts.length > 1 && parts[1].length() > 0)
            res = sites.get(parts[1]);

        return res;
    }

    private void handlePost(String[] parts) {

    }

    private void sendGetRes(String content) {

        //String page = loadPage();

        out.println("HTTP/1.1 200 OK");
        out.println("Server: JServer 1.0");
        out.println("Content-Type: text/html");
        out.println("Content-Length: " + content.length());
        out.println();
        out.println(content);
    }

    private String loadPage() {
        Path path = Paths.get("src/uz/micros/index.html");
        try {
            List<String> lines = Files.readAllLines(path);

            StringBuilder sb = new StringBuilder();
            for (String s : lines)
                sb.append(s);

            return sb.toString();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public void handle() {
        new Thread() {
            public void run() {
                readPacket();
            }
        }.start();
    }
}
