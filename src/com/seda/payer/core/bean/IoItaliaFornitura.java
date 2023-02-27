package com.seda.payer.core.bean;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class IoItaliaFornitura {

	private final long idFornitura;
	private final String codiceSocieta;
	private final String descrizioneSocieta;
	private final String codiceUtente;
	private final String descrizioneUtente;
	private final String codiceEnte;
	private final String descrizioneEnte;
	private final String tipologiaServizio;
	private final String descrizioneTipologiaServizio;
	private final String impostaServizio;
	private final String codiceFornitura;
	private final Date dataInserimento;
	private final String siglaProvincia;
	private final String esito;
	private final String descrizioneImpostaServizio;
	private final String descrizioneProvincia;
	
	public IoItaliaFornitura(ResultSet rs) throws SQLException {
		// YLM PG22XX06 INI
		 ResultSetMetaData rsmd = rs.getMetaData();
		 boolean APPIO= false;
		    int columns = rsmd.getColumnCount();
		    for (int x = 1; x <= columns; x++) {
		        if (rsmd.getColumnName(x).equals("TIO_DTIODTIO")) {
		        	APPIO = true;
		        }
		    }
		// YLM PG22XX06 FINE
		this.idFornitura = rs.getLong("FOR_KFORKFOR");
		this.codiceSocieta = rs.getString("FOR_CSOCCSOC");
		this.descrizioneSocieta = rs.getString("SOC_DSOCDSOC");
		this.codiceUtente = rs.getString("FOR_CUTECUTE");
		this.descrizioneUtente = rs.getString("UTE_DUTEDUTE");
		this.codiceEnte = rs.getString("FOR_KANEKENT");
		this.descrizioneEnte = rs.getString("ANE_DANEDENT");
		this.tipologiaServizio = rs.getString("FOR_CTSECTSE");
		// YLM PG22XX06 caso new appIO INI
		if (APPIO) {
			this.descrizioneTipologiaServizio = rs.getString("TIO_DTIODTIO");
		} else  {
			this.descrizioneTipologiaServizio = rs.getString("TSE_DTSEDTSE");
		}
		// YLM PG22XX06 caso new appIO FINE
		this.impostaServizio = rs.getString("FOR_CISECISE");
		this.codiceFornitura = rs.getString("FOR_CTFOCTFO");
		this.dataInserimento = rs.getDate("FOR_GFORGINS");
		this.siglaProvincia = rs.getString("APC_CAPCSIGL");
		this.esito = rs.getString("ESITO");
		this.descrizioneImpostaServizio = rs.getString("EH6_DEH6DISE");
		this.descrizioneProvincia = rs.getString("APC_DAPCDPRO");
	}
	
	// YLM PG22XX06 INI
	public IoItaliaFornitura() {
		this.idFornitura = 0;
		this.codiceSocieta = "";
		this.descrizioneSocieta = "";
		this.codiceUtente = "";
		this.descrizioneUtente = "";
		this.codiceEnte = "";
		this.descrizioneEnte = "";
		this.tipologiaServizio = "";
		this.descrizioneTipologiaServizio = "";
		this.impostaServizio = "";
		this.codiceFornitura = "";
		this.dataInserimento = null;
		this.siglaProvincia = "";
		this.esito = "";
		this.descrizioneImpostaServizio = "";
		this.descrizioneProvincia = "";
	}
	// YLM PG22XX06 FINE

	public long getIdFornitura() {
		return idFornitura;
	}
	
	public String getCodiceSocieta() {
		return codiceSocieta;
	}
	
	public String getDescrizioneSocieta() {
		return descrizioneSocieta;
	}
	
	public String getCodiceUtente() {
		return codiceUtente;
	}
	
	public String getDescrizioneUtente() {
		return descrizioneUtente;
	}
	
	public String getCodiceEnte() {
		return codiceEnte;
	}
	
	public String getDescrizioneEnte() {
		return descrizioneEnte;
	}
	
	public String getTipologiaServizio() {
		return tipologiaServizio;
	}
	
	public String getDescrizioneTipologiaServizio() {
		return descrizioneTipologiaServizio;
	}
	
	public String getImpostaServizio() {
		return impostaServizio;
	}
	
	public String getCodiceFornitura() {
		return codiceFornitura;
	}
	
	public Date getDataInserimento() {
		return dataInserimento;
	}
	
	public String getSiglaProvincia() {
		return siglaProvincia;
	}
	
	public String getEsito() {
		return esito;
	}

	public String getDescrizioneImpostaServizio() {
		return descrizioneImpostaServizio;
	}

	public String getDescrizioneProvincia() {
		return descrizioneProvincia;
	}
	
}
