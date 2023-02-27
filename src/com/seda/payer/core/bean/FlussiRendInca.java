package com.seda.payer.core.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Calendar;

import javax.imageio.spi.RegisterableService;

import com.seda.payer.commons.bean.Lifecycle;

public class FlussiRendInca extends Lifecycle implements Serializable {

	private static final long serialVersionUID = 1L;
	private java.lang.String chiaveFlussoRend;
    private Date dataCreazioneFlusso;
    private java.lang.String codiceSocietaMittente;
    private BigDecimal progressivoInvio;
    private java.lang.String nomeFileRend;
    private Calendar dataUltimoAggiornamento;
    private java.lang.String operatoreAggiornamento;


    public FlussiRendInca() { 
    }

    public FlussiRendInca(ResultSet data) throws SQLException {
    	if (data == null)
    		return;
    	
    	chiaveFlussoRend = data.getString("POF_KPOFKPOF");
    	dataCreazioneFlusso = data.getDate("POF_GPOFGDAT");
    	codiceSocietaMittente = data.getString("POF_CPOFCSIA");
        progressivoInvio = data.getBigDecimal("POF_NPOFNPRO");
    	nomeFileRend = data.getString("POF_DPOFDFIL");
    	Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(data.getTimestamp("POF_GPOFGAGG").getTime());
        setDataUltimoAggiornamento(cal);
    	dataUltimoAggiornamento = cal; 
    	operatoreAggiornamento = data.getString("POF_CPOFCUTE");
    }

    public java.lang.String getChiaveFlussoRend() {
		return chiaveFlussoRend;
	}

	public void setChiaveFlussoRend(java.lang.String chiaveFlussoRend) {
		this.chiaveFlussoRend = chiaveFlussoRend;
	}

	public Date getDataCreazioneFlusso() {
		return dataCreazioneFlusso;
	}

	public void setDataCreazioneFlusso(Date dataCreazioneFlusso) {
		this.dataCreazioneFlusso = dataCreazioneFlusso;
	}

	public java.lang.String getCodiceSocietaMittente() {
		return codiceSocietaMittente;
	}

	public void setCodiceSocietaMittente(java.lang.String codiceSocietaMittente) {
		this.codiceSocietaMittente = codiceSocietaMittente;
	}

	public BigDecimal getProgressivoInvio() {
		return progressivoInvio;
	}

	public void setProgressivoInvio(BigDecimal progressivoInvio) {
		this.progressivoInvio = progressivoInvio;
	}

	public java.lang.String getNomeFileRend() {
		return nomeFileRend;
	}

	public void setNomeFileRend(java.lang.String nomeFileRend) {
		this.nomeFileRend = nomeFileRend;
	}

	public Calendar getDataUltimoAggiornamento() {
		return dataUltimoAggiornamento;
	}

	public void setDataUltimoAggiornamento(Calendar dataUltimoAggiornamento) {
		this.dataUltimoAggiornamento = dataUltimoAggiornamento;
	}

	public java.lang.String getOperatoreAggiornamento() {
		return operatoreAggiornamento;
	}

	public void setOperatoreAggiornamento(java.lang.String operatoreAggiornamento) {
		this.operatoreAggiornamento = operatoreAggiornamento;
	}

	@Override
	public void onSave(CallableStatement arg) throws SQLException {
		arg.setDate(1, new java.sql.Date(this.dataCreazioneFlusso.getTime()));
		arg.setString(2, this.codiceSocietaMittente);
		arg.setBigDecimal(3, this.progressivoInvio);
		arg.setString(4, this.nomeFileRend);
		arg.setString(5, this.operatoreAggiornamento);
		arg.registerOutParameter(6, Types.CHAR);
	}

	@Override
	public void onDelete(CallableStatement arg) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onLoad(CallableStatement arg) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onUpdate(CallableStatement arg) throws SQLException {
		// TODO Auto-generated method stub
		
	}
	
}
