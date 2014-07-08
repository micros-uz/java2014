package uz.micros.devices;

import java.util.Timer;
import java.util.TimerTask;

public class Sensor extends BaseDevice {
    private Timer timer;

    public Sensor() {
        //timer = new Timer();
        //timer.schedule(new SensorTimer(this) , 2000, 1000);
    }
}
