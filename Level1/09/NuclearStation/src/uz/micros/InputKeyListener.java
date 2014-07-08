package uz.micros;

import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

public class InputKeyListener implements NativeKeyListener {

    private int key;

    public int getKey() {
        return key;
    }

    public void init() {
        try {
            GlobalScreen.registerNativeHook();
        } catch (NativeHookException e) {
            e.printStackTrace();
        }

        GlobalScreen.getInstance().addNativeKeyListener(this);
    }

    @Override
    public void nativeKeyPressed(NativeKeyEvent nativeKeyEvent) {
        key = nativeKeyEvent.getKeyCode();
    }

    @Override
    public void nativeKeyReleased(NativeKeyEvent nativeKeyEvent) {
    }

    @Override
    public void nativeKeyTyped(NativeKeyEvent nativeKeyEvent) {
    }

    public void done() {
        GlobalScreen.getInstance().removeNativeKeyListener(this);
        GlobalScreen.unregisterNativeHook();
    }
}
