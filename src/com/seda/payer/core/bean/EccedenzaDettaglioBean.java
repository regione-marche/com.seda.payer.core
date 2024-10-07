package com.seda.payer.core.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.CallableStatement;
//import java.sql.Date;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Date;
import java.text.SimpleDateFormat;
import com.seda.payer.commons.bean.Lifecycle;
import java.text.ParseException;
import java.util.HashMap;

@SuppressWarnings("unchecked")
public class EccedenzaDettaglioBean extends Lifecycle implements Serializable {

	private static final long serialVersionUID = 1719872855447309079L;
	private String primaryKey;
	private String primaryKeyTesta;
	private String codiceSocieta;
	private String codiceUtente;
	private String chiaveAnagrafica;
	private String codiceUnivoco;
	private String numeroDocumento;
	private String codiceFiscale;
	private Double importoTotale;
	private Double importoPagato;
	private Double importoDiscaricato;
	private Double importoDaRimborsare;
	private String causale;
	private String denominazione;
	private String tipoAnagrafica;
	private String indirizzo;
	private String descrizioneComuneFiscale;
	private String provincia;
	private String cap;
	private String ibanDest;
	private String ibanOrd;
	private String strumento;
	private String modalitaNotifica;
	private String email;
	private String sms;
	private String flagEmail;
	private String flagSms;
	private String codiceCbi;
	private Date dataDisposizione;
	private String flagEsito;
	private String nomeSupportoEsitoCbi;
	private Date dataEsito;
	private String esitoRitorno;
	private String nomeFileEsito;
	private String codiceOperatore;
	private int outRows;
	
	private String tipoAnagraficaDec;
	private String modalitaNotificaDec;
	private String flagEmailDec;
	private String flagSmsDec;
	private String strumentoDec;
	private String flagEsitoDec;
	private String esitoRitornoDec;

	public String getCodiceUnivoco() {
		return codiceUnivoco;
	}

	public void setCodiceUnivoco(String codiceUnivoco) {
		this.codiceUnivoco = codiceUnivoco;
	}

	public String getDescrizioneComuneFiscale() {
		return descrizioneComuneFiscale;
	}

	public void setDescrizioneComuneFiscale(String descrizioneComuneFiscale) {
		this.descrizioneComuneFiscale = descrizioneComuneFiscale;
	}

	public int getOutRows() {
		return outRows;
	}

	public String getPrimaryKey() {
		return primaryKey;
	}

	public void setPrimaryKey(String primaryKey) {
		this.primaryKey = primaryKey;
	}

	public String getPrimaryKeyTesta() {
		return primaryKeyTesta;
	}

