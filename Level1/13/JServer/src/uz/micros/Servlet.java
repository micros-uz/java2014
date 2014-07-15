package uz.micros;

public interface Servlet {
    String getSiteName();
    String doGet(Request req);
    String doPost(Request req);
}
