/**
 * 
 */
package com.seda.tag.library;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;

import com.seda.data.page.PageMetaData;
import com.seda.j2ee5.maf.components.defender.csrf.CSRFContext;
import com.seda.j2ee5.maf.components.defender.csrf.CSRFUtil;
import com.seda.tag.core.Paging;

/**
 * @author f.ricci
 *
 */
public class PagingTag extends BodyTagSupport {

	Paging pg = new Paging();
	
	
	public void setPagesize(String value) { 
		pg.setRowPerPage(value);
	}

	public void setAction(String value) {
		pg.setAction(value);
	}
	
	public void setMetadata(PageMetaData value) {
		pg.setPageMetaData(value);
	}	

	public void setShowfirst(boolean first) {
		pg.setShowfirst(first);
	}
	public void setShowlast(boolean last) {
		pg.setShowlast(last);
	}

	public void setShowrowindex(boolean rowindex) {
		pg.setShowrowindex(rowindex);
	}
	
	public void setShowrows(boolean rows) {
		pg.setShowrows(rows);
	}	

	public void setModifier(String modifier) {
		pg.setModifier(modifier);
	}	
	

	public void setShowpagesize(boolean pagesize) {
		pg.setShowpagesize(pagesize);
	}	
	
	public int doEndTag() {
		try {
			if (CSRFContext.getInstance().isActive()) {
				String safeURL = CSRFUtil.getAugmentedUrl(pageContext
						.getSession(), pg.getAction());
				pg.setAction(safeURL);
			}
			JspWriter out = pageContext.getOut();

			pg.render(out);
		} catch (Exception e) {	}
		pg = new Paging();
		return EVAL_PAGE;
	}
}
