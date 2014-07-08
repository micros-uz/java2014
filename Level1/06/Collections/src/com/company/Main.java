package com.company;

import java.util.ArrayList;
import java.util.Date;

public class Main {

    public static void main(String[] args) {
        ArrayList list = new ArrayList();

        list.add(123);
        list.add(new Date());
        list.add("23werwerwer");


        for (Object o: list)
            System.out.println(o);

        ArrayList<Integer> list2 = new ArrayList();

        list2.add(123);
        list2.add(3423);

        list2.size();

        int[] mas = {1, 3, 456, 0, 23, 90};

        Pair<Integer, Integer> res = getMax(mas);

        System.out.println("Max = " + res.getV() + " Index = " + res.getE());

        //print(234);
        //print("234234");
        print(new Person());
        print(new Employee());
        print(new Person(){
            @Override
            public String toString() {
                return "Anonimous " + getClass().getName();
            }
        });
     }

    private static Pair<Integer, Integer> getMax(int[] mas) {
        int res = 0;
        int idx = 0;

        for(int k = 0; k < mas.length; k++) {
            if (mas[k] > res) {
                res = mas[k];
                idx = k;
            }
        }

        return new Pair<>(res, idx);
    }

    private static <T extends Person> void print(T t){
        System.out.println(t);
    }
}

class Main$1{

}