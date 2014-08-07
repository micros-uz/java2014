package uz.micros.core;

public class SettingManagerInMem implements SettingManager {
    private final boolean isServer;

    public SettingManagerInMem(CmdLineArgsManager argsMgr) {

        String[] args = argsMgr.getArgs();

        isServer = args.length > 0 && args[0].equals("server");
    }

    @Override
    public int getServerPort() {
        return 22011;
    }

    @Override
    public String getServerIp() {
        return "192.168.15.142";
    }

    @Override
    public boolean isServer() {
        return isServer;
    }

    @Override
    public String getUserName() {
        return isServer() ? "Usto-server" : "Usto";
    }
}
