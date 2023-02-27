package com.seda.payer.core.wallet.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;


import com.seda.data.dao.ModelAttributes;

public class PagamentoBorsellino extends ModelAttributes implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// Tabella PYPGBTB

	private String idPagamento;				//  PGB_KPGBKPGB
	private String codSocieta;				//  PGB_CSOCCSOC
	private String codUtente;				//  PGB_CUTECUTE
	private String codEnte;					//  PGB_KANEKENT
	private String idWallet;			//  PGB_KBRSKBRS
	private String tipoPagamento;			//  PGB_CPGBPGAC
	private String canalePagamento;			//  PGB_CPGBCPAG
	private BigDecimal importo;				//	PGB_IPGBIPAG
	private Calendar dataPagamento;			//  PGB_GPGBGPAG
	private Calendar dataAggiornamento;		//  PGB_GPGBGAGG
	private Boolean flagTombstoned;			//  PGB_FPGBFTMB



	public PagamentoBorsellino(){

	}

	public PagamentoBorsellino(ResultSet data) throws SQLException, ParseException {
		if (data == null)
			return;

		setCodUtente(data.getString(1));
		setCodEnte(data.getString(2));
		setIdWallet(data.getString(3));
		String dataPag = data.getString(4);
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		Calendar cal  = Calendar.getInstance();
		cal.setTime(df.parse(dataPag));
		setDataPagamento(cal);
		setImporto(data.getBigDecimal(5));
		setCanalePagamento(data.getString(6)); 
		setFlagTombstoned(true);
	}



	public PagamentoBorsellino(
			String idPagamento,			
			String codSocieta,			
			String codUtente,			
			String codEnte,				
			String idWallet,		
			String tipoPagamento,		
			String canalePagamento,		
			BigDecimal importo,			
			Calendar dataPagamento,		
			Calendar dataAggiornamento,	
			Boolean flagTombstoned)
	{
		this.idPagamento = idPagamento;
		this.codSocieta = codSocieta;
		this.codUtente = codUtente;
		this.codEnte = codEnte;
		this.idWallet = idWallet;
		this.tipoPagamento = tipoPagamento;
		this.canalePagamento = canalePagamento;
		this.importo = importo;
		this.dataPagamento = dataPagamento;
		this.dataAggiornamento = dataAggiornamento;
		this.flagTombstoned = flagTombstoned;
	}



	public String getIdPagamento() {
		return idPagamento;
	}

	public void setIdPagamento(String idPagamento) {
		this.idPagamento = idPagamento;
	}

	public String getCodSocieta() {
		return codSocieta;
	}

	public void setCodSocieta(String codSocieta) {
		this.codSocieta = codSocieta;
	}

	public String getCodUtente() {
		return codUtente;
	}

	public void setCodUtente(String codUtente) {
		this.codUtente = codUtente;
	}

	public String getCodEnte() {
		return codEnte;
	}

	public void setCodEnte(String codEnte) {
		this.codEnte = codEnte;
	}

	public String getIdWallet() {
		return idWallet;
	}

	public void setIdWallet(String idWallet) {
		this.idWallet = idWallet;
	}

	public String getTipoPagamento() {
		return tipoPagamento;
	}

	public void setTipoPagamento(String tipoPagamento) {
		this.tipoPagamento = tipoPagamento;
	}

	public String getCanalePagamento() {
		return canalePagamento;
	}

	public void setCanalePagamento(String canalePagamento) {
		this.canalePagamento = canalePagamento;
	}

	public BigDecimal getImporto() {
		return importo;
	}

	public void setImporto(BigDecimal importo) {
		this.importo = importo;
	}

	public Calendar getDataPagamento() {
		return dataPagamento;
	}

	public void setDataPagamento(Calendar dataPagamento) {
		this.dataPagamento = dataPagamento;
	}

	public Calendar getDataAggiornamento() {
		return dataAggiornamento;
	}

	public void setDataAggiornamento(Calendar dataAggiornamento) {
		this.dataAggiornamento = dataAggiornamento;
	}

	public Boolean getFlagTombstoned() {
		return flagTombstoned;
	}

	public void setFlagTombstoned(Boolean flagTombstoned) {
		this.flagTombstoned = flagTombstoned;
	}

	@Override
	public String toString() {
		return "PagamentoBorsellino [canalePagamento=" + canalePagamento
		+ ", idWallet=" + idWallet + ", codEnte=" + codEnte
		+ ", codSocieta=" + codSocieta + ", codUtente=" + codUtente
		+ ", dataAggiornamento=" + dataAggiornamento
		+ ", dataPagamento=" + dataPagamento + ", flagTombstoned="
		+ flagTombstoned + ", idPagamento=" + idPagamento
		+ ", importo=" + importo + ", tipoPagamento=" + tipoPagamento
		+ "]";
	}

}

