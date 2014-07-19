package uz.micros.servletapi;

public interface Servlet {
    String getSiteName();
    String doGet(Request req);
    String doPost(Request req);
}
