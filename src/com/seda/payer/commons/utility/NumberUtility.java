package com.seda.payer.commons.utility;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.DecimalFormat;
/**
 * 
 * @author Marco Montisano
 *
 */
public class NumberUtility implements Serializable {

	private static final long serialVersionUID = 1L;
	public static final Integer EMPTY = new Integer(0); 
	/**
	 * 
	 * @param pattern
	 * @param number
	 * @return
	 */
	public static String frmtByPattern(String pattern, double number) {
		try { return new DecimalFormat(pattern).format(number);
		} catch (Exception e) { return "";
		}
	}
	/**
	 * 
	 * @param pattern
	 * @param number
	 * @return
	 */
	public static String frmtByPattern(String pattern, Number number) {
		try {
			if (!isEmpty(number))
				return new DecimalFormat(pattern).format(number.doubleValue());

			return "";
		} catch (Exception e) { return "";
		}
	}
	/**
	 * @param number
	 * @param precision
	 * @param scale
	 * @return
	 */
	public static BigDecimal parse(String number, int precision, int scale) {
		if (StringUtility.isEmpty(number))
			return new BigDecimal(0.00D);
		
		String integer = number.substring(0, precision);
		String decimal = number.substring(precision, precision + scale);
		return new BigDecimal(integer + "." + decimal);
	}
	/**
	 * @param object
	 * @return
	 */
	public static String getValue(Number object) {
		if (object == null)
			return "";

		return object.toString();
	}
	/**
	 * @param object
	 * @return
	 */
	public static boolean isEmpty(Number object) {
		if (object == null)
			return true;
		
		if (object instanceof BigDecimal) { 
			if (object.doubleValue() == 0.0)
				return true;
		} else if (object.intValue() == 0)
			return true;

		return false;
	}
	/**
	 * @param num1
	 * @param num2
	 * @return
	 */
	public static boolean compare(Number num1, Number num2) {
		if (num1  == null || num2 == null)
			return false;

		return num1.equals(num2);
	}
}