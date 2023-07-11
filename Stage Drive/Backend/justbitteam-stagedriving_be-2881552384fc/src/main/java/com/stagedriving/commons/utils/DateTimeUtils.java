package com.stagedriving.commons.utils;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.Date;

public class DateTimeUtils {

    public static final String DATE_PATTERN = "yyyy-MM-dd'T'HH:mm:ssZZ";

    public static DateTime stringToDateTime(String date, String pattern, String timeZoneId) {
        DateTimeZone timeZone = DateTimeZone.forID(timeZoneId);
        DateTimeFormatter formatter = DateTimeFormat.forPattern(pattern).withZone(timeZone);

        DateTime dateTime = formatter.parseDateTime(date);
        return dateTime;
    }

    public static String dateTimeToISO(DateTime dateTime) {
        String iso8601String = dateTime.toString();
        return iso8601String;
    }

    public static Date stringToDate(String strDate) {
        if (strDate == null) {
            return null;
        }
        DateTimeFormatter fmt = DateTimeFormat.forPattern(DATE_PATTERN);
        return fmt.parseDateTime(strDate).toDate();
    }

    public static String dateToString(Date date) {
        if (date == null) {
            return null;
        }
        DateTimeFormatter fmt = DateTimeFormat.forPattern(DATE_PATTERN);
        return fmt.print(new DateTime(date));
    }
}