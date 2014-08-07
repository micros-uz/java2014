package uz.micros;

import uz.micros.core.CmdLineArgsManager;
import uz.micros.core.IoC;
import uz.micros.core.SettingManager;
import uz.micros.core.SettingManagerInMem;

public class Bootstrapper {
    public static void init() {
        IoC.register(SettingManager.class, SettingManagerInMem.class);
        IoC.register(CmdLineArgsManager.class, CmdLineArgsManager.class);
    }
}
