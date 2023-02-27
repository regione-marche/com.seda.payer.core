package com.seda.payer.core.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class InfoCosti implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private BigDecimal importoNotificaPosta;
	private BigDecimal importoNotificaSMS;
	
	private List<InfoCostiTransazione> listInfoCostiTrans;

	public InfoCosti(ResultSet data) throws SQLException 
	{
    	if (data == null)
    		return;
    	
    	setImportoNotificaPosta(data.getBigDecimal("CSN_ICSNPOST"));
    	setImportoNotificaSMS(data.getBigDecimal("CSN_ICSNCSMS"));
    	
		setListInfoCostiTrans(new ArrayList<InfoCostiTransazione>());
	}
	
	public InfoCosti()  
	{
    	setImportoNotificaPosta(new BigDecimal(0));
    	setImportoNotificaSMS(new BigDecimal(0));
    	
		setListInfoCostiTrans(new ArrayList<InfoCostiTransazione>());
	}
	
	public void setListInfoCostiTrans(List<InfoCostiTransazione> listInfoCostiTrans) 
	{
		this.listInfoCostiTrans = listInfoCostiTrans;
	}
	public List<InfoCostiTransazione> getListInfoCostiTrans() {
		return listInfoCostiTrans;
	}

	public void setImportoNotificaPosta(BigDecimal importoNotificaPosta) {
		this.importoNotificaPosta = importoNotificaPosta;
	}

	public BigDecimal getImportoNotificaPosta() {
		return importoNotificaPosta;
	}

	public void setImportoNotificaSMS(BigDecimal importoNotificaSMS) {
		this.importoNotificaSMS = importoNotificaSMS;
	}

	public BigDecimal getImportoNotificaSMS() {
		return importoNotificaSMS;
	}
}
