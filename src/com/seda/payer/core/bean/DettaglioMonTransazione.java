package com.seda.payer.core.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class DettaglioMonTransazione implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5781306361745058501L;

	//DettaglioTransazione
	private String codiceTransazione; //TRA_KTRAKTRA
	private LocalDateTime dataTransazione; //TRA_GTRADTRA
	private LocalDateTime dataPagamento; //TRA_GTRADPAG
	private String identificativoUnivocoVersamento; //RPT_KRPTKIUV
	private String identificativoFlussoRendicontazioneNodo; //QUN_CQUNFLUS
	private BigDecimal importoTotaleTransazione; //TRA_ITRAITOT
	
	//DettaglioPagamentiTransazione
	private String codiceDettaglioTransazione; //TDT_KTDTKTDT
	private String codiceEnte; //ANE_CANECENT
	private String tipoUfficio; //ANE_TANETUFF
	private String codiceUfficio; //ANE_CANECUFF
	private String tipologiaServizio; //TDT_CTSECTSE
	private String descrizioneTipologiaServizio; //CFE_DCFEDINT
	private String tassonomiaPagoPA; //TDT_CTASDSPI
	private String identificativoUnivocoRiscossione; //TDT_CTDTCIUR
	private String numeroAvvisoPagoPA; //TDT_CTDTCBOL
	private BigDecimal importo; //TDT_ITDTTOTA
	private String numeroDocumento; //TDT_CTDTNDOC
	private String codiceFiscale; //TDT_CTDTCFIS
	private String denominazione; //TDT_DTDTDENO
	private String indirizzo; //TDT_DTDTINDI
	private String codiceBelfioreComune;
	private String siglaProvincia; //TDT_CTDTPROV
	private String CAP; //TDT_DTDTCCAP
	
	//PagamentoSpontaneo
	// TipoSpontaneo se TDT_TBOLTBOL="SPOM"
	private String causaleServizio; //TDT_DTDTNOTE primo elemento
	private String annoRiferimento; //TDT_DTDTNOTE secondo elemento
	private String cespite; //TDT_DTDTNOTE terzo elemento derivati da uno split sul pipe
	// TipoCDS se TDT_TBOLTBOL="CDSM"
	private String numeroVerbale; //TDT_CTDTCBOL
	private LocalDateTime dataVerbale; //TDT_GTDTSANZ
	private String targa; //TDT_DTDTTARG
	
	//DettaglioContabile
	private String importoDettaglioContabile;
	private String codiceContabilita;
	private String capitolo;
	private String annoCompetenza;
	
	private String datiContabili;
	
	private String tipoBollettino;
	
	public void setTipoBollettino(String tipoBollettino) {
		this.tipoBollettino = tipoBollettino;
	}
	
	public boolean isSPOM() {
		return tipoBollettino != null && tipoBollettino.equalsIgnoreCase("SPOM");
	}
	
	private boolean isCDSM() {
		return tipoBollettino != null && tipoBollettino.equalsIgnoreCase("CDSM");
	}
	
	private boolean isCDSA() {
		return tipoBollettino != null && tipoBollettino.equalsIgnoreCase("CDSA");
	}
	
	public boolean isCDS() {
		return isCDSM() || isCDSA();
	}
	
	public static DettaglioMonTransazione getDettaglioMonTransazione(ResultSet data) throws SQLException {
		
		DettaglioMonTransazione res = new DettaglioMonTransazione();
		//DettaglioTransazione
		res.setCodiceTransazione(data.getString("TRA_KTRAKTRA"));
		res.setDataTransazione(data.getTimestamp("TRA_GTRADTRA").toLocalDateTime());
		res.setDataPagamento(data.getTimestamp("TRA_GTRADPAG").toLocalDateTime());
		res.setIdentificativoUnivocoVersamento(data.getString("RPT_KRPTKIUV"));
		res.setIdentificativoFlussoRendicontazioneNodo(data.getString("QUN_CQUNFLUS"));
		res.setImportoTotaleTransazione(data.getBigDecimal("TRA_ITRAITOT"));
		
		//DettaglioPagamentiTransazione
		res.setCodiceDettaglioTransazione(data.getString("TDT_KTDTKTDT"));
		res.setCodiceEnte(data.getString("ANE_CANECENT"));
		res.setTipoUfficio(data.getString("ANE_TANETUFF"));
		res.setCodiceUfficio(data.getString("ANE_CANECUFF"));
		res.setTipologiaServizio(data.getString("TDT_CTSECTSE"));
		res.setDescrizioneTipologiaServizio(data.getString("TSE_DTSEDTSE"));
		res.setTassonomiaPagoPA(data.getString("TDT_CTASDSPI"));
		res.setIdentificativoUnivocoRiscossione(data.getString("TDT_CTDTCIUR"));
		res.setNumeroAvvisoPagoPA(data.getString("TDT_CTDTCBOL"));
		res.setImporto(data.getBigDecimal("TDT_ITDTTOTA"));
		res.setNumeroDocumento(data.getString("TDT_CTDTNDOC"));
		res.setCodiceFiscale(data.getString("TDT_CTDTCFIS"));
		res.setDenominazione(data.getString("TDT_DTDTDENO"));
		res.setIndirizzo(data.getString("TDT_DTDTINDI"));
		res.setCodiceBelfioreComune(data.getString("ANE_CANEBELF"));
		res.setSiglaProvincia(data.getString("TDT_CTDTPROV"));
		res.setCAP(data.getString("TDT_DTDTCCAP"));
		
		//PagamentoSpontaneo
		String tipoBollettino = data.getString("TDT_TBOLTBOL");
		
		res.setTipoBollettino(tipoBollettino);
		
		if (res.isSPOM() || res.isCDS()) {
			
			if(res.isSPOM()) { //se TipoSpontaneo
				
				String note = data.getString("TDT_DTDTNOTE");
				
				if (note != null) {
					String[] campi = note.split("\\|");
					if (campi.length >= 1) {
						res.setCausaleServizio(campi[0]);
					}
					if(campi.length >= 2){
						res.setAnnoRiferimento(campi[1]);
					}
					if(campi.length == 3){
						res.setCespite(campi[2]);
					}
				}
			} else if(res.isCDS()) { //se TipoCDS
				
				res.setNumeroVerbale(res.isCDSM() ? data.getString("TDT_CTDTCBOL") : data.getString("TDT_CTDTNDOC"));
				Timestamp dataVerbale = data.getTimestamp("TDT_GTDTSANZ");
				res.setDataVerbale(dataVerbale == null || dataVerbale.toLocalDateTime().getYear() == 1 ? null : dataVerbale.toLocalDateTime());
				res.setTarga(data.getString("TDT_DTDTTARG"));
			}
		}
		
		//DettaglioContabile
		res.setDatiContabili(data.getString("TDT_DTDTDCNT"));
		
		res.setImportoDettaglioContabile(null);
		res.setCodiceContabilita(null);
		res.setCapitolo(null);
		res.setAnnoCompetenza(null);
		
		return res;
	}

	public String getCodiceTransazione() {
		return codiceTransazione;
	}

	public void setCodiceTransazione(String codiceTransazione) {
		this.codiceTransazione = codiceTransazione;
	}

	public LocalDateTime getDataTransazione() {
		return dataTransazione;
	}

	public void setDataTransazione(LocalDateTime dataTransazione) {
		this.dataTransazione = dataTransazione;
	}

	public LocalDateTime getDataPagamento() {
		return dataPagamento;
	}

	public void setDataPagamento(LocalDateTime dataPagamento) {
		this.dataPagamento = dataPagamento;
	}

	public String getIdentificativoUnivocoVersamento() {
		return identificativoUnivocoVersamento;
	}

	public void setIdentificativoUnivocoVersamento(String identificativoUnivocoVersamento) {
		this.identificativoUnivocoVersamento = identificativoUnivocoVersamento;
	}

	public String getIdentificativoFlussoRendicontazioneNodo() {
		return identificativoFlussoRendicontazioneNodo;
	}

	public void setIdentificativoFlussoRendicontazioneNodo(String identificativoFlussoRendicontazioneNodo) {
		this.identificativoFlussoRendicontazioneNodo = identificativoFlussoRendicontazioneNodo;
	}

	public BigDecimal getImportoTotaleTransazione() {
		return importoTotaleTransazione;
	}

	public void setImportoTotaleTransazione(BigDecimal importoTotaleTransazione) {
		this.importoTotaleTransazione = importoTotaleTransazione;
	}

	public String getCodiceDettaglioTransazione() {
		return codiceDettaglioTransazione;
	}

	public void setCodiceDettaglioTransazione(String codiceDettaglioTransazione) {
		this.codiceDettaglioTransazione = codiceDettaglioTransazione;
	}

	public String getCodiceEnte() {
		return codiceEnte;
	}

	public void setCodiceEnte(String codiceEnte) {
		this.codiceEnte = codiceEnte;
	}

	public String getTipoUfficio() {
		return tipoUfficio;
	}

	public void setTipoUfficio(String tipoUfficio) {
		this.tipoUfficio = tipoUfficio;
	}

	public String getCodiceUfficio() {
		return codiceUfficio;
	}

	public void setCodiceUfficio(String codiceUfficio) {
		this.codiceUfficio = codiceUfficio;
	}

	public String getTipologiaServizio() {
		return tipologiaServizio;
	}

	public void setTipologiaServizio(String tipologiaServizio) {
		this.tipologiaServizio = tipologiaServizio;
	}

	public String getDescrizioneTipologiaServizio() {
		return descrizioneTipologiaServizio;
	}

	public void setDescrizioneTipologiaServizio(String descrizioneTipologiaServizio) {
		this.descrizioneTipologiaServizio = descrizioneTipologiaServizio;
	}

	public String getTassonomiaPagoPA() {
		return tassonomiaPagoPA;
	}

	public void setTassonomiaPagoPA(String tassonomiaPagoPA) {
		this.tassonomiaPagoPA = tassonomiaPagoPA;
	}

	public String getIdentificativoUnivocoRiscossione() {
		return identificativoUnivocoRiscossione;
	}

	public void setIdentificativoUnivocoRiscossione(String identificativoUnivocoRiscossione) {
		this.identificativoUnivocoRiscossione = identificativoUnivocoRiscossione;
	}

	public String getNumeroAvvisoPagoPA() {
		return numeroAvvisoPagoPA;
	}

	public void setNumeroAvvisoPagoPA(String numeroAvvisoPagoPA) {
		this.numeroAvvisoPagoPA = numeroAvvisoPagoPA;
	}

	public BigDecimal getImporto() {
		return importo;
	}

	public void setImporto(BigDecimal importo) {
		this.importo = importo;
	}

	public String getNumeroDocumento() {
		return numeroDocumento;
	}

	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}

	public String getCodiceFiscale() {
		return codiceFiscale;
	}

	public void setCodiceFiscale(String codiceFiscale) {
		this.codiceFiscale = codiceFiscale;
	}

	public String getDenominazione() {
		return denominazione;
	}

	public void setDenominazione(String denominazione) {
		this.denominazione = denominazione;
	}

	public String getIndirizzo() {
		return indirizzo;
	}

	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}

	public String getCodiceBelfioreComune() {
		return codiceBelfioreComune;
	}

	public void setCodiceBelfioreComune(String codiceBelfioreComune) {
		this.codiceBelfioreComune = codiceBelfioreComune;
	}

	public String getSiglaProvincia() {
		return siglaProvincia;
	}

	public void setSiglaProvincia(String siglaProvincia) {
		this.siglaProvincia = siglaProvincia;
	}

	public String getCAP() {
		return CAP;
	}

	public void setCAP(String cAP) {
		CAP = cAP;
	}


	public String getCausaleServizio() {
		return causaleServizio;
	}

	public void setCausaleServizio(String causaleServizio) {
		this.causaleServizio = causaleServizio;
	}

	public String getAnnoRiferimento() {
		return annoRiferimento;
	}

	public void setAnnoRiferimento(String annoRiferimento) {
		this.annoRiferimento = annoRiferimento;
	}

	public String getCespite() {
		return cespite;
	}

	public void setCespite(String cespite) {
		this.cespite = cespite;
	}

	public String getNumeroVerbale() {
		return numeroVerbale;
	}

	public void setNumeroVerbale(String numeroVerbale) {
		this.numeroVerbale = numeroVerbale;
	}

	public LocalDateTime getDataVerbale() {
		return dataVerbale;
	}

	public void setDataVerbale(LocalDateTime dataVerbale) {
		this.dataVerbale = dataVerbale;
	}

	public String getTarga() {
		return targa;
	}

	public void setTarga(String targa) {
		this.targa = targa;
	}

	public String getImportoDettaglioContabile() {
		return importoDettaglioContabile;
	}

	public void setImportoDettaglioContabile(String importoDettaglioContabile) {
		this.importoDettaglioContabile = importoDettaglioContabile;
	}

	public String getCodiceContabilita() {
		return codiceContabilita;
	}

	public void setCodiceContabilita(String codiceContabilita) {
		this.codiceContabilita = codiceContabilita;
	}

	public String getCapitolo() {
		return capitolo;
	}

	public void setCapitolo(String capitolo) {
		this.capitolo = capitolo;
	}

	public String getAnnoCompetenza() {
		return annoCompetenza;
	}

	public void setAnnoCompetenza(String annoCompetenza) {
		this.annoCompetenza = annoCompetenza;
	}

	public String getDatiContabili() {
		return datiContabili;
	}

	public void setDatiContabili(String datiContabili) {
		this.datiContabili = datiContabili;
	}
	
}