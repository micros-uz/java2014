package com.company;


import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import java.util.Date;

public class Main {

    public static void main(String[] args) {

        Person p, p2;

        p = new Person(11, "John");

        p2 = new Person();

        save(p);
        save(p2);

        p.sayHello();
        p.sayHello("Hello");

        Employee e = new Employee(23);
        e.setName("Nick");

        e.sayHello();
        e.sayHello2();
        System.out.println(e);

        System.out.println(e.count);
    }

    private static void save(Person haha) {
        System.out.println(haha);

        System.out.println(haha.getAge());
        System.out.println(haha.getName());
    }


}
