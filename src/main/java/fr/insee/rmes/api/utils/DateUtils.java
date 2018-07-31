package fr.insee.rmes.api.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class DateUtils {
	
	public static DateTime getDateTimeFromString(String date) {
		DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd");
		DateTime dt = formatter.parseDateTime(date);
		return dt;
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
