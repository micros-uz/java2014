package uz.micros.core;

public class SettingManager {
    private final boolean isServer;

    public SettingManager(String[] args) {
        isServer = args.length > 0 && args[0].equals("server");
    }

    public int getServerPort() {
        return 60001;
    }

    public String getServerIp() {
        return "192.168.15.140";
    }

    public boolean isServer() {
        return isServer;
    }

    public String getUserName() {
        return isServer() ? "Usto-server" : "Usto";
    }
}
