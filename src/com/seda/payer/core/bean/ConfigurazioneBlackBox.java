package com.seda.payer.core.bean;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConfigurazioneBlackBox implements Serializable {

	private static final long serialVersionUID = 1L;

	private String codiceEnte;
	private String codiceIdentificativoDominio;
	private String codiceApplicationCode;
	private String codiceSegregazione;
	private String auxDigit;
	private String flagIuv;
	private String urlFtp;
	private String usrFtp;
	private String passwordFtp;
	private String directoryFtpDownload;
	private String directoryFtpUpload;
	private String pathLocaleInput;
	private String pathLocaleScarti;
	private String pathLocaleOutput;
	private String codiceServizio;
	private String flagPoste;
	private String emailPoste;

	public ConfigurazioneBlackBox() {
	}

	public ConfigurazioneBlackBox(ResultSet data) throws SQLException {
		if (data == null)
			return;

		setCodiceEnte(data.getString("CNF_CCNFCENT"));
		setCodiceIdentificativoDominio(data.getString("CNF_CCNFCIDD"));
		setCodiceApplicationCode(data.getString("CNF_CCNFAPPC"));
		setCodiceSegregazione(data.getString("CNF_CCNFSEGC"));
		setAuxDigit(data.getString("CNF_CCNFAXDC"));
		setFlagIuv(data.getString("CNF_CCNFFIUV"));
		setUrlFtp(data.getString("CNF_CCNFURLF"));
		setUsrFtp(data.getString("CNF_CCNFUSRF"));
		setPasswordFtp(data.getString("CNF_CCNFPWDF"));
		setDirectoryFtpDownload(data.getString("CNF_CCNFDIRD"));
		setDirectoryFtpUpload(data.getString("CNF_CCNFDIRU"));
		setPathLocaleInput(data.getString("CNF_CCNFINPT"));
		setPathLocaleScarti(data.getString("CNF_CCNFSCRT"));
		setPathLocaleOutput(data.getString("CNF_CCNFOUTP"));
		setCodiceServizio(data.getString("CNF_CCNFCODS"));
		setFlagPoste(data.getString("CNF_CCNFFPST"));
		setEmailPoste(data.getString("CNF_CCNFEPST"));
	}

	public ConfigurazioneBlackBox(String codiceEnte, String codiceIdentificativoDominio, String codiceApplicationCode,
			String codiceSegregazione, String auxDigit, String flagIuv, String urlFtp, String usrFtp,
			String passwordFtp, String directoryFtpDownload, String directoryFtpUpload, String pathLocaleInput,
			String pathLocaleScarti, String pathLocaleOutput, String codiceServizio, String flagPoste,
			String emailPoste) {
		super();
		this.codiceEnte = codiceEnte;
		this.codiceIdentificativoDominio = codiceIdentificativoDominio;
		this.codiceApplicationCode = codiceApplicationCode;
		this.codiceSegregazione = codiceSegregazione;
		this.auxDigit = auxDigit;
		this.flagIuv = flagIuv;
		this.urlFtp = urlFtp;
		this.usrFtp = usrFtp;
		this.passwordFtp = passwordFtp;
		this.directoryFtpDownload = directoryFtpDownload;
		this.directoryFtpUpload = directoryFtpUpload;
		this.pathLocaleInput = pathLocaleInput;
		this.pathLocaleScarti = pathLocaleScarti;
		this.pathLocaleOutput = pathLocaleOutput;
		this.codiceServizio = codiceServizio;
		this.flagPoste = flagPoste;
		this.emailPoste = emailPoste;
	}

	public String getCodiceEnte() {
		return codiceEnte;
	}

	public void setCodiceEnte(String codiceEnte) {
		this.codiceEnte = codiceEnte;
	}

	public String getCodiceIdentificativoDominio() {
		return codiceIdentificativoDominio;
	}

	public void setCodiceIdentificativoDominio(String codiceIdentificativoDominio) {
		this.codiceIdentificativoDominio = codiceIdentificativoDominio;
	}

	public String getCodiceApplicationCode() {
		return codiceApplicationCode;
	}

	public void setCodiceApplicationCode(String codiceApplicationCode) {
		this.codiceApplicationCode = codiceApplicationCode;
	}

	public String getCodiceSegregazione() {
		return codiceSegregazione;
	}

	public void setCodiceSegregazione(String codiceSegregazione) {
		this.codiceSegregazione = codiceSegregazione;
	}

	public String getAuxDigit() {
		return auxDigit;
	}

	public void setAuxDigit(String auxDigit) {
		this.auxDigit = auxDigit;
	}

	public String getFlagIuv() {
		return flagIuv;
	}

	public void setFlagIuv(String flagIuv) {
		this.flagIuv = flagIuv;
	}

	public String getUrlFtp() {
		return urlFtp;
	}

	public void setUrlFtp(String urlFtp) {
		this.urlFtp = urlFtp;
	}

	public String getUsrFtp() {
		return usrFtp;
	}

	public void setUsrFtp(String usrFtp) {
		this.usrFtp = usrFtp;
	}

	public String getPasswordFtp() {
		return passwordFtp;
	}

	public void setPasswordFtp(String passwordFtp) {
		this.passwordFtp = passwordFtp;
	}

	public String getDirectoryFtpDownload() {
		return directoryFtpDownload;
	}

	public void setDirectoryFtpDownload(String directoryFtpDownload) {
		this.directoryFtpDownload = directoryFtpDownload;
	}

	public String getDirectoryFtpUpload() {
		return directoryFtpUpload;
	}

	public void setDirectoryFtpUpload(String directoryFtpUpload) {
		this.directoryFtpUpload = directoryFtpUpload;
	}

	public String getPathLocaleInput() {
		return pathLocaleInput;
	}

	public void setPathLocaleInput(String pathLocaleInput) {
		this.pathLocaleInput = pathLocaleInput;
	}

	public String getPathLocaleScarti() {
		return pathLocaleScarti;
	}

	public void setPathLocaleScarti(String pathLocaleScarti) {
		this.pathLocaleScarti = pathLocaleScarti;
	}

	public String getPathLocaleOutput() {
		return pathLocaleOutput;
	}

	public void setPathLocaleOutput(String pathLocaleOutput) {
		this.pathLocaleOutput = pathLocaleOutput;
	}

	public String getCodiceServizio() {
		return codiceServizio;
	}

	public void setCodiceServizio(String codiceServizio) {
		this.codiceServizio = codiceServizio;
	}

	public String getFlagPoste() {
		return flagPoste;
	}

	public void setFlagPoste(String flagPoste) {
		this.flagPoste = flagPoste;
	}

	public String getEmailPoste() {
		return emailPoste;
	}

	public void setEmailPoste(String emailPoste) {
		this.emailPoste = emailPoste;
	}

}
