package com.seda.payer.core.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.SQLException;

import com.seda.payer.commons.bean.Lifecycle;

public class ArticoloPartitaRuolo extends Lifecycle implements Serializable{

	private static final long serialVersionUID = 1L;

protected long progrFlussoL;				//RAR_KRARKFLU
protected String codiceSocieta;			//RAR_CSOCCSOC
protected String codiceUtente;			//RAR_CUTECUTE
protected String codiceEnte;				    
protected String chiaveEnte;				//RAR_KANEKENT		    
protected String agRiscossione;			//RAR_CRPACAGE
protected BigDecimal annoRuoloBD;			//RAR_NRPAANNO
protected BigDecimal numeroRuoloBD;		//RAR_NRPANUME
protected long numeroPartita;       		//RAR_NRARNPAR
protected String progTributo;				//RAR_CRARPROG
protected String codTributo;				//    RAR_CRARTRIB CHAR(4) NOT NULL,
protected String annoTributo;				//    RAR_CRARANNO CHAR(4) NOT NULL,
protected String flagSospensione;			//    RAR_FRARSOSP CHAR(1) NOT NULL,
protected String idCartella;				//    RAR_CRARCCAR VARCHAR(20) NOT NULL,
protected BigDecimal impCarico;			//    RAR_IRARICAR DECIMAL(15 , 2) NOT NULL,
protected BigDecimal impDiscaricato;		//    RAR_IRARDCAR DECIMAL(15 , 2) NOT NULL,
protected BigDecimal impSgravato;			//    RAR_IRARSCAR DECIMAL(15 , 2) NOT NULL,
protected BigDecimal impRiscosso;			//    RAR_IRARRISC DECIMAL(15 , 2) NOT NULL,
protected BigDecimal impRimborso;			//    RAR_IRARRIMB DECIMAL(15 , 2) NOT NULL,
protected BigDecimal impMora;				//    RAR_IRARMORA DECIMAL(15 , 2) NOT NULL,
protected BigDecimal impRendicontato;		//    RAR_IRARREND DECIMAL(15 , 2) NOT NULL,
protected BigDecimal impResiduo;			//    RAR_IRARRESI DECIMAL(15 , 2) NOT NULL,
protected String codiceTomb;				//    RAR_CRARTOMB CHAR(1) NOT NULL

	
	
	public void onSave(CallableStatement arg) throws SQLException {
		arg.setLong(1, progrFlussoL);				//RAR_KRARKFLU
		arg.setString(2, codiceSocieta);			//RAR_CSOCCSOC
		arg.setString(3, codiceUtente);			//RAR_CUTECUTE
		arg.setString(4, chiaveEnte);				//RAR_KANEKENT				    
		arg.setString(5, agRiscossione);			//RAR_CRPACAGE
		arg.setBigDecimal(6, annoRuoloBD);			//RAR_NRPAANNO
		arg.setBigDecimal(7, numeroRuoloBD);		//RAR_NRPANUME
		arg.setLong(8, numeroPartita);       		//RAR_NRARNPAR
		arg.setString(9, progTributo);				//RAR_CRARPROG
		arg.setString(10, codTributo);				//    RAR_CRARTRIB CHAR(4) NOT NULL,
		arg.setString(11, annoTributo);				//    RAR_CRARANNO CHAR(4) NOT NULL,
		arg.setString(12, flagSospensione);			//    RAR_FRARSOSP CHAR(1) NOT NULL,
		arg.setString(13, idCartella);				//    RAR_CRARCCAR VARCHAR(20) NOT NULL,
		arg.setBigDecimal(14, impCarico);			//    RAR_IRARICAR DECIMAL(15 , 2) NOT NULL,
		arg.setBigDecimal(15, impDiscaricato);		//    RAR_IRARDCAR DECIMAL(15 , 2) NOT NULL,
		arg.setBigDecimal(16, impSgravato);			//    RAR_IRARSCAR DECIMAL(15 , 2) NOT NULL,
		arg.setBigDecimal(17, impRiscosso);			//    RAR_IRARRISC DECIMAL(15 , 2) NOT NULL,
		arg.setBigDecimal(18, impRimborso);			//    RAR_IRARRIMB DECIMAL(15 , 2) NOT NULL,
		arg.setBigDecimal(19, impMora);				//    RAR_IRARMORA DECIMAL(15 , 2) NOT NULL,
		arg.setBigDecimal(20, impRendicontato);		//    RAR_IRARREND DECIMAL(15 , 2) NOT NULL,
		arg.setBigDecimal(21, impResiduo);			//    RAR_IRARRESI DECIMAL(15 , 2) NOT NULL,
		arg.setString(22, codiceTomb);				//    RAR_CRARTOMB CHAR(1) NOT NULL
	}
	
