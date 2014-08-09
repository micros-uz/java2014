package com.arslansoft;

import org.springframework.stereotype.Component;

@Component
public class SrcUrlProvider {

    public String getUrl() {
        return "http://ozon.ru";
    }
}
