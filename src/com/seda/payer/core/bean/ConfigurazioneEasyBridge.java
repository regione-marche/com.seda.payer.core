package com.seda.payer.core.bean;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;

import com.seda.payer.commons.bean.Lifecycle;

public class ConfigurazioneEasyBridge implements Serializable{
	
private static final long serialVersionUID = 1L;
	
	private String codiceIdentificativoDominio;
	private String cutecute;
	private String operatore;
	
	
public String getCutecute() {
		return cutecute;
	}

	public void setCutecute(String cutecute) {
		this.cutecute = cutecute;
	}

	public String getOperatore() {
		return operatore;
	}

	public void setOperatore(String operatore) {
		this.operatore = operatore;
	}

public ConfigurazioneEasyBridge() {}
	
	public ConfigurazioneEasyBridge(ResultSet data) throws SQLException
    {
    	if (data == null)
    		return;
    	
    	setCodiceIdentificativoDominio(data.getString("UTE_KEYKCIDD"));
    	setCutecute(data.getString("UTE_CUTECUTE"));
    	setOperatore(data.getString("UTE_CUSRNAME"));
    	
    }
	
	
	public ConfigurazioneEasyBridge(String codiceIdentificativoDominio,
			String cutecute, String operatore) {
		super();
		this.codiceIdentificativoDominio = codiceIdentificativoDominio;
		this.cutecute = cutecute;
		this.operatore = operatore;
	}

	
	public String getCodiceIdentificativoDominio() {
		return codiceIdentificativoDominio;
	}
	public void setCodiceIdentificativoDominio(String codiceIdentificativoDominio) {
		this.codiceIdentificativoDominio = codiceIdentificativoDominio;
	}
	
	
	
	

}
