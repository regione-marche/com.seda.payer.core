package com.seda.payer.core.bean;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;

public class TestataComunicazioneImpostaSoggiorno implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String chiaveTestataComunicazione;
	private String chiaveAnagraficaStrutturaRicettiva;
	private String codiceSocieta;
	private String codiceUtente;
	private String chiaveEnte;
	private Calendar dataInserimentoComunicazione;
	private Date dataInizioComunicazione;
	private Date dataFineComunicazione;
	private Integer numeroGiorniPeriodoPermanenzaTotale;
	private String tipoComunicazione;
	private String chiaveTariffaImpostaSoggiorno;
	private String noteAggiuntive;
	private Date dataScadenzaComunicazione;
	private String statoComunicazione;
	private String modalitaPagamento;
	private String codiceRID;
	private String numeroDocumentoGestionaleEntrate;
	private String codiceBollettino;
	private String statoDocumento;
	private Date dataPagamento;
	private String usernameUtenteUltimoAggiornamento;
	private Calendar dataUltimoAggiornamento;
	private String operatoreUltimoAggiornamento;
	private Calendar dataConfermaComunicazione;
	private String codiceFreccia;
	//inizio LP PG190010_002_LP
	private String descrizioneEnte;
	private String tipoCC;
	private String numeroCC;
	private String intestazioneCC;
	private String autorizzazioneCC;
	private String codiceFiscaleEnte;
	private String codiceCBill;
	private String barcodePagoPA;
	private String qrCodePagoPA;
	private String causaleDocumento;
	private String descrizioneUfficio;
	private String descrizioneTipoServizio;
	private String descrizioneImpostaServizio;
	private String numeroAvvisoPagoPA;
	private String codiceIUV;
	//fine LP PG190010_002_LP
	//PG190300 - inizio
	private String chiaveAnagraficaStrutturaRicettivaPrincipale;
	private Date dataLimiteComunicazione;
	private String codiceBollettinoCumulativo;
	private String noteOperatore;
	//PG190300 - fine
	private String flagAlloggio;	//PG190330 GG
	//inizio LP PG200230
    private String barcodePagoPACumulativo;
    private String qrCodePagoPACumulativo;
    private String numeroAvvisoPagoPACumulativo;
    private String codiceIUVCumulativo;
	//fine LP PG200230
	private String operatoreInserimentoComunicazione;
	
	public TestataComunicazioneImpostaSoggiorno() {}
	
	public TestataComunicazioneImpostaSoggiorno(ResultSet data) throws SQLException
    {
    	if (data == null)
    		return;
    	
    	setChiaveTestataComunicazione(data.getString("SCT_KSCTKSCT"));
    	setChiaveAnagraficaStrutturaRicettiva(data.getString("SCT_KSANKSAN"));
    	setCodiceSocieta(data.getString("SCT_CSOCCSOC"));
    	setCodiceUtente(data.getString("SCT_CUTECUTE"));
    	setChiaveEnte(data.getString("SCT_KANEKENT"));
    	Calendar calInse = Calendar.getInstance();
    	calInse.setTimeInMillis(data.getTimestamp("SCT_GSCTGINS").getTime());
    	setDataInserimentoComunicazione(calInse);
    	setDataInizioComunicazione(data.getDate("SCT_GSCTGINI"));
    	setDataFineComunicazione(data.getDate("SCT_GSCTGFIN"));
    	setNumeroGiorniPeriodoPermanenzaTotale(data.getInt("SCT_NSCTNPER"));
    	setTipoComunicazione(data.getString("SCT_FSCTFTIP"));
    	setChiaveTariffaImpostaSoggiorno(data.getString("SCT_KSTFKSTF"));
    	setNoteAggiuntive(data.getString("SCT_DSCTNOTE"));
    	setDataScadenzaComunicazione(data.getDate("SCT_GSCTSCAD"));
    	setStatoComunicazione(data.getString("SCT_FSCTSTAT"));
    	setModalitaPagamento(data.getString("SCT_FSCTTPAG"));
    	setCodiceRID(data.getString("SCT_CSCTCRID"));
    	setNumeroDocumentoGestionaleEntrate(data.getString("SCT_NSCTNDOC"));
    	setCodiceBollettino(data.getString("SCT_CSCTCBOL"));
    	setStatoDocumento(data.getString("SCT_FSCTFPAG"));
    	setDataPagamento(data.getDate("SCT_GSCTDPAG"));
    	setUsernameUtenteUltimoAggiornamento(data.getString("SCT_CSCTUSER"));
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(data.getTimestamp("SCT_GSCTGAGG").getTime());
        setDataUltimoAggiornamento(cal);
        setOperatoreUltimoAggiornamento(data.getString("SCT_CSCTCOPE"));
        Calendar calConf = Calendar.getInstance();
        calConf.setTimeInMillis(data.getTimestamp("SCT_GSCTCONF").getTime());
        setDataConfermaComunicazione(calConf);
        //LP PG200230 Ho inserito la trim in tutti le data.getString da qui in avanti
        setCodiceFreccia(data.getString("SCT_CSCTCFRE").trim());
    	//inizio LP PG190010_002_LP
    	setDescrizioneEnte(data.getString("SCT_DSCTENTE").trim());
    	setTipoCC(data.getString("SCT_FSCTFTCC").trim());
    	setNumeroCC(data.getString("SCT_CSCTCNCC").trim());
    	setIntestazioneCC(data.getString("SCT_DSCTINCC").trim());
    	setAutorizzazioneCC(data.getString("SCT_DSCTAUCC").trim());
    	setCodiceFiscaleEnte(data.getString("SCT_CSCTCFIS").trim());
    	setCodiceCBill(data.getString("SCT_CSCTCBIL").trim());
    	setBarcodePagoPA(data.getString("SCT_CSCTCBAR").trim());
    	setQrCodePagoPA(data.getString("SCT_CSCTQRCD").trim());
    	setCausaleDocumento(data.getString("SCT_DSCTCCAU").trim());
    	setDescrizioneUfficio(data.getString("SCT_DSCTCUFF").trim());
    	setDescrizioneTipoServizio(data.getString("SCT_DSCTCTSR").trim());
    	setDescrizioneImpostaServizio(data.getString("SCT_DSCTCSER").trim());
    	setNumeroAvvisoPagoPA(data.getString("SCT_CSCTNAVV").trim());
    	setCodiceIUV(data.getString("SCT_CSCTCIUV").trim());
    	//fine LP PG190010_002_LP
    	//PG190300 - inizio
    	setDataLimiteComunicazione(data.getDate("SCT_GSCTDLIM"));
    	setChiaveAnagraficaStrutturaRicettivaPrincipale(data.getString("SCT_KSANKSAN_PRI").trim());
    	setCodiceBollettinoCumulativo(data.getString("SCT_CSCTCBOL_CUM").trim());
    	setNoteOperatore(data.getString("SCT_DSCTNOTE_OPE").trim());
    	//PG190300 - fine
    	//PG190330 GG - inizio
    	try {
    		setFlagAlloggio(data.getString("FLAG_ALLOGGIO").trim());
    	} catch (Exception ex) {
    	}
    	//PG190330 GG - fine
		//inizio LP PG200230
    	try {
        	setBarcodePagoPACumulativo(data.getString("SCT_CSCTCBAR_CUM").trim());
        	setQrCodePagoPACumulativo(data.getString("SCT_CSCTQRCD_CUM").trim());
        	setNumeroAvvisoPagoPACumulativo(data.getString("SCT_CSCTNAVV_CUM").trim());
        	setCodiceIUVCumulativo(data.getString("SCT_CSCTCIUV_CUM").trim());
			setOperatoreInserimentoComunicazione(data.getString("SCT_CSCTOPINV").trim());
    	} catch (Exception ex) {
    	}
		//TODO: gestire i campi pagopa cumulativo
		//fine LP PG200230
     }
	
	public String getChiaveTestataComunicazione() {
		return chiaveTestataComunicazione;
	}
	public void setChiaveTestataComunicazione(String chiaveTestataComunicazione) {
		this.chiaveTestataComunicazione = chiaveTestataComunicazione;
	}
	public String getChiaveAnagraficaStrutturaRicettiva() {
		return chiaveAnagraficaStrutturaRicettiva;
	}
	public void setChiaveAnagraficaStrutturaRicettiva(
			String chiaveAnagraficaStrutturaRicettiva) {
		this.chiaveAnagraficaStrutturaRicettiva = chiaveAnagraficaStrutturaRicettiva;
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
	public Calendar getDataInserimentoComunicazione() {
		return dataInserimentoComunicazione;
	}
	public void setDataInserimentoComunicazione(Calendar dataInserimentoComunicazione) {
		this.dataInserimentoComunicazione = dataInserimentoComunicazione;
	}
	public Date getDataInizioComunicazione() {
		return dataInizioComunicazione;
	}
	public void setDataInizioComunicazione(Date dataInizioComunicazione) {
		this.dataInizioComunicazione = dataInizioComunicazione;
	}
	public Date getDataFineComunicazione() {
		return dataFineComunicazione;
	}
	public void setDataFineComunicazione(Date dataFineComunicazione) {
		this.dataFineComunicazione = dataFineComunicazione;
	}
	public Integer getNumeroGiorniPeriodoPermanenzaTotale() {
		return numeroGiorniPeriodoPermanenzaTotale;
	}
	public void setNumeroGiorniPeriodoPermanenzaTotale(
			Integer numeroGiorniPeriodoPermanenzaTotale) {
		this.numeroGiorniPeriodoPermanenzaTotale = numeroGiorniPeriodoPermanenzaTotale;
	}
	public String getTipoComunicazione() {
		return tipoComunicazione;
	}
	public void setTipoComunicazione(String tipoComunicazione) {
		this.tipoComunicazione = tipoComunicazione;
	}
	public String getChiaveTariffaImpostaSoggiorno() {
		return chiaveTariffaImpostaSoggiorno;
	}
	public void setChiaveTariffaImpostaSoggiorno(
			String chiaveTariffaImpostaSoggiorno) {
		this.chiaveTariffaImpostaSoggiorno = chiaveTariffaImpostaSoggiorno;
	}
	public String getNoteAggiuntive() {
		return noteAggiuntive;
	}
	public void setNoteAggiuntive(String noteAggiuntive) {
		this.noteAggiuntive = noteAggiuntive;
	}
	public Date getDataScadenzaComunicazione() {
		return dataScadenzaComunicazione;
	}
	public void setDataScadenzaComunicazione(Date dataScadenzaComunicazione) {
		this.dataScadenzaComunicazione = dataScadenzaComunicazione;
	}
	public String getStatoComunicazione() {
		return statoComunicazione;
	}
	public void setStatoComunicazione(String statoComunicazione) {
		this.statoComunicazione = statoComunicazione;
	}
	public String getModalitaPagamento() {
		return modalitaPagamento;
	}
	public void setModalitaPagamento(String modalitaPagamento) {
		this.modalitaPagamento = modalitaPagamento;
	}
	public String getCodiceRID() {
		return codiceRID;
	}
	public void setCodiceRID(String codiceRID) {
		this.codiceRID = codiceRID;
	}
	public String getNumeroDocumentoGestionaleEntrate() {
		return numeroDocumentoGestionaleEntrate;
	}
	public void setNumeroDocumentoGestionaleEntrate(
			String numeroDocumentoGestionaleEntrate) {
		this.numeroDocumentoGestionaleEntrate = numeroDocumentoGestionaleEntrate;
	}
	public String getCodiceBollettino() {
		return codiceBollettino;
	}
	public void setCodiceBollettino(String codiceBollettino) {
		this.codiceBollettino = codiceBollettino;
	}
	public String getStatoDocumento() {
		return statoDocumento;
	}
	public void setStatoDocumento(String statoDocumento) {
		this.statoDocumento = statoDocumento;
	}
	public Date getDataPagamento() {
		return dataPagamento;
	}
	public void setDataPagamento(Date dataPagamento) {
		this.dataPagamento = dataPagamento;
	}
	public void setUsernameUtenteUltimoAggiornamento(
			String usernameUtenteUltimoAggiornamento) {
		this.usernameUtenteUltimoAggiornamento = usernameUtenteUltimoAggiornamento;
	}

	public String getUsernameUtenteUltimoAggiornamento() {
		return usernameUtenteUltimoAggiornamento;
	}

	public Calendar getDataUltimoAggiornamento() {
		return dataUltimoAggiornamento;
	}
	public void setDataUltimoAggiornamento(Calendar dataUltimoAggiornamento) {
		this.dataUltimoAggiornamento = dataUltimoAggiornamento;
	}
	public String getOperatoreUltimoAggiornamento() {
		return operatoreUltimoAggiornamento;
	}
	public void setOperatoreUltimoAggiornamento(String operatoreUltimoAggiornamento) {
		this.operatoreUltimoAggiornamento = operatoreUltimoAggiornamento;
	}

	public Calendar getDataConfermaComunicazione() {
		return dataConfermaComunicazione;
	}

	public void setDataConfermaComunicazione(Calendar dataConfermaComunicazione) {
		this.dataConfermaComunicazione = dataConfermaComunicazione;
	}

	public String getCodiceFreccia() {
		return codiceFreccia;
	}

	public void setCodiceFreccia(String codiceFreccia) {
		this.codiceFreccia = codiceFreccia;
	}
	//inizio LP PG190010_002_LP
	public String getDescrizioneEnte() {
		return descrizioneEnte;
	}

	public void setDescrizioneEnte(String descrizioneEnte) {
		this.descrizioneEnte = descrizioneEnte;
	}

	public String getTipoCC() {
		return tipoCC;
	}

	public void setTipoCC(String tipoCC) {
		this.tipoCC = tipoCC;
	}

	public String getNumeroCC() {
		return numeroCC;
	}

	public void setNumeroCC(String numeroCC) {
		this.numeroCC = numeroCC;
	}

	public String getIntestazioneCC() {
		return intestazioneCC;
	}

	public void setIntestazioneCC(String intestazioneCC) {
		this.intestazioneCC = intestazioneCC;
	}

	public String getAutorizzazioneCC() {
		return autorizzazioneCC;
	}

	public void setAutorizzazioneCC(String autorizzazioneCC) {
		this.autorizzazioneCC = autorizzazioneCC;
	}

	public String getCodiceFiscaleEnte() {
		return codiceFiscaleEnte;
	}

	public void setCodiceFiscaleEnte(String codiceFiscaleEnte) {
		this.codiceFiscaleEnte = codiceFiscaleEnte;
	}

	public String getCodiceCBill() {
		return codiceCBill;
	}

	public void setCodiceCBill(String codiceCBill) {
		this.codiceCBill = codiceCBill;
	}

	public String getBarcodePagoPA() {
		return barcodePagoPA;
	}

	public void setBarcodePagoPA(String barcodePagoPA) {
		this.barcodePagoPA = barcodePagoPA;
	}

	public String getQrCodePagoPA() {
		return qrCodePagoPA;
	}

	public void setQrCodePagoPA(String qrCodePagoPA) {
		this.qrCodePagoPA = qrCodePagoPA;
	}

	public String getCausaleDocumento() {
		return causaleDocumento;
	}

	public void setCausaleDocumento(String causaleDocumento) {
		this.causaleDocumento = causaleDocumento;
	}

	public String getDescrizioneUfficio() {
		return descrizioneUfficio;
	}

	public void setDescrizioneUfficio(String descrizioneUfficio) {
		this.descrizioneUfficio = descrizioneUfficio;
	}

	public String getDescrizioneTipoServizio() {
		return descrizioneTipoServizio;
	}

	public void setDescrizioneTipoServizio(String descrizioneTipoServizio) {
		this.descrizioneTipoServizio = descrizioneTipoServizio;
	}

	public String getDescrizioneImpostaServizio() {
		return descrizioneImpostaServizio;
	}

	public void setDescrizioneImpostaServizio(String descrizioneImpostaServizio) {
		this.descrizioneImpostaServizio = descrizioneImpostaServizio;
	}

	public String getNumeroAvvisoPagoPA() {
		return numeroAvvisoPagoPA;
	}

	public void setNumeroAvvisoPagoPA(String numeroAvvisoPagoPA) {
		this.numeroAvvisoPagoPA = numeroAvvisoPagoPA;
	}

	public String getCodiceIUV() {
		return codiceIUV;
	}

	public void setCodiceIUV(String codiceIUV) {
		this.codiceIUV = codiceIUV;
	}
	//fine LP PG190010_002_LP

	//PG190300 - inizio
	public String getChiaveAnagraficaStrutturaRicettivaPrincipale() {
		return chiaveAnagraficaStrutturaRicettivaPrincipale;
	}

	public void setChiaveAnagraficaStrutturaRicettivaPrincipale(
			String chiaveAnagraficaStrutturaRicettivaPrincipale) {
		this.chiaveAnagraficaStrutturaRicettivaPrincipale = chiaveAnagraficaStrutturaRicettivaPrincipale;
	}

	public Date getDataLimiteComunicazione() {
		return dataLimiteComunicazione;
	}

	public void setDataLimiteComunicazione(Date dataLimiteComunicazione) {
		this.dataLimiteComunicazione = dataLimiteComunicazione;
	}	
	
	public String getCodiceBollettinoCumulativo() {
		return codiceBollettinoCumulativo;
	}

	public void setCodiceBollettinoCumulativo(String codiceBollettinoCumulativo) {
		this.codiceBollettinoCumulativo = codiceBollettinoCumulativo;
	}

	public String getNoteOperatore() {
		return noteOperatore;
	}

	public void setNoteOperatore(String noteOperatore) {
		this.noteOperatore = noteOperatore;
	}
	//PG190300 - fine
	
	//PG190330 GG - inizio
	public String getFlagAlloggio() {
		return flagAlloggio;
	}

	public void setFlagAlloggio(String flagAlloggio) {
		this.flagAlloggio = flagAlloggio;
	}
	//PG190330 GG - fine

	//inizio LP PG200230
	public String getBarcodePagoPACumulativo() {
		return barcodePagoPACumulativo;
	}

	public void setBarcodePagoPACumulativo(String barcodePagoPACumulativo) {
		this.barcodePagoPACumulativo = barcodePagoPACumulativo;
	}

	public String getQrCodePagoPACumulativo() {
		return qrCodePagoPACumulativo;
	}

	public void setQrCodePagoPACumulativo(String qrCodePagoPACumulativo) {
		this.qrCodePagoPACumulativo = qrCodePagoPACumulativo;
	}

	public String getNumeroAvvisoPagoPACumulativo() {
		return numeroAvvisoPagoPACumulativo;
	}

	public void setNumeroAvvisoPagoPACumulativo(String numeroAvvisoPagoPACumulativo) {
		this.numeroAvvisoPagoPACumulativo = numeroAvvisoPagoPACumulativo;
	}

	public String getCodiceIUVCumulativo() {
		return codiceIUVCumulativo;
	}

	public void setCodiceIUVCumulativo(String codiceIUVCumulativo) {
		this.codiceIUVCumulativo = codiceIUVCumulativo;
	}
	//fine LP PG200230


	public String getOperatoreInserimentoComunicazione() {
		return operatoreInserimentoComunicazione;
	}

	public void setOperatoreInserimentoComunicazione(String operatoreInserimentoComunicazione) {
		this.operatoreInserimentoComunicazione = operatoreInserimentoComunicazione;
	}
}
