package uz.micros.devices;

import uz.micros.Device;

public abstract class BaseDevice implements Device{
    private static int counter = 0;
    private boolean isStarted;
    private int id;

    public BaseDevice() {
        id = ++counter;
    }

    protected String getName(){
        return getClass().getSimpleName();
    }

    protected int getId(){
        return id;
    }

    @Override
    public void start() {
        if (!isStarted){
            System.out.println(getName() + " started ");
            isStarted = true;
        }
    }

    @Override
    public void stop() {
        if (isStarted){
            System.out.println(getName() + " stopped");
            isStarted = false;
        }
    }
}
