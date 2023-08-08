package com.warehouse;

import com.warehouse.util.Helper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

@SpringBootTest
public class HelperTest {

    @Autowired
    private Helper helper;

    @Test
    public void testLogParse() throws Exception {
        String log = "08-06 17:16:35,736 [http-nio-8080-exec-7] INFO  c.w.controller.DeliveryController - Пользователь admin запланировал поставку на 2023-09-10";
        String expected = "08-06 17:16:35  Пользователь admin запланировал поставку на 2023-09-10";

        String result = helper.logParse(log);

        assertEquals(expected, result);
    }



    @Test
    public void testFormatDate() {
        Date date = new Date();
        Instant instant = date.toInstant();
        ZoneId zoneId = ZoneId.of("Europe/Moscow");
        DateTimeFormatter userFriendlyDateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd").withZone(zoneId);
        String expected = userFriendlyDateFormat.format(instant);

        String result = helper.formatDate(date);

        assertEquals(expected, result);
    }
}

