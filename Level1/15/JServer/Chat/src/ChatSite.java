import uz.micros.servletapi.Request;
import uz.micros.servletapi.Servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;

public class ChatSite implements Servlet {

    ArrayList<String> messages = new ArrayList<>();

    @Override
    public String getSiteName() {
        return "chat";
    }

    @Override
    public String doGet(Request request) {
        String s = loadPage();

        String msgList = "";

        for(String st: messages)
            msgList = msgList + "<li>" + st + "</li>";

        return s.replace("%list%", msgList).replace("%ip%", request.getClientIp());
    }

    private String loadPage() {
        String res = null;

        BufferedReader br = new BufferedReader(new InputStreamReader(
                this.getClass().getResourceAsStream("index.html")));

        String st;
        StringBuilder sb = new StringBuilder();
        try {
            while ((st = br.readLine()) != null)
                sb.append(st);

            res = sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return res;
    }

    @Override
    public String doPost(Request request) {

        String formData = request.getHeaders().get(request.getHeaders().size() - 1);
        String[] parts = formData.split("=");

        if (parts.length > 1)
            try {
                messages.add(URLDecoder.decode(parts[1], "UTF-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }

        return "/chat";
    }
}
