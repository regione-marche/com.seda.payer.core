package com.seda.payer.core.bean;

import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import com.seda.payer.commons.bean.Lifecycle;

public class TransazioneMod21Err extends Lifecycle implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String chiaveTransazione; //TRA_KTRAKTRA" CHAR(10)
	private String codSocietaImp;//TRA_CSOCCSOC CHAR(5)
	private String codUtenteImp;//TDT_CUTECUTE CHAR(5)
	private String codAnagraficaImp;//TDT_KANEKENT_ENT CHAR(10)

	private Date dataPagamento;//TRA_GTRADPAG TIMESTAMP
	private Date dataRiversamento;//REV_GREVGDAT DATE --> TIMESTAMP,

	private String codSocietaBen;//REV_COSCCSOC CHAR(5)
	private String codUtenteBen;//REV_CUTECUTE CHAR(5) 
	private String codAnagraficaBen;//REV_KANEKANE_BEN CHAR(10) 

	private String codDIsposizioneCBI;//CBI_CCBIDISP CHAR(30)
	private Date dataBonifico;//CBI_GCBIGDAT DATE --> TIMESTAMP
	private String flagCBI;//CBI_FCBIFLAG CHAR(1)
	
	
    public TransazioneMod21Err() { 
    }

    public TransazioneMod21Err(ResultSet data) throws SQLException {
    	if (data == null)
    		return;
        
    	chiaveTransazione = data.getString("TRA_KTRAKTRA");
    	codSocietaImp = data.getString("TRA_CSOCCSOC");
    	codUtenteImp = data.getString("BOL_CUTECUTE");
    	codAnagraficaImp = data.getString("BOL_KANEKENT");

    	dataPagamento = new Date(data.getTimestamp("TRA_GTRADPAG").getTime());
    	dataRiversamento = new Date(data.getTimestamp("REV_GREVGDAT").getTime());

    	codSocietaBen = data.getString("REV_COSCCSOC");
    	codUtenteBen = data.getString("REV_CUTECUTE");
    	codAnagraficaBen = data.getString("REV_KANEKANE_BEN");

    	codDIsposizioneCBI = data.getString("CBI_CCBIDISP");
    	dataBonifico = new Date(data.getTimestamp("CBI_GCBIGDAT").getTime());
    	flagCBI = data.getString("CBI_FCBIFLAG");
    }
    


	@Override
	public void onSave(CallableStatement arg) throws SQLException {

	}
	

	@Override
	public void onUpdate(CallableStatement arg) throws SQLException {
	}
	
	@Override
	public void onLoad(CallableStatement arg) throws SQLException {

	}
	
	@Override
	public void onDelete(CallableStatement arg) throws SQLException {

	}

	public String getChiaveTransazione() {
		return chiaveTransazione;
	}

	public void setChiaveTransazione(String chiaveTransazione) {
		this.chiaveTransazione = chiaveTransazione;
	}

	public String getCodSocietaImp() {
		return codSocietaImp;
	}

	public void setCodSocietaImp(String codSocietaImp) {
		this.codSocietaImp = codSocietaImp;
	}

	public String getCodUtenteImp() {
		return codUtenteImp;
	}

	public void setCodUtenteImp(String codUtenteImp) {
		this.codUtenteImp = codUtenteImp;
	}

	public String getCodAnagraficaImp() {
		return codAnagraficaImp;
	}

	public void setCodAnagraficaImp(String codAnagraficaImp) {
		this.codAnagraficaImp = codAnagraficaImp;
	}

	public Date getDataPagamento() {
		return dataPagamento;
	}

	public void setDataPagamento(Date dataPagamento) {
		this.dataPagamento = dataPagamento;
	}

	public Date getDataRiversamento() {
		return dataRiversamento;
	}

	public void setDataRiversamento(Date dataRiversamento) {
		this.dataRiversamento = dataRiversamento;
	}

	public String getCodSocietaBen() {
		return codSocietaBen;
	}

	public void setCodSocietaBen(String codSocietaBen) {
		this.codSocietaBen = codSocietaBen;
	}

	public String getCodUtenteBen() {
		return codUtenteBen;
	}

	public void setCodUtenteBen(String codUtenteBen) {
		this.codUtenteBen = codUtenteBen;
	}

	public String getCodAnagraficaBen() {
		return codAnagraficaBen;
	}

	public void setCodAnagraficaBen(String codAnagraficaBen) {
		this.codAnagraficaBen = codAnagraficaBen;
	}

	public String getCodDIsposizioneCBI() {
		return codDIsposizioneCBI;
	}

	public void setCodDIsposizioneCBI(String codDIsposizioneCBI) {
		this.codDIsposizioneCBI = codDIsposizioneCBI;
	}

	public Date getDataBonifico() {
		return dataBonifico;
	}

	public void setDataBonifico(Date dataBonifico) {
		this.dataBonifico = dataBonifico;
	}

	public String getFlagCBI() {
		return flagCBI;
	}

	public void setFlagCBI(String flagCBI) {
		this.flagCBI = flagCBI;
	}

	
	
}