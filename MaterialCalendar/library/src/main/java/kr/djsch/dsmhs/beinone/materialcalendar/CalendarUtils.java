package kr.djsch.dsmhs.beinone.materialcalendar;

import java.util.Calendar;

/**
 * Created by BeINone on 2017-04-13.
 */

public class CalendarUtils {

    public static int getDayOfMonth(Calendar calendar) {
        return calendar.get(Calendar.DAY_OF_MONTH);
    }

    public static Calendar setDayOfMonth(Calendar calendar, int dayOfMonth) {
        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        return calendar;
    }

    public static int getDayOfWeek(Calendar calendar) {
        return calendar.get(Calendar.DAY_OF_WEEK);
    }

    public static Calendar setDayOfWeek(Calendar calendar, int dayOfWeek) {
        calendar.set(Calendar.DAY_OF_WEEK, dayOfWeek);
        return calendar;
    }

    public static int getMonth(Calendar calendar) {
        return calendar.get(Calendar.MONTH) + 1;
    }

    public static Calendar setMonth(Calendar calendar, int month) {
        calendar.set(Calendar.MONTH, month + 1);
        return calendar;
    }
}
