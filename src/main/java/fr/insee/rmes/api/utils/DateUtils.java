package fr.insee.rmes.api.utils;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class DateUtils {
	
	public static DateTime getDateTimeFromString(String date) {
		DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy/MM/dd");
		DateTime dt = formatter.parseDateTime(date);
		return dt;
	}

}
