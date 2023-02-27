package com.seda.payer.core.bean;

import java.io.Serializable;
import java.sql.Date;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import com.seda.payer.commons.bean.Lifecycle;

public class AssocImpBen extends Lifecycle implements Serializable {

		private static final long serialVersionUID = 1L;
	    private User user;
	    private User userBeneficiario;
	    private AnagEnte anagImpositore;
	    private AnagEnte anagBeneficiario;
	    private TipologiaServizio tipologiaServizio;

	    private String annoRifDa="";
	    private String annoRifA="";
	    private String dataValidita="";
	    private String email="";
	    private String tipoRendicontazione="";
	    private String strumRiversamento="";
	    private String metodoRend="";
//	    private String dataUltimoAgg;
	    private String codiceOperatore="";

	    public AssocImpBen() { 
	    	user = new User();
	    	userBeneficiario = new User();
	    	anagImpositore = new AnagEnte();
	    	anagBeneficiario = new AnagEnte();
	    	tipologiaServizio = new TipologiaServizio();

	    }

	    public AssocImpBen(String companyCode, String codProvincia, String userCode,
	    				   String companyCodeBen, String codProvinciaBen, String userCodeBen,
	    		           String codImpositore, String codBeneficiario, 
	    		           String codTipologiaServizio, String dataValidita, String annoDa, String annoA) { 
	    	user = new User();
	    	user.getCompany().setCompanyCode(companyCode);
	    	user.setUserCode(userCode);

	    	userBeneficiario = new User();
	    	userBeneficiario.getCompany().setCompanyCode(companyCodeBen);
	    	userBeneficiario.setUserCode(userCodeBen);

	    	anagImpositore = new AnagEnte();
	    	anagImpositore.setChiaveEnte(codImpositore);
	    	anagImpositore.getAnagProvCom().setCodiceBelfiore(codProvincia);
	    	anagBeneficiario = new AnagEnte();
	    	anagBeneficiario.setChiaveEnte(codBeneficiario);
	    	anagBeneficiario.getAnagProvCom().setCodiceBelfiore(codProvinciaBen);
	    	tipologiaServizio = new TipologiaServizio();
	    	tipologiaServizio.getCompany().setCompanyCode(companyCode);	    	
	    	tipologiaServizio.setCodiceTipologiaServizio(codTipologiaServizio);
	    	
	    	this.dataValidita = dataValidita;
	    	this.annoRifDa = annoDa;
	    	this.annoRifA = annoA;
	    }

	    public AssocImpBen(ResultSet data) throws SQLException {
	    	if (data == null)
	    		return;

	    	user = new User();
	    	user.getCompany().setCompanyCode(data.getString("CEB_CSOCCSOC"));
	    	user.setUserCode(data.getString("CEB_CUTECUTE"));

	    	userBeneficiario = new User();
	    	userBeneficiario.getCompany().setCompanyCode(data.getString("CEB_CSOCCSOC_BEN"));
	    	userBeneficiario.setUserCode(data.getString("CEB_CUTECUTE_BEN"));

	    	anagImpositore = new AnagEnte();
	    	anagImpositore.setChiaveEnte(data.getString("CEB_KANEKENT_IMP"));
	    	anagBeneficiario = new AnagEnte();
	    	anagBeneficiario.setChiaveEnte(data.getString("CEB_KANEKENT_BEN"));
	    	tipologiaServizio = new TipologiaServizio();
	    	tipologiaServizio.getCompany().setCompanyCode(data.getString("CEB_CSOCCSOC"));	    	
	    	tipologiaServizio.setCodiceTipologiaServizio(data.getString("CEB_CTSECTSE"));	    	
	    	
	    	
	    	annoRifDa = data.getString("CEB_NCEBANNO_DA");
		    annoRifA = data.getString("CEB_NCEBANNO_A");
		    dataValidita = data.getString("CEB_GCEBGDAT");
		    email = data.getString("CEB_DCEBMAIL");
		    tipoRendicontazione = data.getString("CEB_FCEBTIPO");
		    strumRiversamento = data.getString("CEB_FCEBRIVE");
		    metodoRend = data.getString("CEB_FCEBREND");
//		    dataUltimoAgg = data.getString("");
		    codiceOperatore = data.getString("CEB_CCEBCOPE");

	    }
	    
