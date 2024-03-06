package com.seda.tag.library;

import javax.servlet.jsp.tagext.BodyTagSupport;
import javax.servlet.jsp.tagext.Tag;

import com.seda.tag.core.Cell;
import com.seda.tag.core.CellHeader;
import com.seda.tag.core.Row;

public class RowTag extends BodyTagSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Row tr = new Row();

	public void setCssclass(String value) {
		tr.setSCss(value);
	}
	public void setId(String value) {
		tr.setId(value);
	}
	public void Add(Cell cell) {
		tr.Add(cell);

	}

	public void Add(CellHeader cell) {
		tr.Add(cell);

	}

	public int doStartTag() {
		tr.sContent = "";

		return EVAL_BODY_INCLUDE;
	}

	public int doEndTag() {
		/*
		 * TbodyTag tbodyTag = (TbodyTag) findAncestorWithClass(this,
		 * TbodyTag.class); if (tbodyTag!=null) { tbodyTag.Add(tr); }
		 * 
		 * TheaderTag theaderTag = (TheaderTag) findAncestorWithClass(this,
		 * TheaderTag.class); if (theaderTag!=null) { theaderTag.Add(tr); }
		 */

		Tag t = RowTag.this.getParent();
		if (t.getClass() == com.seda.tag.library.TbodyTag.class) {
			com.seda.tag.library.TbodyTag tb = (com.seda.tag.library.TbodyTag) t;
			tb.Add(tr);
		} else if (t.getClass() == com.seda.tag.library.TheaderTag.class) {
			com.seda.tag.library.TheaderTag tb = (com.seda.tag.library.TheaderTag) t;
			tb.Add(tr);
		} else {
			TbodyTag tbodyTag = (TbodyTag) findAncestorWithClass(this, TbodyTag.class);
			if (tbodyTag!=null) { tbodyTag.Add(tr); }
		}

		return EVAL_PAGE;
	}
}
