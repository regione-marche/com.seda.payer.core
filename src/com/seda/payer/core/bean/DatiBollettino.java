package com.seda.payer.core.bean;

import java.io.Serializable;
import java.math.BigDecimal;

public class DatiBollettino implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String codiceFreccia;
	private BigDecimal importoBollettino;
	private String numeroBollettinoCumulativo;
	//inizio LP PG200230
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
	//fine LP PG200230
	
	private String retCode;
	private String retMessage;
	
	public DatiBollettino(){}
	
	public String getCodiceFreccia() {
		return codiceFreccia;
	}
	public BigDecimal getImportoBollettino() {
		return importoBollettino;
	}
	public String getRetCode() {
		return retCode;
	}
	public String getRetMessage() {
		return retMessage;
	}
	public void setCodiceFreccia(String codiceFreccia) {
		this.codiceFreccia = codiceFreccia;
	}
	public void setImportoBollettino(BigDecimal importoBollettino) {
		this.importoBollettino = importoBollettino;
	}
	public void setRetCode(String retCode) {
		this.retCode = retCode;
	}
	public void setRetMessage(String retMessage) {
		this.retMessage = retMessage;
	}

	public String getNumeroBollettinoCumulativo() {
		return numeroBollettinoCumulativo;
	}

	public void setNumeroBollettinoCumulativo(String numeroBollettinoCumulativo) {
		this.numeroBollettinoCumulativo = numeroBollettinoCumulativo;
	}

	//inizio LP PG200230
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
	//fine LP PG200230
}
