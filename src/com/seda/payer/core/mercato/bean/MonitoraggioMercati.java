package com.seda.payer.core.mercato.bean;

import com.seda.data.dao.ModelAttributes;
import java.util.Calendar;

public class MonitoraggioMercati extends ModelAttributes implements java.io.Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String codUt;
    private String codiceTariffa;
    private String codiceAutorizzazione;
    private String codiceSocieta;
    private String descrizioneEnte;
    private String codiceKeyMercato;
    private String codiceMercato;
    private String descrizioneMercato;
    private String codiceKeyPiazzola;
    private String codicePiazzola;
    private String descrizionePiazzola;
    private String nominativoAutorizzazione;
    private String codiceFiscaleAutorizzazione;
    private String tipologiaBanco;
    private String periodoGiornaliero;
    private String giornoSettimana;
    private String codicePrenotazione;
    private Calendar dataOraPagamento;
    private Calendar dataPrenotazione;
    private Calendar dataInizio;
    private Calendar dataFine;
    private float importoDovuto;
    private float importoTari;
    private float importoCosap;
    private float importoCompenso;
    private String pagato;

    public MonitoraggioMercati() {
    }

    public MonitoraggioMercati(
           String codUt,
           String codiceTariffa,
           String codiceAutorizzazione,
           String codiceSocieta,
           String descrizioneEnte,
           String codiceKeyMercato,
           String codiceMercato,
           String descrizioneMercato,
           String codiceKeyPiazzola,
           String codicePiazzola,
           String descrizionePiazzola,
           String nominativoAutorizzazione,
           String codiceFiscaleAutorizzazione,
           String tipologiaBanco,
           String periodoGiornaliero,
           String giornoSettimana,
           String codicePrenotazione,
           Calendar dataOraPagamento,
           Calendar dataPrenotazione,
           Calendar dataInizio,
           Calendar dataFine,
           float importoDovuto,
           float importoTari,
           float importoCosap,
           float importoCompenso,
           java.lang.String pagato) {
           this.codUt = codUt;
           this.codiceTariffa = codiceTariffa;
           this.codiceAutorizzazione = codiceAutorizzazione;
           this.codiceSocieta = codiceSocieta;
           this.descrizioneEnte = descrizioneEnte;
           this.codiceKeyMercato = codiceKeyMercato;
           this.codiceMercato = codiceMercato;
           this.descrizioneMercato = descrizioneMercato;
           this.codiceKeyPiazzola = codiceKeyPiazzola;
           this.codicePiazzola = codicePiazzola;
           this.descrizionePiazzola = descrizionePiazzola;
           this.nominativoAutorizzazione = nominativoAutorizzazione;
           this.codiceFiscaleAutorizzazione = codiceFiscaleAutorizzazione;
           this.tipologiaBanco = tipologiaBanco;
           this.periodoGiornaliero = periodoGiornaliero;
           this.giornoSettimana = giornoSettimana;
           this.codicePrenotazione = codicePrenotazione;
           this.dataOraPagamento = dataOraPagamento; 
           this.dataPrenotazione = dataPrenotazione;
           this.dataInizio = dataInizio;
           this.dataFine = dataFine;
           this.importoDovuto = importoDovuto;
           this.importoTari = importoTari;
           this.importoCosap = importoCosap;
           this.importoCompenso = importoCompenso;
           this.pagato = pagato;
    }

	@Override
	public String toString() {
		return "MonitoraggioMercati [codUt=" + codUt 
		        + "codiceTariffa=" + codiceTariffa
		        + ", codiceAutorizzazione=" + codiceAutorizzazione
				+ ", codiceSocieta=" + codiceSocieta 
				+ ", descrizioneEnte=" + descrizioneEnte 
				+ ", codiceKeyMercato=" + codiceKeyMercato
				+ ", codiceMercato=" + codiceMercato  
				+ ", descrizioneMercato=" + descrizioneMercato
				+ ", codiceKeyPiazzola=" + codiceKeyPiazzola
				+ ", codicePiazzola=" + codicePiazzola
				+ ", descrizionePiazzola=" + descrizionePiazzola
				+ ", nominativoAutorizzazione=" + nominativoAutorizzazione
				+ ", tipologiaBanco=" + tipologiaBanco
				+ ", periodoGiornaliero=" + periodoGiornaliero
				+ ", giornoSettimana=" + giornoSettimana
				+ ", dataOraPagamento=" + dataOraPagamento
				+ ", codicePrenotazione=" + codicePrenotazione
				+ ", dataPrenotazione=" + dataPrenotazione
				+ ", dataInizio=" + dataInizio
				+ ", dataFine=" + dataFine
				+ ", importoDovuto=" + importoDovuto
				+ ", importoTari=" + importoTari
				+ ", importoCosap=" + importoCosap
				+ ", importoCompenso=" + importoCompenso
				+ ", pagato=" + pagato
				+ "]";
	}
    
    
	public String getCodicePrenotazione() {
		return codicePrenotazione;
	}
	
	public void setCodicePrenotazione(String codicePrenotazione) {
		this.codicePrenotazione = codicePrenotazione;
	}
	
    public Calendar getDataPrenotazione() {
		return dataPrenotazione;
	}

	public void setDataPrenotazione(Calendar dataPrenotazione) {
		this.dataPrenotazione = dataPrenotazione;
	}

	public String getCodiceSocieta() {
		return codiceSocieta;
	}

	public void setCodiceSocieta(String codiceSocieta) {
		this.codiceSocieta = codiceSocieta;
	}

	public String getCodiceTariffa(){
		return codiceTariffa;
	}
	
	public void setCodiceTariffa(String codiceTariffa) {
		this.codiceTariffa = codiceTariffa;
	}
	
	public String getCodiceKeyMercato() {
		return codiceKeyMercato;
	}

	public void setCodiceKeyMercato(String codiceKeyMercato) {
		this.codiceKeyMercato = codiceKeyMercato;
	}

	public String getCodiceKeyPiazzola() {
		return codiceKeyPiazzola;
	}

	public void setCodiceKeyPiazzola(String codiceKeyPiazzola) {
		this.codiceKeyPiazzola = codiceKeyPiazzola;
	}

	public String getPagato() {
		return pagato;
	}

	public void setPagato(String pagato) {
		this.pagato = pagato;
	}

	public String getTipologiaBanco() {
		return tipologiaBanco;
	}

	public void setTipologiaBanco(String tipologiaBanco) {
		this.tipologiaBanco = tipologiaBanco;
	}

	public String getPeriodoGiornaliero() {
		return periodoGiornaliero;
	}

	public void setPeriodoGiornaliero(String periodoGiornaliero) {
		this.periodoGiornaliero = periodoGiornaliero;
	}

	public String getGiornoSettimana() {
		return giornoSettimana;
	}

	public void setGiornoSettimana(String giornoSettimana) {
		this.giornoSettimana = giornoSettimana;
	}

	public String getNominativoAutorizzazione() {
		return nominativoAutorizzazione;
	}

	public void setNominativoAutorizzazione(String nominativoAutorizzazione) {
		this.nominativoAutorizzazione = nominativoAutorizzazione;
	}

	public String getCodiceFiscaleAutorizzazione() {
		return codiceFiscaleAutorizzazione;
	}

	public void setCodiceFiscaleAutorizzazione(String codiceFiscaleAutorizzazione) {
		this.codiceFiscaleAutorizzazione = codiceFiscaleAutorizzazione;
	}

	public Calendar getDataInizio() {
		return dataInizio;
	}

	public void setDataInizio(Calendar dataInizio) {
		this.dataInizio = dataInizio;
	}

	public Calendar getDataFine() {
		return dataFine;
	}

	public void setDataFine(Calendar dataFine) {
		this.dataFine = dataFine;
	}

	public Calendar getDataOraPagamento() {
		return dataOraPagamento;
	}

	public void setDataOraPagamento(Calendar dataOraPagamento) {
		this.dataOraPagamento = dataOraPagamento;
	}

	public float getImportoDovuto() {
		return importoDovuto;
	}

	public void setImportoDovuto(float importoDovuto) {
		this.importoDovuto = importoDovuto;
	}

	public float getImportoTari() {
		return importoTari;
	}

	public void setImportoTari(float importoTari) {
		this.importoTari = importoTari;
	}

	public float getImportoCosap() {
		return importoCosap;
	}

	public void setImportoCosap(float importoCosap) {
		this.importoCosap = importoCosap;
	}

	public float getImportoCompenso() {
		return importoCompenso;
	}

	public void setImportoCompenso(float importoCompenso) {
		this.importoCompenso = importoCompenso;
	}

	/**
     * Gets the codUt value for this Monitoraggio.
     * 
     * @return codUt
     */
    public String getCodUt() {
        return codUt;
    }


    /**
     * Sets the codUt value for this Monitoraggio.
     * 
     * @param codUt
     */
    public void setCodUt(String codUt) {
        this.codUt = codUt;
    }


    /**
     * Gets the codiceAutorizzazione value for this Monitoraggio.
     * 
     * @return codiceAutorizzazione
     */
    public String getCodiceAutorizzazione() {
        return codiceAutorizzazione;
    }


    /**
     * Sets the codiceAutorizzazione value for this Monitoraggio.
     * 
     * @param codiceAutorizzazione
     */
    public void setCodiceAutorizzazione(String codiceAutorizzazione) {
        this.codiceAutorizzazione = codiceAutorizzazione;
    }


    /**
     * Gets the descrizioneEnte value for this Monitoraggio.
     * 
     * @return descrizioneEnte
     */
    public String getDescrizioneEnte() {
        return descrizioneEnte;
    }


    /**
     * Sets the descrizioneEnte value for this Monitoraggio.
     * 
     * @param descrizioneEnte
     */
    public void setDescrizioneEnte(String descrizioneEnte) {
        this.descrizioneEnte = descrizioneEnte;
    }


    /**
     * Gets the codiceMercato value for this Monitoraggio.
     * 
     * @return codiceMercato
     */
    public String getCodiceMercato() {
        return codiceMercato;
    }


    /**
     * Sets the codiceMercato value for this Monitoraggio.
     * 
     * @param codiceMercato
     */
    public void setCodiceMercato(String codiceMercato) {
        this.codiceMercato = codiceMercato;
    }


    /**
     * Gets the descrizioneMercato value for this Monitoraggio.
     * 
     * @return descrizioneMercato
     */
    public String getDescrizioneMercato() {
        return descrizioneMercato;
    }


    /**
     * Sets the descrizioneMercato value for this Monitoraggio.
     * 
     * @param descrizioneMercato
     */
    public void setDescrizioneMercato(String descrizioneMercato) {
        this.descrizioneMercato = descrizioneMercato;
    }


    /**
     * Gets the codicePiazzola value for this Monitoraggio.
     * 
     * @return codicePiazzola
     */
    public String getCodicePiazzola() {
        return codicePiazzola;
    }


    /**
     * Sets the codicePiazzola value for this Monitoraggio.
     * 
     * @param codicePiazzola
     */
    public void setCodicePiazzola(String codicePiazzola) {
        this.codicePiazzola = codicePiazzola;
    }


    /**
     * Gets the descrizionePiazzola value for this Monitoraggio.
     * 
     * @return descrizionePiazzola
     */
    public String getDescrizionePiazzola() {
        return descrizionePiazzola;
    }


    /**
     * Sets the descrizionePiazzola value for this Monitoraggio.
     * 
     * @param descrizionePiazzola
     */
    public void setDescrizionePiazzola(String descrizionePiazzola) {
        this.descrizionePiazzola = descrizionePiazzola;
    }

//Termine della classe
}
