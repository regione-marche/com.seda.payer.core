package com.seda.payer.core.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import com.seda.payer.commons.bean.Lifecycle;

public class ContoGestione extends Lifecycle implements Serializable {

		private static final long serialVersionUID = 1L;
	    private String identificativo; 
		private User user;
	    private AnagEnte anagEnte;

  	    private String tipoModello;
  	    private String periodo;
  	    private BigDecimal importoDa;
  	    private BigDecimal importoA;


	    public ContoGestione() { 
	    	user = new User();
	    	anagEnte = new AnagEnte();
	    }

	    public ContoGestione(String companyCode, String codProvincia, String userCode, 
	    		           String codEnte, String tipoModello, String periodo,BigDecimal importoDa, BigDecimal importoA) { 
	    	user = new User();
	    	user.getCompany().setCompanyCode(companyCode);
	    	user.setUserCode(userCode);
	    	anagEnte = new AnagEnte();
	    	anagEnte.setChiaveEnte(codEnte);
	    	anagEnte.getAnagProvCom().setCodiceProvincia(codProvincia);
	    	
	    	this.tipoModello = tipoModello;
	    	this.periodo = periodo;
	    	this.importoDa = importoDa;
	    	this.importoA = importoA;
	    }

	    public ContoGestione(ResultSet data) throws SQLException {
	    	if (data == null)
	    		return;
			identificativo = data.getString("MCG_CUTECUTE");
			user = new User();
	    	user.getCompany().setCompanyCode(data.getString("MCG_CSOCCSOC"));
	    	user.setUserCode(data.getString("MCG_CUTECUTE"));
	    	anagEnte = new AnagEnte();
	    	anagEnte.setChiaveEnte(data.getString("MCG_KANEKENT"));
	    	
	    	tipoModello = data.getString("MCG_FMCGFTIP");
		    periodo = data.getString("MCG_CMCGCPER");
		    //da terminare
	    }
	    
		public void onSave(CallableStatement arg) throws SQLException {

		}
		
		public void onUpdate(CallableStatement arg) throws SQLException {

		}
		
		public void onLoad(CallableStatement arg) throws SQLException {

		}
		
		public void onDelete(CallableStatement arg) throws SQLException {
		}

		public User getUser() {
			return user;
		}

		public void setUser(User user) {
			this.user = user;
		}

		public AnagEnte getAnagEnte() {
			return anagEnte;
		}

		public void setAnagEnte(AnagEnte anagEnte) {
			this.anagEnte = anagEnte;
		}

		public String getIdentificativo() {
			return identificativo;
		}

		public void setIdentificativo(String identificativo) {
			this.identificativo = identificativo;
		}

		public String getTipoModello() {
			return tipoModello;
		}

		public void setTipoModello(String tipoModello) {
			this.tipoModello = tipoModello;
		}

		public String getPeriodo() {
			return periodo;
		}

		public void setPeriodo(String periodo) {
			this.periodo = periodo;
		}

		public BigDecimal getImportoDa() {
			return importoDa;
		}

		public void setImportoDa(BigDecimal importoDa) {
			this.importoDa = importoDa;
		}

		public BigDecimal getImportoA() {
			return importoA;
		}

		public void setImportoA(BigDecimal importoA) {
			this.importoA = importoA;
		}




}