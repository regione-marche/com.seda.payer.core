package com.esed.log.req.dati;

import java.io.Serializable;
import java.math.BigInteger;
import java.sql.Timestamp;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class LogRequest implements Serializable
{
	private static final long serialVersionUID = 1L;
	//parametri da classe request
	private BigInteger idRequest;
	private String indirizzoIP;
	private String sessionID;
	private String tipoRequest;
	private String applicativo;
	private String action;
	private String queryString;
	private String param;
	private String error;
	private String request;
	private Timestamp dataRequest;
	private String operatoreRequest;
	//parametri da applicazione
	private String template;
	private String dbSchemaCodSocieta;
	private String sezioneApplicativa;
	private String canalePagamento;
	private String userName;
	private String userProfile;
	private String codiceSocieta;
	private String codiceUtente;
	private String chiaveEnte;
	private String descrizioneEnte;
	private String siglaProvinciaEnte;
	//eventuali parametri presenti sulla request
	private String codiceFiscale;
	private String numeroDocumento;
	private String numeroBollettino;
	private String belfioreRequest;
	private String comuneRequest;
	private String siglaProvinciaRequest;
	private String chiaveTransazione;
	private String numeroIUV;
	//parametri per la ricerca
	private int rowsPerPage;
	private int pageNumber;
	private String order;	
	private String dataInizio_da;
	private String dataFine_a;
	//per selezionare la tabella su cui lavorare
	private String tagSuffissoTabella;

	public BigInteger getIdRequest() {
		return idRequest;
	}

	public void setIdRequest(BigInteger idRequest) {
		this.idRequest = idRequest;
	}

	public String getIndirizzoIP() {
		return indirizzoIP;
	}

	public void setIndirizzoIP(String indirizzoIP) {
		this.indirizzoIP = indirizzoIP;
	}

	public String getSessionID() {
		return sessionID;
	}

	public void setSessionID(String sessionID) {
		this.sessionID = sessionID;
	}

	public String getTipoRequest() {
		return tipoRequest;
	}

	public void setTipoRequest(String tipoRequest) {
		this.tipoRequest = tipoRequest;
	}

	public String getApplicativo() {
		return applicativo;
	}

	public void setApplicativo(String applicativo) {
		this.applicativo = applicativo;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getQueryString() {
		return queryString;
	}

	public void setQueryString(String queryString) {
		this.queryString = queryString;
	}

	public String getParam() {
		return param;
	}

	public void setParam(String param) {
		this.param = param;
	}
	
	public String getError() {
		return error;
	}
	
	public void setError(String error) {
		this.error = error;
	}

	public String getRequest() {
		return request;
	}

	public void setRequest(String request) {
		this.request = request;
	}

	public Timestamp getDataRequest() {
		return dataRequest;
	}

	public void setDataRequest(Timestamp dataRequest) {
		this.dataRequest = dataRequest;
	}

	public String getOperatoreRequest() {
		return operatoreRequest;
	}

	public void setOperatoreRequest(String operatoreRequest) {
		this.operatoreRequest = operatoreRequest;
	}

	public String getTemplate() {
		return template;
	}

	public void setTemplate(String template) {
		this.template = template;
	}

	public String getDbSchemaCodSocieta() {
		return dbSchemaCodSocieta;
	}

	public void setDbSchemaCodSocieta(String dbSchemaCodSocieta) {
		this.dbSchemaCodSocieta = dbSchemaCodSocieta;
	}

	public String getSezioneApplicativa() {
		return sezioneApplicativa;
	}

	public void setSezioneApplicativa(String sezioneApplicativa) {
		this.sezioneApplicativa = sezioneApplicativa;
	}

	public String getCanalePagamento() {
		return canalePagamento;
	}

	public void setCanalePagamento(String canalePagamento) {
		this.canalePagamento = canalePagamento;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserProfile() {
		return userProfile;
	}

	public void setUserProfile(String userProfile) {
		this.userProfile = userProfile;
	}

	public String getCodiceSocieta() {
		return codiceSocieta;
	}

	public void setCodiceSocieta(String codiceSocieta) {
		this.codiceSocieta = codiceSocieta;
	}

	public String getCodiceUtente() {
		return codiceUtente;
	}

	public void setCodiceUtente(String codiceUtente) {
		this.codiceUtente = codiceUtente;
	}

	public String getChiaveEnte() {
		return chiaveEnte;
	}

	public void setChiaveEnte(String chiaveEnte) {
		this.chiaveEnte = chiaveEnte;
	}

	public String getDescrizioneEnte() {
		return descrizioneEnte;
	}

	public void setDescrizioneEnte(String descrizioneEnte) {
		this.descrizioneEnte = descrizioneEnte;
	}

	public String getSiglaProvinciaEnte() {
		return siglaProvinciaEnte;
	}

	public void setSiglaProvinciaEnte(String siglaProvinciaEnte) {
		this.siglaProvinciaEnte = siglaProvinciaEnte;
	}

	public String getCodiceFiscale() {
		return codiceFiscale;
	}

	public void setCodiceFiscale(String codiceFiscale) {
		this.codiceFiscale = codiceFiscale;
	}

	public String getNumeroDocumento() {
		return numeroDocumento;
	}

	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}

	public String getNumeroBollettino() {
		return numeroBollettino;
	}

	public void setNumeroBollettino(String numeroBollettino) {
		this.numeroBollettino = numeroBollettino;
	}

	public String getBelfioreRequest() {
		return belfioreRequest;
	}

	public void setBelfioreRequest(String belfioreRequest) {
		this.belfioreRequest = belfioreRequest;
	}

	public String getComuneRequest() {
		return comuneRequest;
	}

	public void setComuneRequest(String comuneRequest) {
		this.comuneRequest = comuneRequest;
	}

	public String getSiglaProvinciaRequest() {
		return siglaProvinciaRequest;
	}

	public void setSiglaProvinciaRequest(String siglaProvinciaRequest) {
		this.siglaProvinciaRequest = siglaProvinciaRequest;
	}

	public boolean setSezioneApplicativaCheckEmpty(String sezioneApplicativa) {
		if(sezioneApplicativa != null && sezioneApplicativa.trim().length() > 0) {
			if(this.sezioneApplicativa == null || this.sezioneApplicativa.trim().length() == 0) {
				this.sezioneApplicativa = sezioneApplicativa.trim();
				return true;
			}
		}
		return false;
	}

	public boolean setUserNameCheckEmpty(String userName) {
		if(userName != null && userName.trim().length() > 0) {
			if(this.userName == null || this.userName.trim().length() == 0) {
				this.userName = userName.trim();
				return true;
			}
		}
		return false;
	}

	public boolean setUserProfileCheckEmpty(String userProfile) {
		if(userProfile != null && userProfile.trim().length() == 4) {
			if(this.userProfile == null || this.userProfile.trim().length() == 0) {
				this.userProfile = userProfile.trim();
				return true;
			}
		}
		return false;
	}

	public boolean setCodiceSocietaCheckEmpty(String codiceSocieta) {
		if(codiceSocieta != null && codiceSocieta.trim().length() == 5) {
			if(this.codiceSocieta == null || this.codiceSocieta.trim().length() == 0) {
				this.codiceSocieta = codiceSocieta.trim();
				return true;
			}
		}
		return false;
	}

	public boolean setCodiceUtenteCheckEmpty(String codiceUtente) {
		if(codiceUtente != null && codiceUtente.trim().length() == 5) {
			if(this.codiceUtente == null || this.codiceUtente.trim().length() == 0) {
				this.codiceUtente = codiceUtente.trim();
				return true;
			}
		}
		return false;
	}

	public boolean setChiaveEnteCheckEmpty(String chiaveEnte) {
		if(chiaveEnte != null && chiaveEnte.trim().length() == 10 && chiaveEnte.substring(0,3).equals("ANE")) {
			if(this.chiaveEnte == null || this.chiaveEnte.trim().length() == 0) {
				this.chiaveEnte = chiaveEnte.trim();
				return true;
			}
		}
		return false;
	}

	public boolean setDescrizioneEnteCheckEmpty(String descrizioneEnte) {
		if(descrizioneEnte != null && descrizioneEnte.trim().length() > 0) {
			if(this.descrizioneEnte == null || this.descrizioneEnte.trim().length() == 0) {
				this.descrizioneEnte = descrizioneEnte.trim();
				return true;
			}
		}
		return false;
	}

	public boolean setSiglaProvinciaEnteCheckEmpty(String siglaProvinciaEnte) {
		if(siglaProvinciaEnte != null && siglaProvinciaEnte.trim().length() == 2) {
			if(this.siglaProvinciaEnte == null || this.siglaProvinciaEnte.trim().length() == 0) {
				this.siglaProvinciaEnte = siglaProvinciaEnte.trim();
				return true;
			}
		}
		return false;
	}

	public boolean setCodiceFiscaleCheckEmpty(String codiceFiscale) {
		if(codiceFiscale != null && codiceFiscale.trim().length() > 0) {
			if(this.codiceFiscale == null || this.codiceFiscale.trim().length() == 0) {
				this.codiceFiscale = codiceFiscale.trim();
				return true;
			}
		}
		return false;
	}

	public boolean setNumeroDocumentoCheckEmpty(String numeroDocumento) {
		if(numeroDocumento != null && numeroDocumento.trim().length() > 0) {
			if(this.numeroDocumento == null || this.numeroDocumento.trim().length() == 0) {
				this.numeroDocumento = numeroDocumento.trim();
				return true;
			}
		}
		return false;
	}

	public boolean setNumeroBollettinoCheckEmpty(String numeroBollettino) {
		if(numeroBollettino != null && numeroBollettino.trim().length() > 0) {
			if(this.numeroBollettino == null || this.numeroBollettino.trim().length() == 0) {
				this.numeroBollettino = numeroBollettino.trim();
				return true;
			}
		}
		return false;
	}

	public boolean setBelfioreRequestCheckEmpty(String belfioreRequest) {
		if(belfioreRequest != null && belfioreRequest.trim().length() == 4) {
			if(this.belfioreRequest == null || this.belfioreRequest.trim().length() == 0) {
				this.belfioreRequest = belfioreRequest.trim();
				return true;
			}
		}
		return false;
	}

	public boolean setComuneRequestCheckEmpty(String comuneRequest) {
		if(comuneRequest != null && comuneRequest.trim().length() > 0) {
			if(this.comuneRequest == null || this.comuneRequest.trim().length() == 0) {
				this.comuneRequest = comuneRequest.trim();
				return true;
			}
		}
		return false;
	}

	public boolean setSiglaProvinciaRequestCheckEmpty(String siglaProvinciaRequest) {
		if(siglaProvinciaRequest != null && siglaProvinciaRequest.trim().length() == 2) {
			if(this.siglaProvinciaRequest == null || this.siglaProvinciaRequest.trim().length() == 0) {
				this.siglaProvinciaRequest = siglaProvinciaRequest.trim();
				return true;
			}
		}
		return false;
	}
	
	public boolean setChiaveTransazioneCheckEmpty(String chiaveTransazione) {
		if(chiaveTransazione != null && chiaveTransazione.trim().length() > 0) {
			if(this.chiaveTransazione == null || this.chiaveTransazione.trim().length() == 0) {
				this.chiaveTransazione = chiaveTransazione.trim();
				return true;
			}
		}
		return false;
	}

	public boolean setNumeroIUVCheckEmpty(String numeroIUV) {
		if(numeroIUV != null && numeroIUV.trim().length() > 0) {
			if(this.numeroIUV == null || this.numeroIUV.trim().length() == 0) {
				this.numeroIUV = numeroIUV.trim();
				return true;
			}
		}
		return false;
	}

	public String getChiaveTransazione() {
		return chiaveTransazione;
	}

	public void setChiaveTransazione(String chiaveTransazione) {
		this.chiaveTransazione = chiaveTransazione;
	}

	public String getNumeroIUV() {
		return numeroIUV;
	}

	public void setNumeroIUV(String numeroIUV) {
		this.numeroIUV = numeroIUV;
	}

	public int getRowsPerPage() {
		return rowsPerPage;
	}

	public void setRowsPerPage(int rowsPerPage) {
		this.rowsPerPage = rowsPerPage;
	}

	public int getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public String getDataInizio_da() {
		return dataInizio_da;
	}

	public void setDataInizio_da(String dataInizio_da) {
		this.dataInizio_da = dataInizio_da;
	}

	public String getDataFine_a() {
		return dataFine_a;
	}

	public void setDataFine_a(String dataFine_a) {
		this.dataFine_a = dataFine_a;
	}

	public String getTagSuffissoTabella() {
		return tagSuffissoTabella;
	}

	public void setTagSuffissoTabella(String tagSuffissoTabella) {
		this.tagSuffissoTabella = tagSuffissoTabella;
	}

	public LogRequest() {
		super();
	}

	public String toString() {
		return "LogRequest ["
				+ "idRequest=" + idRequest
				+ ", indirizzoIP=" + indirizzoIP
				+ ", sessionID=" + sessionID
				+ ", tipoRequest=" + tipoRequest
				+ ", applicativo=" + applicativo
				+ ", action=" + action
				+ ", queryString=" + queryString
				+ ", param=" + param
				+ ", request=" + request
				+ ", dataRequest=" + dataRequest
				+ ", operatoreRequest="	+ operatoreRequest
				+ ", template="	+ template
				+ ", dbSchemaCodSocieta=" + dbSchemaCodSocieta
				+ ", sezioneApplicativa=" + sezioneApplicativa
				+ ", canalePagamento=" + canalePagamento
				+ ", userName="	+ userName
				+ ", userProfile=" + userProfile
				+ ", codiceSocieta=" + codiceSocieta
				+ ", codiceUtente=" + codiceUtente
				+ ", chiaveEnte=" + chiaveEnte
				+ ", descrizioneEnte=" + descrizioneEnte
				+ ", siglaProvinciaEnte=" + siglaProvinciaEnte
				+ ", codiceFiscale=" + codiceFiscale
				+ ", numeroDocumento=" + numeroDocumento
				+ ", numeroBollettino=" + numeroBollettino
				+ ", belfioreRequest=" + belfioreRequest
				+ ", comuneRequest=" + comuneRequest
				+ ", siglaProvinciaRequest=" + siglaProvinciaRequest
				+ ", chiaveTransazione=" + chiaveTransazione
				+ ", numeroIUV=" + numeroIUV
				+ ", tagSuffissoTabella=" + tagSuffissoTabella
				+ "]";
	}

}