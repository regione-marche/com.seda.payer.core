package com.seda.tag.core;

import java.io.IOException;
import java.util.Calendar;
import java.util.Locale;
import java.util.ResourceBundle;
import javax.servlet.jsp.JspWriter;
import com.seda.commons.string.Pad;
import com.seda.tag.i18n.Strings;

 
public class DateSelector implements TagRenderInterface {
	
	protected String sHtml="";
	protected String locale = Strings.DEFAULTLOCALE.format();
	protected String prefix = Strings.DEFAULTPREFIX.format();
	private Calendar calendar;
	private String cssclasslabel;
	private String cssclass;
	private Boolean descriptivemonth = false;
	protected String label;
	private String separator;
	private ResourceBundle rb;
	protected String yearbegin = Strings.YEARBEGIN.format();
	protected String yearend = Strings.YEAREND.format();
	protected String monthbegin = Strings.MONTHBEGIN.format();
	protected String monthend = Strings.MONTHEND.format();
	private Boolean disabled=false;
	protected String sValidator;
	private String sMessage;
	public boolean showRequired = false;
	protected Integer day =0;
	protected Integer month =0;
	protected Integer year =0;

	public void setDay(Integer day) {
		this.day = day;
	}

	public void setMonth(Integer month) {
		this.month = month;
	}


	public void setYear(Integer year) {
		this.year = year;
	}
	
	public void setShowRequired(boolean showRequired) {
		this.showRequired = showRequired;
	}
		
	
	public String getsMessage() {
		return sMessage;
	}

	public void setsMessage(String sMessage) {
		this.sMessage = sMessage;
	}

	public String getSValidator() {
		return sValidator;
	}

	public void setSValidator(String validator) {
		sValidator = validator;
	}
	
	public boolean getDisabled() {
		return disabled;
	}
	
	public void setDisabled(Boolean disabled) {
		this.disabled = disabled;
	}

	public String getYearbegin() {
		return yearbegin;
	}

	public void setYearbegin(String yearbegin) {
		this.yearbegin = yearbegin;
	}

	public String getYearend() {
		return yearend;
	}

	public void setYearend(String yearend) {
		this.yearend = yearend;
	}

	public void setMonthbegin(String monthbegin) {
		this.monthbegin = monthbegin;
	}

	public String getMonthbegin() {
		return monthbegin;
	}

	public void setMonthend(String monthend) {
		this.monthend = monthend;
	}

