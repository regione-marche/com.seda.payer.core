package com.seda.payer.commons.formulary;

import java.util.Calendar;

/**
 * @author dbadm
 *
 */
public class DateFormulary {

	private boolean easterDay;
	private boolean angelDay;
	private boolean holiday;
	
	private Calendar date;
	private String description;
	
	public boolean isEasterDay() {
		return easterDay;
	}

	public void setEasterDay(boolean easterDay) {
		this.easterDay = easterDay;
	}

	public boolean isAngelDay() {
		return angelDay;
	}

	public void setAngelDay(boolean angelDay) {
		this.angelDay = angelDay;
	}

	public boolean isHoliday() {
		return holiday;
	}

	public void setHoliday(boolean holiday) {
		this.holiday = holiday;
	}

	public Calendar getDate() {
		return date;
	}

	public void setDate(Calendar date) {
		this.date = date;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public DateFormulary() {
		date = Calendar.getInstance();
	}

	public DateFormulary(Calendar date) {
		this.date = date;
	}
}
