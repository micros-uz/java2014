package com.arslansoft;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class Downloader {

    @Autowired
    public Downloader(SrcUrlProvider srcUrlProvider) {
        String url = srcUrlProvider.getUrl();

        new Logger().log(url);
    }

    public String get() {
        new Logger().log("Start Download: " + new Date().toString());

        return "<h1>Name:</h1><br><p>Nokia 2110</p>";
    }
}
