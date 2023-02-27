package com.seda.payer.core.bean;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;


public class ConfigurazionePosteBlackBoxPos extends ConfigurazioneBlackBoxPos {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5340238956245705982L;

	private int chiaveTestata;
	private boolean flagPoste;
	private boolean flagInviato;
	private String codiceServizio;
	private int chiaveTestataRT;
	
	public enum TIPO_OPERAZIONE_ENUM {
		
		INS, //inserimento
		VAR, //variazione
		CAN, //cancellazione
		PAG; //pagata fuori nodo
		public static TIPO_OPERAZIONE_ENUM recuperaTipoDaStringa(String stringa) {
			for (TIPO_OPERAZIONE_ENUM v : values()) {
				if(v.name().equalsIgnoreCase(stringa)) return v;
			}
			return null;
		}
	}
	
	private TIPO_OPERAZIONE_ENUM tipoOperazione;
	
	public ConfigurazionePosteBlackBoxPos() {
		super();
	}

	public ConfigurazionePosteBlackBoxPos(ResultSet data) throws SQLException {
		super(data);
		
		if (data == null)
    		return;
		
		setCodiceUtente(data.getString("DOC_CDOCCUTE"));
		setCodiceSocieta(data.getString("DOC_CDOCCSOC"));
		setChiaveEnte(data.getString("DOC_CDOCKANE"));
		
		setChiaveTestata(data.getInt("DOC_KPSTCDID"));
		setFlagPoste(data.getString("DOC_FDOCFPST") != null && data.getString("DOC_FDOCFPST").equals("Y") ? true : false);
		setFlagInviato(data.getString("DOC_FDOCFINV")!= null && data.getString("DOC_FDOCFINV").equals("Y") ? true : false);
		setCodiceServizio(data.getString("DOC_CDOCCDSR"));
		setChiaveTestataRT(data.getInt("DOC_KPSTIDRT"));
	}

	public ConfigurazionePosteBlackBoxPos(String codiceIdentificativoDominio, String codiceEnte, String numeroAvviso,
			String codiceIdentificativoFlusso, Calendar dataCreazione, String tipoRecord,
			String codiceIdentificativoDocumento, String numeroRata, Calendar dataScadenza, String codiceFiscale,
			Double importo, String denominazioneDebitore, String indirizzoContribuente, String localitaContribuente,
			String provinciaContribuente, String flagAnnullamento, Calendar dataAggiornamentoRecord,
			String codiceIbanAccredito, String codiceIuv, String flagPagato, Double importoPagato,
			String codiceTipologiaServizio, Calendar dataPagamento, String codiceIBAN2, String causaleServizio,
			String cespite, String annoRif, String cittaCAP, String codiceUtente, String codiceSocieta,
			String chiaveEnte, String tassonomia/*, String codiceSia, String progressivoRecord*/, int chiaveTestata, boolean flagPoste, boolean flagInviato,
			String codiceServizio
			) {
		super(codiceIdentificativoDominio, codiceEnte, numeroAvviso, codiceIdentificativoFlusso, dataCreazione, tipoRecord,
				codiceIdentificativoDocumento, numeroRata, dataScadenza, codiceFiscale, importo, denominazioneDebitore,
				indirizzoContribuente, localitaContribuente, provinciaContribuente, flagAnnullamento, dataAggiornamentoRecord,
				codiceIbanAccredito, codiceIuv, flagPagato, importoPagato, codiceTipologiaServizio, dataPagamento, codiceIBAN2,
				causaleServizio, cespite, annoRif, cittaCAP, codiceUtente, codiceSocieta, chiaveEnte, tassonomia);
		/*
		this.codiceSia = codiceSia;
		this.progressivoRecord = progressivoRecord;
		*/
		this.chiaveTestata = chiaveTestata;
		this.flagPoste = flagPoste;
		this.flagInviato = flagInviato;
		this.codiceServizio = codiceServizio;
	}

	public int getChiaveTestata() {
		return chiaveTestata;
	}

	public void setChiaveTestata(int chiaveTestata) {
		this.chiaveTestata = chiaveTestata;
	}

	public boolean getFlagPoste() {
		return flagPoste;
	}

	public void setFlagPoste(boolean flagPoste) {
		this.flagPoste = flagPoste;
	}

	public boolean getFlagInviato() {
		return flagInviato;
	}

	public void setFlagInviato(boolean flagInviato) {
		this.flagInviato = flagInviato;
	}
	
	public void setTipoOperazione(String TipoOperazione) {
		this.tipoOperazione = TIPO_OPERAZIONE_ENUM.recuperaTipoDaStringa(TipoOperazione);
	}
	
	public TIPO_OPERAZIONE_ENUM getTipoOperazione() {
		return this.tipoOperazione;
	}
	
	public String getCodiceServizio() {
		return codiceServizio;
	}

	public void setCodiceServizio(String codiceServizio) {
		this.codiceServizio = codiceServizio;
	}

	public int getChiaveTestataRT() {
		return chiaveTestataRT;
	}

	public void setChiaveTestataRT(int chiaveTestataRT) {
		this.chiaveTestataRT = chiaveTestataRT;
	}
	
}
