package com.seda.payer.core.bean;

import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.seda.payer.commons.bean.Lifecycle;

public class ApplProf extends Lifecycle implements Serializable {

	private static final long serialVersionUID = 1L;
	private java.lang.String chiaveProfilo;
	private java.lang.String codiceProfilo;
	private java.lang.String descrizioneProfilo;
	private java.lang.Short livelloProfilo;
    private java.lang.String codiceOperatore;

    public ApplProf() { }
    
    public ApplProf(ResultSet data) throws SQLException {
    	if (data == null)
    		return;

    	chiaveProfilo = data.getString("PRF_KPRFKPRF");
    	codiceProfilo = data.getString("PRF_CPRFPROF");
    	descrizioneProfilo = data.getString("PRF_DPRFDESC");
    	livelloProfilo = data.getShort("PRF_NPRFLIVE");
        codiceOperatore = data.getString("PRF_CPRFCOPE");
    }

    


	public java.lang.String getChiaveProfilo() {
		return chiaveProfilo;
	}




	public void setChiaveProfilo(java.lang.String chiaveProfilo) {
		this.chiaveProfilo = chiaveProfilo;
	}




	public java.lang.String getCodiceProfilo() {
		return codiceProfilo;
	}




	public void setCodiceProfilo(java.lang.String codiceProfilo) {
		this.codiceProfilo = codiceProfilo;
	}




	public java.lang.String getDescrizioneProfilo() {
		return descrizioneProfilo;
	}




	public void setDescrizioneProfilo(java.lang.String descrizioneProfilo) {
		this.descrizioneProfilo = descrizioneProfilo;
	}




	public java.lang.Short getLivelloProfilo() {
		return livelloProfilo;
	}




	public void setLivelloProfilo(java.lang.Short livelloProfilo) {
		this.livelloProfilo = livelloProfilo;
	}




	public java.lang.String getCodiceOperatore() {
		return codiceOperatore;
	}




	public void setCodiceOperatore(java.lang.String codiceOperatore) {
		this.codiceOperatore = codiceOperatore;
	}




	@Override
	public void onSave(CallableStatement arg) throws SQLException {
		arg.setString(1, this.codiceProfilo);
		arg.setString(2, this.descrizioneProfilo);
		arg.setShort(3, this.livelloProfilo);
		arg.setString(4, this.codiceOperatore);  // (OPERATORE ULTIMO AGGIORNAMENTO)
	}
	
	@Override
	public void onUpdate(CallableStatement arg) throws SQLException {
		arg.setString(1, this.chiaveProfilo);
		arg.setString(2, this.codiceProfilo);
		arg.setString(3, this.descrizioneProfilo);
		arg.setShort(4, this.livelloProfilo);
		arg.setString(5, this.codiceOperatore);  // (OPERATORE ULTIMO AGGIORNAMENTO)
	}
	
	@Override
	public void onLoad(CallableStatement arg) throws SQLException {

	}
	
	@Override
	public void onDelete(CallableStatement arg) throws SQLException {

	}
}
