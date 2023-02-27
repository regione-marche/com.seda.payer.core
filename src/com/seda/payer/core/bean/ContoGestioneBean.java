package com.seda.payer.core.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;


import com.seda.payer.commons.bean.Lifecycle;

public class ContoGestioneBean extends Lifecycle implements Serializable{

	
	private static final long serialVersionUID = -590637394985335186L;
	
	private String primaryKey; //MCG_KMCGKMCG;
	private String codiceSocieta; // MCG_CSOCCSOC
	private String codiceUtente; // MCG_CUTECUTE
	private String chiaveAnagrafica; // MCG_KANEKENT
	private String tipo; // MCG_FMCGFTIP
	private String periodo; // MCG_CMCGCPER
	private BigDecimal importo; //MCG_IMCGITOT
	private String nomeFile; // MCG_DMCGDFIL    
	private String codiceOperatore;//MCG_CMCGCOPE

	public ContoGestioneBean()
	{
		super();
	}
	
    public ContoGestioneBean(ResultSet data) throws SQLException {
    	if (data == null)
    		return;

/*
		MCG_KMCGKMCG CHAR(10) NOT NULL,
		MCG_CSOCCSOC CHAR(5) NOT NULL,
		MCG_CUTECUTE CHAR(5) NOT NULL,
		MCG_KANEKENT CHAR(10) NOT NULL,
		MCG_FMCGFTIP CHAR(1) NOT NULL,
		MCG_CMCGCPER CHAR(6) NOT NULL,
		MCG_IMCGITOT DECIMAL(15 , 2) NOT NULL,
		MCG_DMCGDFIL VARCHAR(50) NOT NULL,
		MCG_GMCGGAGG TIMESTAMP NOT NULL,
		MCG_CMCGCOPE VARCHAR(50) NOT NULL
*/

    	primaryKey = data.getString("MCG_KMCGKMCG");
    	codiceSocieta = data.getString("MCG_CSOCCSOC");
    	codiceUtente = data.getString("MCG_CUTECUTE");
    	chiaveAnagrafica = data.getString("MCG_KANEKENT");
    	tipo = data.getString("MCG_FMCGFTIP");
    	periodo = data.getString("MCG_CMCGCPER");
    	importo = data.getBigDecimal("MCG_IMCGITOT");
    	nomeFile = data.getString("MCG_DMCGDFIL");    
    	codiceOperatore = data.getString("MCG_CMCGCOPE");
    	
    }

	public void onDelete(CallableStatement arg) throws SQLException {
	}

	
	public void onLoad(CallableStatement arg) throws SQLException {
	}

	
	public void onSave(CallableStatement arg) throws SQLException {
		
		arg.setString(1, codiceSocieta);
		arg.setString(2, codiceUtente);
		arg.setString(3, chiaveAnagrafica);
		arg.setString(4, tipo);
		arg.setString(5, periodo);
		arg.setBigDecimal(6, importo);
		arg.setString(7, nomeFile);
		arg.setString(8, codiceOperatore);
		arg.registerOutParameter(9, Types.INTEGER);
	}


	
	public void onUpdate(CallableStatement arg) throws SQLException {
		
		
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


	public String getTipo() {
		return tipo;
	}


	public void setTipo(String tipo) {
		this.tipo = tipo;
	}


	public String getPeriodo() {
		return periodo;
	}


	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}


	public BigDecimal getImporto() {
		return importo;
	}


	public void setImporto(BigDecimal importo) {
		this.importo = importo;
	}


	public String getNomeFile() {
		return nomeFile;
	}


	public void setNomeFile(String nomeFile) {
		this.nomeFile = nomeFile;
	}


	public String getCodiceOperatore() {
		return codiceOperatore;
	}


	public void setCodiceOperatore(String codiceOperatore) {
		this.codiceOperatore = codiceOperatore;
	}

	
}
