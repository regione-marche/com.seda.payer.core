package com.seda.payer.core.bean;

import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import com.seda.payer.commons.bean.Lifecycle;

public class Beneficiario extends Lifecycle implements Serializable {

		private static final long serialVersionUID = 1L;
	    private User user;
	    private AnagEnte anagEnte;
	    
		private String codiceFiscale;
	    private String indirizzo;
	    private String email;
	    private String descBeneficiario;
	    
	    private String codiceIban;
	    private String codiceSia;
	    private String flagTipologiaFile;
	    private String dataUltimoAgg;
	    private String codiceOperatore;

	    public Beneficiario() { 
	    	user = new User();
	    	anagEnte = new AnagEnte();
	    }

	    public Beneficiario(String companyCode, String userCode, String chiaveEnte) { 
	    	user = new User();
	    	user.getCompany().setCompanyCode(companyCode);
	    	user.setUserCode(userCode);
	    	anagEnte = new AnagEnte();
	    	anagEnte.setChiaveEnte(chiaveEnte);
	    }

	    public Beneficiario(ResultSet data) throws SQLException {
	    	if (data == null)
	    		return;

			codiceFiscale= data.getString("BEN_CBENCFIS");
		    indirizzo= data.getString("BEN_DBENDIND");
		    email= data.getString("BEN_DBENEBEN");
		    descBeneficiario = data.getString("BEN_DBENBENE");
		    
		    codiceIban= data.getString("BEN_CBENIBAN");
		    flagTipologiaFile = data.getString("BEN_TBENFCSV");
		    codiceSia= data.getString("BEN_CBENCSIA");
		    codiceOperatore= data.getString("BEN_CBENCOPE");
		    
	        user = new User(); {
	        	user.getCompany().setCompanyCode(data.getString("BEN_CSOCCSOC"));
	        	user.setUserCode(data.getString("BEN_CUTECUTE"));
	        }
	        anagEnte = new AnagEnte(); {
	        	anagEnte.setChiaveEnte(data.getString("BEN_KANEKANE"));
	        }
	    }
	    
		public void onSave(CallableStatement arg) throws SQLException {
			arg.setString(1, this.user.getCompany().getCompanyCode());
			arg.setString(2, this.user.getUserCode());
			arg.setString(3, this.anagEnte.getChiaveEnte());

			arg.setString(4, this.codiceFiscale);
			arg.setString(5, this.indirizzo);
			arg.setString(6, this.email);
			arg.setString(7, this.codiceIban);
			arg.setString(8, this.codiceSia);
			arg.setString(9, this.codiceOperatore);
			arg.setString(10, this.descBeneficiario);
			arg.setString(11, this.flagTipologiaFile);
			arg.registerOutParameter(12, Types.INTEGER);
		}
		
		public void onUpdate(CallableStatement arg) throws SQLException {

		}
		
		public void onLoad(CallableStatement arg) throws SQLException {

		}
		
		public void onDelete(CallableStatement arg) throws SQLException 
		{
			arg.setString(1, this.getUser().getCompany().getCompanyCode());
			arg.setString(2, this.getUser().getUserCode());
			arg.setString(3, this.getAnagEnte().getChiaveEnte());
			arg.registerOutParameter(4, Types.INTEGER);
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

		public String getCodiceFiscale() {
			return codiceFiscale;
		}

		public void setCodiceFiscale(String codiceFiscale) {
			this.codiceFiscale = codiceFiscale;
		}

		public String getIndirizzo() {
			return indirizzo;
		}

		public void setIndirizzo(String indirizzo) {
			this.indirizzo = indirizzo;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public String getCodiceIban() {
			return codiceIban;
		}

		public void setCodiceIban(String codiceIban) {
			this.codiceIban = codiceIban;
		}

		public String getCodiceSia() {
			return codiceSia;
		}

		public void setCodiceSia(String codiceSia) {
			this.codiceSia = codiceSia;
		}

		public String getDataUltimoAgg() {
			return dataUltimoAgg;
		}

		public void setDataUltimoAgg(String dataUltimoAgg) {
			this.dataUltimoAgg = dataUltimoAgg;
		}

		public String getCodiceOperatore() {
			return codiceOperatore;
		}

		public void setCodiceOperatore(String codiceOperatore) {
			this.codiceOperatore = codiceOperatore;
		}

		public String getDescBeneficiario() {
			return descBeneficiario;
		}

		public void setDescBeneficiario(String descBeneficiario) {
			this.descBeneficiario = descBeneficiario;
		}

		public void setFlagTipologiaFile(String flagTipologiaFile) {
			this.flagTipologiaFile = flagTipologiaFile;
		}

		public String getFlagTipologiaFile() {
			return flagTipologiaFile;
		}


}