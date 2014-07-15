package uz.micros.sites;

import uz.micros.Request;
import uz.micros.Servlet;

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
