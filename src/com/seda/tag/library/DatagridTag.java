package com.seda.tag.library;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.BodyTagSupport;
import javax.sql.rowset.CachedRowSet;

import com.seda.commons.string.Convert;
import com.seda.j2ee5.maf.components.defender.csrf.CSRFContext;
import com.seda.j2ee5.maf.components.defender.csrf.CSRFUtil;
import com.seda.tag.core.Datagrid;
import com.seda.tag.core.DgColumn;

public class DatagridTag extends BodyTagSupport {
	/**
	 * 
	 */

	private static final long serialVersionUID = 1L;
	
	protected Datagrid dg = getDatagrid();

	public void setCssclass(String value) {
		dg.setSCss(value);
	}

	protected Datagrid getDatagrid() {
		
		return new Datagrid();
	}

	public void addDgHeader(DgColumn dgh) {
		dg.addDgColumn(dgh);
	}

	public void setRowperpage(String value) {
		dg.setSRowperPage(value);
	}

	public void setCachedrowset(String value) {
		dg.setRsQuery(value);

	}

	public void setBorder(int iBorder) {
		dg.setIBorder(iBorder);
	}

	public void setUsexml(boolean value) {
		dg.setBUsexml(value);
	}

	public void setCellpadding(int iCellPadding) {
		dg.setICellPadding(iCellPadding);
	}

	public void setCellspacing(int iCellSpacing) {
		dg.setICellSpacing(iCellSpacing);
	}

	public void setViewstate(String value) {
		dg.setViewstate(value);
	}

	public void setAction(String value) {
		dg.setSAction(value);
	}

	public int doStartTag() {
		dg.dgList = new ArrayList<DgColumn>();

		return EVAL_BODY_INCLUDE;
	}

	public int doEndTag() {
		{
			try {
				
				
				dg.setSAction(Utility.buildURL(dg.getSAction(),(HttpServletRequest)pageContext.getRequest(),(HttpServletResponse)pageContext.getResponse()));
				
				
				if (CSRFContext.getInstance().isActive()) {
						String safeURL = CSRFUtil.getAugmentedUrl(pageContext
								.getSession(), dg.getSAction());
						dg.setSAction(safeURL);
				}
				
				Object orderobject = pageContext.getAttribute("order");
				String order=null; 
				if (orderobject==null) {
					orderobject = pageContext.getRequest().getParameter("order");
				}
				if (orderobject!=null && orderobject instanceof java.lang.String) {
					order=String.valueOf(orderobject);
				}
				dg.setSOrder(order);
				
				if (dg.bUsexml) {
					dg.cRowSet = Convert.stringToWebRowSet((String) pageContext
							.getAttribute(dg.getRsQuery(),
									PageContext.REQUEST_SCOPE));
				}

				else if (pageContext.getRequest().getAttribute(dg.getRsQuery()) != null) {
					dg.cRowSet = (CachedRowSet) pageContext.getRequest().getAttribute(dg.getRsQuery());
				} else if (pageContext.getSession().getAttribute(dg.getRsQuery()) != null) {
					dg.cRowSet = (CachedRowSet) pageContext.getSession().getAttribute(dg.getRsQuery());
				}

				if (pageContext.getRequest().getAttribute(dg.getRsQuery() + ".pageInfo") != null)
					dg.setPageInfo(pageContext.getRequest().getAttribute(dg.getRsQuery() + ".pageInfo"));
				else if (pageContext.getSession().getAttribute(dg.getRsQuery() + ".pageInfo") != null) {
					dg.setPageInfo(pageContext.getSession().getAttribute(dg.getRsQuery() + ".pageInfo"));
				}
				
				/*
				if (pageContext.getRequest().getAttribute("viewstate") != null)
					dg.viewstate = (String) pageContext.getRequest().getAttribute(dg.viewstate);
				else if (pageContext.getSession().getAttribute(dg.getRsQuery()) != null) {
					dg.viewstate = (String) pageContext.getSession().getAttribute(dg.viewstate);
				} else
					dg.viewstate = "";
				*/
				
				if (pageContext.getRequest().getAttribute("viewstate") != null)
					dg.viewstate = (String) pageContext.getRequest().getAttribute("viewstate");
				else if (pageContext.getSession().getAttribute("viewstate") != null) {
					dg.viewstate = (String) pageContext.getSession().getAttribute("viewstate");
				} else
					dg.viewstate = "";
				
				JspWriter out = pageContext.getOut();

				if (dg.cRowSet != null)
					dg.render(out);

			} catch (Exception e) {
				e.printStackTrace();
			}
			dg = getDatagrid();
		}
		return EVAL_PAGE;
	}

	public void Add(DgColumn dg2) {
		dg.addDgColumn(dg2);
	}
}
