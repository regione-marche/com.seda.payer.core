package com.seda.payer.core.bean;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Flusso {

	private int idFlusso;
	private String tipoFlusso;
	private String codFiscAgg;
	private Date timestampFlusso;
	private String nomeFile;
	private Date creazioneFlusso;
	
	public Flusso(ResultSet rs) throws SQLException {
		
		this.idFlusso = rs.getInt("PST_KPSTCDID");
		this.tipoFlusso = rs.getString("PST_CPSTTPFL");
		this.codFiscAgg = rs.getString("PST_CPSTCFIS");
		this.timestampFlusso = rs.getDate("PST_GPSTDFLS");
		this.nomeFile = rs.getString("PST_CPSTNMFL");
		this.creazioneFlusso = rs.getDate("PST_GPSTDCRE");
	}
	public int getIdFlusso() {
		return idFlusso;
	}
	public void setIdFlusso(int idFlusso) {
		this.idFlusso = idFlusso;
	}
	public String getTipoFlusso() {
		return tipoFlusso;
	}
	public void setTipoFlusso(String tipoFlusso) {
		this.tipoFlusso = tipoFlusso;
	}
	public String getCodFiscAgg() {
		return codFiscAgg;
	}
	public void setCodFiscAgg(String codFiscAgg) {
		this.codFiscAgg = codFiscAgg;
	}
	public Date getTimestampFlusso() {
		return timestampFlusso;
	}
	public void setTimestampFlusso(Date timestampFlusso) {
		this.timestampFlusso = timestampFlusso;
	}
	public String getNomeFile() {
		return nomeFile;
	}
	public void setNomeFile(String nomeFile) {
		this.nomeFile = nomeFile;
	}
	public Date getCreazioneFlusso() {
		return creazioneFlusso;
	}
	public void setCreazioneFlusso(Date creazioneFlusso) {
		this.creazioneFlusso = creazioneFlusso;
	}
	
}
