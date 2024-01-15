package com.seda.tag.core;

import java.io.IOException;

import javax.servlet.jsp.JspWriter;

public class TextArea implements TagRenderInterface {
	private  String text;
	private  String label;
	private  String cssclass;
	private  String cssclasslabel;
	
	private  boolean bmodify =true;
	private  String name;
	private  String sHtml;
	private  int col;
	private int row =1;
	private boolean bDisabled = false;
	private boolean bReadonly = false;
    String sTabIndex;
    private boolean showRequired=false;
    private String sValidator;
    private String sMessage;
	
    
    public String getsValidator() {
		return sValidator;
	}

	public void setsValidator(String sValidator) {
		this.sValidator = sValidator;
	}

	public String getsMessage() {
		return sMessage;
	}

	public void setsMessage(String sMessage) {
		this.sMessage = sMessage;
	}

	public String getSTabIndex() {
		return sTabIndex;
	}

    public void setShowRequired(boolean showRequired) {
		this.showRequired = showRequired;
	}
    

	public void setSTabIndex(String tabIndex) {
		sTabIndex = tabIndex;
	}
	
	public boolean hasTabIndex()
	{
		return (sTabIndex!=null && sTabIndex.trim().length() >0);
	}
	public String getCssclass() {
		return cssclass;
	}
	public void setCssclass(String cssclass) {
		this.cssclass = cssclass;
	}
	public String getCssclasslabel() {
		return cssclasslabel;
	}
	public void setCssclasslabel(String cssclasslabel) {
		this.cssclasslabel = cssclasslabel;
	}
	public int getCol() {
		return col;
	}
	public void setCol(int col) {
		this.col = col;
	}
	public int getRow() {
		return row;
	}
	public void setRow(int row) {
		this.row = row;
	}
	public boolean isBDisabled() {
		return bDisabled;
	}
	public void setBDisabled(boolean disabled) {
		bDisabled = disabled;
	}
	public boolean isBReadonly() {
		return bReadonly;
	}
	public void setBReadonly(boolean bReadonly) {
		this.bReadonly = bReadonly;
	}

		
	public String getSValidator() {
		return sValidator;
	}
	public void setSValidator(String validator) {
		sValidator = validator;
	}
	public void setName(String value){
		name=value;
		
	}
	public String getName(){
		return(name);
		
	}
	public void setBmodify(boolean value){
		bmodify=value;
		
	}
	public boolean getBmodify(){
		return(bmodify);
		
	}
	public void setLabel(String value){
		label=value;
		
	}
	public String getLabel(){
		return(label);
		
	}
	public void setText(String value){
		text=value;
		
	}
	public String getText(){
		return(text);
		
	}
	

	public boolean hasName(){
        return (getName()!=null && getName().length()>0);
	}
	public boolean hasText(){
        return (getText()!=null && getText().length()>0);
	}
	public boolean hasCol(){
        return (getCol()!=0 && getCol()>0);
	}
	public String hasCss()
	{
		if (cssclass != null && cssclass.trim()!="")
			return " "+cssclass;
			else
				return"";
	}
	public String hasCssLabel()
	{
		if (cssclasslabel != null && cssclasslabel.trim()!="")
			return " "+cssclasslabel;
			else
				return"";
	}
	
	
	public String render() {
	try{
			
		if(hasName())
		{
			this.sHtml=" <label ";
			
			this.sHtml +="class=\"seda-ui-label"+hasCssLabel()+  "\" ";
			
			this.sHtml +=" for=\""+name +"\" ";
			
			
			this.sHtml += " >" +label; 
			
			if (showRequired && HtmlUtil.isRequired(sValidator)) {
				this.sHtml += HtmlUtil.getRequired();
			}
			
			this.sHtml +="</label> ";
			
			
			this.sHtml +="<textarea id=\""+name+"\" name=\""+name +"\"  class=\"seda-ui-textarea"+hasCss()+ "\" rows=\""+row + "\" ";
			if(hasCol())
			this.sHtml+= " cols=\""+col+"\" "  ;
			if(hasTabIndex())
			{sHtml += " tabindex=\""+sTabIndex + "\" ";
			}
			if(bDisabled)
			{
				this.sHtml += " disabled=\"disabled\""; 
			}
			if(bReadonly)
			{
				this.sHtml += " readonly=\"readonly\""; 
			}
			if(!bmodify)
			{
				this.sHtml += " readonly=\"readonly\""; 
			}
			this.sHtml += ">";
			if(hasText())
			{
				
				this.sHtml += text; 
				
			}
			
			
			this.sHtml += "</textarea> ";
		}
		else
			this.sHtml="<div class=\"seda-ui-error\" >la text area non è ben definita</div>"	;
			
			
		}
		catch(Exception ex){
			this.sHtml="<div class=\"seda-ui-error\" >la text area non è ben definita</div>"	;
		}
		
		
		
	return this.sHtml;
	}

	public void render(JspWriter writer) throws IOException {
	
		writer.print(render());
    	
	}
}
