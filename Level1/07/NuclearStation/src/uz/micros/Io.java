package uz.micros;

import java.util.Scanner;

public class Io {

    private static Scanner scanner = new Scanner(System.in);

    public static void print(String st) {
        System.out.println(st);
    }

    public static int getCommand() {
        String st = scanner.nextLine();

        return Integer.parseInt(st);
    }
}
