package com.seda.payer.core.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;


import com.seda.payer.commons.bean.Lifecycle;

public class EccedenzaTestaBean extends Lifecycle implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -590637394985335186L;
	private String primaryKey; //ECT_KECTKECT;
	private String codiceSocieta; // ECT_CSOCCSOC
	private String codiceUtente; // ECT_CUTECUTE
	private Date dataElaborazione; // ECT_GECTGDAT
	private String chiaveAnagrafica; // ECT_KANEKANE
	private Date dataProduzioneFlusso; //ECT_GECTGCRE
	private String tipoServizio; //ECT_FECTTIPS
	private String proceduraGestione; //ECT_FECTPROC
	private Date dataAccredito;//ECT_GECTGVAL
	private String nomeFlusso;//ECT_CCETNOME
	private Double numeroRecord;//ECT_NECCNREC
	private String codiceOperatore;//ECT_CECTCOPE
	private int outRows;
	
	public int getOutRows() {
		return outRows;
	}

	public String getCodiceOperatore() {
		return codiceOperatore;
	}

	public void setCodiceOperatore(String codiceOperatore) {
		this.codiceOperatore = codiceOperatore;
	}

	public String getPrimaryKey() {
		return primaryKey;
	}

	public void setPrimaryKey(String primaryKey) {
		this.primaryKey = primaryKey;
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

	
	public String getChiaveAnagrafica() {
		return chiaveAnagrafica;
	}

	public void setChiaveAnagrafica(String chiaveAnagrafica) {
		this.chiaveAnagrafica = chiaveAnagrafica;
	}

	

	public String getTipoServizio() {
		return tipoServizio;
	}

	public void setTipoServizio(String tipoServizio) {
		this.tipoServizio = tipoServizio;
	}

	public String getProceduraGestione() {
		return proceduraGestione;
	}

	public void setProceduraGestione(String proceduraGestione) {
		this.proceduraGestione = proceduraGestione;
	}

	public String getNomeFlusso() {
		return nomeFlusso;
	}

	public void setNomeFlusso(String nomeFlusso) {
		this.nomeFlusso = nomeFlusso;
	}


	public void onDelete(CallableStatement arg) throws SQLException {
		arg.setString(1, this.primaryKey);
		arg.registerOutParameter(2, Types.INTEGER);
		
	}

	
	public void onLoad(CallableStatement arg) throws SQLException {
		
		
	}

	
	public void onSave(CallableStatement arg) throws SQLException {
		
		arg.setString(1, this.primaryKey);
		arg.setString(2, this.codiceSocieta);
		arg.setString(3, codiceUtente);
		arg.setDate(4, this.dataElaborazione);
		arg.setString(5, this.chiaveAnagrafica);
		arg.setDate(6, this.dataProduzioneFlusso);
		arg.setString(7, this.tipoServizio);
		arg.setDate(8, this.dataAccredito);
		arg.setString(9, this.proceduraGestione);
		arg.setString(10, this.nomeFlusso);
		//arg.setDouble(11, this.numeroRecord);
		arg.setBigDecimal(11, new BigDecimal(this.numeroRecord));
		arg.setString(12,this.codiceOperatore);
		arg.registerOutParameter(13, Types.INTEGER);
	}

	public Date getDataElaborazione() {
		return dataElaborazione;
	}

	public void setDataElaborazione(Date dataElaborazione) {
		this.dataElaborazione = dataElaborazione;
	}

	public Date getDataProduzioneFlusso() {
		return dataProduzioneFlusso;
	}

	public void setDataProduzioneFlusso(Date dataProduzioneFlusso) {
		this.dataProduzioneFlusso = dataProduzioneFlusso;
	}

	public Date getDataAccredito() {
		return dataAccredito;
	}

	public void setDataAccredito(Date dataAccredito) {
		this.dataAccredito = dataAccredito;
	}

	public Double getNumeroRecord() {
		return numeroRecord;
	}

	public void setNumeroRecord(Double numeroRecord) {
		this.numeroRecord = numeroRecord;
	}

	
	public void onUpdate(CallableStatement arg) throws SQLException {
		
		
	}

	public EccedenzaTestaBean()
	{
		super();
	}
	
	public EccedenzaTestaBean(ResultSet data) throws SQLException
	{
		if(data==null)
			return;
		
		dataAccredito = data.getDate("ECT_GECTGVAL");
	}
}
