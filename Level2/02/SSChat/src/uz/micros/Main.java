package uz.micros;

import uz.micros.core.ChatManager;

public class Main {

    public static void main(String[] args) {
        new ChatManager().start();

        System.out.println("uz.micros.Main Thread exit");
    }
}
