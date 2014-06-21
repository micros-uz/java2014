package com.company;

import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Random gen = new Random();
        int x = gen.nextInt(100);

        System.out.println("Enter the number between 0 and 100");

        Scanner scanner = new Scanner(System.in);

        while(true) {
            String st = scanner.nextLine();

            if (st.matches("\\d+")) break;

            int n = Integer.parseInt(st);

            if (x == n) {
                System.out.println("You have won!");
                break;
            }
            else if (x > n)
                System.out.println("Your number is less");
            else
                System.out.println("Your number is greater");
        }
    }
}
