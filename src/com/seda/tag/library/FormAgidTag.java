package com.seda.tag.library;

import com.seda.tag.core.Form;
import com.seda.tag.core.FormAgid;

public class FormAgidTag extends FormTag {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1370475387438261599L;

	@Override
	protected Form getForm() {
		return new FormAgid();
	}
	
}
