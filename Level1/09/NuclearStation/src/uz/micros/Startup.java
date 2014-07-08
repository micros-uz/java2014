package uz.micros;

import org.jnativehook.keyboard.NativeKeyEvent;
import uz.micros.devices.BaseDevice;
import uz.micros.devices.Cooler;
import uz.micros.devices.Reactor;
import uz.micros.devices.Sensor;

import java.util.ArrayList;

import static uz.micros.Io.print;

public class Startup {
    private final int START_CMD = 1;
    private final int STOP_CMD = 2;
    private ArrayList<Device> devices = new ArrayList<>();
    private InputKeyListener listener = new InputKeyListener();

    public void run() {
        print("Nuclear Station firmware v1.0.9");

        init();

        int cmd;
        print("Station is waiting for a command...");

        do {
            cmd = listener.getKey();

            switch (cmd) {
                case NativeKeyEvent.VK_F11:
                    start();
                    break;
                case NativeKeyEvent.VK_F12:
                    stop();
                    break;
            }

        } while (cmd != NativeKeyEvent.VK_ESCAPE);

        listener.done();
    }

    private void stop() {
        for (Device dev : devices)
            dev.stop();
    }

    private void start() {
        for (Device dev : devices)
            dev.start();
    }

//PS C:\> (cmd /c dir c:)[((cmd /c dir).length)-1]

    private void init() {
        listener.init();

        devices.add(new Cooler());
        devices.add(new Cooler());
        devices.add(new Cooler());
        devices.add(new Cooler());
        devices.add(new Reactor());

        devices.add(new Sensor());
    }
}
