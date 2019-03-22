package kz.dorm.utils;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateText {

    /**
     * Из {@link Date} получить {@link String} по формату {@link DateConfig#GLOBAL_FORMAT_DATE_TIME}
     */
    public static String getDateText(Date date) {
        return new SimpleDateFormat(DateConfig.GLOBAL_FORMAT_DATE_TIME).format(date);
    }

    /**
     * Из {@link DateTime} получить {@link String} по формату {@link DateConfig#GLOBAL_FORMAT_DATE_TIME}
     */
    public static String getDateTimeText(DateTime dateTime) {
        return new SimpleDateFormat(DateConfig.GLOBAL_FORMAT_DATE_TIME).format(dateTime.toDate());
    }

    /**
     * Дату начала проживания.
     */
    public static String getResidentDate(String date) {
        DateTime dateTime = getDate(date);

        return dateTime.getDayOfMonth() + " " + getMonth(dateTime.getMonthOfYear() - 1);
    }

    /**
     * Получить год проживания в общажитии.
     */
    public static String getResidentYear(String date) {
        DateTime dateTime = getDate(date);

        if (dateTime.getMonthOfYear() >= 1 &&
                dateTime.getMonthOfYear() <= 7)
            return String.valueOf(dateTime.getYear());
        else
            return dateTime.getYear() + "-" + dateTime.plusYears(1).getYear();
    }

    /**
     * Получить дату создания документа.
     */
    public static String getDocCreate() {
        return new SimpleDateFormat(DateConfig.GLOBAL_FORMAT_DATE_DOC_CREATE)
                .format(new Date(System.currentTimeMillis()));
    }

    /**
     * Получить из текста, дату.
     */
    private static DateTime getDate(String date) {
        DateTimeFormatter formatter = DateTimeFormat.forPattern(DateConfig.GLOBAL_FORMAT_DATE);

        return formatter.parseDateTime(date);
    }

    /**
     * Получить название месяца, по его ID.
     */
    private static String getMonth(int index) {
        String[] months = {DateConfig.GLOBAL_MONTH_JANUARY, DateConfig.GLOBAL_MONTH_FEBRUARY,
                DateConfig.GLOBAL_MONTH_MARCH, DateConfig.GLOBAL_MONTH_APRIL,
                DateConfig.GLOBAL_MONTH_MAY, DateConfig.GLOBAL_MONTH_JUNE,
                DateConfig.GLOBAL_MONTH_JULY, DateConfig.GLOBAL_MONTH_AUGUST,
                DateConfig.GLOBAL_MONTH_SEPTEMBER, DateConfig.GLOBAL_MONTH_OCTOBER,
                DateConfig.GLOBAL_MONTH_NOVEMBER, DateConfig.GLOBAL_MONTH_DECEMBER};

        return months[index];
    }
}
