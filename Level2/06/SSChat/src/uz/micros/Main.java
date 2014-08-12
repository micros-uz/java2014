package uz.micros;

import uz.micros.core.*;
import uz.micros.network.Server;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {

        //Bootstrapper.init();

        CmdLineArgsManager argsMgr = new CmdLineArgsManager();

        argsMgr.init(args);

        SettingManager sm = new SettingManagerInMem(argsMgr);

        new ChatManager(sm).start();

        //IoC.get(CmdLineArgsManager.class).init(args);
        //IoC.get(SettingManager.class).isServer();
    }
}
