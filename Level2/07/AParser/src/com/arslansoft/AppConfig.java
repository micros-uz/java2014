package com.arslansoft;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.arslansoft")
public class AppConfig {

    @Bean(name = "hh")
    public Logger getLogger(){

        System.out.println("Logger created");

        Logger res = new Logger();
        res.clearCache();

        return res;
    }

    @Bean(name = "hh2")
    public Logger getLogger2(){

        System.out.println("Logger created");

        return new Logger();
    }
}
