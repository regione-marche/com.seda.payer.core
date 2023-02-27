
package com.seda.payer.core.bean;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;


public class GruppoAgenzia implements Serializable{
	
private static final long serialVersionUID = 1L;
	
	private String codiceGruppoAgenzia;
	private String descrizioneGruppoAgenzia;
	
public GruppoAgenzia() {}

	public GruppoAgenzia(ResultSet data) throws SQLException
    {
    	if (data == null)
    		return;
    	
    	setCodiceGruppoAgenzia(data.getString("GAG_KGAGKGRU"));
    	setDescrizioneGruppoAgenzia(data.getString("GAG_DGAGDESC"));
    }
	
	public GruppoAgenzia(String codiceGruppoAgenzia, String descrizioneGruppoAgenzia) {
		super();
		this.codiceGruppoAgenzia = codiceGruppoAgenzia;
		this.descrizioneGruppoAgenzia = descrizioneGruppoAgenzia;
	}

	public String getCodiceGruppoAgenzia() {
		return codiceGruppoAgenzia;
	}

	public void setCodiceGruppoAgenzia(String codiceGruppoAgenzia) {
		this.codiceGruppoAgenzia = codiceGruppoAgenzia;
	}

	public String getDescrizioneGruppoAgenzia() {
		return descrizioneGruppoAgenzia;
	}

	public void setDescrizioneGruppoAgenzia(String descrizioneGruppoAgenzia) {
		this.descrizioneGruppoAgenzia = descrizioneGruppoAgenzia;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}


		
	
	

}
