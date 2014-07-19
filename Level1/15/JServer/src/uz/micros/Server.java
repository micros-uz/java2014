package uz.micros;

import uz.micros.servletapi.Servlet;
import uz.micros.sites.BlogServlet;
import uz.micros.sites.ForumServlet;

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
        ServletLoader loader = new ServletLoader();

        try {
            Path sitesPath = Paths.get(new File(".").getCanonicalPath(), "sites");

            for (File f : sitesPath.toFile().listFiles()){
                System.out.println(f.getCanonicalPath());

                Servlet servlet = loader.loadServlet(f);

                if (servlet != null)
                    servlets.add(servlet);
            }


            servlets.add(new BlogServlet());
            servlets.add(new ForumServlet());

            for (Servlet servlet : servlets) {
                sites.put(servlet.getSiteName(), servlet);
                System.out.println("Servlet loaded: " + servlet.getSiteName());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
