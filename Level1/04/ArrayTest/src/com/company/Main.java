package com.company;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {

        int[] m = new int[5];

        for (int k = 0; k < 5; ++k) {
            m[k] = k;
        }

        for (int n : m)
            System.out.println(n);

        int[] m2 = m.clone();


        if (Arrays.equals(m, m2))
            System.out.println("YES");

        for (int k = 0; k < 5; k++)
            if (m[k] != m2[k]) {
                System.out.println("NO");
                break;
            }
    }
}
