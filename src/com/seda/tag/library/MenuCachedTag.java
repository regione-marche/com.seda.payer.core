package com.seda.tag.library;
 
import java.io.IOException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.BodyTagSupport;
import javax.sql.rowset.CachedRowSet;

import com.seda.commons.string.Convert;
import com.seda.tag.core.MenuCached;
import com.seda.tag.core.MenuCachedItem;


public class MenuCachedTag extends BodyTagSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
  MenuCached mMenu = new MenuCached();
  

  public void setCachedrowset(String sCachedRowSet){mMenu.setSCachedRowSet(sCachedRowSet); }
  public void Add(MenuCachedItem mcItem){mMenu.Add(mcItem);}
  public void setCssclass(String value){ mMenu.setSCss(value);}
  public void setVertical(boolean bVertical) {mMenu.setBVertical(bVertical);}
  public void setBusexml(boolean bUsexml){mMenu.setBUseXml(bUsexml);}
  
  
  
 public int doEndTag(){
	   	JspWriter writer = pageContext.getOut();
	   
	   	try {
	   		if(mMenu.getSCachedRowSet()!=null &&  mMenu.getSCachedRowSet().trim()!="")
	   		{	
	   		if(mMenu.isBUseXml())	
	    		{if(pageContext.getRequest().getAttribute(mMenu.getSCachedRowSet())!=null)
	   			mMenu.setCRowSet(Convert.stringToWebRowSet((String)pageContext.getAttribute(mMenu.getSCachedRowSet(),PageContext.REQUEST_SCOPE)));
	    		mMenu.render(writer);
	    		}
	   		else 
	   			if(pageContext.getRequest().getAttribute(mMenu.getSCachedRowSet())!=null){
	   				mMenu.setCRowSet((CachedRowSet) pageContext.getRequest().getAttribute(mMenu.getSCachedRowSet()));
	   				mMenu.render(writer);}
	   		else
	   			if(pageContext.getSession().getAttribute(mMenu.getSCachedRowSet())!=null){
	   						mMenu.setCRowSet((CachedRowSet) pageContext.getSession().getAttribute(mMenu.getSCachedRowSet()));
	   						mMenu.render(writer);
	   					}
	   		}
	   		else
	   			mMenu.render(writer);
	   	
	   	}
	   	catch (Exception e) {
			try {
				mMenu.render(writer);
			} catch (IOException e1) {
				e1.printStackTrace();
				System.out.println((String)pageContext.getAttribute(mMenu.getSCachedRowSet(),PageContext.REQUEST_SCOPE));
				
			}
			e.printStackTrace();
			System.out.println((String)pageContext.getAttribute(mMenu.getSCachedRowSet(),PageContext.REQUEST_SCOPE));
		}
	   
	   	
	   	mMenu= new MenuCached();
		  return EVAL_PAGE;   
 				}
}
