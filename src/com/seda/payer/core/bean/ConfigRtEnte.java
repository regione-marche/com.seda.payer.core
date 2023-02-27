package com.seda.payer.core.bean;

import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.seda.payer.commons.bean.Lifecycle;

public class ConfigRtEnte extends Lifecycle implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String codiceSocieta;        		//	"CFT_CSOCCSOC" CHAR(5)
	private String cuteCute;					//	"CFT_CUTECUTE" CHAR(5)
	private String chiaveEnte;					//	"CFT_KANEKENT" CHAR(10)
	private String codiceIdpaEnte;				//  "CFT_CCFTIDPA" VARCHAR(50)
	private String utenteAccesso;		  		//	"CFT_CCFTUSER" VARCHAR(100)
	private String passwAccesso;			  	//	"CFT_CCFTPASW" VARCHAR(64)
	private String urlInvioRt;					//  "CFT_CCFTURLI" VARCHAR(256)
	private String mailInvioEsitoInvioRt;		//  "CFT_CCFTEMAI" VARCHAR(100)
	private String urlRecuperoEsitoRt;			//	"CFT_CCFTURLR" VARCHAR(256)
	private String mailInvioEsitoRecuperoRt;	//	"CFT_CCFTEMAR" VARCHAR(100)
	private boolean flagAttivo;					//	"CFT_FCFTATTI" boolean
	private boolean flagAbilitato;				//	"CFT_FCFTABIL" boolean
	private String utenteOperazione;			//  "CFT_CCFTCOPE" VARCHAR(50)    
    
    
	public ConfigRtEnte() {
		//ente = new Ente();
	}
	
	 public ConfigRtEnte(ResultSet data) throws SQLException {
	    	if (data == null)
	    		return;

	    	codiceSocieta = data.getString("CFT_CSOCCSOC");
	    	cuteCute = data.getString("CFT_CUTECUTE");
	    	chiaveEnte = data.getString("CFT_KANEKENT");
	    	codiceIdpaEnte = data.getString("CFT_CCFTIDPA");
	    	utenteAccesso = data.getString("CFT_CCFTUSER");
	    	passwAccesso =data.getString("CFT_CCFTPASW");
	    	urlInvioRt= data.getString("CFT_CCFTURLI");
	    	mailInvioEsitoInvioRt = data.getString("CFT_CCFTEMAI");
	    	urlRecuperoEsitoRt = data.getString("CFT_CCFTURLR");
	    	mailInvioEsitoRecuperoRt = data.getString("CFT_CCFTEMAR");
	    	flagAttivo = data.getBoolean("CFT_FCFTATTI");
	    	flagAbilitato  = data.getBoolean("CFT_FCFTABIL");
	    	utenteOperazione = data.getString("CFT_CCFTCOPE");
	 }

		public String getCodiceSocieta() {
			return codiceSocieta;
		}
		public void setCodiceSocieta(String codiceSocieta) {
			this.codiceSocieta = codiceSocieta;
		}
		public String getCuteCute() {
			return cuteCute;
		}
		public void setCuteCute(String cuteCute) {
			this.cuteCute = cuteCute;
		}
		public String getChiaveEnte() {
			return chiaveEnte;
		}
		public void setChiaveEnte(String chiaveEnte) {
			this.chiaveEnte = chiaveEnte;
		}
		public String getCodiceIdpaEnte() {
			return codiceIdpaEnte;
		}
		public void setCodiceIdpaEnte(String codiceIdpaEnte) {
			this.codiceIdpaEnte = codiceIdpaEnte;
		}
		public String getUtenteAccesso() {
			return utenteAccesso;
		}
		public void setUtenteAccesso(String utenteAccesso) {
			this.utenteAccesso = utenteAccesso;
		}
		public String getPasswAccesso() {
			return passwAccesso;
		}
		public void setPasswAccesso(String passwAccesso) {
			this.passwAccesso = passwAccesso;
		}
		public String getUrlInvioRt() {
			return urlInvioRt;
		}
		public void setUrlInvioRt(String urlInvioRt) {
			this.urlInvioRt = urlInvioRt;
		}
		public String getMailInvioEsitoInvioRt() {
			return mailInvioEsitoInvioRt;
		}
		public void setMailInvioEsitoInvioRt(String mailInvioEsitoInvioRt) {
			this.mailInvioEsitoInvioRt = mailInvioEsitoInvioRt;
		}
		public String getUrlRecuperoEsitoRt() {
			return urlRecuperoEsitoRt;
		}
		public void setUrlRecuperoEsitoRt(String urlRecuperoEsitoRt) {
			this.urlRecuperoEsitoRt = urlRecuperoEsitoRt;
		}
		public String getMailInvioEsitoRecuperoRt() {
			return mailInvioEsitoRecuperoRt;
		}
		public void setMailInvioEsitoRecuperoRt(String mailInvioEsitoRecuperoRt) {
			this.mailInvioEsitoRecuperoRt = mailInvioEsitoRecuperoRt;
		}
		public boolean isFlagAttivo() {
			return flagAttivo;
		}
		public void setFlagAttivo(boolean flagAttivo) {
			this.flagAttivo = flagAttivo;
		}
		public boolean isFlagAbilitato() {
			return flagAbilitato;
		}
		public void setFlagAbilitato(boolean flagAbilitato) {
			this.flagAbilitato = flagAbilitato;
		}
		public String getUtenteOperazione() {
			return utenteOperazione;
		}
		public void setUtenteOperazione(String utenteOperazione) {
			this.utenteOperazione = utenteOperazione;
		}
	
	@Override
	public void onSave(CallableStatement arg) throws SQLException {
		arg.setString(1, this.getCodiceSocieta());
		arg.setString(2, this.getCuteCute());
		arg.setString(3, this.getChiaveEnte());
		arg.setString(4, this.getCodiceIdpaEnte());
		arg.setString(5, this.getUtenteAccesso());
		arg.setString(6, this.getPasswAccesso());
		arg.setString(7, this.getUrlInvioRt());
		arg.setString(8, this.getMailInvioEsitoInvioRt());
		arg.setString(9, this.getUrlRecuperoEsitoRt());
		arg.setString(10, this.getMailInvioEsitoRecuperoRt());
		arg.setBoolean(11, this.isFlagAttivo());
		arg.setBoolean(12, this.isFlagAbilitato());
		arg.setString(13, this.getUtenteOperazione());		
	}
	
	@Override
	public void onUpdate(CallableStatement arg) throws SQLException {
		arg.setString(1, this.getCodiceSocieta());
		arg.setString(2, this.getCuteCute());
		arg.setString(3, this.getChiaveEnte());
		arg.setString(4, this.getCodiceIdpaEnte());
		arg.setString(5, this.getUtenteAccesso());
		arg.setString(6, this.getPasswAccesso());
		arg.setString(7, this.getUrlInvioRt());
		arg.setString(8, this.getMailInvioEsitoInvioRt());
		arg.setString(9, this.getUrlRecuperoEsitoRt());
		arg.setString(10, this.getMailInvioEsitoRecuperoRt());
		arg.setBoolean(11, this.isFlagAttivo());
		arg.setBoolean(12, this.isFlagAbilitato());
		arg.setString(13, this.getUtenteOperazione());		
	}
	
	@Override
	public void onLoad(CallableStatement arg) throws SQLException {

	}
	
	@Override
	public void onDelete(CallableStatement arg) throws SQLException {

	}
    
   

}
