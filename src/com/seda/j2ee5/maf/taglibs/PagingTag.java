/**
 * 
 */
package com.seda.j2ee5.maf.taglibs;

import java.io.IOException;

import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.BodyTagSupport;

import com.seda.commons.logger.CustomLoggerManager;
import com.seda.commons.logger.LoggerWrapper;
//import com.seda.commons.string.Convert;
import com.seda.data.page.PageMetaData;
import com.seda.j2ee5.maf.components.defender.csrf.CSRFContext;
import com.seda.j2ee5.maf.components.defender.csrf.CSRFUtil;
import com.seda.j2ee5.maf.components.validation.ValidationContext;
import com.seda.j2ee5.maf.components.validation.ValidationUtil;
import com.seda.j2ee5.maf.util.MAFLogger;
/**
 * @author Seda Lab
 *
 */
public class PagingTag extends BodyTagSupport {

	private static final long serialVersionUID = 1L;

	private static final LoggerWrapper logger =  CustomLoggerManager.get(PagingTag.class);
	
	private String name;
	private String action;	
	
	private String pageSizeName;
	private String pageNumberName;
	private String pageNumberTargetName;	

	private int pageSizeBase;
	private int pageSizeGap;
	private int pageSizeMax;	
	
	private PageMetaData pageMetaData;

