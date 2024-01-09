package com.seda.payer.commons.utility;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class CalendarUtility implements Serializable {

	private static final long serialVersionUID = 1L;
	/**
	 * @param date
	 * @param pattern
	 * @return
	 * @throws ParseException
	 */
	public static Calendar stringToCalendar(String date, String pattern) throws ParseException {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new SimpleDateFormat(pattern).parse(date));
		return calendar;
	}
}

