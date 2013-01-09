package com.nwice.barapp.calendartag.util;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class CalendarTagUtil {

    public static void trimCalendar(Calendar calendar) {
        calendar.set(Calendar.MILLISECOND, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
    }

    public static Calendar getCalendar(Date date) {
    	Calendar calendar = new GregorianCalendar();
    	calendar.setTime(date);
    	return calendar;
    }
    
    public static void trimDate(Date date) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        trimCalendar(calendar);
        date = calendar.getTime();
    }
    
    public static boolean isSameDay(Calendar a, Calendar b) {
        return (a.get(Calendar.DATE) == b.get(Calendar.DATE) &&
                a.get(Calendar.MONTH) == b.get(Calendar.MONTH) &&
                a.get(Calendar.YEAR) == b.get(Calendar.YEAR));
    }

    public static int dayCompare(Calendar leftCalendar, Calendar rightCalendar) {

        if (leftCalendar.get(Calendar.DATE) == rightCalendar.get(Calendar.DATE) &&
                leftCalendar.get(Calendar.MONTH) == rightCalendar.get(Calendar.MONTH) &&
                leftCalendar.get(Calendar.YEAR) == rightCalendar.get(Calendar.YEAR)) {
            return 0;
        } else if (leftCalendar.before(rightCalendar)) {
            return -1;
        } else {
            return 1;
        }

    }

    public static int differenceInDays(Calendar a, Calendar b) {

        Calendar calendar = new GregorianCalendar();
        calendar = (Calendar) b.clone();

        int dayCompared = dayCompare(a, calendar);
        int dayDifference = 0;
        while (dayCompared != 0) {
            calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) + dayCompared);
            dayCompared = dayCompare(a, calendar);
            dayDifference++;
        }

        return dayDifference;

    }

}