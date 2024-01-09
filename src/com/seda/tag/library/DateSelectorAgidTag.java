package com.seda.tag.library;

import com.seda.tag.core.DateSelector;
import com.seda.tag.core.DateSelectorAgid;

public class DateSelectorAgidTag extends DateSelectorTag {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5774985908766193206L;

	@Override
	protected DateSelector getDateSelector() {
		
		return new DateSelectorAgid();
	}
	
}
