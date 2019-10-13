package com.gfranke.duedate.util;

import java.time.DayOfWeek;
import java.time.LocalDateTime;

public final class DateUtil {

    private DateUtil() {
        // avoid object creation
    }

    public static boolean isWeekend(LocalDateTime time) {
        DayOfWeek day = time.getDayOfWeek();
        return day == DayOfWeek.SATURDAY || day == DayOfWeek.SUNDAY;
    }

    public static boolean isNotWorkingHour(LocalDateTime time) {
        int hour = time.getHour();
        if (9 > hour) {
            return true;
        }
        if (16 < hour) {
            return !(17 == hour && 0 == time.getMinute());
        }
        return false;
    }
}