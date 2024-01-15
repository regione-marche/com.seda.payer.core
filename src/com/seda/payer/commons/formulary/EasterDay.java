package com.seda.payer.commons.formulary;

import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Torna la data del giorno di pasqua 
 * 
 * @author dbadm
 *
 */
public final class EasterDay {

	private Map<Integer, Calendar> easterCache;	
	private Map<Integer, Calendar> angelCache;	
	
	private static EasterDay me;

    static {
    	try {
    		me = new EasterDay();
    	} catch(Exception e) {
    		System.err.println(e);
    		e.printStackTrace(System.err);
    	}
    }
    
    private EasterDay() throws Exception  {
    	easterCache = Collections.synchronizedMap(new HashMap<Integer, Calendar>());
    	angelCache = Collections.synchronizedMap(new HashMap<Integer, Calendar>());
    }
	
    public static EasterDay getInstance() {
    	return me;
    }
    
    public Calendar easter(int year) throws Exception {
    	Integer key = new Integer(year);
    	Calendar easterDate = null;
    	int[] easter;
    	try {
        	if (easterCache.containsKey(key)) {
        		easterDate = easterCache.get(key); 
        	} else {
                easter = easterDay(year);
                easterDate = Calendar.getInstance();
                easterDate.set(easter[0], easter[1] -1, easter[2]);
                easterCache.put(key, easterDate);
                
                Calendar angelDate = Calendar.getInstance();
                angelDate.setTime(easterDate.getTime());
                angelDate.add(Calendar.DAY_OF_MONTH, 1); 
                angelCache.put(key, angelDate);
        	}
    	} catch (Exception e) {
	    	throw e;
    	}   	
    	return easterDate;
    	
    }
    
    
    public Calendar angel(int year) throws Exception {
    	Integer key = new Integer(year);
    	Calendar angelDate=null;
    	int[] easter;
    	try {
        	if (angelCache.containsKey(key)) {
        		angelDate = angelCache.get(key);       		
        	} else {
                easter = easterDay(year);
                
                Calendar easterDate = Calendar.getInstance();
                easterDate.set(easter[0], easter[1] -1, easter[2]);
                easterCache.put(key, easterDate);
                
                angelDate = Calendar.getInstance();
                angelDate.setTime(easterDate.getTime());
                angelDate.add(Calendar.DAY_OF_MONTH, 1); 
                angelCache.put(key, angelDate);
        	}
    	} catch (Exception e) {
	    	throw e;
    	}
    	   	
    	return angelDate;
    }
    
    public int[] easterDay(int year) {
    	int h = (24 + 19 * (year % 19)) % 30;
        int i = h - (h / 28);
        int j = (year + (year / 4) + i - 13) % 7;
        int l = i - j;
        int month = 3 + ((l + 40) / 44);
        int date = l + 28 - (31 * (month / 4));
        return new int[] {year,month,date};
    }
}
