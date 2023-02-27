package com.seda.payer.core.bean;

import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.seda.payer.commons.bean.Lifecycle;

public class AbilitaSistemiEsterniSecureSite extends Lifecycle implements Serializable {

	private static final long serialVersionUID = 1L;
	private String urlAccesso;
	private String idServizio;
	private String descrizione;
	private String pathFileImmagine;
    private String flagAttivazione;
    private String flagRedirect;
    private String codiceTipologiaServizio;
    private String flagCalcoloCosti;
    private String flagInvioNotificaPayer;
    private String codiceOperatore;
    private int oreIntervalloPagamento;

    public AbilitaSistemiEsterniSecureSite() {
    }
    
    public AbilitaSistemiEsterniSecureSite(String urlAccesso, String idServizio,
			String descrizione, String pathFileImmagine,
			String flagAttivazione, String flagRedirect, 
			String codiceTipologiaServizio, String flagCalcoloCosti, 
			String flagInvioNotificaPayer, String codiceOperatore, int oreIntervalloPagamento) {
		this.urlAccesso = urlAccesso;
		this.idServizio = idServizio;
		this.descrizione = descrizione;
		this.pathFileImmagine = pathFileImmagine;
		this.flagAttivazione = flagAttivazione;
		this.flagRedirect = flagRedirect;
		this.codiceTipologiaServizio = codiceTipologiaServizio;
		this.flagCalcoloCosti = flagCalcoloCosti;
		this.flagInvioNotificaPayer = flagInvioNotificaPayer;
		this.codiceOperatore = codiceOperatore;
		this.oreIntervalloPagamento = oreIntervalloPagamento;
	}

	public AbilitaSistemiEsterniSecureSite(ResultSet data) throws SQLException {
    	if (data == null)
    		return;

    	urlAccesso = data.getString("SEC_CSECCURL");
    	idServizio = data.getString("SEC_KSECSERV");
    	descrizione = data.getString("SEC_DSECDESC");
    	pathFileImmagine = data.getString("SEC_DSECPIMG");
        flagAttivazione = data.getString("SEC_FSECFATT");
        flagRedirect = data.getString("SEC_FSECREDI");
        codiceTipologiaServizio = data.getString("SEC_CSECCTSE");
        flagCalcoloCosti = data.getString("SEC_FSECCOST");
        flagInvioNotificaPayer = data.getString("SEC_FSECNOTI");
        codiceOperatore = data.getString("SEC_CSECCOPE");
        oreIntervalloPagamento = data.getInt("SEC_NSECIPAG");
    }

	public String getUrlAccesso() {
		return urlAccesso;
	}

	public void setUrlAccesso(String urlAccesso) {
		this.urlAccesso = urlAccesso;
	}

	public void setIdServizio(String idServizio) {
		this.idServizio = idServizio;
	}

	public String getIdServizio() {
		return idServizio;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public String getPathFileImmagine() {
		return pathFileImmagine;
	}

	public void setPathFileImmagine(String pathFileImmagine) {
		this.pathFileImmagine = pathFileImmagine;
	}

	public String getFlagAttivazione() {
		return flagAttivazione;
	}

	public void setFlagAttivazione(String flagAttivazione) {
		this.flagAttivazione = flagAttivazione;
	}

	public void setFlagRedirect(String flagRedirect) {
		this.flagRedirect = flagRedirect;
	}

	public String getFlagRedirect() {
		return flagRedirect;
	}

	public void setCodiceTipologiaServizio(String codiceTipologiaServizio) {
		this.codiceTipologiaServizio = codiceTipologiaServizio;
	}

	public String getCodiceTipologiaServizio() {
		return codiceTipologiaServizio;
	}

	public String getCodiceOperatore() {
		return codiceOperatore;
	}

	public void setCodiceOperatore(String codiceOperatore) {
		this.codiceOperatore = codiceOperatore;
	}
	
	public void setFlagCalcoloCosti(String flagCalcoloCosti) {
		this.flagCalcoloCosti = flagCalcoloCosti;
	}

	public String getFlagCalcoloCosti() {
		return flagCalcoloCosti;
	}

	/**
	 * @param flagInvioNotificaPayer the flagInvioNotificaPayer to set
	 */
	public void setFlagInvioNotificaPayer(String flagInvioNotificaPayer) {
		this.flagInvioNotificaPayer = flagInvioNotificaPayer;
	}

	/**
	 * @return the flagInvioNotificaPayer
	 */
	public String getFlagInvioNotificaPayer() {
		return flagInvioNotificaPayer;
	}

	public void setOreIntervalloPagamento(int oreIntervalloPagamento) {
		this.oreIntervalloPagamento = oreIntervalloPagamento;
	}

	public int getOreIntervalloPagamento() {
		return oreIntervalloPagamento;
	}

	@Override
	public void onSave(CallableStatement arg) throws SQLException {
		arg.setString(1, this.urlAccesso);
		arg.setString(2, this.descrizione);
		arg.setString(3, this.pathFileImmagine);
		arg.setString(4, this.flagAttivazione);
		arg.setString(5, this.flagRedirect);
		arg.setString(6, this.idServizio);
		arg.setString(7, this.codiceTipologiaServizio);
		arg.setString(8, this.flagCalcoloCosti);
		arg.setString(9, this.flagInvioNotificaPayer);
		arg.setString(10, this.codiceOperatore);
	}
	
	@Override
	public void onUpdate(CallableStatement arg) throws SQLException {
		arg.setString(1, this.urlAccesso);
		arg.setString(2, this.descrizione);
		arg.setString(3, this.pathFileImmagine);
		arg.setString(4, this.flagAttivazione);
		arg.setString(5, this.flagRedirect);
		arg.setString(6, this.idServizio);
		arg.setString(7, this.codiceTipologiaServizio);
		arg.setString(8, this.flagCalcoloCosti);
		arg.setString(9, this.flagInvioNotificaPayer);
		arg.setString(10, this.codiceOperatore);
	}
	
	@Override
	public void onLoad(CallableStatement arg) throws SQLException {

	}
	
	@Override
	public void onDelete(CallableStatement arg) throws SQLException {

	}

	
}
