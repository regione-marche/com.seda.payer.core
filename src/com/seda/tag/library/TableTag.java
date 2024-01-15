package com.seda.tag.library;


import java.io.IOException;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;

import com.seda.tag.core.Tbody;
import com.seda.tag.core.Table;
import com.seda.tag.core.Theader;




public class TableTag extends BodyTagSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	Table table = new Table();
	
	
	
	   public void setCssclass(String value){table.setSCss(value);}
	public void setBorder(int iBorder){
		table.setIBorder(iBorder) ;
	}
	public void setCellpadding(int iCellPadding){
		table.setICellPadding(iCellPadding);
	}
	public void setId(String id)
	{
		table.setId(id);
	}
	public void setCellspacing(int iCellSpacing){
		table.setICellSpacing(iCellSpacing);
	}
	
	 public void setTBody(Tbody tbody){
		 table.setTBody(tbody);
	 }
	 public void setTHead(Theader thead){
		 table.setTHead(thead);
	 }
	
	 public int doStartTag() {
	     table.tHead =null;
		 table.tBody = null;
	      
	    	return EVAL_BODY_INCLUDE;
	    	
	    }

	    public int doEndTag(){
	    	JspWriter writer = pageContext.getOut();
	    	try {
				table.render(writer);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    table = new Table();
		  return EVAL_PAGE;
	 }
	
	



}