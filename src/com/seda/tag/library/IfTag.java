package com.seda.tag.library;

import java.io.IOException;

import javax.servlet.jsp.*;

import javax.servlet.jsp.tagext.*;

import com.seda.tag.core.IfSeda;

/**
 * Tag handler class for the if tag. It relies on the
 * 
 * required 'test' attribute and stores the evaluated
 * 
 * condition in the test instance variable to be later
 * 
 * accessed by the ThenTag.java and ElseTag.java.
 */

public class IfTag extends BodyTagSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	IfSeda ifSeda = new IfSeda();

	public void setSElse(String else1) {
		ifSeda.sElse = else1;
	}

	public void setSThen(String then) {
		ifSeda.sThen = then;
	}

	public void setRight(String test) {

		ifSeda.right = test;

	}

	public void setLeft(String test) {

		ifSeda.left = test;

	}

	public void setControl(String test) {

		ifSeda.control = test;

	}

	public void setSecondleft(String test) {
		ifSeda.secondLeft = test;
	}

	public void setSecondright(String test) {
		ifSeda.secondRight = test;
	}

	public void setSecondcontrol(String test) {
		ifSeda.secondControl = test;

	}

	public void setOperator(String test) {

		ifSeda.operator = test;

	}

	public void setTest(boolean test) {

		ifSeda.test = test;

	}

	public int doStartTag() {
		return EVAL_BODY_INCLUDE;

	}

	public int doEndTag() throws JspException {
		RepeaterTag rptTag = (RepeaterTag) findAncestorWithClass(this, RepeaterTag.class);
		
		

		Tag tg = getParent();
		
		if(tg.getClass()== com.seda.tag.library.DgColumnTag.class){
			
		((DgColumnTag)tg).add(ifSeda);
		}
		else if(rptTag!= null){
			rptTag.Add(ifSeda);
			JspWriter out = pageContext.getOut();
			try {
				out.print("_IfSedaTag_");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		else
		{
			String msg = "Error: 'if' must be inside 'dgColumn' or 'repeater'.";

			throw new JspTagException(msg);
			
			
		}
		
		ifSeda = new IfSeda();
		return EVAL_PAGE;

	}

}
