package com.stagedriving.modules.commons.utils;

import biweekly.Biweekly;
import biweekly.ICalendar;
import biweekly.component.VEvent;
import biweekly.property.Summary;
import com.stagedriving.modules.commons.conf.AppConfiguration;

import java.io.UnsupportedEncodingException;
import java.util.Date;

/**
 * Created by simone on 10/05/16.
 */
public class ICalUtils {

    public static String generateICal(String sum, Date start, Date end) throws UnsupportedEncodingException {
        String language = "it-it";

        ICalendar ical = new ICalendar();
        VEvent event = new VEvent();
        Summary summary = event.setSummary(sum);
        summary.setLanguage(language);

        event.setDateStart(start, true);
        event.setDateEnd(end, true);

        ical.addEvent(event);

        return Biweekly.write(ical).go();
    }

    public static String getICalLink(AppConfiguration conf, String eventId, String calType, String ticketId) {
        String ret = conf.getBaseUri()+"calendar.ics?eventId="+eventId+"&type="+calType;
        if (ticketId != null) {
            ret += "&ticket="+ticketId;
        }

        return ret;
    }
}
