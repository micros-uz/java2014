package uz.micros.plugins;

import uz.micros.CameraHost;
import uz.micros.PluginEx;

public class MXXPlugin implements PluginEx {

    private String lastCode;

    @Override
    public boolean allow(CameraHost cameraHost) {

        boolean valid = !lastCode.contains("x");

        if (!valid) {
            if (cameraHost != null)
                cameraHost.takePhoto();
        }

        return valid;
    }

    @Override
    public void notify(String code) {
        System.out.printf("Secure Registered: code = %s\n", code);

        lastCode = code;
    }


    public void f()
    {

    }
}
