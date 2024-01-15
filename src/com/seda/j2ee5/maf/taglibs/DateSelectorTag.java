/**
 * 
 */
package com.seda.j2ee5.maf.taglibs;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.jsp.JspTagException;

import com.seda.commons.logger.CustomLoggerManager;
import com.seda.commons.logger.LoggerWrapper;
import com.seda.commons.validator.Validation;
import com.seda.j2ee5.maf.util.MAFLogger;

/**
 * @author f.ricci
 *
 */
public class DateSelectorTag extends HTMLLabelSupport {

	private static final long serialVersionUID = 1L;	
	
	private List<DateFormat> dateFormats;
	
	private String value;
	private Calendar calendar;	
	private java.sql.Date sqlDate;
	private String name;
	private String locale;
	private int yearMin;
	private int yearMax;
	private boolean required;
	private String message;
	
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value=value;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getLocale() {
		return locale;
	}
	public void setLocale(String locale) {
		this.locale = locale;
	}
	public Calendar getCalendar() {
		return calendar;
	}
	public void setCalendar(Calendar calendar) {
		this.calendar = calendar;
	}
	
	public java.sql.Date getSqlDate() {
		return sqlDate;
	}
	public void setSqlDate(java.sql.Date sqlDate) {
		this.sqlDate = sqlDate;
	}
	public int getYearMin() {
		return yearMin;
	}
	public void setYearMin(int yearMin) {
		this.yearMin = yearMin;
	}
	public int getYearMax() {
		return yearMax;
	}
	public void setYearMax(int yearMax) {
		this.yearMax = yearMax;
	}
	public boolean isRequired() {
		return required;
	}
	public void setRequired(boolean required) {
		this.required = required;
	}
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

	private static final LoggerWrapper logger =  CustomLoggerManager.get(DateSelectorTag.class);
	
	public DateSelectorTag() {
		dateFormats = new ArrayList<DateFormat>();
        dateFormats.add( new SimpleDateFormat("dd.MM.yyyy") );        
        dateFormats.add( new SimpleDateFormat("dd/MM/yyyy") );
        dateFormats.add( new SimpleDateFormat("dd-MM-yyyy") );
        dateFormats.add( new SimpleDateFormat("yyyy-MM-dd") );		
        dateFormats.add(DateFormat.getDateTimeInstance() );
        dateFormats.add(DateFormat.getDateTimeInstance( DateFormat.LONG, DateFormat.LONG ) );
        dateFormats.add(DateFormat.getDateTimeInstance( DateFormat.MEDIUM, DateFormat.MEDIUM ) );
        dateFormats.add(DateFormat.getDateTimeInstance( DateFormat.SHORT, DateFormat.SHORT ) );
        dateFormats.add( new SimpleDateFormat("EEE MMM d hh:mm:ss a z yyyy") );
        dateFormats.add( new SimpleDateFormat("EEE MMM d HH:mm:ss z yyyy") );
        dateFormats.add( new SimpleDateFormat("MM/dd/yy hh:mm:ss a"));
        dateFormats.add( new SimpleDateFormat("MM/dd/yy") );
	}
	
	private void defaultDate() {
		calendar = Calendar.getInstance();
		calendar.setTimeInMillis(System.currentTimeMillis());
		setValue(dateFormats.get(0).format(calendar.getTime()));
	}
	public int doStartTag() throws JspTagException {
        return EVAL_BODY_BUFFERED;
    }
	
