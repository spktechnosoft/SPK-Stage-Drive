package com.stagedriving.modules.commons.utils;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class DateUtils {
    public static final String DATE_PATTERN = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
    public static final String DATE_PATTERN_ = "yyyy-MM-dd'T'HH:mm:ssZZ";
    public static final String DATE_PATTERN__ = "yyyy-MM-dd'T'HH:mm:ss.SSSZZ";
    public static final String DATE_PATTERN_IT = "dd/MM/yyyy HH:mm";
    // 2017/12/04 15:16:35 +0000
    public static final String DATE_PATTERN_FULL = "yyyy/MM/dd HH:mm:ss Z";

    public static Date stringToDate(String strDate) {
        if (strDate == null) {
            return null;
        }

        DateTimeFormatter fmt = DateTimeFormat.forPattern(DATE_PATTERN_);
        if (strDate.contains(".")) {
            fmt = DateTimeFormat.forPattern(DATE_PATTERN__);
            //strDate = strDate.replace(".000Z", "Z");
        }

        return fmt.parseDateTime(strDate).toDate();
    }

    public static Date stringToDateFull(String strDate) {
        if (strDate == null) {
            return null;
        }
        /*if (strDate.contains(".")) {
            strDate = strDate.substring(0, strDate.indexOf("."))+"Z";
            //strDate = strDate.replace(".000Z", "Z");
        }*/
        //SimpleDateFormat formatter = new SimpleDateFormat(DATE_PATTERN);
        DateTimeFormatter fmt = DateTimeFormat.forPattern(DATE_PATTERN_FULL);
        return fmt.parseDateTime(strDate).toDate();
    }

    public static String dateToString(Date date) {
        if (date == null) {
            return null;
        }
        DateTimeFormatter fmt = DateTimeFormat.forPattern(DATE_PATTERN);

        return fmt.print(new DateTime(date));

    }

    public static String dateToStringIt(Date date) {
        if (date == null) {
            return null;
        }
        SimpleDateFormat formatter = new SimpleDateFormat(DATE_PATTERN_IT);
        String strDate = formatter.format(date);

        return strDate;
    }

    public static String dateToStringIt(Date date, TimeZone timeZone) {
        if (date == null) {
            return null;
        }
        SimpleDateFormat formatter = new SimpleDateFormat(DATE_PATTERN_IT);
        formatter.setTimeZone(timeZone);
        String strDate = formatter.format(date);

        return strDate;
    }

}
