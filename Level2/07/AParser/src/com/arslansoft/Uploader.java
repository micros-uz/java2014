package com.arslansoft;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Uploader {
    private final Logger log;

    @Autowired
    public Uploader(Logger logger) {
        log = logger;
    }

    public void upload(String name) {
        log.log(name);
    }
}