	public void onUpdate(CallableStatement arg) throws SQLException {
	}
	
	public void onLoad(CallableStatement arg) throws SQLException {

	}
	
	public void onDelete(CallableStatement arg) throws SQLException {

	}

	
	//GET e SET
	
	
	public long getProgrFlussoL() {
		return progrFlussoL;
	}

	public void setProgrFlussoL(long progrFlussoL) {
		this.progrFlussoL = progrFlussoL;
	}

	public String getCodiceSocieta() {
		return codiceSocieta;
	}

	public void setCodiceSocieta(String codiceSocieta) {
		this.codiceSocieta = codiceSocieta;
	}

	public String getCodiceUtente() {
		return codiceUtente;
	}

	public void setCodiceUtente(String codiceUtente) {
		this.codiceUtente = codiceUtente;
	}

	public String getCodiceEnte() {
		return codiceEnte;
	}

	public void setCodiceEnte(String codiceEnte) {
		this.codiceEnte = codiceEnte;
	}

	public String getAgRiscossione() {
		return agRiscossione;
	}

	public void setAgRiscossione(String agRiscossione) {
		this.agRiscossione = agRiscossione;
	}

	public BigDecimal getAnnoRuoloBD() {
		return annoRuoloBD;
	}

	public void setAnnoRuoloBD(BigDecimal annoRuoloBD) {
		this.annoRuoloBD = annoRuoloBD;
	}

	public BigDecimal getNumeroRuoloBD() {
		return numeroRuoloBD;
	}

	public void setNumeroRuoloBD(BigDecimal numeroRuoloBD) {
		this.numeroRuoloBD = numeroRuoloBD;
	}

	public long getNumeroPartita() {
		return numeroPartita;
	}

	public void setNumeroPartita(long numeroPartita) {
		this.numeroPartita = numeroPartita;
	}

	public String getProgTributo() {
		return progTributo;
	}

	public void setProgTributo(String progTributo) {
		this.progTributo = progTributo;
	}

	public String getCodTributo() {
		return codTributo;
	}

	public void setCodTributo(String codTributo) {
		this.codTributo = codTributo;
	}

	public String getAnnoTributo() {
		return annoTributo;
	}

	public void setAnnoTributo(String annoTributo) {
		this.annoTributo = annoTributo;
	}

	public String getFlagSospensione() {
		return flagSospensione;
	}

	public void setFlagSospensione(String flagSospensione) {
		this.flagSospensione = flagSospensione;
	}

	public String getIdCartella() {
		return idCartella;
	}

	public void setIdCartella(String idCartella) {
		this.idCartella = idCartella;
	}

	public BigDecimal getImpCarico() {
		return impCarico;
	}

	public void setImpCarico(BigDecimal impCarico) {
		this.impCarico = impCarico;
	}

	public BigDecimal getImpDiscaricato() {
		return impDiscaricato;
	}

	public void setImpDiscaricato(BigDecimal impDiscaricato) {
		this.impDiscaricato = impDiscaricato;
	}

	public BigDecimal getImpSgravato() {
		return impSgravato;
	}

	public void setImpSgravato(BigDecimal impSgravato) {
		this.impSgravato = impSgravato;
	}

	public BigDecimal getImpRiscosso() {
		return impRiscosso;
	}

	public void setImpRiscosso(BigDecimal impRiscosso) {
		this.impRiscosso = impRiscosso;
	}

	public BigDecimal getImpRimborso() {
		return impRimborso;
	}

	public void setImpRimborso(BigDecimal impRimborso) {
		this.impRimborso = impRimborso;
	}

	public BigDecimal getImpMora() {
		return impMora;
	}

	public void setImpMora(BigDecimal impMora) {
		this.impMora = impMora;
	}

	public BigDecimal getImpRendicontato() {
		return impRendicontato;
	}

	public void setImpRendicontato(BigDecimal impRendicontato) {
		this.impRendicontato = impRendicontato;
	}

	public BigDecimal getImpResiduo() {
		return impResiduo;
	}

	public void setImpResiduo(BigDecimal impResiduo) {
		this.impResiduo = impResiduo;
	}

	public String getCodiceTomb() {
		return codiceTomb;
	}

	public void setCodiceTomb(String codiceTomb) {
		this.codiceTomb = codiceTomb;
	}

	public String getChiaveEnte() {
		return chiaveEnte;
	}

	public void setChiaveEnte(String chiaveEnte) {
		this.chiaveEnte = chiaveEnte;
	}	
	

	

}
