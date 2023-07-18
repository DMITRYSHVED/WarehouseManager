package com.warehouse.util;

import org.springframework.stereotype.Service;

@Service
public class Helper {

    public String logParse(String log) {
        String date = log.substring(0, log.indexOf(",")) + " ";
        String message = " " + log.substring(log.lastIndexOf("- ") + 2);
        return date + message;
    }
}
