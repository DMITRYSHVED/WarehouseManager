package com.warehouse.util;

import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Service
public class Helper {

    public String logParse(String log) throws Exception {
        String date = log.substring(0, log.indexOf(",")) + " ";
        String message = " " + log.substring(log.lastIndexOf("- ") + 2);
        return date + message;
    }

    public String formatDate(Date date) {
        Instant instant = date.toInstant();
        ZoneId zoneId = ZoneId.of("Europe/Moscow");
        DateTimeFormatter userFriendlyDateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd").withZone(zoneId);
        return userFriendlyDateFormat.format(instant);
    }
}
