package com.seda.payer.core.wallet.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;

import com.seda.data.dao.ModelAttributes;

public class Wallet  extends ModelAttributes implements Serializable {

	private static final long serialVersionUID = 1L;

	private String idWallet;				//	"BRS_KBRSKBRS" VARCHAR(18) NOT NULL,
	private String codiceSocieta;			//	"BRS_CSOCCSOC" VARCHAR(5) NOT NULL,
	private String cuteCute;				//	"BRS_CUTECUTE" VARCHAR(5) NOT NULL,
	private String chiaveEnte;				//	"BRS_KANEKENT" CHAR(10) NOT NULL,
	private String codiceAnagraficaGenitore;//	"BRS_CANAAGEN" VARCHAR(10) NOT NULL,
	private String codiceFiscaleGenitore;	//	"BRS_CFISCGEN" VARCHAR(16) NOT NULL,
	private String tipoAnagrafica;			//	"BRS_FBRSFTIP" VARCHAR(1) NOT NULL,
	private String numeroCell;				//	"BRS_CBRSCSMS" VARCHAR(10) NOT NULL,
	private String indirizzoEmail;			//	"BRS_CBRSMAIL" VARCHAR(100) NOT NULL,
	private Boolean flagAttivazione;		//	"BRS_FBRSFATT" VARCHAR(1) NOT NULL,
	private Calendar dataCaricamento;		//	"BRS_GBRSGCAR" TIMESTAMP NOT NULL,	
	private Boolean flagPrimoAccesso;		//	"BRS_FBRSPACC" CHAR(1) NOT NULL,
	private BigDecimal importoBorsellino;	//	"BRS_IBRSIMPO" DECIMAL(15 , 2) NOT NULL

	private String denominazioneGenitore;	//	"BRS_DBRSGENI" VARCHAR(61) NOT NULL,
	private String inidirizzoGenitore;		//	"BRS_DBRSINDI" VARCHAR(50) NOT NULL,
	private String denominazioneComuneGenitore;	//	"BRS_DBRSCOMU" VARCHAR(50) NOT NULL,
	private String capComuneGenitore;		//	"BRS_CBRSCCAP" VARCHAR(5) NOT NULL,
	private String provinciaGenitore;		//	"BRS_CBRSCPRV" VARCHAR(2) NOT NULL,
	private String anagraficaDaBonificare; 	//	"BRS_FBRSFANB" CHAR(1) NOT NULL
	
	private Boolean FlagEsclusioneSMSCortesia;
	private Boolean FlagEsclusioneSMSSollecito;
	private Boolean FlagEsclusioneSollecitoCartaceo;
	private Boolean FlagEsclusioneEvoluzioneIntimazione;
	private String operatore;
	private Boolean noFlagRivestizione;
	
	private String codiceRid; 				//	"BRS_CBRSCRID" CHAR(35) NOT NULL
	private BigDecimal importoOnDemand;		//	"BRS_IBRSIMPD" DECIMAL(15 , 2) NOT NULL		//PG180040
	
	
	
	public String getAnagraficaDaBonificare() {
		return anagraficaDaBonificare;
	}

	public void setAnagraficaDaBonificare(String anagraficaDaBonificare) {
		this.anagraficaDaBonificare = anagraficaDaBonificare;
	}

	public String getOperatore() {
		return operatore;
	}

	public void setOperatore(String operatore) {
		this.operatore = operatore;
	}

	public Boolean isFlagEsclusioneSMSCortesia() {
		return FlagEsclusioneSMSCortesia;
	}

	public void setFlagEsclusioneSMSCortesia(Boolean flagEsclusioneSMSCortesia) {
		FlagEsclusioneSMSCortesia = flagEsclusioneSMSCortesia;
	}

	public Boolean isFlagEsclusioneSMSSollecito() {
		return FlagEsclusioneSMSSollecito;
	}

	public void setFlagEsclusioneSMSSollecito(Boolean flagEsclusioneSMSSollecito) {
		FlagEsclusioneSMSSollecito = flagEsclusioneSMSSollecito;
	}

	public Boolean isFlagEsclusioneSollecitoCartaceo() {
		return FlagEsclusioneSollecitoCartaceo;
	}

	public void setFlagEsclusioneSollecitoCartaceo(
			Boolean flagEsclusioneSollecitoCartaceo) {
		FlagEsclusioneSollecitoCartaceo = flagEsclusioneSollecitoCartaceo;
	}

	public Boolean isFlagEsclusioneEvoluzioneIntimazione() {
		return FlagEsclusioneEvoluzioneIntimazione;
	}

	public void setFlagEsclusioneEvoluzioneIntimazione(
			Boolean flagEsclusioneEvoluzioneIntimazione) {
		FlagEsclusioneEvoluzioneIntimazione = flagEsclusioneEvoluzioneIntimazione;
	}

