package com.seda.tag.core;

import java.util.ArrayList;



public class DgColumn {
	int iIndex;
	String sLabel;
	String sHtml;
	String sTitle;
	String sContent;
	String sAsc;
	String sDesc;
	ArrayList <IfSeda> IfTags = new ArrayList<IfSeda>();
	IfSeda ifTag;
	String sCss;
	String format;
	
	
	public String getFormat() {
		return format;
	}
	public void setFormat(String format) {
		this.format = format;
	}
	public String getSCss() {
		return sCss;
	}
	public void setSCss(String css) {
		sCss = css;
	}
	public IfSeda getIfTag() {
		return ifTag;
	}
	public void setIfTag(IfSeda ifSeda) {
		this.ifTag = ifSeda;
	}
	public int getIIndex() {
		return iIndex;
	}
	public void setIIndex(int index) {
		iIndex = index;
	}
	public String getSLabel() {
		return sLabel;
	}
	public void setSLabel(String label) {
		sLabel = label;
	}
	public String getSTitle() {
		return sTitle;
	}
	public void setSTitle(String title) {
		sTitle = title;
	}
	public String getSContent() {
		return sContent;
	}
	public void setSContent(String content) {
		sContent = content;
	}
	public String getSAsc() {
		return sAsc;
	}
	public void setSAsc(String asc) {
		sAsc = asc;
	}
	public String getSDesc() {
		return sDesc;
	}
	public void setSDesc(String desc) {
		sDesc = desc;
	}
	public void Add(IfSeda IfTag)
	{
		IfTags.add(IfTag);
		
		
	}	
	public String Css(){
	if(sCss!=null && sCss.trim()!="")
		return " " +sCss;
	else
		return"";
	
		
	}
	}

