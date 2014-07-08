package uz.micros.devices;

import uz.micros.Device;

public class Cooler extends BaseDevice implements Device {
    @Override
    protected String getName() {
        return "Cooler " + getId();
    }
}
