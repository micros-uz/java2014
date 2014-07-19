package uz.micros.sites;


import uz.micros.servletapi.Request;
import uz.micros.servletapi.Servlet;

public class ForumServlet implements Servlet {
    @Override
    public String getSiteName() {
        return "forum";
    }

    @Override
    public String doGet(Request req) {
        System.out.println(req.getUrl());
        return "<h1>Hello from phpBB forum!</h1>";
    }

    @Override
    public String doPost(Request req) {
        return null;
    }
}
