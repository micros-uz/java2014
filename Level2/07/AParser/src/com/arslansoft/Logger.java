package com.arslansoft;

import org.springframework.stereotype.Component;

import java.util.Date;

//@Component
public class Logger {
    public void log(String st){
        System.out.println(new Date().toString() + " :" + st);
    }

    public void clearCache() {
        //((Logger)Main.Ctx.getBean("hh2")).log("qwerwer");
    }
}
