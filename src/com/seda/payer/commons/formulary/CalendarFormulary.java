package com.seda.payer.commons.formulary;

import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author dbadm
 *
 */
public final class CalendarFormulary {

	private Map<String, DateFormulary> dateCache;
	private Map<String, String> holidayCache;
	private Map<String, Calendar> lastdayCache;
	private Map<Integer, int[]> daypermonthCache;
	private Map<Integer, String> dayOfWeekHolidayCache;
	
	private static CalendarFormulary me;

    static {
    	try {
    		me = new CalendarFormulary();
    	} catch(Exception e) {
    		System.err.println(e);
    		e.printStackTrace(System.err);
    	}
    }
	
    private CalendarFormulary() throws Exception  {
    	dateCache = Collections.synchronizedMap(new HashMap<String, DateFormulary>());
    	holidayCache = Collections.synchronizedMap(new HashMap<String, String>());
    	dayOfWeekHolidayCache = Collections.synchronizedMap(new HashMap<Integer, String>());
    	lastdayCache = Collections.synchronizedMap(new HashMap<String, Calendar>());
    	daypermonthCache = Collections.synchronizedMap(new HashMap<Integer, int[]>());    	
    }
	
    public static CalendarFormulary getInstance() {
    	return me;
    }
    public void setHolidays(HashMap<String, String> holiDays) {
    	holidayCache.clear();
    	holidayCache.putAll(holiDays);
    }
    public void setDayOfWeekHolidays(HashMap<Integer, String> holiDays) {
    	dayOfWeekHolidayCache.clear();
    	dayOfWeekHolidayCache.putAll(holiDays);
    }
    
    public boolean isHoliday(Calendar date) {
    	boolean holiday=false;
    	String dateCacheKey=date.get(Calendar.YEAR)+","+date.get(Calendar.MONTH)+","+date.get(Calendar.DATE);
		String holidayCacheKey=date.get(Calendar.DATE)+","+date.get(Calendar.MONTH);
		Integer dayOfWeekHolidayCacheKey=Integer.valueOf(date.get(Calendar.DAY_OF_WEEK));
    	if (dateCache.containsKey(dateCacheKey)) {
    		holiday = dateCache.get(dateCacheKey).isHoliday(); 
    	} else {
    		DateFormulary dateFormulary = new DateFormulary(date);
    		Calendar easterDate=null;
    		Calendar angelDate=null;
    		try {
    			easterDate = EasterDay.getInstance().easter(date.get(Calendar.YEAR));
    			angelDate = EasterDay.getInstance().angel(date.get(Calendar.YEAR));
    			if (equalsDate(date,easterDate)) {
    				dateFormulary.setEasterDay(true);
    				dateFormulary.setDescription("Easter day");
    				dateFormulary.setHoliday(true);
    			} 
    			if (equalsDate(date,angelDate)) {
    				dateFormulary.setAngelDay(true);
    				dateFormulary.setDescription("Angel day");    				
    				dateFormulary.setHoliday(true);
    			}
    			if (holidayCache.containsKey(holidayCacheKey)) {
    				dateFormulary.setHoliday(true);
    				dateFormulary.setDescription(holidayCache.get(holidayCacheKey));    				
    			}
    			if (dayOfWeekHolidayCache.containsKey(dayOfWeekHolidayCacheKey)) {
    				dateFormulary.setHoliday(true);
    				dateFormulary.setDescription(dayOfWeekHolidayCache.get(dayOfWeekHolidayCacheKey)); 
    			}
    			holiday=dateFormulary.isHoliday();
    			dateCache.put(dateCacheKey,dateFormulary);
			} catch (Exception e) {
				e.printStackTrace();
			}
    	}
    	return holiday;
    }
    
    public Calendar lastDayOfMonth(Calendar date0) {
    	Calendar lastday = null;    	
    	int year = date0.get(Calendar.YEAR);
    	int month = date0.get(Calendar.MONTH);
    	int date = date0.get(Calendar.DATE);
    	String lastdayCacheKey = year+","+month+","+date;
    	Integer daypermonthCacheKey = Integer.valueOf(year);
    	
    	if (lastdayCache.containsKey(lastdayCacheKey)) {
    		lastday=lastdayCache.get(lastdayCacheKey);
    	} else {
    		int day;
    		if (daypermonthCache.containsKey(daypermonthCacheKey)) {
    			day = daypermonthCache.get(daypermonthCacheKey)[month];
    		} else {
    			int[] daypermonth = new int[]{31,28,31,30,31,30,31,31,30,31,30,31};
    			if (isLeap(year)) daypermonth[1] = 29;
    			daypermonthCache.put(daypermonthCacheKey, daypermonth);
    			day = daypermonthCache.get(daypermonthCacheKey)[month];
    		}
    		lastday = Calendar.getInstance();
			lastday.set(year, month, day, 0, 0, 0);
			lastdayCache.put(lastdayCacheKey, lastday);
    	}
    	return lastday;
    }
    
    public static boolean isLeap(int year) {
    	boolean isLeapYear;
    	// divisible by 4
        isLeapYear = (year % 4 == 0);
        // divisible by 4 and not 100
        isLeapYear = isLeapYear && (year % 100 != 0);
        // divisible by 4 and not 100 unless divisible by 400
        isLeapYear = isLeapYear || (year % 400 == 0);    	
    	return isLeapYear;
    }
    
    private static boolean equalsDate(Calendar date0, Calendar date1) {
		boolean equals=false;
		if (date0!=null && date1!=null) {
			if (date0.get(Calendar.YEAR)==date1.get(Calendar.YEAR) &&
					date0.get(Calendar.MONTH)==date1.get(Calendar.MONTH) &&
					date0.get(Calendar.DATE)==date1.get(Calendar.DATE))
				equals=true;
		}
		return equals;
	}    
}