		public void onSave(CallableStatement arg) throws SQLException {

			
			arg.setString(1, this.user.getCompany().getCompanyCode());
			arg.setString(2, this.user.getUserCode());
			arg.setString(3, this.anagImpositore.getChiaveEnte());
			arg.setString(4, this.tipologiaServizio.getCodiceTipologiaServizio());
			arg.setString(5, this.annoRifDa);
			arg.setString(6, this.annoRifA);
			arg.setString(7, this.dataValidita);	
			arg.setString(8, this.anagBeneficiario.getChiaveEnte());
			arg.setString(9, this.tipoRendicontazione);  //CEB_FCEBTIPO
			arg.setString(10, this.strumRiversamento);	//CEB_FCEBRIVE
			arg.setString(11, this.metodoRend);			//CEB_FCEBREND
			arg.setString(12, this.email);
			arg.setString(13, this.codiceOperatore);
			arg.setString(14, this.userBeneficiario.getCompany().getCompanyCode());
			arg.setString(15, this.userBeneficiario.getUserCode());
			arg.registerOutParameter(16, Types.INTEGER);
		}
		
		public void onUpdate(CallableStatement arg) throws SQLException {

		}
		
		public void onLoad(CallableStatement arg) throws SQLException {

		}
		
		public void onDelete(CallableStatement arg) throws SQLException {
			arg.setString(1, this.user.getCompany().getCompanyCode());
			arg.setString(2, this.user.getUserCode());
			arg.setString(3, this.anagImpositore.getChiaveEnte());
			arg.setString(4, this.tipologiaServizio.getCodiceTipologiaServizio());
			arg.setString(5, this.annoRifDa);
			arg.setString(6, this.annoRifA);
			arg.setString(7, this.dataValidita);
			arg.setString(8, this.anagBeneficiario.getChiaveEnte());
			arg.setString(9, this.userBeneficiario.getCompany().getCompanyCode());
			arg.setString(10, this.userBeneficiario.getUserCode());
			arg.registerOutParameter(11, Types.INTEGER);

		}

		public User getUserBeneficiario() {
			return userBeneficiario;
		}

		public void setUserBeneficiario(User userBeneficiario) {
			this.userBeneficiario = userBeneficiario;
		}

		public User getUser() {
			return user;
		}

		public void setUser(User user) {
			this.user = user;
		}

		public AnagEnte getAnagImpositore() {
			return anagImpositore;
		}

		public void setAnagImpositore(AnagEnte anagImpositore) {
			this.anagImpositore = anagImpositore;
		}

		public AnagEnte getAnagBeneficiario() {
			return anagBeneficiario;
		}

		public void setAnagBeneficiario(AnagEnte anagBeneficiario) {
			this.anagBeneficiario = anagBeneficiario;
		}

		public TipologiaServizio getTipologiaServizio() {
			return tipologiaServizio;
		}

		public void setTipologiaServizio(TipologiaServizio tipologiaServizio) {
			this.tipologiaServizio = tipologiaServizio;
		}

		public String getAnnoRifDa() {
			return annoRifDa;
		}

		public void setAnnoRifDa(String annoRifDa) {
			this.annoRifDa = annoRifDa;
		}

		public String getAnnoRifA() {
			return annoRifA;
		}

		public void setAnnoRifA(String annoRifA) {
			this.annoRifA = annoRifA;
		}

		public String getDataValidita() {
			return dataValidita;
		}

		public void setDataValidita(String dataValidita) {
			this.dataValidita = dataValidita;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public String getTipoRendicontazione() {
			return tipoRendicontazione;
		}

		public void setTipoRendicontazione(String tipoRendicontazione) {
			this.tipoRendicontazione = tipoRendicontazione;
		}

		public String getStrumRiversamento() {
			return strumRiversamento;
		}

		public void setStrumRiversamento(String strumRiversamento) {
			this.strumRiversamento = strumRiversamento;
		}

		public String getMetodoRend() {
			return metodoRend;
		}

		public void setMetodoRend(String metodoRend) {
			this.metodoRend = metodoRend;
		}

		public String getCodiceOperatore() {
			return codiceOperatore;
		}

		public void setCodiceOperatore(String codiceOperatore) {
			this.codiceOperatore = codiceOperatore;
		}



}