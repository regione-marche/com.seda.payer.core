package com.seda.payer.core.bean;

import java.io.Serializable;

import com.seda.data.spi.PageInfo;
/**
 * Questo bean contiene le informazioni relative
 * ad una pagina della tabella PYUSRTB: lista, riepilogo e paginazione
 * @author f.vadicamo
 *
 */
public class PyUserList implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String listXml = null;
	private String groupXml = null;
	private PageInfo pageInfo = null;
	
	public PyUserList() {
	}
	
	public PyUserList(String listXml, String groupXml, PageInfo pageInfo) {
		this.listXml = listXml;
		this.groupXml = groupXml;
		this.pageInfo = pageInfo;
	}
	
	public String getListXml() {
		return listXml;
	}
	public void setListXml(String listXml) {
		this.listXml = listXml;
	}
	public String getGroupXml() {
		return groupXml;
	}
	public void setGroupXml(String groupXml) {
		this.groupXml = groupXml;
	}
	public PageInfo getPageInfo() {
		return pageInfo;
	}
	public void setPageInfo(PageInfo pageInfo) {
		this.pageInfo = pageInfo;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	

}
