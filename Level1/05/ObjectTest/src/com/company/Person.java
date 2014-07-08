package com.company;

import java.io.PrintStream;
import java.util.Date;

/*final */
public class Person extends Object{
    static public int count;
    private int age;
    private boolean isMarried;
    private String name;
    final private Date birthDay;
    private boolean passedBorder;

    public Person() {
        count++;
        birthDay = new Date();
        passedBorder = false;
        isMarried = false;
    }

    public Person(int i, String john)
    {

        this();
        age = i;
        name = john;
    }

    public static Person construct(){
        return new Person();
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age)  {
        if (age <= 168)
            this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isMarried() {
        return isMarried;
    }

    public void sayHello() {
        System.out.println("Hi, my name is " + getName());
    }

    public void sayHello(String hello) {
        System.out.println(3142134);
    }

    @Override
    /*final */public String toString() {
        return getName() + " - " + getAge();
    }

    protected void sayHello2(){
        System.out.println(getName());
    }
}
