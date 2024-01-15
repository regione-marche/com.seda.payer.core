package com.seda.tag.library;


import javax.servlet.jsp.*;

import javax.servlet.jsp.tagext.*;

import com.seda.tag.core.DgColumn;
import com.seda.tag.core.IfSedaDatagrid;




 

/** Tag handler class for the if tag. It relies on the

 *  required 'test' attribute and stores the evaluated

 *  condition in the test instance variable to be later

 *  accessed by the ThenTag.java and ElseTag.java.

 */

public class IfDatagridTag extends BodyTagSupport {

  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
   IfSedaDatagrid ifSeda = new IfSedaDatagrid();
 



public void addDgElse(DgColumn dg) {
	ifSeda.AddDgElse(dg);
}


public void addDgThen(DgColumn dg) {
	ifSeda.AddDgThen(dg) ;
}



  public void setRight(String test) {
	 if(test!=null)
	  if(!test.contains("$")){
 if(test.contains("{"))
 {  int iSelint =	test.indexOf("{");
	int iSelend = test.indexOf("}");
	if(iSelint>=0 && iSelend >=0){
	String session =  test.substring(iSelint+1, iSelend);
	String sValue =(String)pageContext.getRequest().getAttribute(session);
	
		
	if(sValue!=null)
	test =test.replace(test.substring(iSelint, iSelend+1),sValue);}
	else 
		test="";
	}
	  }
	else 
 		test="";
 
 ifSeda.right = test;

	  }

  public void setLeft(String test) {
	  if(test!=null)
	  if(!test.contains("$")){
	  if(test.contains("{"))
	  {  int iSelint =	test.indexOf("{");
	 	int iSelend = test.indexOf("}");
	 	if(iSelint>=0 && iSelend >=0){
	 	String session =  test.substring(iSelint+1, iSelend);
	 	String sValue =(String)pageContext.getRequest().getAttribute(session);
	 	
	 		
	 	if(sValue!=null)
	 	test =test.replace(test.substring(iSelint, iSelend+1),sValue);}
	 	else 
	 		test="";
	 	}
  }
		else 
	 		test="";
	  ifSeda.left = test;

	  }

  public void setControl(String test) {

	  ifSeda.control = test;

	  }
  
  public void setTest(boolean test) {

	  ifSeda.test = test;

	  }

 

 
  


  public int doStartTag(){
	return EVAL_BODY_INCLUDE;  
  }
  
  
  
  public int doEndTag() throws JspException {
     
	  
     
	  Tag t = this.getParent();
	  if(t.getClass()== com.seda.tag.library.DatagridTag.class)
	  {    DatagridTag dgTag = null;
		  
	    try {

	    	dgTag = (DatagridTag)getParent();

	    }
	    catch(ClassCastException cce){
	    	 String msg = "Error: 'if' must be inside 'Datagrid or DgRecord'.";
	    	
	    	  throw new JspTagException(msg);
	    }
	    
	    if (dgTag != null) {
        
	    	
	 	   if (!ifSeda.Check(ifSeda.left, ifSeda.right,ifSeda.getControl()))
	 	   {
	 		  if(ifSeda.dgElse!= null)
		 			for(int i=0;i<ifSeda.dgElse.size();i++)	
		 			{
		 	    	dgTag.Add(ifSeda.dgElse.get(i));	
		 	    	}

	 		   
	 		
	 		
	 	    }
	 	    else
	 	    {	
	 	    	  if(ifSeda.dgThen!= null)
			 	    	for(int i=0;i<ifSeda.dgThen.size();i++)	
			 	 	   {
			 	 	    	dgTag.Add(ifSeda.dgThen.get(i));	
			 	 	    	
			 	 	   } 	    }
	    	
	  
	    	
	    	
	    }
	    	
	     else {

	        String msg = "Error: 'if' must be inside 'dgColumn'.";

	        throw new JspTagException(msg);

	      }
	  }
	  
	  else 
	  if(t.getClass()== com.seda.tag.library.DgRecordTag.class)
	  {   DgRecordTag dgTag = null;
		  
	    try {

	    	dgTag = (DgRecordTag)getParent();

	    }
	    catch(ClassCastException cce){
	    	 String msg = "Error: 'if' must be inside 'Datagrid or DgRecord'.";
	    	
	    	  throw new JspTagException(msg);
	    }
	    
 if (dgTag != null) {
        
	    	
	 	    if (!ifSeda.Check(ifSeda.left, ifSeda.right,ifSeda.getControl()))
	 	   {	if(ifSeda.dgElse!= null)
	 	    for(int i=0;i<ifSeda.dgElse.size();i++)	
	 	   {
	 	    	dgTag.Add(ifSeda.dgElse.get(i));	
	 	    	
	 	   }
	 	    }
	 	    else
	 	    {	if(ifSeda.dgThen!= null)
	 	    	for(int i=0;i<ifSeda.dgThen.size();i++)	
	 	 	   {
	 	 	    	dgTag.Add(ifSeda.dgThen.get(i));	
	 	 	    	
	 	 	   }

	 	    }
 }
	     else {

	        String msg = "Error: 'if' must be inside 'dgColumn'.";

	        throw new JspTagException(msg);

	      }
	  }
	  
	ifSeda = new IfSedaDatagrid();
   return EVAL_PAGE;

  }

}
