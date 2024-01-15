package com.seda.tag.library;



import javax.servlet.jsp.tagext.BodyTagSupport;
import javax.servlet.jsp.tagext.Tag;
import com.seda.tag.core.MenuCachedItem;

public class MenuCachedItemTag extends BodyTagSupport{
 /**
	 * Name
	 */
	private static final long serialVersionUID = 1L;



MenuCachedItem mcItem = new MenuCachedItem();
 			
public void setTitle(String value){
	mcItem.setSTitle(value);
	
}	
public void setName(String value){
	mcItem.setSId(value);
	
	
}	
public void setCssclass(String value){ mcItem.setSCss(value);}
public void setImagesrc(String value){ mcItem.setSrc(value);}
public void setText(String value){
	mcItem.setSText(value);
	
}
public void setCoords(String value){
	mcItem.setSCoords(value);
	
}			
public void setHref(String value){
	mcItem.setSHref(value);
	
}	
public int doStartTag() {
  
     
   	return SKIP_BODY;
   	
   }

   public int doEndTag(){
	   
	   Tag t = MenuCachedItemTag.this.getParent();
 	  if(t.getClass() == com.seda.tag.library.MenuCachedTag.class )
 	  {com.seda.tag.library.MenuCachedTag mcTag = (com.seda.tag.library.MenuCachedTag)t ;
 	 mcTag.Add(mcItem);}
 	  
 	  mcItem = new MenuCachedItem();
 	  
   	 return EVAL_PAGE;
   	 
}



}
