package com.arslansoft;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Core {

    private final Downloader downloader;
    private final Parser parser;
    private final Uploader uploader;

    @Autowired
    public Core(Downloader d, Parser p, Uploader u) {
        downloader = d;
        parser = p;
        uploader = u;
    }

    public void start() {
        String html = downloader.get();

        String name = parser.parse(html);

        uploader.upload(name);
    }
}
