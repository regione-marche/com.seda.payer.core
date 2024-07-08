package com.seda.tag.core;

import com.seda.tag.i18n.Strings;

 
public class DateSelectorAgid extends DateSelector {
	
	@Override
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
				
				sHtml += "<div id=\"" + prefix + "_div\" class=\"" + cssClass +"\" >";
				//sHtml += "<label for=\"" + prefix + Strings.DEFAULTMONTHID.format() + "\">"+ getSeparator(Strings.MONTHID.format(),1) +"</label>";
				sHtml += "<label style=\"display:none\" for=\"" + prefix + Strings.DEFAULTMONTHID.format() + "\">hidden</label>";
				
				sHtml += createSelect(prefix + Strings.DEFAULTSUFFIXMONTH.format(), Integer.parseInt(monthbegin), Integer.parseInt(monthend), month, prefix + Strings.DEFAULTMONTHID.format());
				//sHtml += "<label for=\"" + prefix + Strings.DEFAULTDAYID.format() + "\">"+ getSeparator(Strings.DAYID.format(),2) +"</label>";
				sHtml += createSelect(prefix + Strings.DEFAULTSUFFIXDAY.format(), 1, 31, day, prefix + Strings.DEFAULTDAYID.format());
				//sHtml += "<label for=\"" + prefix + Strings.DEFAULTYEARID.format() + "\">"+ getSeparator(Strings.YEARID.format(),3) +"</label>";
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
				
				sHtml += "<div style=\"column-gap: 10px\" id=\"" + prefix + "_div\" class=\"" + cssClass +"\">";
				//sHtml += "<label for=\"" + prefix + Strings.DEFAULTDAYID.format() + "\">"+ getSeparator(Strings.DAYID.format(),1) +"</label>";
				sHtml += "<label style=\"display:none\" for=\"" + prefix + Strings.DEFAULTDAYID.format() + "\">hidden</label>";
				sHtml += createSelect(prefix + Strings.DEFAULTSUFFIXDAY.format(), 1, 31, day, prefix + Strings.DEFAULTDAYID.format());
				//sHtml += "<label for=\"" + prefix + Strings.DEFAULTMONTHID.format() + "\">"+ getSeparator(Strings.MONTHID.format(),2) +"</label>";
				sHtml += createSelect(prefix + Strings.DEFAULTSUFFIXMONTH.format(), Integer.parseInt(monthbegin), Integer.parseInt(monthend), month, prefix + Strings.DEFAULTMONTHID.format());
				//sHtml += "<label for=\"" + prefix + Strings.DEFAULTYEARID.format() + "\">"+ getSeparator(Strings.YEARID.format(),3) +"</label>";
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
	
}
