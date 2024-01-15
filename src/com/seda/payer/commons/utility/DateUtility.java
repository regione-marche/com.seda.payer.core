package com.seda.payer.commons.utility;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

import com.seda.commons.logger.CustomLoggerManager;
import com.seda.commons.logger.LoggerWrapper;
/**
 * <p>Title:		 DateUtility</p>
 * <p>Description:	 </p>
 * <p>Copyright:	 Copyright (c) 01-gen-2000</p>
 * <p>Creation date: 1-dic-2008</p>
 * <p>Company:		 Penta</p>
 * @author Marco Montisano
 */
public class DateUtility implements Serializable {

	private static final long serialVersionUID = 1L;
	public static int CURRENT_YEAR = Calendar.YEAR;
	public static final int GE = 1; // Grather Than (>) or Equal
	public static final int LE = 2; // Less Than (<) or Equal
	public static final int EQ = 3; // Equal
	public static final int NE = 4; // Not Equal
	public static final int GT = 5; // Grather Than (>)
	public static final int LT = 6; // Less Than (<)
	public static int MILLISECONDS = 3600000;
	public static int DAY_IN_MILLISECONDS = 86400000;
	public static long YEAR_IN_MILLISECONDS = 31536000000L;
	public static Date MAX_VALUE = Date.valueOf("2020-12-31");
	private static final LoggerWrapper logger =  CustomLoggerManager.get(DateUtility.class);
	/**
	 * frmtTimeByPattern
	 * 
	 * @param pattern
	 * @param timestamp
	 * @return
	 */
	public static String frmtTimeByPattern(String pattern, Timestamp timestamp) {
		if (timestamp == null)
			return "";
		
		try { return new SimpleDateFormat(pattern).format(timestamp);
		} catch (Exception e) { return "";
		}
	}
	/**
	 * frmtTimeByPattern
	 * 
	 * @param pattern
	 * @param date
	 * @return
	 */
	
