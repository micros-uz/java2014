package uz.micros.core;

public class SettingManagerFile implements SettingManager {
    @Override
    public int getServerPort() {
        return 0;
    }

    @Override
    public String getServerIp() {
        return null;
    }

    @Override
    public boolean isServer() {
        return false;
    }

    @Override
    public String getUserName() {
        return null;
    }
}
