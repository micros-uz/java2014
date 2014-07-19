package de.supersoft;

import uz.micros.servletapi.Request;
import uz.micros.servletapi.Servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class ChatServlet implements Servlet{
    ArrayList<String> messages = new ArrayList<>();

    @Override
    public String getSiteName() {
        return "chat";
    }

    @Override
    public String doGet(Request request) {
        String res = loadPage();

        String msgList = "";

/*
        for (String s: messages)
            msgList = msgList + "<li>" + s + "</li>";
*/

        msgList = messages.stream().map(s -> "<li>" + s + "</li>")
                .collect(Collectors.joining()).toString();

        return res.replace("%list%", msgList);
    }

    @Override
    public String doPost(Request request) {

        String formData = request.getHeaders().get(request.getHeaders().size() - 1);
        messages.add(formData.split("=")[1]);

        return "/chat";
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
