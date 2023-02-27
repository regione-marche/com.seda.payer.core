package com.seda.payer.core.bean;

import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.seda.payer.commons.bean.Lifecycle;

public class AnagProvCom extends Lifecycle implements Serializable {

	private static final long serialVersionUID = 1L;
	private java.lang.String codiceBelfiore;
    private java.lang.String codiceProvincia;
    private java.lang.String codiceComune;
    private java.lang.String descrizioneComune;
    private java.lang.String cap;
    private java.lang.String siglaProvincia;
    private java.lang.String descrizioneProvincia;
    private java.lang.String descrizioneRegione;
    private java.lang.String codiceCatastale;
    private java.lang.String codiceOperatore;
    private java.lang.String descrizioneProvinciaDE;
    private java.lang.String descrizioneComuneDE;
    private java.lang.String flagComuneAssociato;
    private java.lang.String codiceIstat;	//PG200390 GG

    public AnagProvCom() {
    }

    public AnagProvCom(ResultSet data) throws SQLException {
    	if (data == null)
    		return;

    	codiceBelfiore = data.getString("APC_CANEBELF");
    	codiceProvincia = data.getString("APC_CAPCCPRO");
        codiceComune = data.getString("APC_CAPCCCOM");
        descrizioneComune = data.getString("APC_DAPCDCOM");
        cap = data.getString("APC_CAPCCCAP");
        siglaProvincia = data.getString("APC_CAPCSIGL");
        descrizioneProvincia = data.getString("APC_DAPCDPRO");
        descrizioneRegione = data.getString("APC_DAPCDREG");
        codiceCatastale = data.getString("APC_CAPCCCAT");
        codiceOperatore = data.getString("APC_CAPCCOPE");
        descrizioneProvinciaDE = data.getString("APC_DAPCPRDE");
        descrizioneComuneDE = data.getString("APC_DAPCCODE");
        flagComuneAssociato = data.getString("APC_FAPCASSO");
        codiceIstat = data.getString("APC_CAPCCIST");	//PG200390 GG
        //company = new Company(); {
        	//company.setCompanyCode(data.getString("APC_CSOCCSOC"));
        //}
    }

    
	public java.lang.String getCodiceBelfiore() {
		return codiceBelfiore;
	}

	public void setCodiceBelfiore(java.lang.String codiceBelfiore) {
		this.codiceBelfiore = codiceBelfiore;
	}

	public java.lang.String getCodiceProvincia() {
		return codiceProvincia;
	}

	public void setCodiceProvincia(java.lang.String codiceProvincia) {
		this.codiceProvincia = codiceProvincia;
	}

	public java.lang.String getCodiceComune() {
		return codiceComune;
	}

	public void setCodiceComune(java.lang.String codiceComune) {
		this.codiceComune = codiceComune;
	}

	public java.lang.String getDescrizioneComune() {
		return descrizioneComune;
	}

	public void setDescrizioneComune(java.lang.String descrizioneComune) {
		this.descrizioneComune = descrizioneComune;
	}

	public java.lang.String getCap() {
		return cap;
	}

	public void setCap(java.lang.String cap) {
		this.cap = cap;
	}

	public java.lang.String getSiglaProvincia() {
		return siglaProvincia;
	}

	public void setSiglaProvincia(java.lang.String siglaProvincia) {
		this.siglaProvincia = siglaProvincia;
	}

	public java.lang.String getDescrizioneProvincia() {
		return descrizioneProvincia;
	}

	public void setDescrizioneProvincia(java.lang.String descrizioneProvincia) {
		this.descrizioneProvincia = descrizioneProvincia;
	}

	public java.lang.String getDescrizioneRegione() {
		return descrizioneRegione;
	}

	public void setDescrizioneRegione(java.lang.String descrizioneRegione) {
		this.descrizioneRegione = descrizioneRegione;
	}

	public java.lang.String getCodiceCatastale() {
		return codiceCatastale;
	}

	public void setCodiceCatastale(java.lang.String codiceCatastale) {
		this.codiceCatastale = codiceCatastale;
	}

	public java.lang.String getCodiceOperatore() {
		return codiceOperatore;
	}

	public void setCodiceOperatore(java.lang.String codiceOperatore) {
		this.codiceOperatore = codiceOperatore;
	}
	
	public java.lang.String getDescrizioneProvinciaDE() {
		return descrizioneProvinciaDE;
	}

	public void setDescrizioneProvinciaDE(java.lang.String descrizioneProvinciaDE) {
		this.descrizioneProvinciaDE = descrizioneProvinciaDE;
	}

	public java.lang.String getDescrizioneComuneDE() {
		return descrizioneComuneDE;
	}

	public void setDescrizioneComuneDE(java.lang.String descrizioneComuneDE) {
		this.descrizioneComuneDE = descrizioneComuneDE;
	}
	
	public java.lang.String getFlagComuneAssociato() {
		return flagComuneAssociato;
	}

	public void setFlagComuneAssociato(java.lang.String flagComuneAssociato) {
		this.flagComuneAssociato = flagComuneAssociato;
	}

	//PG200390 GG - inizio
	public java.lang.String getCodiceIstat() {
		return codiceIstat;
	}

	public void setCodiceIstat(java.lang.String codiceIstat) {
		this.codiceIstat = codiceIstat;
	}
	//PG200390 GG - fine
	
	@Override
	public void onSave(CallableStatement arg) throws SQLException {
		arg.setString(1, this.codiceBelfiore);
		arg.setString(2, this.codiceProvincia);
		arg.setString(3, this.codiceComune);
		arg.setString(4, this.descrizioneComune);
		arg.setString(5, this.cap);
		arg.setString(6, this.siglaProvincia);
		arg.setString(7, this.descrizioneProvincia);
		arg.setString(8, this.descrizioneRegione);
		arg.setString(9, this.codiceCatastale);
		arg.setString(10, this.codiceOperatore);
		//inizio LP PG200060
		/*
		arg.setString(11, this.descrizioneProvinciaDE);
		arg.setString(12, this.descrizioneComuneDE);
		arg.setString(13, this.flagComuneAssociato);
		*/
		arg.setString(11, (this.descrizioneProvinciaDE == null ? "" : this.descrizioneProvinciaDE));
		arg.setString(12, (this.descrizioneComuneDE == null ? "" : this.descrizioneComuneDE));
		arg.setString(13, (this.flagComuneAssociato == null ? "" : this.flagComuneAssociato));
		//fine LP PG200060
		// !! UTE_CUTECURL (OPERATORE ULTIMO AGGIORNAMENTO)
		arg.setString(14, (this.codiceIstat == null ? "" : this.codiceIstat));	//PG200390 GG
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
}
