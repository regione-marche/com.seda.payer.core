package com.seda.tag.library;
import javax.servlet.jsp.*;

import javax.servlet.jsp.tagext.*;

import java.io.*;

/** Tag handler class for the else tag. It gets a hold of

 *  the IfTag instance and processes its body if the value

 *  test attribute of the IfTag is false. It also throws

 *  a JspTagException if the parent of this tag is anything

 *  other than an instance of the IfTag class.

 */

public class ElseTag extends BodyTagSupport {

  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

public int doAfterBody() throws JspException {

    // Get parent tag (if tag)

    IfTag ifTag = null;

    try {

      ifTag = (IfTag)getParent();

    }

    catch (ClassCastException cce) {

      String msg = "Error: 'else' must be inside 'if'.";

      throw new JspTagException(msg);

    }

    if (ifTag != null) {

      // Decide whether to output body of else

     

    	  BodyContent bodyContent = super.getBodyContent();
	      String      bodyString  = bodyContent.getString();
	    

	     ifTag.setSElse(bodyString);

	     
	      try {
			bodyContent.clear();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	    
    	  
    

      

    } else {

      String msg = "Error: 'else' must be inside 'if'.";

      throw new JspTagException(msg);

    }
  return EVAL_PAGE;
  }

}