package uz.micros.servletapi;

import java.util.List;

public class Request {
    private String url;
    private String clientIp;
    private List<String> headers;

    public Request(String url, String clientIp, List<String> headers) {
        this.url = url;
        this.clientIp = clientIp;
        this.headers = headers;
    }

    public String getUrl() {
        return url;
    }

    public String getClientIp() {
        return clientIp;
    }

    public List<String> getHeaders() {
        return headers;
    }
}
