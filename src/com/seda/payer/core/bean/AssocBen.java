package com.seda.payer.core.bean;

import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import com.seda.payer.commons.bean.Lifecycle;

public class AssocBen extends Lifecycle implements Serializable {

		private static final long serialVersionUID = 1L;
	    private User user;
	    private AnagEnte anagBeneficiario;

	    private String annoRifDa="";
	    private String annoRifA="";
	    private String dataValidita="";
	    private String tipoRendicontazione="";
	    private String strumRiversamento="";
	    private String metodoRend="";
	    private String codiceOperatore="";

	    public AssocBen() { 
	    	user = new User();
	    	anagBeneficiario = new AnagEnte();
	    }

	    public AssocBen(String companyCode, String codProvincia, String userCode, 
	    		           String codBeneficiario, String dataValidita, String annoDa, String annoA) { 
	    	user = new User();
	    	user.getCompany().setCompanyCode(companyCode);
	    	user.setUserCode(userCode);
	    	anagBeneficiario = new AnagEnte();
	    	anagBeneficiario.setChiaveEnte(codBeneficiario);
	    	anagBeneficiario.getAnagProvCom().setCodiceProvincia(codProvincia);
	    	
	    	this.dataValidita = dataValidita;
	    	this.annoRifDa = annoDa;
	    	this.annoRifA = annoA;
	    }

	    public AssocBen(ResultSet data) throws SQLException {
	    	if (data == null)
	    		return;

	    	user = new User();
	    	user.getCompany().setCompanyCode(data.getString("CBP_CSOCCSOC"));
	    	user.setUserCode(data.getString("CBP_CUTECUTE"));
	    	anagBeneficiario = new AnagEnte();
	    	anagBeneficiario.setChiaveEnte(data.getString("CBP_KANEKENT_BEN"));
	    	
	    	annoRifDa = data.getString("CBP_NCBPANNO_DA");
		    annoRifA = data.getString("CBP_NCBPANNO_A");
		    dataValidita = data.getString("CBP_GCBPGDAT");
		    tipoRendicontazione = data.getString("CBP_FCBPTIPO");
		    strumRiversamento = data.getString("CBP_FCBPRIVE");
		    metodoRend = data.getString("CBP_FCBPREND");
		    codiceOperatore = data.getString("CBP_CCBPCOPE");

	    }
	    
		public void onSave(CallableStatement arg) throws SQLException {

			/*IN I_CBP_CSOCCSOC CHAR(5),
			IN I_CBP_CUTECUTE CHAR(5),
			IN I_CBP_KANEKENT_BEN CHAR(10),
			IN I_CBP_NCBPANNO_DA CHAR(4),
			IN I_CBP_NCBPANNO_A CHAR(4),
			IN I_CBP_GCBPGDAT CHAR(10),
			IN I_CBP_FCBPTIPO CHAR(1),
			IN I_CBP_FCBPRIVE CHAR(1),
			IN I_CBP_FCBPREND CHAR(1),
			IN I_CBP_CCBPCOPE VARCHAR(50),
			OUT O_CODE INTEGER*/
			arg.setString(1, this.user.getCompany().getCompanyCode());
			arg.setString(2, this.user.getUserCode());
			arg.setString(3, this.anagBeneficiario.getChiaveEnte());
			arg.setString(4, this.annoRifDa);
			arg.setString(5, this.annoRifA);
			arg.setString(6, this.dataValidita);	
			arg.setString(7, this.tipoRendicontazione);  //CEB_FCEBTIPO
			arg.setString(8, this.strumRiversamento);	//CEB_FCEBRIVE
			arg.setString(9, this.metodoRend);			//CEB_FCEBREND
			arg.setString(10, this.codiceOperatore);
			arg.registerOutParameter(11, Types.INTEGER);
		}
		
		public void onUpdate(CallableStatement arg) throws SQLException {

		}
		
		public void onLoad(CallableStatement arg) throws SQLException {

		}
		
		public void onDelete(CallableStatement arg) throws SQLException {
			/*IN I_CBP_CSOCCSOC CHAR(5),
			IN I_CBP_CUTECUTE CHAR(5),
			IN I_CBP_KANEKENT_BEN CHAR(10),
			IN I_CBP_GCBPGDAT CHAR(10),
			OUT O_CODE INTEGER*/
			arg.setString(1, this.user.getCompany().getCompanyCode());
			arg.setString(2, this.user.getUserCode());
			arg.setString(3, this.anagBeneficiario.getChiaveEnte());
			arg.setString(4, this.dataValidita);	
			arg.registerOutParameter(5, Types.INTEGER);
		}

		public User getUser() {
			return user;
		}

		public void setUser(User user) {
			this.user = user;
		}

		public AnagEnte getAnagBeneficiario() {
			return anagBeneficiario;
		}

		public void setAnagBeneficiario(AnagEnte anagBeneficiario) {
			this.anagBeneficiario = anagBeneficiario;
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