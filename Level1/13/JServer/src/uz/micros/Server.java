package uz.micros;

import uz.micros.sites.BlogServlet;
import uz.micros.sites.ChatServlet;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Server {
    private List<Servlet> servlets = new ArrayList<Servlet>();
    private Map<String, Servlet> sites = new HashMap<String, Servlet>();

    public void run() {

        loadServlets();

        try {
            ServerSocket socket = new ServerSocket(80);

            for(;;) {
                Socket client = socket.accept();

                handleClient(client);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Server shutdown");
    }

    private void handleClient(Socket client) {
        new HttpHandler(client, sites).handle();
    }

    private void loadServlets() {

        servlets.add(new ChatServlet());
        servlets.add(new BlogServlet());

        for (Servlet servlet: servlets){
            sites.put(servlet.getSiteName(), servlet);
        }
    }
}