	public String getDenominazioneGenitore() {
		return denominazioneGenitore;
	}

	public void setDenominazioneGenitore(String denominazioneGenitore) {
		this.denominazioneGenitore = denominazioneGenitore;
	}

	public String getInidirizzoGenitore() {
		return inidirizzoGenitore;
	}

	public void setInidirizzoGenitore(String inidirizzoGenitore) {
		this.inidirizzoGenitore = inidirizzoGenitore;
	}

	public String getDenominazioneComuneGenitore() {
		return denominazioneComuneGenitore;
	}

	public void setDenominazioneComuneGenitore(String denominazioneComuneGenitore) {
		this.denominazioneComuneGenitore = denominazioneComuneGenitore;
	}

	public String getCapComuneGenitore() {
		return capComuneGenitore;
	}

	public void setCapComuneGenitore(String capComuneGenitore) {
		this.capComuneGenitore = capComuneGenitore;
	}

	public String getProvinciaGenitore() {
		return provinciaGenitore;
	}

	public void setProvinciaGenitore(String provinciaGenitore) {
		this.provinciaGenitore = provinciaGenitore;
	}

	public BigDecimal getImportoOnDemand() {
		return importoOnDemand;
	}

	public void setImportoOnDemand(BigDecimal importoOnDemand) {
		this.importoOnDemand = importoOnDemand;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Wallet(){
	};
	
	public Wallet( 
		String idWallet,
		String codiceSocieta,
		String cuteCute,
		String chiaveEnte,
		String codiceAnagraficaGenitore,
		String codiceFiscaleGenitore,
		String tipoAnagrafica,
		String numeroCell,
		String indirizzoEmail,
		Boolean flagAttivazione,
		Calendar dataCaricamento,
		BigDecimal importoBorsellino,
		Boolean flagPrimoAccesso,
		String denominazioneGenitore,
		String inidirizzoGenitore,
		String denominazioneComuneGenitore,
		String capComuneGenitore,
		String provinciaGenitore,
		Boolean  FlagEsclusioneSMSCortesia,
		Boolean  FlagEsclusioneSMSSollecito,
		Boolean  FlagEsclusioneSollecitoCartaceo,
		Boolean  FlagEsclusioneEvoluzioneIntimazione,
		String operatore,
		String anagraficaDaBonificare,
		Boolean noFlagRivestizione,
		String codiceRid,
		BigDecimal importoOnDemand
		) 
		{
		this.idWallet=idWallet;
		this.codiceSocieta=codiceSocieta;
		this.cuteCute=cuteCute;
		this.chiaveEnte=chiaveEnte;
		this.codiceAnagraficaGenitore=codiceAnagraficaGenitore;
		this.codiceFiscaleGenitore=codiceFiscaleGenitore;
		this.tipoAnagrafica=tipoAnagrafica;
		this.numeroCell=numeroCell;
		this.indirizzoEmail=indirizzoEmail;
		this.flagAttivazione=flagAttivazione;
		this.dataCaricamento=dataCaricamento;
		this.importoBorsellino=importoBorsellino;
		this.flagPrimoAccesso=flagPrimoAccesso;
		this.denominazioneGenitore=denominazioneGenitore;
		this.inidirizzoGenitore=inidirizzoGenitore;
		this.denominazioneComuneGenitore=denominazioneComuneGenitore;
		this.capComuneGenitore=capComuneGenitore;
		this.provinciaGenitore=provinciaGenitore;
		this.FlagEsclusioneSMSCortesia = FlagEsclusioneSMSCortesia;
		this.FlagEsclusioneSMSSollecito = FlagEsclusioneSMSSollecito;
		this.FlagEsclusioneSollecitoCartaceo = FlagEsclusioneSollecitoCartaceo;
		this.FlagEsclusioneEvoluzioneIntimazione = FlagEsclusioneEvoluzioneIntimazione;
		this.operatore = operatore;
		this.anagraficaDaBonificare = anagraficaDaBonificare;
		this.noFlagRivestizione = noFlagRivestizione; 
		this.codiceRid = codiceRid;
		this.importoOnDemand = importoOnDemand;
		}

	public String getIdWallet() {
		return idWallet;
	}

	public void setIdWallet(String idWallet) {
		this.idWallet = idWallet;
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

	public String getCodiceAnagraficaGenitore() {
		return codiceAnagraficaGenitore;
	}

	public void setCodiceAnagraficaGenitore(String codiceAnagraficaGenitore) {
		this.codiceAnagraficaGenitore = codiceAnagraficaGenitore;
	}

	public String getCodiceFiscaleGenitore() {
		return codiceFiscaleGenitore;
	}

	public void setCodiceFiscaleGenitore(String codiceFiscaleGenitore) {
		this.codiceFiscaleGenitore = codiceFiscaleGenitore;
	}

	public String getTipoAnagrafica() {
		return tipoAnagrafica;
	}

	public void setTipoAnagrafica(String tipoAnagrafica) {
		this.tipoAnagrafica = tipoAnagrafica;
	}

	public String getNumeroCell() {
		return numeroCell;
	}

	public void setNumeroCell(String numeroCell) {
		this.numeroCell = numeroCell;
	}

	public String getIndirizzoEmail() {
		return indirizzoEmail;
	}

	public void setIndirizzoEmail(String indirizzoEmail) {
		this.indirizzoEmail = indirizzoEmail;
	}

	public Boolean isFlagAttivazione() {
		return flagAttivazione;
	}

	public void setFlagAttivazione(Boolean flagAttivazione) {
		this.flagAttivazione = flagAttivazione;
	}

	public Calendar getDataCaricamento() {
		return dataCaricamento;
	}

	public void setDataCaricamento(Calendar dataCaricamento) {
		this.dataCaricamento = dataCaricamento;
	}

	public BigDecimal getImportoBorsellino() {
		return importoBorsellino;
	}

	public void setImportoBorsellino(BigDecimal importoBorsellino) {
		this.importoBorsellino = importoBorsellino;
	}

	
	
	public Boolean isFlagPrimoAccesso() {
		return flagPrimoAccesso;
	}

	public void setFlagPrimoAccesso(Boolean flagPrimoAccesso) {
		this.flagPrimoAccesso = flagPrimoAccesso;
	}

	public Boolean isFlagNoRivestizione() {
		return noFlagRivestizione;
	}

	public void setFlagNoRivestizione(Boolean noRivestizione) {
		this.noFlagRivestizione = noRivestizione;
	}
	
	public Boolean getNoFlagRivestizione() {
		return noFlagRivestizione;
	}

	public void setNoFlagRivestizione(Boolean noFlagRivestizione) {
		this.noFlagRivestizione = noFlagRivestizione;
	}

	public String getCodiceRid() {
		return codiceRid;
	}

	public void setCodiceRid(String codiceRid) {
		this.codiceRid = codiceRid;
	}

	public Boolean getFlagAttivazione() {
		return flagAttivazione;
	}

	public Boolean getFlagPrimoAccesso() {
		return flagPrimoAccesso;
	}

	public Boolean getFlagEsclusioneSMSCortesia() {
		return FlagEsclusioneSMSCortesia;
	}

	public Boolean getFlagEsclusioneSMSSollecito() {
		return FlagEsclusioneSMSSollecito;
	}

	public Boolean getFlagEsclusioneSollecitoCartaceo() {
		return FlagEsclusioneSollecitoCartaceo;
	}

	public Boolean getFlagEsclusioneEvoluzioneIntimazione() {
		return FlagEsclusioneEvoluzioneIntimazione;
	}

	@Override
	public String toString() {
		return "Wallet [FlagEsclusioneEvoluzioneIntimazione="
				+ FlagEsclusioneEvoluzioneIntimazione
				+ ", FlagEsclusioneSMSCortesia=" + FlagEsclusioneSMSCortesia
				+ ", FlagEsclusioneSMSSollecito=" + FlagEsclusioneSMSSollecito
				+ ", FlagEsclusioneSollecitoCartaceo="
				+ FlagEsclusioneSollecitoCartaceo + ", anagraficaDaBonificare="
				+ anagraficaDaBonificare + ", capComuneGenitore="
				+ capComuneGenitore + ", chiaveEnte=" + chiaveEnte
				+ ", codiceAnagraficaGenitore=" + codiceAnagraficaGenitore
				+ ", codiceFiscaleGenitore=" + codiceFiscaleGenitore
				+ ", codiceRid=" + codiceRid + ", codiceSocieta="
				+ codiceSocieta + ", cuteCute=" + cuteCute
				+ ", dataCaricamento=" + dataCaricamento
				+ ", denominazioneComuneGenitore="
				+ denominazioneComuneGenitore + ", denominazioneGenitore="
				+ denominazioneGenitore + ", flagAttivazione="
				+ flagAttivazione + ", flagPrimoAccesso=" + flagPrimoAccesso
				+ ", idWallet=" + idWallet + ", importoBorsellino="
				+ importoBorsellino + ", importoOnDemand=" + importoOnDemand
				+ ", indirizzoEmail=" + indirizzoEmail
				+ ", inidirizzoGenitore=" + inidirizzoGenitore
				+ ", noFlagRivestizione=" + noFlagRivestizione
				+ ", numeroCell=" + numeroCell + ", operatore=" + operatore
				+ ", provinciaGenitore=" + provinciaGenitore
				+ ", tipoAnagrafica=" + tipoAnagrafica + "]";
	}
	
}