	public String getMonthend() {
		return monthend;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public boolean hasLabel() {
		return (this.label != null && this.label.trim() != "");
	}
	
	public String getLabel() {
		String label="";
		if (hasLabel())
			label = "<label" + this.getCssclasslabel()
			+ ">" + this.label + "</label>"; 
		return label;
	}
	
	public String getLabel(boolean original) {
		
		return this.label;
	}
	
	public void setSeparator(String separator) {
		this.separator = separator;
	}

	private boolean hasSeparator() {
		return (this.separator != null && this.separator.trim() != "");
	}
	
	protected String getSeparator(String obj, Integer position) {
		String separator="";
		if (hasSeparator())
		{
			if(position>1)
				separator = this.separator;
			else
				separator = " ";
		}
		else
		{
			if (locale == null)
				rb = ResourceBundle.getBundle(Strings.MESSAGESCLASS.format());
			else
				rb = ResourceBundle.getBundle(Strings.MESSAGESCLASS.format(),
						new Locale(locale));
		
			separator=rb.getString(obj);
			
			rb=null;
		}
		
		return separator;
	}
	
	public String getPrefix() {
		return prefix;
	}

	public void setDescriptivemonth(Boolean descriptivemonth) {
		this.descriptivemonth = descriptivemonth;
	}

	public void setCssclass(String cssclass) {
		this.cssclass = cssclass;
	}

	public void setCssclasslabel(String cssclasslabel) {
		this.cssclasslabel = cssclasslabel;
	}

	protected String getCssclass() {
		String cssClass="seda-ui-date ";
		if (hasCssclass())
			cssClass += this.cssclass;

		return cssClass;
	}

	protected String getCssclasslabel() {
		String cssClassLabel="seda-ui-label ";
		if (hasCssclasslabel())
			cssClassLabel += this.cssclasslabel;

		return cssClassLabel;
	}

	private boolean hasCssclass() {
		return (this.cssclass != null && this.cssclass.trim() != "");
	}

	public boolean hasCssclasslabel() {
		return (this.cssclasslabel != null && this.cssclasslabel.trim() != "");
	}

	public void setLocale(String locale) {
		this.locale = locale;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}
	
	public void setCalendar(Calendar calendar) {
		this.calendar = calendar;
		if (this.calendar!=null)
		{
			day = this.calendar.get(Calendar.DAY_OF_MONTH);
			month = this.calendar.get(Calendar.MONTH) + 1;
			year = this.calendar.get(Calendar.YEAR);
		}
	}
	
	protected String getStrDisabled(){
		String disabled="";
		if (this.disabled)
			disabled="disabled=\"disabled\"";
		
		return disabled;
	}

	public String render() {

		try {
			
			String cssClass = this.getCssclass();
			String cssClassLabel = this.getCssclasslabel();

			// default to current calendar if not specified
//			if (calendar == null) {
//				setCurrentCalendar();
//			}
			//Integer day =0;
			//Integer month =0;
			//Integer year =0;
				
			//if (calendar != null) {
				//day = calendar.get(Calendar.DAY_OF_MONTH);
				//month = calendar.get(Calendar.MONTH) + 1;
				//year = calendar.get(Calendar.YEAR);
				// Gestione data di default 1970-01-01
				if(day.equals(1) && month.equals(1) && year.equals(1000)) {
					day=-1;
					month=-1;
					year=-1;
				}
			//}
					
			
		
			// create the select tags
			if ((locale != null) && locale.toLowerCase().equals("en_us")
					|| locale.toLowerCase().equals("en-us")) {
				if (hasLabel())
				{
					sHtml = "<div id=\"" + prefix + "_label_div\" class=\"" + cssClassLabel +"\">" + this.label;

					if (showRequired && HtmlUtil.isRequired(sValidator)) {
						this.sHtml += HtmlUtil.getRequired();
					}
					
					sHtml += "</div>";
				}
				else
					sHtml ="";
				
				sHtml += "<div id=\"" + prefix + "_div\" class=\"" + cssClass +"\">";
				//sHtml += "<label for=\"" + prefix + Strings.DEFAULTMONTHID.format() + "\">"+ getSeparator(Strings.MONTHID.format(),1) +"</label>";
				sHtml += "<label style=\"display:none\" for=\"" + prefix + Strings.DEFAULTMONTHID.format() + "\">hidden</label>";
				
				sHtml += createSelect(prefix + Strings.DEFAULTSUFFIXMONTH.format(), Integer.parseInt(monthbegin), Integer.parseInt(monthend), month, prefix + Strings.DEFAULTMONTHID.format());
				sHtml += "<label for=\"" + prefix + Strings.DEFAULTDAYID.format() + "\">"+ getSeparator(Strings.DAYID.format(),2) +"</label>";
				sHtml += createSelect(prefix + Strings.DEFAULTSUFFIXDAY.format(), 1, 31, day, prefix + Strings.DEFAULTDAYID.format());
				sHtml += "<label for=\"" + prefix + Strings.DEFAULTYEARID.format() + "\">"+ getSeparator(Strings.YEARID.format(),3) +"</label>";
				sHtml += createSelect(prefix + Strings.DEFAULTSUFFIXYEAR.format(), Integer.parseInt(yearbegin) , Integer.parseInt(yearend), year, prefix + Strings.DEFAULTYEARID.format());
				sHtml += "</div>";
			} else if ((locale != null) && locale.toLowerCase().equals("it_it")
					|| locale.toLowerCase().equals("it-it")) {

				if (hasLabel()){
					sHtml = "<div id=\"" + prefix + "_label_div\" class=\"" + cssClassLabel +"\">" + this.label;
				
					if (showRequired && HtmlUtil.isRequired(sValidator)) {
						this.sHtml += HtmlUtil.getRequired();
					}
				
					sHtml += "</div>";
				}
				else
					sHtml ="";
				
				sHtml += "<div id=\"" + prefix + "_div\" class=\"" + cssClass +"\">";
				//sHtml += "<label for=\"" + prefix + Strings.DEFAULTDAYID.format() + "\">"+ getSeparator(Strings.DAYID.format(),1) +"</label>";
				sHtml += "<label style=\"display:none\" for=\"" + prefix + Strings.DEFAULTDAYID.format() + "\">hidden</label>";
				sHtml += createSelect(prefix + Strings.DEFAULTSUFFIXDAY.format(), 1, 31, day, prefix + Strings.DEFAULTDAYID.format());
				sHtml += "<label for=\"" + prefix + Strings.DEFAULTMONTHID.format() + "\">"+ getSeparator(Strings.MONTHID.format(),2) +"</label>";
				sHtml += createSelect(prefix + Strings.DEFAULTSUFFIXMONTH.format(), Integer.parseInt(monthbegin), Integer.parseInt(monthend), month, prefix + Strings.DEFAULTMONTHID.format());
				sHtml += "<label for=\"" + prefix + Strings.DEFAULTYEARID.format() + "\">"+ getSeparator(Strings.YEARID.format(),3) +"</label>";
				sHtml += createSelect(prefix + Strings.DEFAULTSUFFIXYEAR.format(), Integer.parseInt(yearbegin), Integer.parseInt(yearend), year, prefix + Strings.DEFAULTYEARID.format());
				sHtml += "</div>";
			}
			
		} catch (Exception ex) {
			sHtml = "<div class=\"seda-ui-error\" >il tag DateSelector non è ben definito</div>";
		}
		finally{
			reset();
		}

		return sHtml;

	}

	protected String createSelect(String name, int start, int count,
			int selectedIndex, String id) {
		String selectTag;
		selectTag = "<select name=\"" + name + "\" id=\"" + id + "\" " + getStrDisabled() + ">";
		
		selectTag += "<option selected value=\"\"></option>";
		
		if (descriptivemonth && id.equals(prefix + Strings.DEFAULTMONTHID.format())) {
			
			if (locale == null)
				rb = ResourceBundle.getBundle(Strings.MONTHCLASS.format());
			else
				rb = ResourceBundle.getBundle(Strings.MONTHCLASS.format(),
						new Locale(locale));
			
			for (Integer loop = start; loop < count + 1; loop++) {
				if (loop == selectedIndex) {
					selectTag += "<option selected value=\"" + loop + "\">"
							+ rb.getString(loop.toString()) + "</option>";
				} else {
					selectTag += "<option value=\"" + loop + "\">" + rb.getString(loop.toString())
							+ "</option>";
				}
			}
			
			rb=null;
			
		} else {
			String value;
			
			for (Integer loop = start; loop < count + 1; loop++) {
				if(id.equals(prefix + Strings.DEFAULTMONTHID.format()) || id.equals(prefix + Strings.DEFAULTDAYID.format()))
				{
					value=Pad.left(loop.toString(),2,'0');
				}else 
				{
					value=loop.toString();
				}
				
				if (loop == selectedIndex) {
					selectTag += "<option selected value=\"" + value + "\">"
							+ value + "</option>";
				} else {
					selectTag += "<option value=\"" + value + "\">" + value
							+ "</option>";
				}
			}
		}
		
		selectTag += "</select>";

		return selectTag;
	}

	protected void reset() {
		locale = "en_US";
		prefix = "date";
		calendar = null;
	}

	public void render(JspWriter writer) throws IOException {
		writer.println(render());
	}

}
