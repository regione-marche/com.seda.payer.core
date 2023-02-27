package com.seda.payer.core.bean;

import java.io.Serializable;

import com.seda.data.spi.PageInfo;

@SuppressWarnings("serial")
public class LogPage implements Serializable{
private String listaXml = null;
private String listaRiepilogoXml = null;
private PageInfo pageInfo = null;

public String getListaXml() {
	return listaXml;
}
public void setListaXml(String listaXml) {
	this.listaXml = listaXml;
}
public String getListaRiepilogoXml() {
	return listaRiepilogoXml;
}
public void setListaRiepilogoXml(String listaRiepilogoXml) {
	this.listaRiepilogoXml = listaRiepilogoXml;
}
public PageInfo getPageInfo() {
	return pageInfo;
}
public void setPageInfo(PageInfo pageInfo) {
	this.pageInfo = pageInfo;
}
public LogPage() {
}


}