	public static String frmtTimeByPattern(String pattern, Date date) {
		if (date == null)
			return "";
		
		try { return new SimpleDateFormat(pattern).format(date);
		} catch (Exception e) { return "";
		}
	}
	/**
	 * frmtTimeByPattern
	 * 
	 * @param pattern
	 * @param date
	 * @return
	 */
	public static String frmtTimeByPattern(String pattern, String date) {
		if (date == null)
			return "";

		try { return new SimpleDateFormat(pattern).format(Date.valueOf(date));
		} catch (Exception e) { return "";
		}
	}
	/**
	 * frmtTimeByPattern
	 * 
	 * @param pattern
	 * @param date
	 * @return
	 */
	public static String frmtTimeByPattern(String pattern, java.util.Date date) {
		if (date == null)
			return "";

		try { return new SimpleDateFormat(pattern).format(date);
		} catch (Exception e) { return "";
		}
	}
	/**
	 * frmtTimeByPattern
	 * 
	 * @param pattern
	 * @param locale
	 * @param timestamp
	 * @return
	 */
	public static String frmtTimeByPattern(String pattern, Locale locale, Timestamp timestamp) {
		if (timestamp == null)
			return "";

		try { return new SimpleDateFormat(pattern, locale).format(timestamp);
		} catch (Exception e) { return "";
		}
	}
	/**
	 * frmtTimeByPattern
	 * 
	 * @param pattern
	 * @param locale
	 * @param date
	 * @return
	 */
	public static String frmtTimeByPattern(String pattern, Locale locale, Date date) {
		if (date == null)
			return "";

		try { return new SimpleDateFormat(pattern, locale).format(date);
		} catch (Exception e) { return "";
		}
	}
	/**
	 * frmtByPattern
	 * 
	 * @param pattern
	 * @param time
	 * @return
	 */
	public static String frmtByPattern(String pattern, Time time) {
		if (time == null)
			return "";

		try { return new SimpleDateFormat(pattern).format(time);
		} catch (Exception e) { return "";
		}
	}
	/**
	 * 
	 * @param pattern
	 * @param date
	 * @return
	 */
	public static java.util.Date parse(String pattern, String date) {
		if (StringUtility.isEmpty(date))
			return null;

		try { return new SimpleDateFormat(pattern).parse(date);
		} catch (Exception e) {
			logger.warn("parse string date " + date + " by pattern " + pattern + " failed:", e);
			return null;
		}
	}
	/**
	 * 
	 * @param pattern
	 * @return
	 */
	public static String getCurrentTime(String pattern) {
		Calendar temp = new GregorianCalendar(java.util.Locale.ITALY); {
			temp.setTime(new java.sql.Date(System.currentTimeMillis()));
			temp.add(java.util.Calendar.DATE, 0);
		}
		SimpleDateFormat actualDate = null;
		try { actualDate = new SimpleDateFormat(pattern, java.util.Locale.ITALIAN);
		} catch (Exception e) { actualDate = new SimpleDateFormat("HH:mm", java.util.Locale.ITALIAN); 
		}
		return actualDate.format(new java.util.Date(temp.getTime().getTime()));
	}
	/**
	 * 
	 * @param pattern
	 * @return
	 */
	public static String getCurrentDate(String pattern) {
		Calendar temp = new GregorianCalendar(java.util.Locale.ITALY); {
			temp.setTime(new java.sql.Date(System.currentTimeMillis()));
			temp.add(java.util.Calendar.DATE, 0);
		}
		SimpleDateFormat actualDate = null;
		try { actualDate = new SimpleDateFormat(pattern, java.util.Locale.ITALIAN);
		} catch (Exception e) { actualDate = new SimpleDateFormat("dd/MM/yyyy", java.util.Locale.ITALIAN); 
		}
		return actualDate.format(new java.util.Date(temp.getTime().getTime()));
	}
	/**
	 * @param startTime
	 * @param endTime
	 * @return
	 * @throws Exception
	 */
	public static long getTimeDistance(Timestamp startTime, Timestamp endTime) throws Exception {
		if (startTime == null || endTime == null)
			throw new Exception("startTime & endTime is mandatory");

		if (startTime.after(endTime))
			return -1;

		return (endTime.getTime() - startTime.getTime())/DAY_IN_MILLISECONDS;
	}
	/**
	 * @param startTime
	 * @param endTime
	 * @return
	 * @throws Exception
	 */
	public static long getDays(Timestamp startTime, Timestamp endTime) throws Exception {
		if (startTime == null || endTime == null)
			throw new Exception("startTime & endTime is mandatory");

		if (startTime.after(endTime))
			return -1;

		Date Date_1 = Date.valueOf(new SimpleDateFormat("yyyy-MM-dd").format(startTime));
		Date Date_2 = Date.valueOf(new SimpleDateFormat("yyyy-MM-dd").format(endTime));
		return ((Date_2.getTime() - Date_1.getTime())/DAY_IN_MILLISECONDS) + 1L;
	}
	/**
	 * @param startTime
	 * @param endTime
	 * @return
	 * @throws Exception
	 */
	public static long getHoursDistance(Timestamp startTime, Timestamp endTime) throws Exception {
		if (startTime == null || endTime == null)
			throw new Exception("startTime & endTime is mandatory");

		if (startTime.after(endTime))
			return -1;

		return (endTime.getTime() - startTime.getTime())/MILLISECONDS;
	}
	/**
	 * @param startTime
	 * @param endTime
	 * @return
	 * @throws Exception
	 */
	public static long getMillisDistance(Timestamp startTime, Timestamp endTime) throws Exception {
		if (startTime == null || endTime == null)
			throw new Exception("startTime & endTime is mandatory");

		if (startTime.after(endTime))
			return -1;

		return endTime.getTime() - startTime.getTime();
	}
	/**
	 * @param birthDate
	 * @return
	 * @throws Exception
	 */
	public static long getAge(java.util.Date birthDate) throws Exception {
		if (birthDate == null)
			throw new Exception("birthDate is mandatory");

		if (birthDate.after(new java.util.Date()))
			return -1;

		return (new java.util.Date().getTime() - birthDate.getTime()) / YEAR_IN_MILLISECONDS;
	}
	/**
	 * @param typeComparison
	 * @param date1
	 * @param date2
	 * @return
	 * @throws Exception
	 */
	public static boolean compare(int typeComparison, Date date1, Date date2) throws Exception {
		if (date1 == null || date2 == null)
			return false;

		boolean response = false;
		Date Date_1 = Date.valueOf(new SimpleDateFormat("yyyy-MM-dd").format(date1));
		Date Date_2 = Date.valueOf(new SimpleDateFormat("yyyy-MM-dd").format(date2));
		switch (typeComparison) {
			case EQ :
				if (Date_1.compareTo(Date_2) == 0) 
					response = true;
				break;
			case NE :
				if (!Date_1.equals(Date_2)) 
					response = true;
				break;
			case GT :
				if (Date_1.after(Date_2)) 
					response = true;
				break;
			case LT :
				if (Date_1.before(Date_2))
					response = true;
				break;
			case GE :
				if (Date_1.after(Date_2) || Date_1.compareTo(Date_2) == 0)
					response = true;
				break;
			case LE :
				if (Date_1.before(Date_2) || Date_1.compareTo(Date_2) == 0)
					response = true;
				break;
		}
		return response;
	}
	/**
	 * @param typeComparison
	 * @param date1
	 * @param date2
	 * @return
	 * @throws Exception
	 */
	public static boolean compare(int typeComparison, Timestamp date1, Timestamp date2) throws Exception {
		if (date1 == null || date2 == null)
			return false;

		boolean response = false;
		Timestamp Date_1 = Timestamp.valueOf(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date1));
		Timestamp Date_2 = Timestamp.valueOf(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date2));
		switch (typeComparison) {
			case EQ :
				if (Date_1.compareTo(Date_2) == 0) 
					response = true;
				break;
			case NE :
				if (!Date_1.equals(Date_2)) 
					response = true;
				break;
			case GT :
				if (Date_1.after(Date_2)) 
					response = true;
				break;
			case LT :
				if (Date_1.before(Date_2))
					response = true;
				break;
			case GE :
				if (Date_1.after(Date_2) || Date_1.compareTo(Date_2) == 0)
					response = true;
				break;
			case LE :
				if (Date_1.before(Date_2) || Date_1.compareTo(Date_2) == 0)
					response = true;
				break;
		}
		return response;
	}
	/**
	 * @param typeComparison
	 * @param time1
	 * @param time2
	 * @return
	 * @throws Exception
	 */
	public static boolean compare(int typeComparison, Time time1, Time time2) throws Exception {
		if (time1 == null || time2 == null)
			return false;

		boolean response = false;
		Time Time_1 = Time.valueOf(new SimpleDateFormat("HH:mm:ss").format(time1));
		Time Time_2 = Time.valueOf(new SimpleDateFormat("HH:mm:ss").format(time2));
		switch (typeComparison) {
			case EQ :
				if (Time_1.compareTo(Time_2) == 0) 
					response = true;
				break;
			case NE :
				if (!Time_1.equals(Time_2)) 
					response = true;
				break;
			case GT :
				if (Time_1.after(Time_2)) 
					response = true;
				break;
			case LT :
				if (Time_1.before(Time_2))
					response = true;
				break;
			case GE :
				if (Time_1.after(Time_2) || Time_1.compareTo(Time_2) == 0)
					response = true;
				break;
			case LE :
				if (Time_1.before(Time_2) || Time_1.compareTo(Time_2) == 0)
					response = true;
				break;
		}
		return response;
	}
	/**
	 * @param date
	 * @param millisecond
	 * @return
	 * @throws Exception
	 */
	public static java.util.Date addMillis(java.util.Date date, int millisecond) throws Exception {
		Calendar calendar = GregorianCalendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MILLISECOND, millisecond);
		return calendar.getTime();
	}
	/**
	 * @param date
	 * @param hours
	 * @return
	 * @throws Exception
	 */
	public static java.util.Date addHours(java.util.Date date, int hours) throws Exception {
		Calendar calendar = GregorianCalendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.HOUR, hours);
		return calendar.getTime();
	}
	/**
	 * @param date
	 * @param days
	 * @return
	 * @throws Exception
	 */
	public static java.util.Date addDays(java.util.Date date, int days) throws Exception {
		Calendar calendar = GregorianCalendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, days);
		return calendar.getTime();
	}
	/**
	 * @param date
	 * @param days
	 * @return
	 * @throws Exception
	 */
	public static java.util.Date addMonths(java.util.Date date, int days) throws Exception {
		Calendar calendar = GregorianCalendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, days);
		return calendar.getTime();
	}
	/**
	 * @param calendar
	 * @return
	 */
	public static java.sql.Date getDate(Calendar calendar) {
		if (calendar == null)
			return new java.sql.Date(System.currentTimeMillis());

		return new java.sql.Date(calendar.getTimeInMillis());
	}
	/**
	 * @param calendar
	 * @return
	 */
	public static java.sql.Timestamp getTimestamp(Calendar calendar) {
		if (calendar == null)
			return new java.sql.Timestamp(System.currentTimeMillis());

		return new java.sql.Timestamp(calendar.getTimeInMillis());
	}
	/**
	 * @param object
	 * @return
	 */
	public static boolean isEmpty(java.util.Date object) {
		if (object == null)
			return true;

		return false;
	}
}