package com.seda.payer.core.bean;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Log implements Serializable{

		private static final long serialVersionUID = 1L;
		
		 private java.lang.String userToken;

		 private java.lang.String sessionId;

		 private java.lang.String userName;

		 private java.lang.String userProfile;

	    private java.lang.String codiceSocieta;

	    private java.lang.String codiceUtente;

	    private java.lang.String chiaveEnte;

	    private java.lang.String canalePagamento;

	    private java.lang.String indirizzoIP;

	    private java.lang.String startSession;

	    private java.lang.String endSession;

	    public Log() {
	    }
  
	    public Log(ResultSet data) throws SQLException {
	    	if (data == null)
	    		return;

	    	setUserToken(data.getString("LOG_CLOGTOKE"));
	        setSessionId(data.getString("LOG_CLOGSEID"));
	        setUserName(data.getString("LOG_CLOGUSER"));
	        setUserProfile(data.getString("LOG_CPRFPROF"));
	        setCodiceSocieta(data.getString("LOG_CSOCCSOC"));
	        setCodiceUtente(data.getString("LOG_CUTECUTE"));
	        setChiaveEnte(data.getString("LOG_KANEKENT"));
	        setCanalePagamento(data.getString("LOG_KCANKCAN"));
	        setIndirizzoIP(data.getString("LOG_CLOGINIP"));
	        setStartSession(data.getString("LOG_GLOGINIZ"));
	        setEndSession(data.getString("LOG_GLOGFINE"));
	        
	    }

		public java.lang.String getUserToken() {
			return userToken;
		}

		public java.lang.String getSessionId() {
			return sessionId;
		}

		public java.lang.String getUserName() {
			return userName;
		}

		public java.lang.String getUserProfile() {
			return userProfile;
		}

		public java.lang.String getCodiceSocieta() {
			return codiceSocieta;
		}

		public java.lang.String getCodiceUtente() {
			return codiceUtente;
		}

		public java.lang.String getChiaveEnte() {
			return chiaveEnte;
		}

		public java.lang.String getCanalePagamento() {
			return canalePagamento;
		}

		public java.lang.String getIndirizzoIP() {
			return indirizzoIP;
		}



		public java.lang.String getEndSession() {
			return endSession;
		}

		public void setUserToken(java.lang.String userToken) {
			this.userToken = userToken;
		}

		public void setSessionId(java.lang.String sessionId) {
			this.sessionId = sessionId;
		}

		public void setUserName(java.lang.String userName) {
			this.userName = userName;
		}

		public void setUserProfile(java.lang.String userProfile) {
			this.userProfile = userProfile;
		}

		public void setCodiceSocieta(java.lang.String codiceSocieta) {
			this.codiceSocieta = codiceSocieta;
		}

		public void setCodiceUtente(java.lang.String codiceUtente) {
			this.codiceUtente = codiceUtente;
		}

		public void setChiaveEnte(java.lang.String chiaveEnte) {
			this.chiaveEnte = chiaveEnte;
		}

		public void setCanalePagamento(java.lang.String canalePagamento) {
			this.canalePagamento = canalePagamento;
		}

		public void setIndirizzoIP(java.lang.String indirizzoIP) {
			this.indirizzoIP = indirizzoIP;
		}

		public java.lang.String getStartSession() {
			return startSession;
		}

		public void setStartSession(java.lang.String startSession) {
			this.startSession = startSession;
		}

		public void setEndSession(java.lang.String endSession) {
			this.endSession = endSession;
		}

	}
