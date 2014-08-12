package uz.micros.core;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;

public class SettingManagerInMem implements SettingManager {
    private final boolean isServer;

    public SettingManagerInMem(CmdLineArgsManager argsMgr) {

        String[] args = argsMgr.getArgs();

        isServer = args.length > 0 && args[0].equals("server");
    }

    @Override
    public int getServerPort() {
        return 60001;
    }

    @Override
    public String getServerIp() {
        return "localhost";
    }

    @Override
    public boolean isServer() {
        return isServer;
    }

    @Override
    public String getUserName() {
        return isServer() ? "Usto-server" : getHostName();
    }

    private String getHostName(){
        String res = "Unknown " + new Date().hashCode();

        try
        {
            InetAddress addr;
            addr = InetAddress.getLocalHost();
            res = addr.getHostName();
        }
        catch (UnknownHostException ex)
        {
            System.out.println("Hostname can not be resolved");
        }

        return res;
    }
}
