package com.seda.tag.core;

import com.seda.tag.i18n.Strings;

public class DateSelectorAgid extends DateSelector {

	@Override
	public String render() {

		try {

			sHtml = "<label class=\"active\" for=\"" + prefix + "_hidden\">" + label;

			if (showRequired && HtmlUtil.isRequired(sValidator)) {
				this.sHtml += HtmlUtil.getRequired();
			}

			sHtml += "</label>";

			String value_hidden = "";
			String value_day = "";
			String value_month = "";
			String value_year = "";

			if (!(day.equals(1) && month.equals(1) && year.equals(1000))) {

				value_hidden = String.format("%04d-%02d-%02d", year, month, day);
				value_day = day.toString();
				value_month = month.toString();
				value_year = year.toString();
			} else {
				
				day = -1;
				month = -1;
				year = -1;
			}
			
			sHtml += "<input type=\"date\" id=\"" + prefix + "_hidden\" name=\"" + prefix + "_hidden\" value=\""
					+ value_hidden + "\" class=\"form-control\" ";

			if (yearbegin != null && !yearbegin.isEmpty()) {
				sHtml += "min=\"" + yearbegin + "-01-01\" ";
			}

			if (yearend != null && !yearend.isEmpty()) {
				sHtml += "max=\"" + yearend + "-12-31\" ";
			}

			sHtml += getStrDisabled();
			
			sHtml += "/>";

			sHtml += "<input type=\"hidden\" id=\"" + prefix + Strings.DEFAULTSUFFIXDAY.format() + "\" name=\"" + prefix
					+ Strings.DEFAULTSUFFIXDAY.format() + "\" value=\"" + value_day + "\" />";

			sHtml += "<input type=\"hidden\" id=\"" + prefix + Strings.DEFAULTSUFFIXMONTH.format() + "\" name=\""
					+ prefix + Strings.DEFAULTSUFFIXMONTH.format() + "\" value=\"" + value_month + "\" />";

			sHtml += "<input type=\"hidden\" id=\"" + prefix + Strings.DEFAULTSUFFIXYEAR.format() + "\" name=\""
					+ prefix + Strings.DEFAULTSUFFIXYEAR.format() + "\" value=\"" + value_year + "\" />";

			sHtml += "<script type=\"text/javascript\" >\n"
					+ "	$(document).ready(function(){\n"
					+ "	    $(\"#" + prefix + "_hidden\").on(\"change\", function(){\n"
					+ "	    	console.log($(\"#" + prefix + "_hidden\").val());\n"
					+ "	    	const myArray = $(\"#" + prefix + "_hidden\").val().split(\"-\");\n"
					+ "	    	$(\"#" + prefix + Strings.DEFAULTSUFFIXDAY.format() + "\").val(myArray[2]);\n"
					+ "	    	$(\"#" + prefix + Strings.DEFAULTSUFFIXMONTH.format() + "\").val(myArray[1]);\n"
					+ "	    	$(\"#" + prefix + Strings.DEFAULTSUFFIXYEAR.format() + "\").val(myArray[0]);\n"
					+ "	    });\n"
					+ "	});\n"
					+ "</script>";
			
		} catch (Exception ex) {
			sHtml = "<div class=\"seda-ui-error\" >il tag DateSelector non è ben definito</div>";
		} finally {
			reset();
		}

		return sHtml;

	}

	@Override
	protected String getStrDisabled() {
		
		String disabled="";
		if (this.getDisabled())
			disabled="readonly=\"readonly\"";
		
		return disabled;
	}
	
}
