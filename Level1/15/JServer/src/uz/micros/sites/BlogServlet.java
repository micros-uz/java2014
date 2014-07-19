package uz.micros.sites;

import uz.micros.servletapi.Request;
import uz.micros.servletapi.Servlet;

public class BlogServlet implements Servlet {
    @Override
    public String getSiteName() {
        return "blog";
    }

    @Override
    public String doGet(Request req) {
        return "<h1>BLOG</h1>";
    }

    @Override
    public String doPost(Request req) {
        return null;
    }
}
