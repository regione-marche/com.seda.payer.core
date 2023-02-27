package com.seda.payer.core.bean;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class IoItaliaConfigurazione {

	private final long idConfigurazione;
	private final String codiceSocieta;
	private final String descrizioneSocieta;
	private final String codiceUtente;
	private final String descrizioneUtente;
	private final String codiceEnte;
	private final String descrizioneEnte;
	private final String tipologiaServizio;
	private final String descrizioneTipologiaServizio;
	private final String impostaServizio;
	private final String descrizioneImpostaServizio;
	private final String wsKey1;
	private final String wsKey2;
	private final String ioKey1;
	private final String ioKey2;
	private final String email;
	private final boolean abilitato;
	private final BigDecimal importoDa;
	private final BigDecimal importoA;
	private final String idDominio;
	
	
	
	@Override
	public int hashCode() {
		return new Long(idConfigurazione).intValue();
	}

	public IoItaliaConfigurazione(long idConfigurazione, String codiceSocieta, String codiceUtente, String codiceEnte,
			String tipologiaServizio, String impostaServizio, String wsKey1, String wsKey2, String ioKey1,
			String ioKey2, String email, boolean abilitato, BigDecimal importoDa, BigDecimal importoA) {
		
		this.idConfigurazione = idConfigurazione;
		this.codiceSocieta = codiceSocieta;
		this.descrizioneSocieta = null;
		this.codiceUtente = codiceUtente;
		this.descrizioneUtente = null;
		this.codiceEnte = codiceEnte;
		this.descrizioneEnte = null;
		this.tipologiaServizio = tipologiaServizio;
		this.descrizioneTipologiaServizio = null;
		this.impostaServizio = impostaServizio;
		this.descrizioneImpostaServizio = null;
		this.wsKey1 = wsKey1;
		this.wsKey2 = wsKey2;
		this.ioKey1 = ioKey1;
		this.ioKey2 = ioKey2;
		this.email = email;
		this.abilitato = abilitato;
		this.importoDa = importoDa;
		this.importoA = importoA;
		this.idDominio = null;
	}

	public IoItaliaConfigurazione(ResultSet rs) throws SQLException {
		
		// YLM PG22XX06 caso new appIO INI
		 ResultSetMetaData rsmd = rs.getMetaData();
		 boolean APPIO= false;
		    int columns = rsmd.getColumnCount();
		    for (int x = 1; x <= columns; x++) {
		        if (rsmd.getColumnName(x).equals("TIO_DTIODTIO")) {
		        	APPIO = true;
		        }
		    }
		// YLM PG22XX06 caso new appIO FINE
		    
		this.idConfigurazione = rs.getLong("IIC_KIICKIIC");
		this.codiceSocieta = rs.getString("IIC_CSOCCSOC");
		this.descrizioneSocieta = rs.getString("SOC_DSOCDSOC");
		this.codiceUtente = rs.getString("IIC_CUTECUTE");
		this.descrizioneUtente = rs.getString("UTE_DUTEDUTE");
		this.codiceEnte = rs.getString("IIC_KANEKENT");
		this.descrizioneEnte = rs.getString("ANE_DANEDENT");
		this.tipologiaServizio = rs.getString("IIC_CTSECTSE");
		// YLM PG22XX06 caso new appIO INI
		if (APPIO) {
			this.descrizioneTipologiaServizio = rs.getString("TIO_DTIODTIO");
		} else  {
			this.descrizioneTipologiaServizio = rs.getString("TSE_DTSEDTSE");
		}
		// YLM PG22XX06 caso new appIO FINE
		this.impostaServizio = rs.getString("IIC_CISECISE");
		this.descrizioneImpostaServizio = rs.getString("EH6_DEH6DISE");
		this.wsKey1 = rs.getString("IIC_CWS1CWS1");
		this.wsKey2 = rs.getString("IIC_CWS2CWS2");
		this.ioKey1 = rs.getString("IIC_CIO1CIO1");
		this.ioKey2 = rs.getString("IIC_CIO2CIO2");
		this.email = rs.getString("IIC_MIICMAIL");
		this.abilitato = rs.getString("IIC_FIICABIL").equals("1");
		this.importoDa = rs.getBigDecimal("IIC_IIICIMDA");
		this.importoA = rs.getBigDecimal("IIC_IIICIMPA");
		this.idDominio = rs.getString("ENT_CENTCFIS");
	}
	
	// YLM PG22XX06 INI
	public IoItaliaConfigurazione() {
		this.idConfigurazione = 0;
		this.codiceSocieta = "";
		this.descrizioneSocieta = "";
		this.codiceUtente = "";
		this.descrizioneUtente = "";
		this.codiceEnte = "";
		this.descrizioneEnte = "";
		this.tipologiaServizio = "";
		this.descrizioneTipologiaServizio = "";
		this.impostaServizio = "";
		this.descrizioneImpostaServizio = "";
		this.wsKey1 = "";
		this.wsKey2 = "";
		this.ioKey1 = "";
		this.ioKey2 = "";
		this.email = "";
		this.abilitato = false;
		this.importoDa = null;
		this.importoA = null;
		this.idDominio = "";
	}
	// YLM PG22XX06 FINE

	public long getIdConfigurazione() {
		return idConfigurazione;
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

	public String getDescrizioneImpostaServizio() {
		return descrizioneImpostaServizio;
	}

	public String getWsKey1() {
		return wsKey1;
	}

	public String getWsKey2() {
		return wsKey2;
	}

	public String getIoKey1() {
		return ioKey1;
	}

	public String getIoKey2() {
		return ioKey2;
	}

	public String getEmail() {
		return email;
	}
	
	public boolean isAbilitato() {
		return abilitato;
	}

	public BigDecimal getImportoDa() {
		return importoDa;
	}

	public BigDecimal getImportoA() {
		return importoA;
	}

	public String getIdDominio() {
		return idDominio;
	}
	
}
