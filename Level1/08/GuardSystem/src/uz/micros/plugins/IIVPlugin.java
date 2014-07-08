package uz.micros.plugins;

import uz.micros.Plugin;

public class IIVPlugin implements Plugin{
    @Override
    public void notify(String code) {
        System.out.printf("Registered: code = %s\n", code);
    }
}
