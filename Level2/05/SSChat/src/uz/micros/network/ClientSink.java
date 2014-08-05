package uz.micros.network;

public interface ClientSink {
    void gotMsg(String msg, Client client);
    void gotName(String name, Client client);
    void disconnected(Client client);

    String getHostName();
}
