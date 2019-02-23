package kz.dorm.utils;

import org.joda.time.DateTime;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateText {

    /**
     * Из {@link Date} получить {@link String} по формату {@link DataConfig#GLOBAL_DATE_FORMAT}
     */
    public static String getDateText(Date date) {
        return new SimpleDateFormat(DataConfig.GLOBAL_DATE_FORMAT).format(date);
    }

    /**
     * Из {@link DateTime} получить {@link String} по формату {@link DataConfig#GLOBAL_DATE_FORMAT}
     */
    public static String getDateTimeText(DateTime dateTime) {
        return new SimpleDateFormat(DataConfig.GLOBAL_DATE_FORMAT).format(dateTime.toDate());
    }
}
