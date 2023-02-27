package com.seda.payer.core.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.SQLException;
import java.util.Date;

import com.seda.payer.commons.bean.Lifecycle;

public class PagamentoPartitaRuolo extends Lifecycle implements Serializable {

	private static final long serialVersionUID = 1L;

	protected long progrFlussoL;				//RMO_KRMOKFLU
	protected String codiceSocieta;			//RMO_CSOCCSOC
	protected String codiceUtente;			//RMO_CUTECUTE
	protected String codiceEnte;				    
	protected String chiaveEnte;				//RMO_KANEKENT				    
	protected String agRiscossione;			//RMO_CRMOCAGE
	protected BigDecimal annoRuoloBD;			//RMO_CRMOANNO
	protected BigDecimal numeroRuoloBD;		//RMO_NRMONUME
	protected long numeroPartita;       		//RMO_NRMONPAR
	
	protected long progRiscossione;			//RMO_PRMONRIS
	protected Date dataEffPagamento;			//RMO_GRMOGEFF
	protected Date dataRegistrazione;			//RMO_GRMOGREG
	protected String segnoRiscossione;		//RMO_FRMOSEGN
	protected BigDecimal importoMovTributo;	//RMO_IRMOIART
	protected BigDecimal importoMovInteressiMora;	//RMO_IRMOIMOR
	protected String codiceTomb;				//RMO_CRMOTOMB
	

	public void onSave(CallableStatement arg) throws SQLException {

		arg.setLong(1, progrFlussoL);				//RMO_KRMOKFLU
		arg.setString(2, codiceSocieta);			//RMO_CSOCCSOC
		arg.setString(3, codiceUtente);			//RMO_CUTECUTE
		arg.setString(4, chiaveEnte);			//RMO_KANEKENT				    
		arg.setString(5, agRiscossione);			//RMO_CRMOCAGE
		arg.setString(6, annoRuoloBD.toString());			//RMO_CRMOANNO
		arg.setBigDecimal(7, numeroRuoloBD);		//RMO_NRMONUME
		arg.setLong(8, numeroPartita);       		//RMO_NRMONPAR
		
		arg.setLong(9, progRiscossione);			//RMO_PRMONRIS
		arg.setDate(10, new java.sql.Date(dataEffPagamento.getTime()));			//RMO_GRMOGEFF
		arg.setDate(11, new java.sql.Date(dataRegistrazione.getTime()));			//RMO_GRMOGREG
		arg.setString(12, segnoRiscossione);		//RMO_FRMOSEGN
		arg.setBigDecimal(13, importoMovTributo);	//RMO_IRMOIART
		arg.setBigDecimal(14, importoMovInteressiMora);	//RMO_IRMOIMOR
		arg.setString(15, codiceTomb);				//RMO_CRMOTOMB
	
	}
	
	public void onUpdate(CallableStatement arg) throws SQLException {
	}
	
	public void onLoad(CallableStatement arg) throws SQLException {

	}
	
	public void onDelete(CallableStatement arg) throws SQLException {

	}
	
	//GET E SET
	
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


	public long getProgRiscossione() {
		return progRiscossione;
	}

	public void setProgRiscossione(long progRiscossione) {
		this.progRiscossione = progRiscossione;
	}

	public void setProgRiscossione(Long progRiscossioneL) {
		this.progRiscossione = progRiscossioneL.longValue();
	}
	
	public Date getDataEffPagamento() {
		return dataEffPagamento;
	}

	public void setDataEffPagamento(Date dataEffPagamento) {
		this.dataEffPagamento = dataEffPagamento;
	}

	public Date getDataRegistrazione() {
		return dataRegistrazione;
	}

	public void setDataRegistrazione(Date dataRegistrazione) {
		this.dataRegistrazione = dataRegistrazione;
	}

	public String getSegnoRiscossione() {
		return segnoRiscossione;
	}

	public void setSegnoRiscossione(String segnoRiscossione) {
		this.segnoRiscossione = segnoRiscossione;
	}

	public BigDecimal getImportoMovTributo() {
		return importoMovTributo;
	}

	public void setImportoMovTributo(BigDecimal importoMovTributo) {
		this.importoMovTributo = importoMovTributo;
	}

	public BigDecimal getImportoMovInteressiMora() {
		return importoMovInteressiMora;
	}

	public void setImportoMovInteressiMora(BigDecimal importoMovInteressiMora) {
		this.importoMovInteressiMora = importoMovInteressiMora;
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
