package fr.insee.rmes.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class DateUtils {

    private static final String DATE_PATTERN = "yyyy-MM-dd";

    public static DateTime getDateTimeFromDateString(String date) {
        DateTimeFormatter formatter = DateTimeFormat.forPattern(DATE_PATTERN);
        return formatter.parseDateTime(date);
    }

    /**
     * return "" or a date with yyyy-MM-dd pattern
     * @param dateTime
     * @return
     */
    public static String getDateStringFromDateTimeString(String dateTime) {
        if (dateTime == null || dateTime.equals("")) {
            return "";
        }
        DateTime dt = new DateTime(dateTime);
        DateTimeFormatter formatter = DateTimeFormat.forPattern(DATE_PATTERN);
        return formatter.print(dt);
    }

    /**
     * check date pattern
     * @param date
     * @return
     */
    public static boolean isValidDate(String date) {
        if (date == null || ! date.matches("\\d{4}-[01]\\d-[0-3]\\d")) {
            return false;
        }
        SimpleDateFormat format = new SimpleDateFormat(DATE_PATTERN);
        try {
            format.parse(date);
            return true;
        }
        catch (ParseException e) {
            return false;
        }
    }

    private DateUtils() {
        throw new IllegalStateException("Utility class");
        // please sonar
    }

    public static String getDateTodayStringFormat() {
        Calendar c = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat(DATE_PATTERN);
        return format.format(c.getTime());
    }

}
