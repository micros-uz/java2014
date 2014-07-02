package uz.micros.devices;

import uz.micros.Device;

public class Cooler implements Device {
    @Override
    public void start() {
        System.out.println("Cooler started");
    }

    @Override
    public void stop() {
        System.out.println("Cooler stopped");
    }
}
