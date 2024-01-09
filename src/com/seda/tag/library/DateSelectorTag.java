package com.seda.tag.library;

import java.io.IOException;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;
import javax.servlet.jsp.tagext.Tag;

import com.seda.j2ee5.maf.components.encoding.ParameterDiscovery;
import com.seda.tag.core.DateSelector;
import com.seda.tag.i18n.Strings;

public class DateSelectorTag extends BodyTagSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		
	protected DateSelector dateSelector = getDateSelector();

	public void setYearbegin(String yearbegin) {
		dateSelector.setYearbegin(yearbegin);
	}
	
	protected DateSelector getDateSelector() {
		
		return new DateSelector();
	}

	public void setYearend(String yearend) {
		dateSelector.setYearend(yearend);
		
	}
	
	public void setMonthbegin(String monthbegin) {
		dateSelector.setMonthbegin(monthbegin);
	}

	public void setMonthend(String monthend) {
		dateSelector.setMonthend(monthend);
	}
	
	public void setLocale(String locale) {
		dateSelector.setLocale(locale);
	}

	public void setPrefix(String prefix) {
		dateSelector.setPrefix(prefix);
		
	}

	public void setCalendar(Calendar calendar) {
		dateSelector.setCalendar(calendar);
	}
	
	public void setCssclass(String cssclass) {
		dateSelector.setCssclass(cssclass);
	}

	public void setCssclasslabel(String cssclasslabel) {
		dateSelector.setCssclasslabel(cssclasslabel);
	}
	
	public void setDescriptivemonth(Boolean descriptivemonth) {
		dateSelector.setDescriptivemonth(descriptivemonth);
	}
	
	public void setLabel(String label) {
		dateSelector.setLabel(label);
	}
	
	public void setSeparator(String separator) {
		dateSelector.setSeparator(separator);
	}

	public void setDisabled (Boolean disabled) {
		dateSelector.setDisabled(disabled);
	}
	
	public void setValidator(String value) {
		dateSelector.setSValidator(value);
	}
	
	public void setMessage(String value) {
		dateSelector.setsMessage(value);
	}
	
	public void setShowrequired(boolean showRequired) {
		dateSelector.setShowRequired(showRequired);
	}	
	
	public int doStartTag() {

		return SKIP_BODY;
	}

	public int doEndTag() {

		JspWriter out = pageContext.getOut();
		
		Tag t = findAncestorWithClass(this, com.seda.tag.library.FormTag.class);
		if (t != null) {
			com.seda.tag.library.FormTag formTag = (com.seda.tag.library.FormTag) t;
			formTag.putValidatedField(dateSelector.getPrefix(), dateSelector.getLabel(true), dateSelector.getSValidator(), dateSelector.getsMessage());
		}
		
		try {

			/*
			Boolean rejected = (Boolean)pageContext.getRequest().getAttribute(MAFAttributes.VALIDATION_REJECTED);

			if (rejected!=null && rejected) {
				String day=ParameterDiscovery.getEncodedParameter((HttpServletRequest) pageContext.getRequest(), dateSelector.getPrefix()+ Strings.DEFAULTSUFFIXDAY.format());
				String month=ParameterDiscovery.getEncodedParameter((HttpServletRequest) pageContext.getRequest(), dateSelector.getPrefix()+ Strings.DEFAULTSUFFIXMONTH.format());
				String year=ParameterDiscovery.getEncodedParameter((HttpServletRequest) pageContext.getRequest(), dateSelector.getPrefix()+ Strings.DEFAULTSUFFIXYEAR.format());				
				if (day!=null && month != null && year != null) {
					Calendar c = Calendar.getInstance();
					try{
					c.set(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(day));
					dateSelector.setCalendar(c);}
					catch(Exception e){}
				}
			}*/
			
			String selectedValueDay=ParameterDiscovery.getSafeEncodedParameter((HttpServletRequest)pageContext.getRequest(), dateSelector.getPrefix()+ Strings.DEFAULTSUFFIXDAY.format(),"");
			String selectedValueMonth=ParameterDiscovery.getSafeEncodedParameter((HttpServletRequest)pageContext.getRequest(), dateSelector.getPrefix()+ Strings.DEFAULTSUFFIXMONTH.format(),"");
			String selectedValueYear=ParameterDiscovery.getSafeEncodedParameter((HttpServletRequest)pageContext.getRequest(), dateSelector.getPrefix()+ Strings.DEFAULTSUFFIXYEAR.format(),"");
			
			if (selectedValueDay!=null && selectedValueDay.trim().length() > 0 && 
					selectedValueMonth != null && selectedValueMonth.trim().length() > 0 && 
					selectedValueYear != null && selectedValueYear.trim().length() > 0) {
				Calendar c = Calendar.getInstance();
				
				try{
					c.set(Integer.parseInt(selectedValueYear), Integer.parseInt(selectedValueMonth)-1, Integer.parseInt(selectedValueDay));
					dateSelector.setCalendar(c);
					dateSelector.setYear(Integer.parseInt(selectedValueYear));
					dateSelector.setMonth(Integer.parseInt(selectedValueMonth));
					dateSelector.setDay(Integer.parseInt(selectedValueDay));
				}
				catch(Exception e){}
			}
			
			dateSelector.render(out);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		dateSelector = getDateSelector();
		
		return EVAL_PAGE;
	}

}