	public void setName(String name) {
		this.name = name;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public void setPageSizeBase(int pageSizeBase) {
		this.pageSizeBase = pageSizeBase;
	}

	public void setPageSizeGap(int pageSizeGap) {
		this.pageSizeGap = pageSizeGap;
	}

	public void setPageSizeMax(int pageSizeMax) {
		this.pageSizeMax = pageSizeMax;
	}

	public void setMetaData(PageMetaData pageMetaData) {
		this.pageMetaData = pageMetaData;
	}
	
	public void setPageSizeName(String pageSizeName) {
		this.pageSizeName = pageSizeName;
	}

	public void setPageNumberName(String pageNumberName) {
		this.pageNumberName = pageNumberName;
	}

	public void setPageNumberTargetName(String pageNumberTargetName) {
		this.pageNumberTargetName = pageNumberTargetName;
	}

	public int doStartTag() throws JspTagException {
        return EVAL_BODY_BUFFERED;
    }
	
	 public int doEndTag() throws JspTagException {
		 if (pageMetaData==null) {
			 return EVAL_PAGE;
		 }
		 pageSizeBase=pageSizeBase>0?pageSizeBase:15;
		 pageSizeGap=pageSizeGap>0?pageSizeGap:5;
		 pageSizeMax=pageSizeMax>0?pageSizeMax:30;
		 pageSizeName=pageSizeName==null?"pageSize":pageSizeName;
		 pageNumberName=pageNumberName==null?"pageNumber":pageNumberName;
		 pageNumberTargetName=pageNumberTargetName==null?"pageNumberTarget":pageNumberTargetName;
		 try {
			
			 StringBuffer html = new StringBuffer();

			 String pageSizeId=getFieldId(pageSizeName);
			 String pageNumberId=getFieldId(pageNumberName);

			 // the action of the button or list box
			 int prevPage = pageMetaData.getPageNumber()>1?pageMetaData.getPageNumber()-1:1;

			 int nextPage = pageMetaData.getPageNumber()<pageMetaData.getTotalPages()?pageMetaData.getPageNumber()+1:pageMetaData.getTotalPages();			 

			 String commonActions = action;
//			 commonActions=addActionParamter(commonActions, pageSizeName, String.valueOf(pageMetaData.getPageSize()));
			 String prevPageAction = addActionParamter(commonActions, pageNumberTargetName, String.valueOf(prevPage));
			 String nextPageAction = addActionParamter(commonActions, pageNumberTargetName, String.valueOf(nextPage));

			 // the functions
			 String prevFn = JSUtil.getFunctionName("click", name, "prev");
			 String nextFn = JSUtil.getFunctionName("click", name, "next");

			 String prevJs = JSUtil.getSubmitFunctionScript(prevFn, prevPageAction, name);
			 String nextJs = JSUtil.getSubmitFunctionScript(nextFn, nextPageAction, name);

			 String jsPageSize=JSUtil.getSubmitFormCode(null);
			 String jsPageNumber=JSUtil.getSubmitFormCode(null);			 
			 // append the js function
			 html.append(prevJs);
			 html.append(nextJs);
			 // append the html tags
			 html.append("<form");
			 html.append(" class=\"seda-ui-formdg\"");
			 html.append(" name=\"" + name +"\"");
			 html.append(" action=\"" + commonActions +"\"");
			 html.append(" method=\"post\"");
			 html.append(">");

			 if (CSRFContext.getInstance().isActive()) {
				 String inputHidden = CSRFUtil.getInputHidden(pageContext.getSession());
				 html.append(inputHidden);
			 }

			 if (ValidationContext.getInstance().isActive()) {
	    			String inputHidden = ValidationUtil.getInputNameHidden(name);
	    			html.append(inputHidden);
	    	 }			 
			 
			 html.append(getLabelFor(pageSizeId, "Righe per pagina:", "seda-ui-labeldgrow"));
			 html.append(getSelectInput(pageSizeId, pageSizeName, jsPageSize, 
					 pageSizeBase, pageSizeGap, pageSizeMax,pageMetaData.getPageSize(),"seda-ui-ddlrow"));

			 html.append("<div class=\"pInfoRight\">");

			 html.append("<a class=\"seda-ui-paginglnk  seda-ui-leftarrow\" href=\"javascript:{}\" onclick=\""+prevFn+"();\"><span class=\"seda-ui-spanprev\">Prev</span></a>");

			 html.append("<span class=\"seda-ui-spandgpage\"> pagina "+pageMetaData.getPageNumber()+" di "+pageMetaData.getTotalPages()+" </span>");

			 html.append(getLabelFor(pageNumberId, "Pagina:", "seda-ui-labeldgpage"));
			 html.append(getSelectInput(pageNumberId,pageNumberName, jsPageNumber,
					 1, 1, pageMetaData.getTotalPages(),pageMetaData.getPageNumber(), "seda-ui-ddlpage"));

			 html.append("<a class=\"seda-ui-paginglnk seda-ui-rightarrow\" href=\"javascript:{}\" onclick=\""+nextFn+"();\"><span class=\"seda-ui-spannext\">Next</span></a>");

			 html.append("</div>");

			 html.append("</form>");

			 pageContext.getOut().print(html.toString());
			 return EVAL_PAGE;
		 }
		 catch (IOException e) {
			 logger.error(MAFLogger.getMessage("generic_exception"), e);
			 throw new JspTagException(e.getMessage());
		 }
	 }
	 
	 public String getFieldId(String name) {
		 return this.name+"_"+name;
	 }	 
	 
	 public String getLabelFor(String id, String label, String css) {
		 StringBuffer html = new StringBuffer();
		 html.append("<label class=\""+ css +"\" for=\""+id+"\">");
		 html.append(label);
		 html.append("</label>");		 
		 return html.toString();
	 }
	 
	 public String getReadonlyInput(String id, String name, int value) {
		 StringBuffer html = new StringBuffer();
		 String valueString = String.valueOf(value);
		 html.append("<input");
		 html.append(" type=\"text\"");
		 html.append(" id=\""+id+"\"");
		 html.append(" name=\""+name+"\"");		 
		 html.append(" value=\""+valueString+"\"");		 
		 html.append(" readonly=\"readonly\"");
		 html.append(" size=\""+valueString.length()+"\"");
		 html.append("/>");		 
		 return html.toString();
	 }	 
	 
//	 public String getInputButton(String id, String name, String value, String alt, String fn, boolean disabled) {
	 public String getInputButton(String id, String name, String value, String alt, String fn) {		 
		 StringBuffer html = new StringBuffer();
		 html.append("<input");
		 html.append(" type=\"button\"");
		 html.append(" id=\""+id+"\"");
		 html.append(" name=\""+name+"\"");		 
		 html.append(" value=\""+value+"\"");
		 html.append(" alt=\""+alt+"\"");		 
		 html.append(" title=\""+alt+"\"");		 
//		 html.append(disabled?" disabled=\"disabled\"":"");		 
		 html.append(" onclick=\""+fn+"\"");		 
		 html.append("/>");		 
		 return html.toString();
	 }	 	 
	 
	 public String getLinkButton(String id, String name, String value, String alt, String function) {		 
		 StringBuffer html = new StringBuffer();
		 html.append("<a");
		 html.append(" id=\""+id+"\"");
//		 html.append(" name=\""+name+"\"");		 
		 html.append(" alt=\""+alt+"\"");		 
		 html.append(" title=\""+alt+"\"");		 
		 html.append(" href=\"javascript:{}");		 
		 html.append(" onclick=\""+function+"\"");		 
		 html.append("/>");
		 html.append(value);		 
		 html.append("</a>");
		 return html.toString();
	 }	 
	 
	 public String getSelectInput(String id, String name, String fn, int pageBase, int pageGap, int pageMax, int selected, String css) {
		 StringBuffer html = new StringBuffer();
		 html.append("<select");
		 html.append(" class=\""+css+"\"");
		 html.append(" id=\""+id+"\"");
		 html.append(" name=\""+name+"\"");
		 html.append(" onchange=\""+fn+"\"");		 
		 html.append("/>");
		 for (int i = pageBase; i <= pageMax; i+=pageGap) {
			 html.append("<option value=\""+i+"\"");
			 html.append(i==selected?" selected=\"selected\"":"");
			 html.append(">");			 
			 html.append(i);
			 html.append("</option>");			 			 
		 }
		 html.append("</select>");
		 return html.toString();
	 }
	
	 public String addActionParamter(String userAction, String param, String value) {
		 String modified="";
		 if (userAction!=null) {
			 if (userAction.indexOf("?")==-1) {
				 modified=userAction+"?";
			 } else {
				 modified=userAction+"&";
			 }
			 modified+=param+"="+value;			 
		 } 
		 return modified;
	 }

}
