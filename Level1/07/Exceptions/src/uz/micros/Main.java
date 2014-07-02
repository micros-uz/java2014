package uz.micros;

import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        System.out.println("234234");

        Scanner scanner = new Scanner(System.in);

        String st  = scanner.nextLine();

        try {
            getNumber(st);
        } catch (RuntimeException e) {
            System.out.println("ERR in main");
        }

        System.out.println("NORMAL END");
    }

    private static void getNumber(String st) throws RuntimeException  {

        try {
            //int n = Integer.parseInt(st);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("ERR");
            throw new ArgumentException(e);
        } finally{
            System.out.println("FINALLY");
        }

        //System.out.println("GETNUMBER");
    }
}
