package com.seda.payer.core.bean;

import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.seda.payer.commons.bean.Lifecycle;

public class AnagEnte extends Lifecycle implements Serializable {

	private static final long serialVersionUID = 1L;
    private java.lang.String chiaveEnte;
    private java.lang.String codiceEnte;
    private java.lang.String tipoUfficio;
    private java.lang.String codiceUfficio;
    private java.lang.String codiceTipoEnte;
    private java.lang.String descrizioneEnte;
    private java.lang.String descrizioneUfficio;
    private java.lang.String codiceRuoliErariali;
    private java.sql.Date dataDecorrenza;
    private java.lang.String ufficioStatale;
    private java.lang.String codiceOperatore;
    private AnagProvCom anagProvCom;

    public AnagEnte() { 
    	anagProvCom = new AnagProvCom();
    }

    public AnagEnte(ResultSet data) throws SQLException {
    	if (data == null)
    		return;

        chiaveEnte = data.getString("ANE_KANEKENT");
        codiceEnte = data.getString("ANE_CANECENT");
        tipoUfficio = data.getString("ANE_TANETUFF");
        codiceUfficio = data.getString("ANE_CANECUFF");
        codiceTipoEnte = data.getString("ANE_TANETENT");
        descrizioneEnte = data.getString("ANE_DANEDENT");
        descrizioneUfficio = data.getString("ANE_DANEDUFF");
        codiceRuoliErariali = data.getString("ANE_CANECIND");
        dataDecorrenza = data.getDate("ANE_GANEDDEC");
        ufficioStatale = data.getString("ANE_FANEFSTA");
        codiceOperatore = data.getString("ANE_CANECOPE");
        
        anagProvCom = new AnagProvCom(); 
    	anagProvCom.setCodiceBelfiore(data.getString("ANE_CANEBELF"));
    	anagProvCom.setSiglaProvincia(data.getString("APC_CAPCSIGL"));
    }

    

	public java.lang.String getChiaveEnte() {
		return chiaveEnte;
	}

	public void setChiaveEnte(java.lang.String chiaveEnte) {
		this.chiaveEnte = chiaveEnte;
	}

	public java.lang.String getCodiceEnte() {
		return codiceEnte;
	}

	public void setCodiceEnte(java.lang.String codiceEnte) {
		this.codiceEnte = codiceEnte;
	}

	public java.lang.String getTipoUfficio() {
		return tipoUfficio;
	}

	public void setTipoUfficio(java.lang.String tipoUfficio) {
		this.tipoUfficio = tipoUfficio;
	}

	public java.lang.String getCodiceUfficio() {
		return codiceUfficio;
	}

	public void setCodiceUfficio(java.lang.String codiceUfficio) {
		this.codiceUfficio = codiceUfficio;
	}

	public java.lang.String getCodiceTipoEnte() {
		return codiceTipoEnte;
	}

	public void setCodiceTipoEnte(java.lang.String codiceTipoEnte) {
		this.codiceTipoEnte = codiceTipoEnte;
	}

	public java.lang.String getDescrizioneEnte() {
		return descrizioneEnte;
	}

	public void setDescrizioneEnte(java.lang.String descrizioneEnte) {
		this.descrizioneEnte = descrizioneEnte;
	}

	public java.lang.String getDescrizioneUfficio() {
		return descrizioneUfficio;
	}

	public void setDescrizioneUfficio(java.lang.String descrizioneUfficio) {
		this.descrizioneUfficio = descrizioneUfficio;
	}

	public java.lang.String getCodiceRuoliErariali() {
		return codiceRuoliErariali;
	}

	public void setCodiceRuoliErariali(java.lang.String codiceRuoliErariali) {
		this.codiceRuoliErariali = codiceRuoliErariali;
	}

	public java.sql.Date getDataDecorrenza() {
		return dataDecorrenza;
	}

	public void setDataDecorrenza(java.sql.Date dataDecorrenza) {
		this.dataDecorrenza = dataDecorrenza;
	}

	public java.lang.String getUfficioStatale() {
		return ufficioStatale;
	}

	public void setUfficioStatale(java.lang.String ufficioStatale) {
		this.ufficioStatale = ufficioStatale;
	}

	public java.lang.String getCodiceOperatore() {
		return codiceOperatore;
	}

	public void setCodiceOperatore(java.lang.String codiceOperatore) {
		this.codiceOperatore = codiceOperatore;
	}

	public AnagProvCom getAnagProvCom() {
		return anagProvCom;
	}

	public void setAnagProvCom(AnagProvCom anagProvCom) {
		this.anagProvCom = anagProvCom;
	}

	@Override
	public void onSave(CallableStatement arg) throws SQLException {
	//	arg.setString(1, this.anagProvCom.getCompany().getCompanyCode());
		arg.setString(1, this.codiceEnte);
		arg.setString(2, this.tipoUfficio);
		arg.setString(3, this.codiceUfficio);
		arg.setString(4, this.codiceTipoEnte);
		arg.setString(5, this.descrizioneEnte);
		arg.setString(6, this.descrizioneUfficio);
		arg.setString(7, this.anagProvCom.getCodiceBelfiore());
		arg.setString(8, this.codiceRuoliErariali);
		arg.setDate(9, this.dataDecorrenza);
		arg.setString(10, this.ufficioStatale);
		arg.setString(11, this.codiceOperatore);  // !! UTE_CUTECURL (OPERTORE ULTIMO AGGIORNAMENTO)
	}
	
	@Override
	public void onUpdate(CallableStatement arg) throws SQLException {
		arg.setString(1, this.chiaveEnte);
	//	arg.setString(2, this.anagProvCom.getCompany().getCompanyCode());
		arg.setString(2, this.codiceEnte);
		arg.setString(3, this.tipoUfficio);
		arg.setString(4, this.codiceUfficio);
		arg.setString(5, this.codiceTipoEnte);
		arg.setString(6, this.descrizioneEnte);
		arg.setString(7, this.descrizioneUfficio);
		arg.setString(8, this.anagProvCom.getCodiceBelfiore());
		arg.setString(9, this.codiceRuoliErariali);
		arg.setDate(10, this.dataDecorrenza);
		arg.setString(11, this.ufficioStatale);
		arg.setString(12, this.codiceOperatore);  // !! UTE_CUTECURL (OPERTORE ULTIMO AGGIORNAMENTO)
	}
	
	@Override
	public void onLoad(CallableStatement arg) throws SQLException {

	}
	
	@Override
	public void onDelete(CallableStatement arg) throws SQLException {

	}
}