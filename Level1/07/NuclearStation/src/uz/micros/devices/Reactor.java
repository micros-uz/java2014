package uz.micros.devices;

import uz.micros.Device;

public class Reactor implements Device{
    @Override
    public void start() {
        System.out.println("Reactor started");
    }

    @Override
    public void stop() {
        System.out.println("Reactor stopped");
    }
}
