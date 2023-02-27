package com.seda.payer.core.bean;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AnagraficaStrutturaRicettivaCsv implements Serializable {

	private static final long serialVersionUID = 1L;

    private String codiceBelfiore;
    private String codiceAutorizzazione;
    private String recordCsv;
	
    public AnagraficaStrutturaRicettivaCsv(ResultSet data) throws SQLException
    {
    	if (data == null)
    		return;
    	
        setCodiceBelfiore(data.getString("SAN_CANEBELF"));
        setCodiceAutorizzazione(data.getString("SAN_CSANCAUT"));
        setRecordCsv(data.getString("RECORD"));
    }
    
	
	public String getCodiceBelfiore() {
		return codiceBelfiore;
	}

	public void setCodiceBelfiore(String codiceBelfiore) {
		this.codiceBelfiore = codiceBelfiore;
	}

	public String getCodiceAutorizzazione() {
		return codiceAutorizzazione;
	}

	public void setCodiceAutorizzazione(String codiceAutorizzazione) {
		this.codiceAutorizzazione = codiceAutorizzazione;
	}
	
	public void setRecordCsv(String recordCsv) {
		this.recordCsv = recordCsv;
	}

	public String getRecordCsv() {
		return recordCsv;
	}





}
