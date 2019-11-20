package fr.insee.rmes.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class DateUtils {
	
	public static DateTime getDateTimeFromDateString(String date) {
		DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd");
		DateTime dt = formatter.parseDateTime(date);
		return dt;
	}
	
	public static String getDateStringFromDateTimeString(String dateTime) {
		if(dateTime == null || dateTime.equals("")) return "";
		DateTime dt = new DateTime(dateTime);
		DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd");
		return formatter.print(dt);
	}

	public static boolean isValidDate(String date) {
		if (date == null || !date.matches("\\d{4}-[01]\\d-[0-3]\\d"))
	        return false;
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	     try {
	          format.parse(date);
	          return true;
	     }
	     catch(ParseException e){
	          return false;
	     }
	}

}