	public int doEndTag() throws JspTagException {
    	try {
    		FormTag formTag	= (FormTag) findAncestorWithClass(this, FormTag.class);
    		
    		if (formTag!=null) {
    			StringBuffer validationBuffer = new StringBuffer("dateISO");
    			if (isRequired()) {
    				validationBuffer.append(";required");	
    			}
        		formTag.putValidationField(getName(), getLabel()==null?getName():getLabel(), validationBuffer.toString(), getMessage());
    		}
    		if (calendar==null) {
    			calendar=Calendar.getInstance();
    			if (sqlDate==null) {
        			if (value == null) {
        				defaultDate();
        			} else {
        				Date selected = processDate();
        				if (selected==null) {
        					defaultDate();
        				} else {
        					setValue(dateFormats.get(0).format(selected));
        					calendar.setTime(selected);
        				}
        			}    					
				} else {
					calendar.setTime(sqlDate);
				}
    		}

    		int gg=calendar.get(Calendar.DAY_OF_MONTH);
    		int mm=calendar.get(Calendar.MONTH)+1;
    		int ssaa=calendar.get(Calendar.YEAR);	
    		
    		StringBuffer html = new StringBuffer();
    		
    		if (ssaa<yearMin) {
    			yearMin=ssaa;
    		} else {
        		yearMin=yearMin==0?ssaa-3:yearMin;    			
    		}

    		if (yearMax>0 && ssaa>yearMax) {
    			yearMax=ssaa;
    		} else {
        		yearMax=yearMax==0?ssaa+3:yearMax;    			
    		}
    		
    		String nameYear=name+Validation.suffixYear;
    		String nameMonth=name+Validation.suffixMonth;
    		String nameDay=name+Validation.suffixDay;
    		
    		String htmlYear=null; 
			String htmlMonth=null;
			String htmlDay=null;
    		String id=null;
    		if (isReadonly()) {
    			htmlYear =getInput(nameYear,ssaa).toString(); 
    			htmlMonth =getInput(nameMonth,mm).toString();
    			htmlDay =getInput(nameDay,gg).toString();
    		} else {
    			htmlYear =getSelect(nameYear,yearMin,yearMax,ssaa).toString(); 
    			htmlMonth =getSelect(nameMonth,1,12,mm).toString();
    			htmlDay =getSelect(nameDay,1,31,gg).toString();
    		}

    		if (locale==null || locale.equals(Locale.ITALY.toString())) {
    			id=nameDay;
    			html.append(htmlDay).append("/");	
    			html.append(htmlMonth).append("/");
    			html.append(htmlYear);
    		} else {
    			id=nameYear;
    			html.append(htmlYear).append("-");
    			html.append(htmlMonth).append("-");
    			html.append(htmlDay);
    		}
    		
    		setHTMLLabel(html,id,isRequired());
    		
    		pageContext.getOut().print(html.toString());
    		
    		recycle();
    		
    		return EVAL_PAGE;
    	}
    	catch (IOException e) {
    		logger.error(MAFLogger.getMessage("generic_exception"), e);
    		throw new JspTagException(e.getMessage());
    	}
    }
	
	private StringBuffer getInput(String name, int value) {
		StringBuffer input = new StringBuffer();
		input.append("<input type=\"text\" ");
		input.append("name=\""+name+"\" ");
		input.append("value=\""+value+"\" ");
		input.append("size=\""+(value>99?"4":"2")+"\" ");
		input.append(" readonly=\"readonly\" ");				
		input.append(isDisabled() ? " disabled=\"disabled\"":"");
		input.append("/>");
//		input.append(value);
//		input.append("</input>");
		return input;
	}
	
	private StringBuffer getSelect(String name, int min, int max, int value) {
		StringBuffer input = new StringBuffer();
		input.append("<select ");
		input.append("name=\""+name+"\">");
		for (int i = min; i < max+1; i++) {
			input.append("<option value=\"" + i + "\" ");
			input.append(i==value?" selected>":">");
			input.append(i<10?"0":"");
			input.append(i);
			input.append("</option>");
		}
		input.append("</select>");
		return input;
	}
	
	private Date processDate() {
		for( DateFormat format : dateFormats ) {
            try {
                return format.parse( value );
            } catch (ParseException e) {
                // try next format
            }
        }
		return null;
	}
	
	public void recycle() {
		super.recycle();
		setValue(null);
		setCalendar(null);
		setRequired(false);
		setMessage(null);
	}	
}
