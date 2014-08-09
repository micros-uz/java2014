package com.arslansoft;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

    public static ApplicationContext Ctx;

    public static void main(String[] args) {

        ApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);

        Ctx = ctx;

        ((Logger)ctx.getBean("hh2")).log("HAHA");

        ctx.getBean(Core.class).start();
    }
}
