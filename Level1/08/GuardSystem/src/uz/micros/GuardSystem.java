package uz.micros;


import uz.micros.plugins.IIVPlugin;
import uz.micros.plugins.MXXPlugin;

import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class GuardSystem implements CameraHost {
    private ArrayList<Plugin> plugins = new ArrayList<Plugin>();

    public void run() {
        System.out.println("Guard System v1.0");

        loadPlugins();

        System.out.println("Enter your code");

        String code = new Scanner(System.in).nextLine();

        boolean valid = checkCode(code);

        System.out.println(valid ? "You are allowed to pass"
                : "You are prohibited to pass");
    }

    private void loadPlugins() {
        plugins.add(new IIVPlugin());
        plugins.add(new MXXPlugin());
    }

    private boolean checkCode(String code) {
        System.out.printf("Code %s Date %s\n", code, new Date());
        boolean valid = true;

        for (Plugin p : plugins) {
            p.notify(code);

            if (PluginEx.class.isAssignableFrom(p.getClass())) {
                boolean allowed = ((PluginEx) p).allow(this);

                valid &= allowed;
            }
        }

        return valid;
    }

    @Override
    public void takePhoto() {

    }
}
