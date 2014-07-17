package de.supersoft;

import uz.micros.servletapi.Request;
import uz.micros.servletapi.Servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ChatServlet implements Servlet{
    @Override
    public String getSiteName() {
        return "chat";
    }

    @Override
    public String doGet(Request request) {
        return loadPage();
    }

    @Override
    public String doPost(Request request) {
        return null;
    }

    private String loadPage() {

        BufferedReader br = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream("index.html")));

        try {
            String line;
            StringBuilder sb = new StringBuilder();
            while ((line = br.readLine()) != null)
                sb.append(line);

            return sb.toString();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
