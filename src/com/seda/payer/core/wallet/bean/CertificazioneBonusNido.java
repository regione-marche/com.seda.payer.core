package com.seda.payer.core.wallet.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Blob;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;




import com.seda.data.dao.ModelAttributes;

public class CertificazioneBonusNido extends ModelAttributes implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// Tabella PYCNBTB

	private String numeroProtocollo;		//CBN_NCBNNPRO
	private String idWallet;				//CBN_KBRSKBRS
	private String anno;					//CBN_CCBNANNO
	private String mese;					//CBN_CCBNMESE
	private Integer progressivo;			//CBN_NCBNPROG
	private String chiavePresenza;			//CBN_KREPKREP	
	private String data;					//CBN_GCBNDATA
	private String denomGenitore;			//CBN_DCBNDGEN
	private String codFiscGenitore;			//CBN_CFISCGEN
	private String annoScolastico;			//CBN_NCBNASCO
	private String denomFiglio;				//CBN_DCBNDFIG
	private String codFiscFiglio;			//CBN_CFISCFIG
	private String codiceScuola;			//CBN_CSCUSCOD
	private String denomScuola;				//CBN_DSCUDENO
	private String indirizzoScuola;			//CBN_DSCUINDI
	private BigDecimal importo;				//CBN_ICBNIMPO
	private String denomDirigente;			//CBN_DCBNDDIR
	private Boolean flagAttivo;				//CBN_FCBNFATT
	private String pathFileCertificazione;	//CBN_DCBNFPTH
	private byte[] fileCertificazione;		//CBN_CCBNFCBN		


	public CertificazioneBonusNido(){

	}

	public CertificazioneBonusNido(ResultSet data) throws SQLException, ParseException {
		if (data == null)
			return;
		
		setNumeroProtocollo(data.getString(1));
		setIdWallet(data.getString(2));
		setAnno(data.getString(3));
		setMese(data.getString(4));
		setProgressivo(data.getInt(5));
		setChiavePresenza(data.getString(6));
		setData(data.getString(7));
//		String dataPag = data.getString(4);
//		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
//		Calendar cal  = Calendar.getInstance();
//		cal.setTime(df.parse(dataPag));
//		setDataPagamento(cal);
		setDenomGenitore(data.getString(8));
		setCodFiscGenitore(data.getString(9));
		setAnnoScolastico(data.getString(10));
		setDenomFiglio(data.getString(11));
		setCodFiscFiglio(data.getString(12));
		setCodiceScuola(data.getString(13));
		setDenomScuola(data.getString(14));
		setIndirizzoScuola(data.getString(15));
		setImporto(data.getBigDecimal(16));
		setDenomDirigente(data.getString(17));
		setFlagAttivo(((String)data.getString(18)).equalsIgnoreCase("Y")?true:false);
		setPathFileCertificazione(data.getString(19));
		
		Blob datiSel = data.getBlob(20);
		byte[] dati = null;
		if (datiSel != null) {
			if(datiSel.length()>0){
				dati = datiSel.getBytes(1, (int) datiSel.length());
			}
		}
		setFileCertificazione(dati);
	}

	public CertificazioneBonusNido(String numeroProtocollo, String idWallet,
			String anno, String mese, Integer progressivo,
			String chiavePresenza, String data, String denomGenitore,
			String codFiscGenitore, String annoScolastico, String denomFiglio,
			String codFiscFiglio, String codiceScuola, String denomScuola,
			String indirizzoScuola, BigDecimal importo, String denomDirigente,
			Boolean flagAttivo, String pathFileCertificazione,
			byte[] fileCertificazione) {
		super();
		this.numeroProtocollo = numeroProtocollo;
		this.idWallet = idWallet;
		this.anno = anno;
		this.mese = mese;
		this.progressivo = progressivo;
		this.chiavePresenza = chiavePresenza;
		this.data = data;
		this.denomGenitore = denomGenitore;
		this.codFiscGenitore = codFiscGenitore;
		this.annoScolastico = annoScolastico;
		this.denomFiglio = denomFiglio;
		this.codFiscFiglio = codFiscFiglio;
		this.codiceScuola = codiceScuola;
		this.denomScuola = denomScuola;
		this.indirizzoScuola = indirizzoScuola;
		this.importo = importo;
		this.denomDirigente = denomDirigente;
		this.flagAttivo = flagAttivo;
		this.pathFileCertificazione = pathFileCertificazione;
		this.fileCertificazione = fileCertificazione;
	}

	public String getNumeroProtocollo() {
		return numeroProtocollo;
	}

	public void setNumeroProtocollo(String numeroProtocollo) {
		this.numeroProtocollo = numeroProtocollo;
	}

	public String getIdWallet() {
		return idWallet;
	}

	public void setIdWallet(String idWallet) {
		this.idWallet = idWallet;
	}

	public String getAnno() {
		return anno;
	}

	public void setAnno(String anno) {
		this.anno = anno;
	}

	public String getMese() {
		return mese;
	}

	public void setMese(String mese) {
		this.mese = mese;
	}

	public Integer getProgressivo() {
		return progressivo;
	}

	public void setProgressivo(Integer progressivo) {
		this.progressivo = progressivo;
	}
	
	public String getChiavePresenza() {
		return chiavePresenza;
	}

	public void setChiavePresenza(String chiavePresenza) {
		this.chiavePresenza = chiavePresenza;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getDenomGenitore() {
		return denomGenitore;
	}

	public void setDenomGenitore(String denomGenitore) {
		this.denomGenitore = denomGenitore;
	}

	public String getCodFiscGenitore() {
		return codFiscGenitore;
	}

	public void setCodFiscGenitore(String codFiscGenitore) {
		this.codFiscGenitore = codFiscGenitore;
	}

	public String getAnnoScolastico() {
		return annoScolastico;
	}

	public void setAnnoScolastico(String annoScolastico) {
		this.annoScolastico = annoScolastico;
	}

	public String getDenomFiglio() {
		return denomFiglio;
	}

	public void setDenomFiglio(String denomFiglio) {
		this.denomFiglio = denomFiglio;
	}

	public String getCodFiscFiglio() {
		return codFiscFiglio;
	}

	public void setCodFiscFiglio(String codFiscFiglio) {
		this.codFiscFiglio = codFiscFiglio;
	}

	public String getCodiceScuola() {
		return codiceScuola;
	}

	public void setCodiceScuola(String codiceScuola) {
		this.codiceScuola = codiceScuola;
	}

	public String getDenomScuola() {
		return denomScuola;
	}

	public void setDenomScuola(String denomScuola) {
		this.denomScuola = denomScuola;
	}

	public String getIndirizzoScuola() {
		return indirizzoScuola;
	}

	public void setIndirizzoScuola(String indirizzoScuola) {
		this.indirizzoScuola = indirizzoScuola;
	}

	public BigDecimal getImporto() {
		return importo;
	}

	public void setImporto(BigDecimal importo) {
		this.importo = importo;
	}

	public String getDenomDirigente() {
		return denomDirigente;
	}

	public void setDenomDirigente(String denomDirigente) {
		this.denomDirigente = denomDirigente;
	}

	public Boolean getFlagAttivo() {
		return flagAttivo;
	}

	public void setFlagAttivo(Boolean flagAttivo) {
		this.flagAttivo = flagAttivo;
	}
	
	public String getPathFileCertificazione() {
		return pathFileCertificazione;
	}

	public void setPathFileCertificazione(String pathFileCertificazione) {
		this.pathFileCertificazione = pathFileCertificazione;
	}

	public byte[] getFileCertificazione() {
		return fileCertificazione;
	}

	public void setFileCertificazione(byte[] fileCertificazione) {
		this.fileCertificazione = fileCertificazione;
	}

	@Override
	public String toString() {
		return "CertificazioneBonusNido [anno=" + anno + ", annoScolastico="
				+ annoScolastico + ", chiavePresenza=" + chiavePresenza
				+ ", codFiscFiglio=" + codFiscFiglio + ", codFiscGenitore="
				+ codFiscGenitore + ", codiceScuola=" + codiceScuola
				+ ", data=" + data + ", denomDirigente=" + denomDirigente
				+ ", denomFiglio=" + denomFiglio + ", denomGenitore="
				+ denomGenitore + ", denomScuola=" + denomScuola
				+ ", fileCertificazione=" + Arrays.toString(fileCertificazione)
				+ ", flagAttivo=" + flagAttivo + ", idWallet=" + idWallet
				+ ", importo=" + importo + ", indirizzoScuola="
				+ indirizzoScuola + ", mese=" + mese + ", numeroProtocollo="
				+ numeroProtocollo + ", pathFileCertificazione="
				+ pathFileCertificazione + ", progressivo=" + progressivo + "]";
	}

}

