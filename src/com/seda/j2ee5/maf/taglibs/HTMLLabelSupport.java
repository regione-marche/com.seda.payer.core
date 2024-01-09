/**
 * 
 */
package com.seda.j2ee5.maf.taglibs;

/**
 * @author Seda Lab
 *
 */
public class HTMLLabelSupport extends HTMLBaseSupport{
	

	private static final long serialVersionUID = 1L;
	// For label support
    private String label;    
    private String labelClass;
    private String labelPosition="left";
    private boolean labelPrint=true;
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public String getLabelClass() {
		return labelClass;
	}
	public void setLabelClass(String labelClass) {
		this.labelClass = labelClass;
	}
	public String getPosition() {
		return labelPosition;
	}
	public void setPosition(String labelPosition) {
		this.labelPosition = labelPosition;
	}
	public void setLabelPrint(boolean labelPrint) {
		this.labelPrint = labelPrint;
	}
	public void setHTMLLabel(StringBuffer html, String id, boolean required) {
    	if (label!=null && labelPrint) {
			StringBuffer labelbuf = new StringBuffer();
			labelbuf.append("<label ");
			if (id!=null)
				labelbuf.append("for=\"" + id + "\"");

			labelbuf.append(labelClass != null ? (" class=\"" + labelClass + "\"") : "");
			labelbuf.append(">");
			if (required) {
//				html.insert(0,"<strong>*&nbsp;</strong>");
//				html.insert(0,"*");
			}
			labelbuf.append(label);            	
			if (labelPosition.equalsIgnoreCase("wrap")) {
				html.insert(0,labelbuf);
				html.append("</label>");
			} else if (labelPosition.equalsIgnoreCase("right")) {
				html.append(labelbuf);
				html.append("</label>");            		
			} else {
				labelbuf.append("</label>");
				html.insert(0,labelbuf);            		
			}
		} else {
			if (required) {
//				html.insert(0,"<strong>*&nbsp;</strong>");
//				html.insert(0,"*");
			}
		}
    }
    
	public void recycle() {
		super.recycle();
		setLabel(null);    
	    setLabelClass(null);
	    setPosition("left");		
	}
	
	public void appendRequired(StringBuffer html) {
//		html.append("<strong>*</strong>");
		html.append("*");
	}
	
}
