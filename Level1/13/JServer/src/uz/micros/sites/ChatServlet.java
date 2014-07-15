package uz.micros.sites;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import uz.micros.Request;
import uz.micros.Servlet;

public class ChatServlet implements Servlet {
    @Override
    public String getSiteName() {
        return "chat";
    }

    @Override
    public String doGet(Request req) {
        return "<h2>Hi from Servlet</h2>";
    }

    @Override
    public String doPost(Request req) {
        throw new NotImplementedException();
    }
}
