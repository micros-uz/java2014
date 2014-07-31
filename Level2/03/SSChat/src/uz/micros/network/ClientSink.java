package uz.micros.network;

public interface ClientSink {
    void gotMsg(String msg, Client client);
}
