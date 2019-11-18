package it.tcgroup.vilear.coursemanager.common.util;

import it.tcgroup.vilear.coursemanager.common.exception.BadParametersException;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Calendar;
import java.util.Date;

@Component
public class DateUtil {

    /******************GET LOCAL DATE******************/
    public LocalDate convertUTCDateToLocalDate(Date date){

        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

    }

    public LocalDate convertUTCInstantToLocalDate(Instant date){

        return date.atZone(ZoneId.systemDefault()).toLocalDate();

    }

    public LocalDate convertIS08601StringToLocalDate(String dateString){

        if(dateString == null)
            return null;

        Instant instantDate = this.convertIS08601StringToUTCInstant(dateString);

        return instantDate.atZone(ZoneId.systemDefault()).toLocalDate();

    }
    /**************************************************/

    /******************GET LOCAL DATE TIME******************/
    public LocalDateTime convertUTCDateToLocalDateTime(Date date){

        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();

    }

    public LocalDateTime convertUTCInstantToLocalDateTime(Instant date){

        return date.atZone(ZoneId.systemDefault()).toLocalDateTime();

    }

    public LocalDateTime convertIS08601StringToLocalDateTime(String dateString){

        Instant instantDate = convertIS08601StringToUTCInstant(dateString);

        return instantDate.atZone(ZoneId.systemDefault()).toLocalDateTime();

    }
    /*******************************************************/

    /******************GET DATE******************/
    public Date convertLocalDateToDate(LocalDate localDate){

        return Date.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());

    }

    public Date convertLocalDateToDate(LocalDateTime localDate){

        return Date.from(localDate.atZone(ZoneId.systemDefault()).toInstant());

    }

    public Date convertIS08601StringToUTCDate(String dateString) {

        if (dateString == null || dateString.isEmpty()) {
            return null;
        }

        LocalDateTime dateTime = LocalDateTime.parse(dateString, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        return Date.from(dateTime.toInstant(ZoneOffset.UTC));
    }
    /********************************************/

    /******************GET INSTANT******************/
    public  Instant convertLocalDateToInstant(LocalDateTime localDate){

        return localDate.atZone(ZoneId.systemDefault()).toInstant();
    }

    public  Instant convertLocalDateToInstant(LocalDate localDate){

        return localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant();
    }

    public Instant convertIS08601StringToUTCInstant(String dateString) {

        if (dateString == null || dateString.isEmpty()) {
            return null;
        }

        LocalDateTime dateTime = LocalDateTime.parse(dateString, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        return Instant.from(dateTime.toInstant(ZoneOffset.UTC));
    }
    /***********************************************/


    /******************GET STRING DATE/DATE_TIME******************/

    //DATA FORMATTATA SECONDO IL PATTERN dd/MM/yyyy
    public  String convertLocalDateToString(LocalDate localDate){

        return localDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    public  String convertLocalDateTimeToIS08601String(LocalDateTime localDateTime){

        localDateTime = localDateTime.withNano(0);

        ZonedDateTime dateTime = ZonedDateTime.ofInstant(localDateTime.toInstant(ZoneOffset.UTC), ZoneId.of("UCT"));
        return dateTime.format(new DateTimeFormatterBuilder().append(DateTimeFormatter.ISO_LOCAL_DATE_TIME).optionalStart().appendOffset("+HH:MM", "+00:00").optionalEnd().toFormatter());

    }

    public String convertUTCInstantToIS08601String(Instant date) {

        if (date == null) {
            return null;
        }

        ZonedDateTime dateTime = ZonedDateTime.ofInstant(date, ZoneId.of("UCT"));
        return dateTime.format(new DateTimeFormatterBuilder().append(DateTimeFormatter.ISO_LOCAL_DATE_TIME).optionalStart().appendOffset("+HH:MM", "+00:00").optionalEnd().toFormatter());
    }

    public String convertUTCDateToIS08601String(Date date) {

        if (date == null) {
            return null;
        }

        ZonedDateTime dateTime = ZonedDateTime.ofInstant(date.toInstant(), ZoneId.of("UCT"));
        return dateTime.format(new DateTimeFormatterBuilder().append(DateTimeFormatter.ISO_LOCAL_DATE_TIME).optionalStart().appendOffset("+HH:MM", "+00:00").optionalEnd().toFormatter());
    }
    /************************************************************/

    public Date getNowDate(){

        LocalDateTime dateTime = LocalDateTime.parse(LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        return Date.from(dateTime.toInstant(ZoneOffset.UTC));
    }

    public Instant getNowInstant() {

        LocalDateTime dateTime = LocalDateTime.parse(LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        dateTime = dateTime.withNano(0);
        return dateTime.toInstant(ZoneOffset.UTC);
    }


    /******************ADD DAYS TO DATE******************/
    public LocalDate addDays(LocalDate date, Integer numberOfDays){

        if(date == null)
            throw new BadParametersException("La data richiesta è assente");

        return date.plusDays(numberOfDays);

    }

    public LocalDateTime addDays(LocalDateTime dateTime, Integer numberOfDays){

        if(dateTime == null)
            throw new BadParametersException("La data richiesta è assente");

        return dateTime.plusDays(numberOfDays);
    }

    public Date addDays(Date dateTime,  Integer numberOfDays) {

        if(dateTime == null)
            throw new BadParametersException("La data richiesta è assente");

        LocalDateTime localDateTime = this.convertUTCDateToLocalDateTime(dateTime);

        localDateTime = this.addDays(localDateTime, numberOfDays);

        return this.convertLocalDateToDate(localDateTime);

        /*SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        c.setTime(dateTime);
        c.add(Calendar.DATE, numberOfDays);  // number of days to add
        return c.getTime();*/
    }

    public Instant addDays(Instant dateTime, Integer numberOfDays){

        if(dateTime == null)
            throw new BadParametersException("La data richiesta è assente");

        LocalDateTime localDateTime = this.convertUTCInstantToLocalDateTime(dateTime);

        localDateTime = this.addDays(localDateTime, numberOfDays);

        return this.convertLocalDateToInstant(localDateTime);
    }

}
