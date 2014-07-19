package uz.micros;


import uz.micros.servletapi.Servlet;
import uz.micros.sites.BlogServlet;

import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Server {
    private List<Servlet> servlets = new ArrayList<Servlet>();
    private Map<String, Servlet> sites = new HashMap<String, Servlet>();

    public void run() {
        System.out.println("Server starting...");

        loadServlets();

        System.out.println("Server started");
        System.out.println("Current dir: " + new File(".").getAbsolutePath());

        try {
            ServerSocket socket = new ServerSocket(80);

            for (; ; ) {
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
        Path sitesPath = null;
        ServletLoader loader = new ServletLoader();

        try {
            sitesPath = Paths.get(new File(".").getCanonicalPath(), "sites");

            for (File f : sitesPath.toFile().listFiles()) {
                System.out.println(f.getName());
                Servlet servlet = loader.loadServlet(f);
                if (servlet != null)
                    servlets.add(servlet);
            }

            servlets.add(new BlogServlet());

            for (Servlet servlet : servlets) {
                sites.put(servlet.getSiteName(), servlet);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
