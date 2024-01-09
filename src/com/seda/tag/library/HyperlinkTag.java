package com.seda.tag.library;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;
import javax.servlet.jsp.tagext.Tag;

import com.seda.j2ee5.maf.components.defender.csrf.CSRFContext;
import com.seda.j2ee5.maf.components.defender.csrf.CSRFUtil;
import com.seda.tag.core.Hyperlink;

public class HyperlinkTag extends BodyTagSupport {
	/**
	 * Name
	 */
	private static final long serialVersionUID = 1L;

	Hyperlink hlnk = new Hyperlink();

	public void setTabindex(String value) {
		hlnk.setSTabIndex(value);
	}

	public void setTitle(String value) {
		hlnk.setSTitle(value);

	}

	public void setAlt(String alt) {
		hlnk.setSAlt(alt);

	}

	public void setName(String value) {
		hlnk.setSId(value);

	}

	public void setCssclass(String value) {
		hlnk.setSCss(value);
	}

	public void setImagesrc(String value) {
		
		try {
			hlnk.setSrc(Utility.buildURL(value,(HttpServletRequest)pageContext.getRequest(),(HttpServletResponse)pageContext.getResponse(),true));
		} catch (JspTagException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void setText(String value) {
		hlnk.setSText(value);

	}

	public void setCoords(String value) {
		hlnk.setSCoords(value);

	}

	public void setHref(String value) {
		hlnk.setSHref(value);

	}
	
	public void setOnclick(String value) {
		hlnk.setOnclick(value);

	}

	public void setTarget(String target) {
		hlnk.setSTarget(target);

	}
	
	public void setDisablecsrf(boolean disablecsrf) {
		hlnk.setbDisablecsrf(disablecsrf);
	}
	
	public int doStartTag() {

		return SKIP_BODY;

	}

	public int doEndTag() {

		Tag t = findAncestorWithClass(this, com.seda.tag.library.MenuTag.class);
		if (t != null) {
			hlnk.setBMenu(true);
		}

		if (hlnk.hasSHref()) {
			try {
				hlnk.setSHref(Utility.buildURL(hlnk.getSHref(),(HttpServletRequest)pageContext.getRequest(),(HttpServletResponse)pageContext.getResponse()));
			} catch (JspTagException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		if (!hlnk.isbDisablecsrf() && CSRFContext.getInstance().isActive()) {
			if (hlnk.hasSHref()) {
				String safeURL = CSRFUtil.getAugmentedUrl(pageContext.getSession(), hlnk.getSHref());
				
				//HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
				
				//safeURL= Utility.getAbsoluteURL(safeURL, request);
				
				hlnk.setSHref(safeURL);
			}
		}

		JspWriter writer = pageContext.getOut();
		try {
			hlnk.render(writer);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return EVAL_BODY_INCLUDE;
	}
	
	
	
}
