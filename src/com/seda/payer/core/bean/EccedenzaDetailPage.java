package com.seda.payer.core.bean;

import java.io.Serializable;


/**
 * 
 * @author ddiemdio
 *
 */


public class EccedenzaDetailPage implements Serializable {

	private static final long serialVersionUID = 1L;

	//input
	private java.lang.String chiaveEccedenza;

    
    //output
    
    private String listXml;		 
//    private PageInfo pageInfo;

    private EccedenzaDettaglioBean dettaglio;
    
    public EccedenzaDetailPage() { 
    	chiaveEccedenza="";
    }

	public java.lang.String getChiaveEccedenza() {
		return chiaveEccedenza;
	}

	public void setChiaveEccedenza(java.lang.String chiaveEccedenza) {
		this.chiaveEccedenza = chiaveEccedenza;
	}

	public String getListXml() {
		return listXml;
	}

	public void setListXml(String listXml) {
		this.listXml = listXml;
	}
/*
	public PageInfo getPageInfo() {
		return pageInfo;
	}

	public void setPageInfo(PageInfo pageInfo) {
		this.pageInfo = pageInfo;
	}
*/
	public EccedenzaDettaglioBean getDettaglio() {
		return dettaglio;
	}

	public void setDettaglio(EccedenzaDettaglioBean dettaglio) {
		this.dettaglio = dettaglio;
	}



}