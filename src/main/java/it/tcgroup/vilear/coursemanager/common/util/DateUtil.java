package it.tcgroup.vilear.coursemanager.common.util;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Date;

@Component
public class DateUtil {

    public String convertUTCDateToIS08601String(Date date) {

        if (date == null) {
            return null;
        }

        ZonedDateTime dateTime = ZonedDateTime.ofInstant(date.toInstant(), ZoneId.of("UCT"));
        return dateTime.format(new DateTimeFormatterBuilder().append(DateTimeFormatter.ISO_LOCAL_DATE_TIME).optionalStart().appendOffset("+HH:MM", "+00:00").optionalEnd().toFormatter());
    }


    public Date convertIS08601StringToUTCDate(String dateString) {

        if (dateString == null || dateString.isEmpty()) {
            return null;
        }

        LocalDateTime dateTime = LocalDateTime.parse(dateString, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        return Date.from(dateTime.toInstant(ZoneOffset.UTC));
    }

    public Date getNowDate(){

        LocalDateTime dateTime = LocalDateTime.parse(LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        return Date.from(dateTime.toInstant(ZoneOffset.UTC));
    }
}
