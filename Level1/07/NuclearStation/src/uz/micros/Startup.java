package uz.micros;

import uz.micros.devices.Cooler;
import uz.micros.devices.Reactor;

import java.util.ArrayList;

import static uz.micros.Io.print;

public class Startup {
    private final int START_CMD = 1;
    private final int STOP_CMD = 2;

    private ArrayList<Device> devices = new ArrayList<>();

    public void run() {
        print("Nuclear Station firmware v1.0.9");

        init();

        int cmd;
        print("Station is waiting for a command...");

        do {
            cmd = Io.getCommand();

            switch (cmd) {
                case START_CMD:
                    start();
                    break;
                case STOP_CMD:
                    stop();
                    break;
            }

        } while (cmd != 0);
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
        devices.add(new Cooler());
        devices.add(new Cooler());
        devices.add(new Reactor());
    }
}