	public void setPrimaryKeyTesta(String primaryKeyTesta) {
		this.primaryKeyTesta = primaryKeyTesta;
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

	public String getChiaveAnagrafica() {
		return chiaveAnagrafica;
	}

	public void setChiaveAnagrafica(String chiaveAnagrafica) {
		this.chiaveAnagrafica = chiaveAnagrafica;
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

	public Double getImportoTotale() {
		return importoTotale;
	}

	public void setImportoTotale(Double importoTotale) {
		this.importoTotale = importoTotale;
	}

	public Double getImportoPagato() {
		return importoPagato;
	}

	public void setImportoPagato(Double importoPagato) {
		this.importoPagato = importoPagato;
	}

	public Double getImportoDiscaricato() {
		return importoDiscaricato;
	}

	public void setImportoDiscaricato(Double importoDiscaricato) {
		this.importoDiscaricato = importoDiscaricato;
	}

	public Double getImportoDaRimborsare() {
		return importoDaRimborsare;
	}

	public void setImportoDaRimborsare(Double importoDaRimborsare) {
		this.importoDaRimborsare = importoDaRimborsare;
	}

	public String getCausale() {
		return causale;
	}

	public void setCausale(String causale) {
		this.causale = causale;
	}

	public String getDenominazione() {
		return denominazione;
	}

	public void setDenominazione(String denominazione) {
		this.denominazione = denominazione;
	}

	public String getTipoAnagrafica() {
		return tipoAnagrafica;
	}

	public void setTipoAnagrafica(String tipoAnagrafica) {
		this.tipoAnagrafica = tipoAnagrafica;
	}

	public String getIndirizzo() {
		return indirizzo;
	}

	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}

	
	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public String getCap() {
		return cap;
	}

	public void setCap(String cap) {
		this.cap = cap;
	}

	public String getIbanDest() {
		return ibanDest;
	}

	public void setIbanDest(String ibanDest) {
		this.ibanDest = ibanDest;
	}

	public String getIbanOrd() {
		return ibanOrd;
	}

	public void setIbanOrd(String ibanOrd) {
		this.ibanOrd = ibanOrd;
	}

	public String getStrumento() {
		return strumento;
	}

	public void setStrumento(String strumento) {
		this.strumento = strumento;
	}

	public String getModalitaNotifica() {
		return modalitaNotifica;
	}

	public void setModalitaNotifica(String modalitaNotifica) {
		this.modalitaNotifica = modalitaNotifica;
	}

	
	
	public String getFlagEmail() {
		return flagEmail;
	}

	public void setFlagEmail(String flagEmail) {
		this.flagEmail = flagEmail;
	}

	public String getFlagSms() {
		return flagSms;
	}

	public void setFlagSms(String flagSms) {
		this.flagSms = flagSms;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSms() {
		return sms;
	}

	public void setSms(String sms) {
		this.sms = sms;
	}

	public String getCodiceCbi() {
		return codiceCbi;
	}

	public void setCodiceCbi(String codiceCbi) {
		this.codiceCbi = codiceCbi;
	}

	public Date getDataDisposizione() {
		return dataDisposizione;
	}

	public void setDataDisposizione(Date dataDisposizione) {
		this.dataDisposizione = dataDisposizione;
	}

	public String getFlagEsito() {
		return flagEsito;
	}

	public void setFlagEsito(String flagEsito) {
		this.flagEsito = flagEsito;
	}

	public String getNomeSupportoEsitoCbi() {
		return nomeSupportoEsitoCbi;
	}

	public void setNomeSupportoEsitoCbi(String nomeSupportoEsitoCbi) {
		this.nomeSupportoEsitoCbi = nomeSupportoEsitoCbi;
	}

	public Date getDataEsito() {
		return dataEsito;
	}

	public void setDataEsito(Date dataEsito) {
		this.dataEsito = dataEsito;
	}

	public String getEsitoRitorno() {
		return esitoRitorno;
	}

	public void setEsitoRitorno(String esitoRitorno) {
		this.esitoRitorno = esitoRitorno;
	}

	public String getNomeFileEsito() {
		return nomeFileEsito;
	}

	public void setNomeFileEsito(String nomeFileEsito) {
		this.nomeFileEsito = nomeFileEsito;
	}

	public String getCodiceOperatore() {
		return codiceOperatore;
	}

	public void setCodiceOperatore(String codiceOperatore) {
		this.codiceOperatore = codiceOperatore;
	}


	
	
	public String getTipoAnagraficaDec() {
		return tipoAnagraficaDec;
	}

	public void setTipoAnagraficaDec(String tipoAnagraficaDec) {
		this.tipoAnagraficaDec = tipoAnagraficaDec;
	}

	public String getModalitaNotificaDec() {
		return modalitaNotificaDec;
	}

	public void setModalitaNotificaDec(String modalitaNotificaDec) {
		this.modalitaNotificaDec = modalitaNotificaDec;
	}

	public String getFlagEmailDec() {
		return flagEmailDec;
	}

	public void setFlagEmailDec(String flagEmailDec) {
		this.flagEmailDec = flagEmailDec;
	}

	public String getFlagSmsDec() {
		return flagSmsDec;
	}

	public void setFlagSmsDec(String flagSmsDec) {
		this.flagSmsDec = flagSmsDec;
	}

	public String getStrumentoDec() {
		return strumentoDec;
	}

	public void setStrumentoDec(String strumentoDec) {
		this.strumentoDec = strumentoDec;
	}

	public String getFlagEsitoDec() {
		return flagEsitoDec;
	}

	public void setFlagEsitoDec(String flagEsitoDec) {
		this.flagEsitoDec = flagEsitoDec;
	}

	public String getEsitoRitornoDec() {
		return esitoRitornoDec;
	}

	public void setEsitoRitornoDec(String esitoRitornoDec) {
		this.esitoRitornoDec = esitoRitornoDec;
	}

	public void onDelete(CallableStatement arg) throws SQLException {
		
		
	}

	
	public void onLoad(CallableStatement arg) throws SQLException 
	{
		
		
	}

	public void onSave(CallableStatement arg) throws SQLException {
		arg.setString(1, this.primaryKey);
		arg.setString(2, this.primaryKeyTesta);
		arg.setString(3, this.codiceSocieta);
		arg.setString(4, this.codiceUtente);
		arg.setString(5, this.chiaveAnagrafica);
		arg.setString(6, this.numeroDocumento);
		arg.setString(7, this.codiceFiscale);
		arg.setBigDecimal(8, new BigDecimal(this.importoTotale));
		arg.setBigDecimal(9, new BigDecimal(this.importoPagato));
		arg.setBigDecimal(10, new BigDecimal(this.importoDiscaricato));
		arg.setBigDecimal(11, new BigDecimal(this.importoDaRimborsare));
		//arg.setDouble(8, this.importoTotale);
		//arg.setDouble(9, this.importoPagato);
		//arg.setDouble(10, this.importoDiscaricato);
		//arg.setDouble(11, this.importoDaRimborsare);
		arg.setString(12,this.causale);
		arg.setString(13, this.denominazione);
		arg.setString(14, this.tipoAnagrafica);
		arg.setString(15, this.indirizzo);
		arg.setString(16, this.descrizioneComuneFiscale);
		arg.setString(17, this.provincia);
		arg.setString(18, this.cap);
		arg.setString(19, this.ibanDest);
		arg.setString(20, this.ibanOrd);
		arg.setString(21, this.strumento);
		arg.setString(22, this.modalitaNotifica);
		arg.setString(23, this.email);
		arg.setString(24, this.sms);
		arg.setString(25, this.flagEmail);
		arg.setString(26, this.flagSms);
		arg.setString(27, this.codiceCbi);
		arg.setDate(28, new java.sql.Date(this.dataDisposizione.getTime()));
		arg.setString(29, this.flagEsito);
		arg.setString(30, this.nomeSupportoEsitoCbi);
		arg.setDate(31, new java.sql.Date(this.dataEsito.getTime()));
		arg.setString(32, this.esitoRitorno);
		arg.setString(33, this.nomeFileEsito);
		arg.setString(34,this.codiceOperatore);
		arg.registerOutParameter(35, Types.INTEGER);
		
	}
	

	
	public void onUpdate(CallableStatement arg) throws SQLException {
		
		
	}
	
	public EccedenzaDettaglioBean()
	{
		super();
	}
	public EccedenzaDettaglioBean(ResultSet data)  throws SQLException
	{
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		
		if (data == null)
    		return;

		ResultSetMetaData meta = data.getMetaData();
		HashMap mappa = new HashMap();
		for (int i=1;i<meta.getColumnCount()+1;i++)
		{
		    mappa.put(meta.getColumnName(i), meta.getColumnName(i));
		}
		
		primaryKey = mappa.containsKey("ECC_KECCKECC") ? data.getString("ECC_KECCKECC") : "";
		primaryKeyTesta = mappa.containsKey("ECC_KECTKECT") ? data.getString("ECC_KECTKECT") : "";
		codiceSocieta = mappa.containsKey("ECC_CSOCCSOC") ? data.getString("ECC_CSOCCSOC") : "";
		codiceUtente = mappa.containsKey("ECC_CUTECUTE") ? data.getString("ECC_CUTECUTE") : "";
		chiaveAnagrafica = mappa.containsKey("ECC_KANEKANE") ? data.getString("ECC_KANEKANE") : "";
		numeroDocumento = mappa.containsKey("ECC_CECCNDOC") ? data.getString("ECC_CECCNDOC") : "";
		codiceFiscale = mappa.containsKey("ECC_CECCCFIS") ? data.getString("ECC_CECCCFIS") : "";
		importoDaRimborsare = mappa.containsKey("ECC_NECCRIMB") ? data.getDouble("ECC_NECCRIMB") : 0.0;
		importoTotale = mappa.containsKey("ECC_NECCIMPO") ? data.getDouble("ECC_NECCIMPO") : 0.0;
		importoPagato = mappa.containsKey("ECC_NECCIMPP") ? data.getDouble("ECC_NECCIMPP") : 0.0;
		importoDiscaricato = mappa.containsKey("ECC_NECCDISC") ? data.getDouble("ECC_NECCDISC") : 0.0;
		causale = mappa.containsKey("ECC_CECCCCAU") ? data.getString("ECC_CECCCCAU") : "";
		denominazione = mappa.containsKey("ECC_CECCDENO") ? data.getString("ECC_CECCDENO") : "";
		tipoAnagrafica = mappa.containsKey("ECC_FECCFANA") ? data.getString("ECC_FECCFANA") : "";
		tipoAnagraficaDec = mappa.containsKey("ECC_FECCFANA_D") ? data.getString("ECC_FECCFANA_D") : "";
		indirizzo = mappa.containsKey("ECC_CECCCIND") ? data.getString("ECC_CECCCIND") : "";
		descrizioneComuneFiscale = mappa.containsKey("ECC_DECCDCOM") ? data.getString("ECC_DECCDCOM") : "";
		provincia = mappa.containsKey("ECC_CECCPROV") ? data.getString("ECC_CECCPROV") : "";
		cap = mappa.containsKey("ECC_CECCCCAP") ? data.getString("ECC_CECCCCAP") : "";
		ibanDest = mappa.containsKey("ECC_CECCIBAN_DEST") ? data.getString("ECC_CECCIBAN_DEST") : "";
		ibanOrd = mappa.containsKey("ECC_CECCIBAN_ORDI") ? data.getString("ECC_CECCIBAN_ORDI") : "";
		strumento = mappa.containsKey("ECC_FECCFTIP") ? data.getString("ECC_FECCFTIP") : "";
		strumentoDec = mappa.containsKey("ECC_FECCFTIP_D") ? data.getString("ECC_FECCFTIP_D") : "";
		modalitaNotifica = mappa.containsKey("ECC_FECCFNOT") ? data.getString("ECC_FECCFNOT") : "";
		modalitaNotificaDec = mappa.containsKey("ECC_FECCFNOT_D") ? data.getString("ECC_FECCFNOT_D") : "";
		email = mappa.containsKey("ECC_CECCMAIL") ?  data.getString("ECC_CECCMAIL") : "";
		sms = mappa.containsKey("ECC_NECCSSMS") ? data.getString("ECC_NECCSSMS") : "";
		flagEmail = mappa.containsKey("ECC_FECCMAIL") ? data.getString("ECC_FECCMAIL") : "";
		flagEmailDec = mappa.containsKey("ECC_FECCMAIL_D") ? data.getString("ECC_FECCMAIL_D") : "";
		flagSms = mappa.containsKey("ECC_FECCSSMS") ? data.getString("ECC_FECCSSMS") : "";
		flagSmsDec = mappa.containsKey("ECC_FECCSSMS_D") ? data.getString("ECC_FECCSSMS_D") : "";
		codiceUnivoco = mappa.containsKey("ECC_CECCCCBI") ? data.getString("ECC_CECCCCBI") : "";
		flagEsito = mappa.containsKey("ECC_FECCFESI") ? data.getString("ECC_FECCFESI") : "";
		flagEsitoDec = mappa.containsKey("ECC_FECCFESI_D") ? data.getString("ECC_FECCFESI_D") : "";		
		nomeSupportoEsitoCbi = mappa.containsKey("ECC_CECCCBIE") ? data.getString("ECC_CECCCBIE") : "";
		esitoRitorno = mappa.containsKey("ECC_FECCESIE") ? data.getString("ECC_FECCESIE") : "";
		esitoRitornoDec = mappa.containsKey("ECC_FECCESIE_D") ? data.getString("ECC_FECCESIE_D") : "";
		nomeFileEsito = mappa.containsKey("ECC_CCECNOME_ESI") ? data.getString("ECC_CCECNOME_ESI") : "";
		
		try
		{
			dataDisposizione = mappa.containsKey("ECC_GECCGRIM") ? data.getDate("ECC_GECCGRIM") : formatter.parse("01/01/1000");
		}
		catch(ParseException pe)
		{}
		
		try
		{
			dataEsito = mappa.containsKey("ECC_GECCGESI") ? data.getDate("ECC_GECCGESI") : formatter.parse("01/01/1000");
		}
		catch(ParseException pe)
		{}

	}
}
