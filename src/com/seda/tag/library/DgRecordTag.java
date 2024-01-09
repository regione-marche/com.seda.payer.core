package com.seda.tag.library;


import java.util.ArrayList;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.BodyTagSupport;
import javax.sql.rowset.CachedRowSet;


import com.seda.commons.string.Convert;
import com.seda.tag.core.DgColumn;
import com.seda.tag.core.DgRecord;
public class DgRecordTag  extends BodyTagSupport{

	private static final long serialVersionUID = 1L;
DgRecord dgr1 = new DgRecord();
  
  
  public void setCachedrowset(String Value){
	 // dgr1.setCRowSet((CachedRowSet) pageContext.getRequest().getAttribute(Value));
	dgr1.setSRowset(Value);
				
  }
  public void setUsexml(boolean value){dgr1.setBUsexml(value);}
  public void setTitle(String Value){
	  dgr1.setSTitle(Value);
}
  public void setBorder(int iBorder){
	  dgr1.setIBorder(iBorder) ;
	}public void setCellpadding(int iCellPadding){
		dgr1.setICellPadding(iCellPadding);
	}
	 public void setCssclass(String value){ dgr1.setSCss(value);}
	public void setCellspacing(int iCellSpacing){
		dgr1.setICellSpacing(iCellSpacing);
	}

  public int doStartTag() {
	  dgr1.dgList= new ArrayList<DgColumn>();
		
		return EVAL_BODY_INCLUDE;
	  }


	public int doEndTag(){
	
		  try {
		if(dgr1.bUsexml)
		{dgr1.cRowSet=Convert.stringToWebRowSet((String)pageContext.getAttribute(dgr1.getSRowset(),PageContext.REQUEST_SCOPE));
		}
		
		else	if(pageContext.getRequest().getAttribute(dgr1.getSRowset())!=null){
			  dgr1.setCRowSet((CachedRowSet) pageContext.getRequest().getAttribute(dgr1.getSRowset()));}
				else
					if(pageContext.getSession().getAttribute(dgr1.getSRowset())!=null){
						dgr1.setCRowSet((CachedRowSet) pageContext.getSession().getAttribute(dgr1.getSRowset()));
						
					}
		JspWriter out = pageContext.getOut();
		if(out != null)
		 
			if(dgr1.cRowSet.size()>0)
				dgr1.render(out);
			
		} catch (Exception e) {
			
		}
		dgr1= new DgRecord();
		
	return EVAL_PAGE;	
	}

	public void Add(DgColumn dg) {
		dgr1.addDgColumn(dg);
	}
	}



