package com.seda.payer.commons.inviaAvvisiForGeos;

import java.util.ArrayList;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * File da mandare al servizio GEOS tramite FTP. Estratti una lista di
 * {@link Flusso} dal DB, si suddividono le pendenze in {@link GruppoAvvisi}...
 * e si genera un file FTP per ciascuno. <br/>
 * 
 */
public class File512 {

	public File512() {
		this(null, null, null, null, null, null);
	}

	public File512(String tt, GruppoAvvisi ga, String codiceUtente, String ente, String idFlusso,
			String descrizioneEnte) {
		this.tipoTemplate = tt;
		this.gruppoAvvisi = ga;
		this.cutecute = codiceUtente;
		this.ente = ente;
		this.idFlusso = idFlusso;
		this.descrizioneEnte = descrizioneEnte;
	}

	/**
	 * In base ad una porzione dell'IBAN contenente "07606" si stabilisce se la
	 * pendenza è ibanpostale(POSTE/STANDARD). Poi su file di configurazione con
	 * chiave "cutecute.ente.servizio.POSTE" e "cutecute.ente.servizio.STANDARD" si
	 * ottiene il TipoTemplate. Ogni servizio può così decidere se usare uno solo o
	 * entrambi i template. Il tipo template attualmente può valere "POSTE_" oppure
	 * "STANDARD_"
	 */
	public String tipoTemplate;

	/** Esempio utente="val d'aosta" */
	public String cutecute;

	/** ente che ha emesso i documenti */
	public String ente;

	/** */
	public String idFlusso;
	public String cBill;

	public String descrizioneEnte;

	/** Raggruppamento delle pendenze, elencati in ordine di priorità. */
	public enum GruppoAvvisi {
		FATTURAZIONE_ELETTRONICA, PEC, MAIL, DOC_ITALIA, DOC_EE
	};

	public GruppoAvvisi gruppoAvvisi;

	/**
	 * istante di esecuzione della procedura che ha generato i files.
	 * "AAAAMMGGHHmmSS"
	 */
	public String dataOra;
	public Date dataElaborazione;

	/**
	 * potenzialmente ho 10 files diversi numerati progressivamente, per
	 * {@link TipoTemplate} e {@link GruppoAvvisi}.
	 */
	public int progressivo;

	public ArrayList<Documento> listaDocumenti = new ArrayList<Documento>();

	public ArrayList<String> fileContent = new ArrayList<String>();

	/**
	 * aggiunge una linea/record, incrementa il contatore righe. Continuo a lasciare
	 * linee in stringhe separate, senza aggiunge il carattere terminatore di
	 * riga... che sarà specificato in fase di scrittura su file.
	 */
	public void addRecordTxt(String r) {
		fileContent.add(r);
		numRecord++;
	}

	public int numRecord = 0;

	public String societa;

	/** codice autorizzazione CCP */
	public String codiceAutorizzazione;

	public ArrayList<String> getFileContent() {
		return fileContent;
	}

	/** campo calcolato a partire dagli altri campi */
	@JsonIgnore
	public String getFileName() {
		return String.format("%sPPA_%s%s%s%s_%03d.txt", tipoTemplate, cutecute, ente, idFlusso, dataOra, progressivo);
	}
}
