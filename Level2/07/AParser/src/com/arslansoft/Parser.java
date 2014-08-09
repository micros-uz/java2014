package com.arslansoft;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Parser {
    private final Logger log;

    @Autowired
    public Parser(Logger logger) {
        log = logger;
    }

    public String parse(String html) {
        int n1 = html.indexOf("<p>");
        int n2 = html.indexOf("</p>");

        log.log("Parsed at: ");

        return html.substring(n1 + 3, n2);
    }
}
